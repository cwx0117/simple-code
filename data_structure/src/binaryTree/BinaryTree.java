package binaryTree;

import java.util.Stack;

public class BinaryTree {
    private Node root;

    /**
     *
     * 内部节点类
     * @author yhh
     */
    private static class Node{
        private Node left;
        private Node right;
        private final int data;
        public Node(int data){
            this.left = null;
            this.right = null;
            this.data = data;
        }
    }

    public BinaryTree(){
        root = null;
    }

    /**
     * 递归创建二叉树
     * @param node
     * @param data
     */
    public void buildTree(Node node,int data){
        if(root == null){
            root = new Node(data);
        }else{
            if(data < node.data){
                if(node.left == null){
                    node.left = new Node(data);
                }else{
                    buildTree(node.left,data);
                }
            }else{
                if(node.right == null){
                    node.right = new Node(data);
                }else{
                    buildTree(node.right,data);
                }
            }
        }
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node){
        if(node != null){
            System.out.print(node.data+" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历非递归
     * @param head
     */
    public void preOrderUnRecur(Node head){
        Stack<Node> stack=new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head=stack.pop();
            System.out.print(head.data+" ");
            if(head.right!=null){
                stack.push(head.right);
            }
            if(head.left!=null){
                stack.push(head.left);
            }
        }

    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data+" ");
            inOrder(node.right);
        }
    }

    /**
     * 中序遍历非递归
     * @param head
     */
    public void inOrderUnRecur(Node head){
        if(head!=null){
            Stack<Node> stack=new Stack<>();
            while (head!=null||!stack.isEmpty()){
                if(head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head=stack.pop();
                    System.out.print(head.data+" ");
                    head=head.right;
                }
            }
        }
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data+" ");
        }
    }

    /**
     * 后序遍历
     * @param head
     */
    public void postOrderUnRecur(Node head){
        Stack<Node> stack=new Stack<>();
        Stack<Node> stack2=new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head=stack.pop();
            stack2.push(head);
            System.out.print(head.data+" ");
            if(head.left!=null){
                stack.push(head.right);
            }
            if(head.right!=null){
                stack.push(head.left);
            }
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().data+" ");
        }
    }

    public static void main(String[] args) {
        int[] a = {2,4,12,45,21,6,111};
        BinaryTree bTree = new BinaryTree();
        for (int value : a) {
            bTree.buildTree(bTree.root, value);
        }
        bTree.preOrder(bTree.root);
        System.out.println();
        bTree.inOrder(bTree.root);
        System.out.println();
        bTree.postOrder(bTree.root);
    }

}
