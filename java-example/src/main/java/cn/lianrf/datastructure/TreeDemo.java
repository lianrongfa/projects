import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的前序遍历方式
 *
 *
 * @author lianrf
 * @version 1.0
 * @since 2021/12/30 10:51 上午
 */
public class TreeDemo {

    public static void main(String[] args) {
        TreeNode root = buildData();
        floor(root);

    }

    /**
     * 树用层遍历，从左至右
     *
     * @param root
     */
    public static void floor(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                System.out.println("treeNode.val = " + treeNode.val);

                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }

    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public static void stack(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            System.out.println("treeNode.val = " + treeNode.val);

            TreeNode right = treeNode.right;
            if (right != null) {
                stack.push(right);
            }
            TreeNode left = treeNode.left;
            if (left != null) {
                stack.push(left);
            }

        }

    }


    /**
     * 构建数据
     *           1
     *          /\
     *         2 3
     *        /| /\
     *       4 56 7
     *      / /\
     *     3 7 6
     */
    private static TreeNode buildData() {
        TreeNode root = new TreeNode(1);
        TreeNode l_1_1 = new TreeNode(2);
        TreeNode r_1_2 = new TreeNode(3);
        root.left = l_1_1;
        root.right = r_1_2;

        TreeNode l_3_1 = new TreeNode(4);
        TreeNode r_3_2 = new TreeNode(5);
        TreeNode l_3_3 = new TreeNode(6);
        TreeNode r_3_4 = new TreeNode(7);

        l_1_1.left = l_3_1;
        l_1_1.right = r_3_2;

        r_1_2.left = l_3_3;
        r_1_2.right = r_3_4;

        TreeNode l_4_1 = new TreeNode(3);
        TreeNode r_4_2 = new TreeNode(7);
        TreeNode l_4_3 = new TreeNode(6);

        l_3_1.left = l_4_1;

        r_3_2.left = r_4_2;
        r_3_2.right = l_4_3;
        return root;
    }


    /**
     * 递归前序遍历
     *
     */
    public static void rec(TreeNode treeNode) {
        if (treeNode == null) return;
        System.out.println("treeNode.val = " + treeNode.val);
        rec(treeNode.left);
        rec(treeNode.right);
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}