package binaryTree;
//后继结点
public class Test6 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data){
            this.value=data;
        }

        public static Node getSuccessorNode(Node node){
            if(node == null){
                return null;
            }
            if (node.right!=null){
                return getLeftMost(node.right);
            }else {
                Node parent = node.parent;
                while (parent!=null&&parent.left!=node){
                    node=parent;
                    parent=node.parent;
                }
                return parent;
            }
        }

        private static Node getLeftMost(Node node) {
            if(node==null){
                return null;
            }
            while (node.left!=null){
                node=node.left;
            }
            return node;
        }
    }

}
