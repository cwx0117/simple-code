package linkList;
/*
题目： 给定两个有序链表的头指针head1，和head2，打印两个链表的公共部分.
 */
public class PrintCommonPart {
    /*
    解题思路：链表的公共部分，其实就是两个链表重合的地部分。重合的情况有好几种，
    1、完全重合。两个链表的首尾node都一样。
    2、部分重合。呈一字型，也就是某个链表的尾部与另一个链表的前半部分有重合。
    3、部分重合。呈Y字型，两个链表的尾部完全重合
    这道题很简单，因为有序，所以就比较大小，小的指针往后移动。直到发现有指针为空到达尾部。
     */
    class Node{
        public int value;
        public Node next;
        public Node(int  data){
            this.value = data;
        }
        public void printCommonPart(Node head1,Node head2){
            while (head1.next != null && head2.next != null){
                if(head1.value < head2.value){
                    head1 = head1.next;
                }else if(head1.value > head2.value){
                    head2 = head2.next;
                }else {
                    System.out.println(head1.value + " ");//打印公共部分
                    head1 = head1.next;
                    head2 = head2.next;
                }
            }
        }
    }
}

