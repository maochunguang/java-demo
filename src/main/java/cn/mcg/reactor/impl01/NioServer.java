package cn.mcg.reactor.impl01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author maocg
 * Date：2018/3/31
 * Description：TODO
 */
public class NioServer {
    public static void main(String[] args) {
        new Thread(new Reactor()).start();
    }

    private static final class Reactor implements Runnable {
        private static final byte[] b = "hello，服务器收到你得消息！".getBytes();

        @Override
        public void run() {
            System.out.println("server is started,waitting for client");
            ServerSocketChannel ssc = null;
            Selector selector = null;
            try {
                ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress("127.0.0.1", 9080));

                selector = Selector.open();
                ssc.register(selector, SelectionKey.OP_ACCEPT);

                Set<SelectionKey> ops = null;
                while (true) {
                    try {
                        selector.select();
                        ops = selector.selectedKeys();
                    } catch (Throwable e) {
                        break;
                    }
                    // handle events
                    for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext(); ) {
                        SelectionKey key = it.next();
                        it.remove();
                        try {
                            if (key.isAcceptable()) {
                                ServerSocketChannel serverSc = (ServerSocketChannel) key.channel();
                                SocketChannel clientChannel = serverSc.accept();
                                clientChannel.configureBlocking(false);
                                clientChannel.register(selector, SelectionKey.OP_READ);
                                System.out.println("receive request from client!");
                            } else if (key.isWritable()) {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer buf = (ByteBuffer) key.attachment();
                                buf.flip();
                                clientChannel.write(buf);
                                System.out.println("server send data to client!");
                                clientChannel.register(selector, SelectionKey.OP_READ);

                            } else if (key.isReadable()) {
                                System.out.println("accept request from client ");
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer buf = ByteBuffer.allocate(1024);
                                System.out.println("size ==" + buf.capacity());
                                clientChannel.read(buf);
                                buf.put(b);
                                clientChannel.register(selector, SelectionKey.OP_WRITE, buf);
                            }

                        } catch (Throwable e) {
                            System.out.println("client disconnect by himself");
                            ssc.register(selector, SelectionKey.OP_ACCEPT);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}