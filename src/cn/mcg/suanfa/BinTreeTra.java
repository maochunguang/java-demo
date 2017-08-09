package cn.mcg.suanfa;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinTreeTra {
    private int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15 };
    private static List<Node> nodeList = null;

    public static Node init() {
        Node root=new Node(1);
        Node left1=new Node(2);
        Node left2=new Node(3);
        Node right1=new Node(4);
        Node right2=new Node(5);
        Node left3=new Node(6);
        Node right3=new Node(7);
        //创建一棵树
        root.leftChild=left1;
        root.rightChild=right1;
        left1.leftChild=left2;
        left1.rightChild = right2;
        right2.leftChild = left3;
        right2.rightChild = right3;
        return root;
    }

    /**
         * 内部类：节点 
         */  
        public static class Node {
            Node leftChild;  
            Node rightChild;  
            int data;  

            Node(int newData) {  
                leftChild = null;  
                rightChild = null;  
                data = newData;  
            }  
        }  

        public void createBinTree() {  
            nodeList = new LinkedList<Node>();  
            // 将一个数组的值依次转换为Node节点  
            for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {  
                nodeList.add(new Node(array[nodeIndex]));  
            }  
            // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树  
            for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {  
                // 左孩子  
                nodeList.get(parentIndex).leftChild = nodeList  
                        .get(parentIndex * 2 + 1);  
                // 右孩子  
                nodeList.get(parentIndex).rightChild = nodeList  
                        .get(parentIndex * 2 + 2);  
            }  
            // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理  
            int lastParentIndex = array.length / 2 - 1;  
            // 左孩子  
            nodeList.get(lastParentIndex).leftChild = nodeList  
                    .get(lastParentIndex * 2 + 1);  
            // 右孩子,如果数组的长度为奇数才建立右孩子  
            if (array.length % 2 == 1) {  
                nodeList.get(lastParentIndex).rightChild = nodeList  
                        .get(lastParentIndex * 2 + 2);  
            }  
        }  

        /** 
         * 先序遍历 
         *  
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
         *  
         * @param node 
         *            遍历的节点 
         */  
        public static void preOrderTraverse(Node node) {  
            if (node == null)  
                return;  
            System.out.print(node.data + " ");  
            preOrderTraverse(node.leftChild);  
            preOrderTraverse(node.rightChild);  
        }  

        /** 
         * 中序遍历 
         *  
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
         *  
         * @param node 
         *            遍历的节点 
         */  
        public static void inOrderTraverse(Node node) {  
            if (node == null)  
                return;  
            inOrderTraverse(node.leftChild);  
            System.out.print(node.data + " ");  
            inOrderTraverse(node.rightChild);  
        }  

        /** 
         * 后序遍历 
         *  
         * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已 
         *  
         * @param node 
         *            遍历的节点 
         */  
        public static void postOrderTraverse(Node node) {  
            if (node == null)  
                return;  

            postOrderTraverse(node.leftChild);  
            postOrderTraverse(node.rightChild);  
            System.out.print(node.data + " ");  
        }  






        //打印节点数值  
        public static void printNode(Node node){  
            System.out.print(node.data+" ");  
        }  



        /**
         * 非递归先序遍历
         * @param root
         */
        public static void preOrder_stack(Node root){//先序遍历  

            Stack<Node> stack = new Stack<Node>();
            Node node = root;  

            while(node != null || stack.size()>0){//将所有左孩子压栈  
                if(node != null){//压栈之前先访问  
                    printNode(node);  
                    stack.push(node);  
                    node = node.leftChild;  

                }else{  
                    node = stack.pop();  
                    node = node.rightChild;  
                }  
            }  
        }  


        /**
         * 非递归 中序遍历
         * @param root
         */
        public static void inOrder_stack(Node root){//zhong序遍历  

            Stack<Node> stack = new Stack<Node>();  
            Node node = root;  

            while(node != null || stack.size()>0){//将所有左孩子压栈  
                if(node != null){//压栈之前先访问  

                    stack.push(node);
                    node = node.leftChild;  

                }else{  
                    node = stack.pop(); 
                    printNode(node); 
                    node = node.rightChild;  
                }  
            }  
        } 

        /**
         * 非递归的 后续遍历
         * @param root
         */
        public static void postOrder_Stack(Node root){//后续遍历  

            Stack<Node> stack = new Stack<Node>();  
            Stack<Node> output = new Stack<Node>();//构造一个中间栈来存储逆后续遍历的结果  
            Node node = root;  
            while(node != null || stack.size()>0){  
                if(node != null){  
                    output.push(node);  
                    stack.push(node);  
                    node = node.rightChild;  
                }else{  
                    node = stack.pop();  
                    node = node.leftChild;  
                }  
            }  

            while(output.size()>0){  
                printNode(output.pop());  
            }  

        }

        /**
         * 层次遍历 
         * @param root
         */
     static   void LevelOrder(Node root){
            Queue<Node> queue=new LinkedList<>();
            queue.add(root);
            while(queue.size()>0){
                Node p=queue.poll();
                printNode(p);
                if(p.leftChild!=null){
                    queue.add(p.leftChild);
                } 

                if(p.rightChild!=null){
                    queue.add(p.rightChild);
                } 
            }
        }

        public static void main(String[] args) {  
            BinTreeTra binTree = new BinTreeTra();  
            binTree.createBinTree();  
            // nodeList中第0个索引处的值即为根节点  
            Node root = nodeList.get(0);  

            System.out.println("先序遍历：");  
            preOrderTraverse(root);  
            System.out.println();  

            System.out.println("中序遍历：");  
            inOrderTraverse(root);  
            System.out.println();  

            System.out.println("后序遍历：");  
            postOrderTraverse(root);  
            System.out.println(); 


            System.out.println("非递归 先序遍历："); 
            preOrder_stack(root);  
            System.out.println();  

            System.out.println("非递归 中序遍历："); 
            inOrder_stack(root);
            System.out.println(); 

            System.out.println("非递归后序遍历："); 
            postOrder_Stack(root);
            System.out.println(); 

            System.out.println("层次 遍历："); 
            LevelOrder(root);
            System.out.println(); 
        }   
}