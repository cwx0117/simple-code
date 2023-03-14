package linkList;

import java.util.Stack;

public class revere_node {
    public static void main(String[] args) {
        LinkedNode list = new LinkedNode();
        Stu_node node1 = new Stu_node(1, "张三");
        Stu_node node2 = new Stu_node(2, "李四");
        Stu_node node3 = new Stu_node(3, "王二");
        Stu_node node4 = new Stu_node(4, "麻子");
        Stu_node node5 = new Stu_node(5, "赵六");
        //打印添加节点之前的链表
        list.print();
        //尾结点添加节点
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        //打印添加加点之后的链表
        list.print();
        System.out.println("-------------------");
        //定义一个头结点接收调用函数返回的头节点
        //Stu_node head = list.reverse_stack(list.head);
        Stu_node head = list.reverse_list(list.head);
        //遍历输出每个节点
        while (head.next != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}

//定义一个链表的操作类
class LinkedNode {
    //定义一个头结点
    Stu_node head = new Stu_node(-1, " ");

    //添加链表的方法
    public void add(Stu_node node) {
        Stu_node temp = head;
        while (true) {
            if (temp.next == null)
                break;
            temp = temp.next;
        }
        temp.next = node;
    }

    //打印链表
    public void print() {
        Stu_node temp = head.next;
        if (head.next == null) {
            System.out.println("此链表为空");
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //原地反转
    public Stu_node reverse_list(Stu_node head) {
        if (head.next == null || head.next.next == null)
            return null;
        Stu_node dump = new Stu_node(-1, " "); //定义一个结点指向头结点
        dump.next = head;
        Stu_node prev = dump.next;
        Stu_node pcur = prev.next;
        while (pcur != null) { //从头结点的next开始反转
            prev.next = pcur.next; // 待移动结点的上一个结点指向待移动结点的下一个结点，此时上一个和待移动都指向下一个
            pcur.next = dump.next;  //将待移动结点指向定义的新结点的下一个结点
            dump.next = pcur;  //新结点指向待移动结点
            pcur = prev.next;  //完成一次移动后开始下一个结点移动
        }
        return dump.next;
    }

    //新建一个新的链表，头结点插入法实现链表的反转
    public Stu_node reverse_list1(Stu_node head) {
        Stu_node dump = new Stu_node(-1, " ");
        Stu_node pcur = head;
        while (pcur != null) {
            Stu_node pnext = pcur.next;
            pcur.next = dump.next;
            dump.next = pcur;
            pcur = pnext;
        }
        return dump.next;
    }

    //利用栈实现反转链表
    public Stu_node reverse_stack(Stu_node head) {
        Stack<Stu_node> stack = new Stack<>();
        Stu_node temp = head;
        //链表入栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //取出一个节点当做新的链表的头结点
        Stu_node new_head = stack.pop();
        Stu_node cur = new_head;
        //出站
        while (!stack.isEmpty()) {
            Stu_node node = stack.pop();
            //将出站的节点指向取消
            node.next = null;
            //将新的链表串起来
            cur.next = node;
            cur = node;
        }
        return new_head;
    }
}

//节点类
class Stu_node {
    int num;
    String name;
    Stu_node next;

    public Stu_node(int num, String name) {
        this.num = num;
        this.name = name;
    }

    //重写toString方法，显示节点数据
    @Override
    public String toString() {
        return "Stu_node{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}

