package binaryTree;

import java.util.HashMap;
import java.util.HashSet;

public class Test5 {

    private Node root;

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public Test5(){
        root = null;
    }

    /**
     * 递归创建二叉树
     * @param node
     * @param data
     */
    public void buildTree(Node node, int data){
        if(root == null){
            root = new Node(data);
        }else{
            if(data < node.value){
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

    //o1和o2一定属于head为头的树 返回o1和o2的公共祖先
    public   Node lca(Node head , Node o1, Node o2){
        HashMap<Node,Node> fatherMap = new HashMap<>();
        fatherMap.put(head,head);
        process(head,fatherMap);
        HashSet<Node> set1 = new HashSet<>();
        set1.add(o1);
        Node cur = o1;
        while (cur!=fatherMap.get(cur)){
            set1.add(cur);
            cur = fatherMap.get(cur);
        }
        set1.add(head);

        //while o2往上蹿
        Node compareCur = o2;
        while (compareCur!=fatherMap.get(compareCur)){
            if(set1.contains(compareCur)){
                return compareCur;
            }
            compareCur=fatherMap.get(compareCur);
        }
        return head;
    }


    public static  void process(Node head,HashMap<Node,Node> fatherMap){
        if(head == null){
            return;
        }
        fatherMap.put(head.left,head);
        fatherMap.put(head.right,head);
        process(head.left,fatherMap);
        process(head.right,fatherMap);
    }

    public static  Node lowestAncestor(Node head,Node o1,Node o2){
        if(head==null||head==o1||head==o2){
            return head;
        }

        Node left = lowestAncestor(head.left,o1,o2);
        Node right = lowestAncestor(head.right,o1,o2);
        if(left!=null&&right!=null){
            return head;
        }
        return left!=null?left:right;
    }
}
