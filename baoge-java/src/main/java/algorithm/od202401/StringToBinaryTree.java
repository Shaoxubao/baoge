package algorithm.od202401;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定字符串建立二叉树 空节点用null表示
 * 如：
 * 输入：root = [A, B, C, D, null, E, F]，如图所示：
 *      A
 *    /   \
 *   B     C
 *  /     / \
 * D     E   F
 * 递归实现遍历：
 * 先序遍历：访问中间结点，再访问左，最后访问右
 * 中序遍历：访问左，访问中间结点，访问右
 * 后序遍历：访问左，访问右，访问中间结点
 */
public class StringToBinaryTree {
    public static void main(String[] args) {
        TreeNode root = stringToTree("[A,B,C,D,null,E,F]");
        StringToBinaryTree binaryTree = new StringToBinaryTree();
        System.out.println("==========递归遍历==============");
        System.out.println("========先序遍历=========");
        binaryTree.preOrder(root);
        System.out.println();
        System.out.println("========中序遍历=========");
        binaryTree.inOrder(root);
        System.out.println();
        System.out.println("========后序遍历=========");
        binaryTree.postOrder(root);
        System.out.println();
        System.out.println("==========非递归遍历======");
        System.out.println(binaryTree.preorderTraversal(root).toString());  // [A, B, D, C, E, F]
        System.out.println(binaryTree.inorderTraversal(root).toString());   // [D, B, A, E, C, F]
        System.out.println(binaryTree.postorderTraversal(root).toString()); // [D, B, E, F, C, A]
        System.out.println("==========层序遍历========");
        System.out.println(binaryTree.levelOrderTraversal(root).toString()); // [[A], [B, C], [D, E, F]]
    }

    public static TreeNode stringToTree(String str) {
        String[] elems = stringToArray(str);
        if (null == elems) {
            return null;
        }

        TreeNode root = createTree(elems, 0);
        return root;
    }

    /**
     * 递归构建二叉树
     *
     * @param arr
     * @param index
     * @return
     */
    private static TreeNode createTree(String[] arr, int index) {
        TreeNode root = null;
        if (index < arr.length) {
            if (arr[index].equals("null")) {
                return null;
            }
            String val = arr[index];
            root = new TreeNode(val);
            // 构建二叉树左子树
            root.left = createTree(arr, 2 * index + 1);
            // 构建二叉树右子树
            root.right = createTree(arr, 2 * index + 2);
        }
        return root;
    }

    private static String[] stringToArray(String str) {
        if (null == str || str.equals("")) {
            return null;
        }
        String[] fields = str.split(",");
        fields[0] = fields[0].substring(1);
        int lastIndex = fields.length - 1;
        fields[lastIndex] = fields[lastIndex].substring(0, 1);
        return fields;
    }

    /**
     * 先序遍历
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    // 前序遍历
    public List<String> preorderTraversal(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);  // 相比中序遍历，只有这行代码换了位置
                stack.push(node);   //为了后面能找到该节点的右节点，所有暂存该节点到栈中
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }
    // 中序遍历
    public List<String> inorderTraversal(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);  // 相比前序遍历，只有这行代码换了位置
            node = node.right;
        }
        return res;
    }
    // 后序遍历
    public List<String> postorderTraversal(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode pre = null;
        while(!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == pre) {
                // 如果不存在右子树，则输出该节点的值
                // 如果该节点的右结点刚刚遍历过了，则也应该输出该节点的值
                res.add(node.val);
                pre = node;
                node = null;
            } else {
                // 如果存在右子树，则重新放回栈中，因为它的值要在右子树遍历完之后添加
                stack.push(node);
                node = node.right;
            }
        }
        return res;
    }

    // 层序遍历

    /**
        广度优先搜索层序遍历
        1 题目描述
        给定一棵二叉树，返回其节点值的层序遍历。
        输入：root = [A,B,C,D,null,E,F]，如图2-12所示:
             A
           /   \
          B     C
         /     / \
        D     E   F
        输出：[[A],[B,C],[D,E,F]]。
        2 题目解析
        首先将根节点放入队列作为二叉树的第一层，求当前队列的长度，然后将节点依次从队列中取出，作为该层的结果，
        并顺便将当前节点的左右非空节点放入队列，作为二叉树下一层需要处理的结果，直至队列为空。
     */
    public List<List<String>> levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<String>> ans = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        // 首先将根节点放入队列作为二叉树的第一层
        list.offer(root);
        while (!list.isEmpty()) {
            // 求当前队列的大小size
            int size = list.size();
            List<String> level = new ArrayList<>();
            // 将节点依次从队列中取出
            for (int i = 0; i < size; i++) {
                TreeNode cur = list.pop();
                // 作为该层的结果
                level.add(cur.val);
                // 将当前节点的左右节点放入队列，作为下一层需要处理的结果
                if (cur.left != null) {
                    list.offer(cur.left);
                }
                if (cur.right != null) {
                    list.offer(cur.right);
                }
            }
            ans.add(level);
        }

        return ans;
    }
}

class TreeNode {

    String val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    public TreeNode(String val) {
        this.val = val;
    }

    TreeNode(String val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


}
