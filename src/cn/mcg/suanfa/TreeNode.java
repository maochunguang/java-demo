package cn.mcg.suanfa;

import java.util.LinkedList;

/**
 * Author: mac
 * Date: 2017/7/23
 * Description: todo
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val){
        this.val=val;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode left1=new TreeNode(2);
        TreeNode left2=new TreeNode(3);
        TreeNode right1=new TreeNode(4);
        //创建一棵树
        root.left=left1;
        left1.right=left2;
        root.right=right1;
        scanNodes(root);
        System.out.println("树的深度是："+getDepth(root));
        System.out.println("非递归深度："+ findDeep2(root));
    }

    //返回二叉树的深度
    static int getDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=getDepth(root.left);
        int right=getDepth(root.right);
        return left>right?left+1:right+1;
    }

    static void scanNodes(TreeNode root){
        if(root==null){
            return;
        }
//        System.out.println(root.val); //先序遍历
        scanNodes(root.left);
//        System.out.println(root.val); //中序遍历
        scanNodes(root.right);
        System.out.println(root.val); // 后序遍历
    }
    public static int findDeep2(TreeNode root)
    {
        if(root == null)
            return 0;
        TreeNode current = null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cur,next;
        int level = 0;
        while(!queue.isEmpty())
        {
            cur = 0;//记录本层已经遍历的节点个数  
            next = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
            while(cur < next)//当还没有遍历到本层最后一个节点时循环
            {
                current = queue.poll();//出队一个元素  
                cur++;
                //把当前节点的左右节点入队（如果存在的话）  
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;//每遍历完一层level+1  
        }
        return level;
    }

}
