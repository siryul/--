import java.util.*;
import java.util.Queue;
import java.util.Stack;

/**
 * @author siryul
 * date: 2020/08/15
 */
public class BinTreeTest {
    public static void main(String[] args) {
        BinTree binTree = new BinTree();
        // 终端输入节点数、先序遍历与中序遍历序列
        // createData(binTree);
        //              1
        //            /   \
        //           2     3
        //          / \   /
        //         4   5 6
        //              /
        //             7
        // 构造二叉树的先序与中序序列
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 7, 6, 3};
        System.out.println("构造前数据：");
        System.out.println("先序序列：" + Arrays.toString(pre) + "\n中序序列：" + Arrays.toString(in));
        int n = pre.length;
        binTree.root = binTree.createBT(pre, in, 0, n-1, 0, n-1);

        System.out.println("二叉树的形状如下：\n" +
                "      1\n" +
                "    /   \\\n" +
                "   2     3\n" +
                "  / \\   /\n" +
                " 4   5 6\n" +
                "      /\n" +
                "     7");

        System.out.println("======================================================");
        // 操作选项选择
        operate(binTree);
    }


    /**
     * 输入数据
     */
    static void createData(BinTree binTree) {
        // 初始化利用人为输入构造二叉树，输入 n 代表要输入节点个数，再输入两次 n 个数（先序遍历与中序遍历）构造二叉树
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入二叉树节点个数 n(n > 0): ");
        int n  = scanner.nextInt();
        while (n <= 0) {
            System.out.print("Error, please input again: ");
            n = scanner.nextInt();
        }

        // 输入先序遍历产生的序列
        System.out.print("输入先序遍历产生的序列(数字之间以空格分开)：");
        int[] preOrder = new int[n];
        for (int i = 0; i < n; i++) {
            preOrder[i] = scanner.nextInt();
        }

        // 输入中序遍历产生的序列
        System.out.print("输入中序遍历产生的序列(数字之间以空格分开)：");
        int[] inOrder = new int[n];
        for (int i = 0; i < n; i++) {
            inOrder[i] = scanner.nextInt();
        }

        // 传入数据
        binTree.root = binTree.createBT(preOrder, inOrder, 0, n-1, 0, n-1);
    }

    /**
     * 将要执行的操作
     */
    static void operate(BinTree binTree) {
        System.out.println("*****************************************************\n" +
                           "** 请输入一下序号执行相关操作：                          **\n" +
                           "** 先序遍历：1                                       **\n" +
                           "** 中序遍历：2                                       **\n" +
                           "** 后序遍历：3                                       **\n" +
                           "** 层序遍历：4                                       **\n" +
                           "** 二叉树深度：5                                      **\n" +
                           "** 二叉树宽度：6                                      **\n" +
                           "** 求节点祖先：7                                      **\n" +
                           "** 退出：-1                                          **\n" +
                           "******************************************************\n");
        int op;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("请输入所要执行的操作序号：");
            op = scanner.nextInt();
            if (op == -1){
                break;
            }else
                switch (op) {
                case 1:{
                    System.out.print("先序遍历结果：");
                    binTree.preOrder(binTree.root);
                    System.out.println();
                    break;
                }
                case 2:{
                    System.out.print("中序遍历结果：");
                    binTree.inOrder(binTree.root);
                    System.out.println();
                    break;
                }
                case 3:{
                    System.out.print("后序遍历结果：");
                    binTree.reOrder(binTree.root);
                    System.out.println();
                    break;
                }
                case 4:{
                    System.out.print("层序遍历结果：");
                    List<List<Integer>> result = binTree.levelOrder(binTree.root);
                    System.out.println(Arrays.toString(result.toArray()));
                    break;
                }
                case 5:{
                    System.out.println("二叉树的深度：" + binTree.getTreeDepth(binTree.root));
                    break;
                }
                case 6:{
                    System.out.println("二叉树的最大宽度：" + binTree.getTreeMaxWidth(binTree.root));
                    break;
                }
                case 7:{
                    int m;
                    System.out.print("请输入所要查询的节点值：");
                    m = scanner.nextInt();
                    System.out.print(m + " 的祖先：");
                    if (!binTree.getRoute(binTree.root, m)) {
                        System.out.println("未找到所要查询的节点！");
                    }
                    System.out.println();
                    break;
                }
                default:{
                    System.out.println("输入数据错误！");
                    break;
                }
            }
        }while (true);
    }
}


/**
 * “链表形式”的二叉树节点
 */
class TreeNode {
    int data;
    TreeNode lChild;
    TreeNode rChild;

