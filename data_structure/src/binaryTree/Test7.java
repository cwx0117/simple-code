package binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//二叉树序列化和反序列化
public class Test7 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static String serialByPre(Node head){
        if(head==null){
            return "#_";
        }
        String res = head.value+"_";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr){
        String[] values=preStr.split("_");
        Queue<String> queue = new LinkedList<String>(Arrays.asList(values));
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if(value.equals("_")){
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }
}
