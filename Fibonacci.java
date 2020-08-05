/**
 * @author siryul
 * date:2020/08/05
 */
public class Fibonacci {
    public static void main(String[] args) {
        int n = 15;
        long start1 = System.currentTimeMillis();
        System.out.print("利用递归实现的斐波那契数列：");
        for (int i = 0; i < n; i++) {
            System.out.print(fib(i) + "  ");
        }
        long end1 = System.currentTimeMillis();
        System.out.println("递归耗费的时间：" + (end1-start1) + "ms");

        long start2 = System.currentTimeMillis();
        StackList stack = new StackList();
        stack.init();
        System.out.print("\n利用栈实现的斐波那契数列：");
        for (int i = 0; i < n; i++) {
            System.out.print(fib(stack, i) + "  ");
        }
        long end2 = System.currentTimeMillis();
        System.out.println("栈耗费的时间：" + (end2-start2) + "ms");
    }

    /**
    * 递归求解
    */
    static int fib(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    /**
    * 利用栈的方法求解，栈的引用来自类 StackTest.java
    */
    static int fib(StackList stackList, int n) {
        if (n == 0){
            stackList.push(0);
            return 0;
        } else if(n == 1){
            stackList.push(1);
            return 1;
        }else {
            int b = stackList.pop();
            int a = stackList.pop();
            int c = a + b;
            stackList.push(a);
            stackList.push(b);
            stackList.push(c);
            return c;
        }
    }
}
