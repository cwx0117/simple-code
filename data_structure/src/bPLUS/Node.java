package bPLUS;

public class Node {
    int n; // 结点中关键字个数
    int[] key; // 关键字数组
    Node[] child; // 子节点数组
    boolean isLeaf; // 是否为叶子节点

    public Node(int degree, boolean isLeaf) {
        this.n = 0;
        this.key = new int[2 * degree - 1];
        this.child = new Node[2 * degree];
        this.isLeaf = isLeaf;
    }
}

class BPlusTree {
    private Node root;
    private int degree;

    public BPlusTree(int degree) {
        this.root = null;
        this.degree = degree;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(degree, true);
            root.key[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * degree - 1) {
                Node s = new Node(degree, false);
                s.child[0] = root;
                splitChild(s, 0, root);
                int i = 0;
                if (s.key[0] < key) i++;
                insertNonFull(s.child[i], key);
                root = s;
            } else {
                insertNonFull(root, key);
            }
        }
    }

    private void insertNonFull(Node node, int key) {
        int i = node.n - 1;
        if (node.isLeaf) {
            while (i >= 0 && node.key[i] > key) {
                node.key[i + 1] = node.key[i];
                i--;
            }
            node.key[i + 1] = key;
            node.n++;
        } else {
            while (i >= 0 && node.key[i] > key) {
                i--;
            }
            i++;
            if (node.child[i].n == 2 * degree - 1) {
                splitChild(node, i, node.child[i]);
                if (node.key[i] < key) i++;
            }
            insertNonFull(node.child[i], key);
        }
    }

    private void splitChild(Node parent, int i, Node child) {
        Node newNode = new Node(degree, child.isLeaf);
        newNode.n = degree - 1;
        for (int j = 0; j < degree - 1; j++) {
            newNode.key[j] = child.key[j + degree];
        }
        if (!child.isLeaf) {
            for (int j = 0; j < degree; j++) {
                newNode.child[j] = child.child[j + degree];
            }
        }
        child.n = degree - 1;
        for (int j = parent.n; j >= i + 1; j--) {
            parent.child[j + 1] = parent.child[j];
        }
        parent.child[i + 1] = newNode;
        for (int j = parent.n - 1; j >= i; j--) {
            parent.key[j + 1] = parent.key[j];
        }
        parent.key[i] = child.key[degree - 1];
        parent.n++;
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        int i = 0;
        while (i < node.n && key > node.key[i]) {
            i++;
        }
        if (i < node.n && key == node.key[i]) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        return search(node.child[i], key);
    }

    //这段代码实现了B+树的插入和查询功能。其中，`Node`类表示B+树中的节点，`BPlusTree`类表示整个B+树。在`BPlusTree`类中，`root`表示根节点，`degree`表示B+树的度。
    //B+树的插入操作首先检查根节点是否为空，如果是，则创建一个叶子节点并将新键值插入其中。否则，检查根节点是否已满。如果是，则创建一个新的根节点并将原根节点分裂为两个子节点，然后将新键值插入到相应的子节点中。否则，将新键值插入到根节点的适当子节点中。
    //B+树的查询操作首先从根节点开始，在节点中查找与给定键匹配的项。如果找到，则返回true。如果没有找到并且当前节点是叶子节点，则返回false。否则，继续在相应的子节点中递归查找。
    //使用B+树时，可以通过创建`BPlusTree`对象来初始化树，然后调用`insert`方法插入键值，调用`search`方法查找键值。
    public static void main(String[] args) {
        BPlusTree tree = new BPlusTree(3);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);

        System.out.println(tree.search(3)); // true
        System.out.println(tree.search(4)); // false


        BPlusUtils.printBPlusTree(tree.root);
    }
}


