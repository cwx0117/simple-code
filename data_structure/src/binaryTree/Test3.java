package binaryTree;

//如何判断一个二叉树是满二叉树
//1.求二叉树的最大深度l，再求结点个数n n=2^l-1
public class Test3 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static boolean isF(Node head){
        if(head==null){
            return  true;
        }
        Info data = process(head);
        return data.nodes == (1<<data.height-1);

    }

    public static  class Info{
        public int height;
        public  int nodes;

        public  Info(int h,int n){
            height=h;
            nodes=n;
        }
    }

    public static Info process(Node x){
        if(x==null){
            return  new Info(0,0);
        }
        Info leftData = process(x.left);
        Info rightData = process(x.right);
        int height = Math.max(leftData.height,rightData.height)+1;
        int nodes = leftData.nodes + rightData.nodes + 1;


        return new Info(height,nodes);
    }

}
  