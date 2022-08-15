package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import android.text.TextUtils;

import org.junit.Test;

public class N7V3_BinaryTreeSerializationDeserialization {


    /**
     * 描述
     * 设计一个算法，并编写代码来序列化和反序列化二叉树。将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
     * <p>
     * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
     * 对二进制树进行反序列化或序列化的方式没有限制，LintCode将您的serialize输出作为deserialize的输入，它不会检查序列化的结果。
     * <p>
     * 样例
     * 给出一个测试数据样例， 二叉树{3,9,20,#,#,15,7}，表示如下的树结构：
     * .   3
     * .  / \
     * . 9  20
     * .   /  \
     * .  15   7
     * 我们的数据是进行BFS遍历得到的。当你测试结果wrong answer时，你可以作为输入调试你的代码。
     * <p>
     * 你可以采用其他的方法进行序列化和反序列化。
     */

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        String serialize = serialize(root);
        System.out.print("serialize = " + serialize);

    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize(TreeNode root) {
        return ser_nlr(root);
//        return ser_lnr(root);
//        return ser_lrn(root);
    }

    // 先序
    private String ser_nlr(TreeNode node) {
        StringBuilder sb = new StringBuilder();

        // n
        sb.append(node.val).append(",");

        // l
        if (node.left == null) {
            sb.append("#,");
        } else {
            sb.append(ser_nlr(node.left)).append(",");
        }

        // r
        if (node.right == null) {
            sb.append("#");
        } else {
            sb.append(ser_nlr(node.right));
        }

        return sb.toString();
    }

    // 中序
    private String ser_lnr(TreeNode node) {
        StringBuilder sb = new StringBuilder();

        // l
        if (node.left == null) {
            sb.append("#,");
        } else {
            sb.append(ser_nlr(node.left)).append(",");
        }

        // n
        sb.append(node.val).append(",");

        // r
        if (node.right == null) {
            sb.append("#");
        } else {
            sb.append(ser_nlr(node.right));
        }

        return sb.toString();
    }

    // 后序
    private String ser_lrn(TreeNode node) {
        StringBuilder sb = new StringBuilder();

        // l
        if (node.left == null) {
            sb.append("#,");
        } else {
            sb.append(ser_nlr(node.left)).append(",");
        }

        // r
        if (node.right == null) {
            sb.append("#,");
        } else {
            sb.append(ser_nlr(node.right)).append(",");
        }

        // n
        sb.append(node.val);

        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        return dex_nlr(data);
    }

    private TreeNode dex_nlr(String data) {

        String[] strs = data.split(",");

        TreeNode root = new TreeNode();

        TreeNode p = root;
        int index = 0;
        while (index < strs.length) {
            if (TextUtils.equals(strs[index], "#")) {
//                p=null;
            } else {
                p.val = Integer.parseInt(strs[index]);
            }
        }

        return root;
    }


//    public TreeNode deserialize(String data) {
//        return des(data);
//    }
//
//    private TreeNode des(String data) {
//        String[] strings = data.split(",");
//
//        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
//        TreeNode first = root;
//
//        TreeNode n = new TreeNode(-1);
//        TreeNode p = n;
//
//        for (int i = 1; i < strings.length; i += 2) {
//            while (first != null) {
//                if ("#".equals(strings[i])) {
//                    first.left = null;
//                } else {
//                    first.left = new TreeNode(Integer.parseInt(strings[i]));
//                    p.right = first.left;
//                    p = first.left;
//                }
//
//                if ("#".equals(strings[i])) {
//                    first.right = null;
//                } else {
//                    first.right = new TreeNode(Integer.parseInt(strings[i + 1]));
//                    p.right = first.right;
//                    p = first.right;
//                }
//                first = first.right;
//            }
//            first = n;
//        }
//        return root;
//    }
}
