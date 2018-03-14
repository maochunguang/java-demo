package cn.mcg.thread;

/**
 * @author maocg
 * 单线程电梯（1台电梯运行,10层）模拟：
 * Stack 表示电梯所能装载的人数
 * 下面两个链表表示每层楼对应的人 0未启用
 * LinkList[] up = new LinkList[11];	want=1 向上去的人所在队列
 * LinkList[] down = new LinkList[11];	want=-1 向下去的人所在队列
 * Customer 乘客：
 * at:所在层数
 * to:目的层数
 * want:1表示向上去，-1表示向下去
 * key：乘客编号
 */
public class TestLift {
    public static void main(String[] args) {
        LinkList[] up = new LinkList[11];
        LinkList[] down = new LinkList[11];
        for (int k = 0; k < 11; k++) {//对每个链表进行初始化
            up[k] = new LinkList();
            down[k] = new LinkList();
        }

        Stack st = new Stack();

        Work work = new Work(st, up, down, 0);
        Thread t1 = new Thread(work);
        t1.start();

        Customer[] customers = new Customer[20];
        for (int i = 0; i < 20; i++) {
            while (true) {
                int a = (int) (Math.random() * 10 + 1);
                int t = (int) (Math.random() * 10 + 1);
                if (a < t && t != 0 && a != 0) {
                    customers[i] = new Customer(a, t, i, 1);
                    up[a].add(customers[i]);
                    break;
                } else if (a > t && t != 0 && a != 0) {
                    customers[i] = new Customer(a, t, i, -1);
                    down[a].add(customers[i]);
                    break;
                }

            }
            work.sleep();
        }

    }
}

class Work implements Runnable {
    int work;
    int stay;
    Stack dt;
    boolean asd = true;
    LinkList[] up;
    LinkList[] down;

    public Work(Stack s, LinkList[] u, LinkList[] d, int w) {
        dt = s;
        up = u;
        down = d;
        work = w;
        stay = 1;
    }

    @Override
    public void run() {
        while (true) {
            if (work == 0) {//无人状态
                if (asd == false) {
                    System.out.println("电梯 暂停在" + stay + "楼");
                    sleep();
                } else {
                    if (up != null) {
                        work = 1;
                    } else if (down != null) {
                        work = -1;
                    } else {
                        sleep();
                    }
                }
            } else if (work == 1) {//向上工作状态
                sleep();
                System.out.println("电梯到达" + stay + "楼--[上]");
                up_out(stay);//每到达一层检查是否有人--出
                if (up[stay].head != null) {
                    up_in(stay);//每到达一层检查是否有人--上
                }
                //当电梯内无人 且没人使用电梯时暂停，继续等待
                if (dt.isEmpty() && isK(up) && isK(down)) {
                    asd = false;
                    work = 0;
                    stay--;
                }
                //将上楼的人运输完之后，检查当前楼层上方是否有人下，若有人下 则去接人，若无人下则电梯下楼
                if (dt.isEmpty() && downyouren(stay)) {
                    System.out.println("电梯 暂停在" + stay + "------->楼");
                    down_in(stay);
                    down_out(stay);
                    //up_in(stay);
                    //up_out(stay);
                }
                stay++;
                if (stay > 10) {
                    work = -1;
                    stay = 10;
                }
            } else if (work == -1) {
                //向下工作状态
                sleep();
                System.out.println("电梯到达" + stay + "楼--[下]");
                down_out(stay);
                if (down[stay].head != null) {
                    down_in(stay);
                }
                if (dt.isEmpty() && isK(up) && isK(down)) {
                    asd = false;
                    work = 0;
                    stay++;
                }
                if (dt.isEmpty() && upyouren(stay)) {
                    System.out.println("电梯 暂停在" + stay + "------->楼");
                    up_in(stay);
                    up_out(stay);
                    //down_in(stay);
                    //down_out(stay);
                }
                stay--;
                if (stay < 1) {
                    work = 1;
                    stay = 1;
                }

            }

        }
    }

    public boolean upyouren(int s) {
        for (int i = s; i <= 10; i++) {
            if (up[i].head != null) {
                return true;
            }
            if (down[i].head != null) {
                return true;
            }
        }
        return false;
    }