    TreeNode() {}

    TreeNode(int data) {
        this.data = data;
        this.lChild = rChild = null;
    }
}

class BinTree {
    TreeNode root;

    BinTree (){}

    BinTree(int[] preOrder, int[] inOrder) {
        int n = preOrder.length;
        root = createBT(preOrder, inOrder, 0, n-1, 0, n-1);
    }


    /**
     * 由先序遍历与中序遍历构造二叉树， 利用 _left, _right 来划分左右子树区域
     * @param preOrder 先序遍历
     * @param inOrder  中序遍历
     */
    TreeNode createBT(int[] preOrder, int[] inOrder, int pre_left, int pre_right, int in_left, int in_right) {
        if (pre_left > pre_right) {
            return null;
        }
        // 先序遍历首节点为根节点
        TreeNode root = new TreeNode();
        root.data = preOrder[pre_left];
        // 获取 preOrder[pre_left] 在中序遍历中的位置
        int local = getLocal(inOrder, preOrder[pre_left]);
        // 获取左子树节点个数
        int leftNumber = local - in_left;
        // 构造左子树
        root.lChild = createBT(preOrder, inOrder, pre_left+1, pre_left+leftNumber, in_left, local-1);
        // 构造右子树
        root.rChild = createBT(preOrder, inOrder, pre_left+leftNumber+1, pre_right, local+1, in_right);

        return root;
    }

    int getLocal(int[] ints, int e) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == e) {
                return i;
            }
        }
        // 未找到该元素
        return -1;
    }

    /**
     * 先序遍历
     * @param root
     */
    void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + "  ");
            preOrder(root.lChild);
            preOrder(root.rChild);
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.lChild);
            System.out.print(root.data + "  ");
            inOrder(root.rChild);
        }
    }

    /**
     * 后续遍历
     * @param root
     */
    void reOrder(TreeNode root) {
        if (root != null) {
            reOrder(root.lChild);
            reOrder(root.rChild);
            System.out.print(root.data + "  ");
        }
    }

    /**
     * 层序遍历
     * @param root
     */
    List<List<Integer>> levelOrder(TreeNode root) {
        // 空树
        if (root == null) {
            return null;
        }
        // 返回列表 res
        List<List<Integer>> res = new ArrayList<>();
        // node 保留某一刚被遍历层的节点
        List<TreeNode> node = new ArrayList<>();
        node.add(root);
        while (node.size() > 0) {
            // 辅助列表 temp 保存下一层节点
            List<Integer> temp = new ArrayList<>();
            int size = node.size();
            // 循环将上一层的节点移除列表
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = node.remove(0);
                temp.add(treeNode.data);
                //System.out.print(treeNode.data + "  ");
                // 子树非空入队
                if (treeNode.lChild != null) {
                    node.add(treeNode.lChild);
                }
                if (treeNode.rChild != null) {
                    node.add(treeNode.rChild);
                }
            }
            res.add(temp);
            // 可以打印逗号来区分层序遍历中的”层“
            //System.out.print(",");
        }

        return res;
    }


    /**
     * 递归求二叉树深度
     * @param root
     * @return
     */
    int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeDepth(root.lChild);
        int right = getTreeDepth(root.rChild);

        // 比较左右子树的深度
        return left > right ? (left+1) : (right+1);
    }

    /**
     * 和层序遍历相同的思想，利用队列的辅助求解，同理还可以求具体 x （0 < x <= h）层的宽度
     * @param root
     * @return
     */
    int getTreeMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int width = 1;
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            // size 为当前层的节点个数，因而和此层宽度相同
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = nodes.remove(0);
                if (treeNode.rChild != null) {
                    nodes.add(treeNode.rChild);
                }
                if (treeNode.lChild != null) {
                    nodes.add(treeNode.lChild);
                }
            }
            width = size > width ? size : width;
        }
        return width;
    }

    /**
     * 得到节点 e 与根节点之间的路径（e 的祖先节点）
     * @return
     */
    boolean getRoute(TreeNode root, int e) {
        if (root == null) {
            return false;
        }
        if (root.data == e) {
            //System.out.print(e);
            return true;
        }
        if (getRoute(root.lChild, e) || getRoute(root.rChild, e)) {
                System.out.print(root.data + "  ");
                return true;
        }
        return false;
    }

    /**
     * 获得某节点及其子树的节点数之和
     */
    int getCountNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 树的节点个数为左子树加右子树加 1
        return getCountNodes(root.lChild) + getCountNodes(root.rChild) + 1;
    }
}
