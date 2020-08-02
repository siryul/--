import java.util.Scanner;

/**
 * @author siryul
 * date: 2020/08/02
 */
public class QueueTest {
    public static void main(String[] args) {
        // 循环队列测试
        Queue queue = new Queue();
        Scanner scanner = new Scanner(System.in);
        System.out.println("数组实现的队列：");
        System.out.print("请输入队列的最大容量 capacity (capacity > 0)：");
        int capacity = scanner.nextInt();
        while (capacity <= 0) {
            System.out.print("输入数值错误！请重新输入：");
            capacity = scanner.nextInt();
        }
        queue.init(capacity);
        // 入队顺序【1， 2， 3， 4， 5】
        for (int i = 1; i <= 5; i++) {
            queue.enQueue(i);
        }
        System.out.println("入队顺序【1， 2， 3， 4， 5】");
        System.out.print("出队顺序：");
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + "  ");
        }
        System.out.println("\n--------------------------------------");


        // 链式队列测试
        QueueList queueList = new QueueList();
        System.out.println("链表(带头结点)实现的队列：");
        System.out.println("入队顺序【1， 2， 3， 4， 5， 6， 7】");
        for (int i = 1; i <= 7; i++) {
            queueList.enQueue(i);
        }
        System.out.print("出队顺序：");
        while (!queueList.isEmpty()) {
            System.out.print(queueList.deQueue() + "  ");
        }
    }
}


/**
 * 队列（循环）的数组实现，其中数组的最后一个元素留空方便满队列与空队列的区分。
 * 单纯的数组来实现队列，判断队列入队时溢出可能不是真正的“溢出”，
 * 利用求余运算可以尽可能的利用数组空间，此时的溢出可以看作为真正的溢出。
 */
class Queue {
    public int[] data;
    public int capacity;
    public int front;
    public int rear;

    Queue () {
        front = rear = 0;
    }

    public boolean init (int capacity) {
        if(capacity < 1) {
            return false;
        }
        data = new int[capacity];
        this.capacity = capacity;
        return true;
    }

    public boolean isEmpty () {
        return front == rear;
    }

    /**
     * 入队
      */
    public boolean enQueue(int element) {
        // 满队列
        if((rear + 1) % capacity == front) {
            System.out.println("队列已满！无法入队！");
            return false;
        }
        data[rear++] = element;
        return true;
    }

    /**
     * 出队
     */
    public int deQueue() {
        if (isEmpty()) {
            return -1;
        }else {
            return data[front++];
        }
    }

    public int length() {
        return (capacity + rear - front)%capacity;
    }
}


/**
 * 链式队列
 */
class QueueNode {
    private int data;
    public QueueNode next = null;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

class QueueList {
    public QueueNode front;
    public QueueNode rear;
    private int length;

    /**
     * 对象建立的时候顺带初始化，带头结点(协调队列是否为空的出入队操作)。
     */
    QueueList (){
        front = rear = new QueueNode();
        length = 0;
    }

    public boolean isEmpty() {
        // 除了判断首尾节点是否相同之外，还可以判断 length == 0.
        return front == rear;
    }

    public void enQueue(int element) {
        QueueNode p = new QueueNode();
        p.setData(element);
        rear.next = p;
        rear = p;
        length++;
    }

    public int deQueue() {
        if (isEmpty()) {
            return -1;
        }
        front = front.next;
        int element = front.getData();
        length--;
        return element;
    }
}
