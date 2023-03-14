package linkList;

import java.util.*;

public class Palindromic {

    public static void process(LinkedList<Integer> list,int[] array,int n){

    }

    public static void main(String[] args) {

//        Arrays.sort();
        int[] array = new int[5];
        List<int[]> ints = Arrays.asList(array);
        Set<Integer> set=new LinkedHashSet<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> list = new LinkedList<>(list2);
        List<Integer> curList=list;
        List<List<Integer>> allList = new ArrayList<>();
        allList.add(list);

    }

    public static class Node {
        public int val;
        public Node next;

        public Node(int data) {
            this.val = data;
            this.next = null;
        }
        public Node() {
        }
    }

    public static boolean isPalindromic1(Node list) {
        Node p = list;
        Stack<Node> st = new Stack<Node>();
        while (p != null) {
            st.push(p);
            p = p.next;
        }
        p = list;
        while (!st.isEmpty()) {
            if (p.val != st.pop().val)
                return false;
            p = p.next;
        }
        return true;
    }

    public static boolean isPalindromic2(Node list) {
        Node head = new Node();
        head.next = list;//create a head Node
        Stack<Node> st = new Stack<Node>();
        Node p = head, q = head.next;//q finally point to the middle Node
        while (p.next != null && p.next.next != null) {
            q = q.next;
            p = p.next.next;
        }
        while (q != null) {
            st.push(q);
            q = q.next;
        }
        p = head.next;
        while (!st.isEmpty()) {
            if (p.val != st.pop().val)
                return false;
            p = p.next;
        }
        return true;
    }


    public static boolean isPalindromic3(Node list) {
        Node head1 = new Node();
        Node head2 = new Node();
        head1.next = list;
        Node p = head1, q = head1.next, r;//q point to the middle Node

        while (p.next != null && p.next.next != null) {
            q = q.next;
            p = p.next.next;
        }

        p = q.next;
        while (p != null) {
            q.next = null;
            r = p.next;
            p.next = q;
            if (r == null) {//note that p point to the last Node
                q = p;
                break;
            }
            q = r;
            p = q.next;
        }

        p = head1;
        head2.next = q;
        q = head2;
        while (p.next != null && q.next != null) {
            if (q.next.val != p.next.val) {
                return false;
            }
            q = q.next;
            p = p.next;
        }
        return true;
    }



}
