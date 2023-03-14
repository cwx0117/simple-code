package binaryTree;

import java.util.Stack;

//如何判断一个树为搜索二叉树
//对于每一棵子树来说，他的左树结点比他小，右树结点比他大
//左树和右树都是搜索二叉树 左树max<x 右树min>x
public class Test1 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static int preValue=Integer.MIN_VALUE;

    public static boolean isBST(Node node){
        if(node == null){
            return true;
        }
        boolean isLeftBst = isBST(node.left);
        if(!isLeftBst){
            return false;
        }
        if(node.value<=preValue){
            return false;
        }else {
            preValue=node.value;
        }
        return isBST(node.right);
    }

    public static boolean isBST2(Node head){
        if(head!=null){
            Stack<Node> stack=new Stack<>();
            while (head!=null||!stack.isEmpty()){
                if(head!=null){
                    stack.push(head);
                    head=head.left;
                }else {
                    head=stack.pop();
                    //System.out.print(head.data+" ");
                    if(head.value<=preValue){
                        return false;
                    }else {
                        preValue=head.value;
                    }
                    head=head.right;
                }
            }
        }
        return true;
    }

    public static class  ReturnType{
        public boolean isBST;
        public int min;
        public int max;

        public ReturnType(boolean is,int mi,int ma){
            isBST=is;
            min=mi;
            max=ma;
        }
    }

    public static ReturnType process(Node x){
        if(x==null){
            return null;
        }

        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        int min = x.value;
        int max = x.value;
        if(leftData!=null){
            min=Math.min(min,leftData.min);
            max=Math.max(max,leftData.max);

        }
        if(rightData!=null){
            min=Math.min(min,rightData.min);
            max=Math.max(max,rightData.max);

        }
        boolean isBST=true;
        if(leftData!=null&&(!leftData.isBST||leftData.max>=x.value)){
            isBST=false;
        }
        if(rightData!=null&&(!rightData.isBST||x.value>=rightData.min)){
            isBST=false;
        }


        return  new ReturnType(isBST,min,max);
    }


}
