package cn.mcg.suanfa;
import cn.mcg.suanfa.BinTreeTra.Node;
/**
 * Author: mac
 * Date: 2017/7/20
 * Description: todo
 */
public class BiTreeDepth {
    /**
     * 递归方法
     *
     * @param root
     * @return
     */
    static int btdep(Node root) {
        if (root == null) {
            return 0;
        }
        int ldep = btdep(root.leftChild);
        int rdep = btdep(root.rightChild);
        if (ldep > rdep) {
            return ldep + 1;
        } else {
            return rdep + 1;
        }
    }

    /**
     * 非递归方法
     * 求每层的个数 树的最大宽度  都可以采用这种思想
     * @param root
     * @return
     */
    static int btdep2(Node root) {
        int front = -1, rear = -1;
        int last = 0, level = 0;
        Node[] queue = new Node[100000];
        if (root == null) {
            return 0;
        }
        queue[++rear]=root;
        Node p;
        while(front<rear){
            p=queue[++front];
            if(p.leftChild!=null){
                queue[++rear]=p.leftChild;
            }
            if(p.rightChild!=null){
                queue[++rear]=p.rightChild;
            }
            if(front==last){
                level++;
                last=rear;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        Node root = BinTreeTra.init();
        System.out.println("递归方法 树的高度是" + btdep(root));

        System.out.println("非递归方法 树的高度是" + btdep2(root));
    }
}
