package binaryTree;

import java.util.LinkedList;

//如何判断一个数是完全二叉树 从左往右依次变满 宽度遍历
//1）任意结点有右无左 false 2）在1）不违规的条件下，遇到了第一个左右孩子不双全的情况下，接下来的所有结点必须是叶结点
public class Test2 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static  boolean isCBT(Node head){
        if(head==null){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        //是否遇到左右两个孩子不双全的结点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if(
                    (leaf&&(l!=null||r!=null))
                    || (l==null&&r!=null)
            ){
                return false;
            }
            if(l!=null){
                queue.add(l);
            }
            if(r!=null){
                queue.add(r);
            }
            if(l==null||r==null){
                leaf=true;
            }
        }
        return true;
    }
}
