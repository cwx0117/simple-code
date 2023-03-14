package linkList;

//将单向链表按某值划分成左边小、中间相等、右边大的形式
public class SmallerEqualBigger {
    public static class Node {
        public Node next;
        public int value;

        public Node(int data) {
            value = data;
        }
    }

    // 交换函数
    private static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        // 把链表的值复制到数组中
        for(i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 对数据进行“荷兰国旗”分组
        arrPartition(nodeArr, pivot);
        // 重新把链表连接
        for(i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /// 荷兰国旗算法
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int more = nodeArr.length;
        int less = -1;
        int i = 0;
        while(i != more) {
            if (nodeArr[i].value < pivot) {
                less++;
                swap(nodeArr, less, i);
                i++;
            }else if(nodeArr[i].value > pivot) {
                more--;
                swap(nodeArr, i, more);
            }else {
                i++;
            }
        }
    }

    public static Node listPartition2(Node head, int pivot) {
        // 小于pivot的链表头尾节点
        Node lessSt = null;
        Node lessEnd = null;
        // 等于pivot的链表头尾节点
        Node eqSt = null;
        Node eqEnd = null;
        // 大于pivot的链表头尾节点
        Node moreSt = null;
        Node moreEnd = null;
        // 用于保存next指针
        Node next = null;
        // 遍历链表
        while(head != null) {
            next = head.next;
            // 为了不污染小于，等于，大于的指针next，这里清空
            head.next = null;
            if(head.value < pivot) {
                if(lessSt == null) {
                    lessSt = head;
                    lessEnd = head;
                }else {
                    lessEnd.next = head;
                    lessEnd = head;
                }
            }else if(head.value == pivot) {
                if(eqSt == null) {
                    eqSt = head;
                    eqEnd = head;
                }else {
                    eqEnd.next = head;
                    eqEnd = head;
                }
            }else {
                if(moreSt == null) {
                    moreSt = head;
                    moreEnd = head;
                }else {
                    moreEnd.next = head;
                    moreEnd = head;
                }
            }
            head = next;
        }
        // 小于和等于区域相连
        if(lessEnd != null) {
            lessEnd.next = eqSt;
            // 判断equal的结尾指针是否为空
            eqEnd = eqEnd == null ? lessEnd : eqEnd;
        }
        // 最后等于，大于区域相连
        if (eqEnd != null) {
            eqEnd.next = moreSt;
        }
        if (lessSt != null) {
            return lessSt;
        }
        if (eqSt != null) {
            return eqSt;
        }
        if (moreSt != null) {
            return moreSt;
        }
        return null;
    }
}
