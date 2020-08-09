import java.util.Arrays;

/**
 * @author siryul
 * date:2020/08/08
 * 字符串的模式匹配（KMP 算法）
 */
public class StringTest {

    public static void main(String[] args) {
        char[] chars = "acbdaacbdac".toCharArray();
        System.out.println("主串 S 为：" + Arrays.toString(chars));
        char[] test = "acbdac".toCharArray();
        System.out.println("模式串 test 为：" + Arrays.toString(test));
        System.out.println("KMP 字符串匹配算法结果：" + getIndex(chars, test));

        System.out.println("=================================================");
        
        char[] s1 = "aabcdaabcaabca".toCharArray();
        char[] t1 = "aabce".toCharArray();
        System.out.println("主串 s1 为：" + Arrays.toString(s1));
        System.out.println("模式串 t1 为：" + Arrays.toString(t1));
        System.out.println("KMP 字符串匹配算法结果：" + getIndex(s1, t1));
    }

    /**
     * 获取 next 数组
     */
    static  int[] getNext(char[] test) {
        int[] next = new int[test.length];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < test.length-1) {
            if (j == -1 || test[i] == test[j]) {
                ++i;
                ++j;
                next[i] = j;
            }else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 获取第一个子串匹配位置
     */
    static int getIndex(char[] chars, char[] test) {
        int[] next = getNext(test);
        System.out.println("next 数组：" + Arrays.toString(next));
        int i = 0, j = 0;
        while (i < chars.length && j < test.length) {
            if (j == 0 || chars[i] == test[j]) {
                ++i;
                ++j;
            } else {
                j = next[j];
            }
        }
        if (j >= test.length) {
            return i-j+1;
        }
        return 0;
    }
}
