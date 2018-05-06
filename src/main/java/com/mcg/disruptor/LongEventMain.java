package com.mcg.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {

    private static final Translator TRANSLATOR = new Translator();

    public static void main(String[] args) throws Exception {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long l = 0; l < 10000; l++) {
            ByteBuffer bb = ByteBuffer.allocate(8);
            bb.putLong(0, l);
            ringBuffer.publishEvent(TRANSLATOR, bb);
//            Thread.sleep(100);
        }
//        executor.
        disruptor.shutdown();
    }
}

class Translator implements EventTranslatorOneArg<LongEvent, ByteBuffer> {
    @Override
    public void translateTo(LongEvent event, long sequence, ByteBuffer data) {
        event.set(data.getLong(0));
    }
}
