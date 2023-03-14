package binaryTree;

//如何判断一个树是否是平衡二叉树
//左子树 平 右子树 平 ｜左高-右高｜<=1
public class Test4 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public  static  boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    public static class ReturnType{
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB,int hei){
            isBalanced=isB;
            height=hei;
        }
    }

    private static ReturnType process(Node x) {
        if(x==null){
            return  new ReturnType(true,0);
        }

        ReturnType leftData=process(x.left);
        ReturnType rightData=process(x.right);

        int height = Math.max(leftData.height,rightData.height)+1;
        boolean isBalanced =leftData.isBalanced&&rightData.isBalanced&&Math.abs(leftData.height-rightData.height)<2;

        return new ReturnType(isBalanced,height);
    }
}
