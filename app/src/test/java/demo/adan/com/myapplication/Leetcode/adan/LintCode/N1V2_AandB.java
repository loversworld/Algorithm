package demo.adan.com.myapplication.Leetcode.adan.LintCode;

/**
 * created by adan on 2018/8/2.
 */
public class N1V2_AandB {

    /**
     * 描述
     * 给出两个整数 aa 和 bb , 求他们的和。
     * <p>
     * 你不需要从输入流读入数据，只需要根据aplusb的两个参数a和b，计算他们的和并返回就行。
     * <p>
     * 说明
     * a和b都是 32位
     * 可以使用位运算符
     * <p>
     * 样例
     * 如果 a=1 并且 b=2，返回3。
     * <p>
     * 挑战
     * 显然你可以直接 return a + b，但是你是否可以挑战一下不这样做？（不使用++等算数运算符）
     */
    public void test() {
        long result = add(1, 2);
        System.out.println("result = " + result);
    }

    private int add(int a, int b) {
        a += b;
        return a;
    }
}
