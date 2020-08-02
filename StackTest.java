import java.util.Scanner;

/**
 * 栈的数组实现
 */
class Stack {
    private int[] dates;
    // top 节点代表实际的栈顶元素，其值非空。
    private int top;
    private int maxsize;

    Stack() { }

    /**
     * 栈的初始化
     */
    public void init (int capacity) {
        dates = new int[capacity];
        maxsize = capacity;
        setTop(-1);
    }

    public boolean push(int element) {
        if(getTop() == maxsize - 1) {
            System.out.println("栈已满！无法将新的元素压栈。");
            return false;
        }
        dates[++top] = element;
        return true;
    }

    public int pop() {
        if(this.isEmpty()) {
            // 当前栈为空
            return -1;
        }
        return dates[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}

/**
 * @author siryul
 */
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数组所实现的栈的总空间 capacity (capacity > 0)：");
        int capacity = scanner.nextInt();
        while (capacity < 1) {
            System.out.print("输入数据不合理，请重新输入：");
            capacity = scanner.nextInt();
        }
        stack.init(capacity);
        System.out.print("请输入总共需要压栈的元素总数 n (n > 0)：");
        int n = scanner.nextInt();
        while (n < 1) {
            System.out.print("输入数据不合理，请重新输入：");
            n = scanner.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            // 数组实现的栈空间已满
            if(!stack.push(i*10)){
                break;
            }
        }
        System.out.print("数组实现的栈的弹出顺序：");
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + "  ");
        }
        System.out.println("\n---------------------------------------------------");

        // 链表实现的栈
        StackList stackList = new StackList();
        stackList.init();
        System.out.print("请输入总共需要压栈的元素总数 n (n > 0)：");
        n = scanner.nextInt();
        while (n < 1) {
            System.out.print("输入数据不合理，请重新输入：");
            n = scanner.nextInt();
        }
        System.out.print("链表实现的栈的弹出顺序：");
        for (int i = 0; i < n; i++) {
            stackList.push(1+i);
            if(i % 2 == 0){
                System.out.print(stackList.pop() + "  ");
            }
        }
        while (!stackList.isEmpty()) {
            System.out.print(stackList.pop() + "  ");
        }
    }
}


/**
 * 栈的单链表实现
  */
class StackNode {
    private int date;
    public StackNode next;

    StackNode (){
        next = null;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
class StackList {
    public StackNode head;

    StackList (){
        head = new StackNode();
    }

    public void init (){
        head.next = null;
    }

    public void push(int element) {
        StackNode p = new StackNode();
        p.setDate(element);
        p.next = head.next;
        head.next = p;
    }

    public int pop() {
        // 返回值为 -1 代表栈为空
        int value = -1;
        if(!isEmpty()) {
            value = head.next.getDate();
            head.next = head.next.next;
        }
        return value;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public int getTop() {
        if(!isEmpty()){
            return head.next.getDate();
        }
        return -1;
    }
}