    public boolean downyouren(int s) {
        for (int i = s; i >= 1; i--) {
            if (down[i].head != null) {
                return true;
            }
            if (up[i].head != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isK(LinkList[] arr) {
        for (int i = 1; i <= 10; i++) {
            if (arr[i].head != null) {
                return false;
            }
        }
        return true;
    }

    public void up_in(int i) {
        if (up[i].head != null) {
            Node temp = up[i].head;
            while (temp != null) {
                if (dt.top <= 10) {
                    System.out.println(temp.customer.key + "号-at-" + temp.customer.at + "-to-" + temp.customer.to + "楼---【上楼--进电梯】");
                    dt.push(temp.customer);
                    up[i].del(temp.customer);
                } else {
                    System.out.println("电梯已上满！");
                }
                temp = temp.n;
            }
        } else {

        }
    }

    public void up_out(int i) {
        if (dt.top > 0) {
            Node temp = dt.ll.head;
            while (temp != null) {
                if (temp.customer.to == i) {
                    System.out.println(temp.customer.key + "号-at-" + temp.customer.at + "-to-" + temp.customer.to + "楼---【已下楼--出电梯】");
                    dt.pop(temp.customer);
                }
                temp = temp.n;
            }
        }
    }

    public void down_in(int i) {
        if (down[i].head != null) {
            Node temp = down[i].head;
            while (temp != null) {
                if (dt.top <= 10) {
                    System.out.println(temp.customer.key + "号-at-" + temp.customer.at + "-to-" + temp.customer.to + "楼---【下楼--进电梯】");
                    dt.push(temp.customer);
                    down[i].del(temp.customer);
                } else {
                    System.out.println("电梯已上满！");
                }
                temp = temp.n;
            }
        }
    }

    public void down_out(int i) {
        if (dt.top > 0) {
            Node temp = dt.ll.head;
            while (temp != null) {
                if (temp.customer.to == i) {
                    System.out.println(temp.customer.key + "号-at-" + temp.customer.at + "-to-" + temp.customer.to + "楼---【已下楼--出电梯】");
                    dt.pop(temp.customer);
                }
                temp = temp.n;
            }
        }
    }

    public void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Customer {
    int at;
    int to;
    int key;
    int want;

    public Customer(int a, int t, int k, int w) {
        at = a;
        to = t;
        key = k;
        want = w;
    }
}

class Node {
    Node f;
    Node n;
    Customer customer;

    public Node(Customer c) {
        f = n = null;
        customer = c;
    }
}

class Stack {
    int top;
    LinkList ll = new LinkList();

    public Stack() {
        top = 0;
    }

    public boolean isEmpty() {
        if (top == 0) {
            return true;
        }
        return false;
    }

    public void push(Customer c) {
        if (top <= 10) {
            top++;
            ll.add(c);
        } else {
            System.out.println("电梯---已满");
        }
    }

    public void pop(Customer c) {
        if (top > 0) {
            ll.del(c);
            top--;
        } else {
            System.out.println("电梯---无人");
        }
    }
}

class LinkList {
    Node head;
    Node tail;

    public LinkList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public void add(Customer c) {
        Node node = new Node(c);
        if (head != null) {
            tail.n = node;
            node.f = tail;
            tail = node;
            tail.n = null;
        } else {
            head = tail = node;
            head.f = tail.n = null;
        }
    }

    public void del(Customer c) {
        Node node = find(c);
        if (node != null) {
            if (node == head) {//.f==null
                if (head.n == null) {
                    head = tail = null;
                } else {
                    head = head.n;
                    head.f = null;
                }
            } else if (node == tail) {
                if (tail.f == null) {
                    head = tail = null;
                } else {
                    tail = tail.f;
                    tail.n = null;
                }
            } else {
                node.f.n = node.n;
                node.n.f = node.f;
            }
        }
    }

    public Node find(Customer c) {
        if (head != null) {
            Node node = head;
            while (node != null) {
                if (node.customer.equals(c)) {
                    return node;
                }
                node = node.n;
            }
        }
        return null;
    }

}















