package bPLUS;

public class BPlusUtils {

    public static void printBPlusTree(Node root) {
        if (root == null) {
            return;
        }
        printNode(root);
        if (!root.isLeaf) {
            for (Node child : root.child) {
                printBPlusTree(child);
            }
        }
    }

    private static void printNode(Node node) {
        System.out.print("[");
        for (int i = 0; i < node.key.length; i++) {
            if (node.key[i] != 0) {
                System.out.print(node.key[i]);
                if (i != node.key.length - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.print("]");
        System.out.print(" -> ");
        if (node.isLeaf) {
            System.out.println("null");
        } else {
            System.out.println();
        }
    }

}
