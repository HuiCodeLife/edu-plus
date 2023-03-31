import java.util.Arrays;

/**
 * @author: Lin
 * @since: 2023-03-29
 */
public class Test {
    public static void main(String[] args) {
        // 模拟输入
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        // 递归反转
        reverse(chars, 0, chars.length - 1);
        // 输出结果
        System.out.println(Arrays.toString(chars));
    }
    /**
     * 反转
     * @param chars 数组
     * @param start 左边索引
     * @param end   右边索引
     */
    public static void reverse(char[] chars, int start, int end) {
        // 边界条件
        if (start >= end) {
            return;
        }
        // 左右指针交换
        char tmp = chars[start];
        chars[start] = chars[end];
        chars[end] = tmp;
        // 递归调用 左边索引+1 右边索引-1
        reverse(chars, start + 1, end - 1);
    }
}
