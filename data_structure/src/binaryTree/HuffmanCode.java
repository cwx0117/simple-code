package binaryTree;

import java.util.*;

public class HuffmanCode {
    public static class Node implements Comparable<Node> {
        public Node right;
        public Node left;
        public Integer weight;
        public Byte data;

        public Node(Integer weight) {
            this.weight = weight;
        }

        public Node(Byte data, Integer weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (o.weight.equals(this.weight)) {
                return 0;
            }
            return this.weight - o.weight < 0 ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        String str = "Hello hello world love haha";
        System.out.println("原数据：" + str);
        byte[] zip = zip(str);
        System.out.println("压缩后：" + Arrays.toString(zip));
        String unzip = unzip(zip);
        System.out.println("解压后：" + unzip);
    }

    static Map<Byte, String> codeMap = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    //整合解压的方法
    public static String unzip(byte[] bytes) {
        String str = byteToStr(bytes);
        return decoding(str, codeMap);
    }

    //将二进制字符串解码
    public static String decoding(String hashCode, Map<Byte, String> codeMap) {
        //先将编码表的key value反过来，变成解码表
        Map<String, Byte> decodingMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            decodingMap.put(entry.getValue(), entry.getKey());
        }
        //存放每个符号的ASCII码值
        List<Byte> byteList = new ArrayList<>();
        //用来表示开始对比的首位1,或0的下标，循环中的i为subString截取的最后一个0、1，它后面一位的下标。
        int start = 0;
        for (int i = 0; i < hashCode.length(); i++) {
            //遍历传入的二进制hashCode字符串，与解码表对比，得到一个字符，就加入到集合中
            String substring = hashCode.substring(start, i);
            if (decodingMap.get(substring) != null) {
                //表示在解码表中有此字符
                byteList.add(decodingMap.get(substring));
                //让start=i，重新开始对比扫描
                start = i;
            } else if (i == hashCode.length() - 1) {
                //因为subString是左闭右开，在最后一段的时候会少截一个字符，所以需要判断一下，i是否到达最后一个字符
                substring = hashCode.substring(start);
                byteList.add(decodingMap.get(substring));
            }
        }
        //将集合中的byte取出，放入数组，方便转换成字符串
        byte[] decodingBytes = new byte[byteList.size()];
        for (int i = 0; i < decodingBytes.length; i++) {
            //遍历集合，放入数组
            decodingBytes[i] = byteList.get(i);
        }
        //将数组中的byte依照ASCII码表转换成字符串
        String decodingStr = new String(decodingBytes);
        return decodingStr;
    }

    //解码：1、将压缩的byte数组，变成二进制字符串
    public static String byteToStr(byte[] bytes) {
        String string;//表示将byte转换成的String字符串
        String substring;//因为负数转换成二进制补码的时候是32位，系统会自动用1补全前面的位数，所以需要截取字符串
        StringBuilder stringBuilder = new StringBuilder();//用来拼接转换后的字符串
        for (int i = 0; i < bytes.length; i++) {
            //如果是正数，会不足八位，需要用0填充，所以使用 按位或 256运算，256的二进制位100000000，后面是8个0.
            if (bytes[i] >= 0 && i < bytes.length - 1) {
                //string= Integer.toBinaryString(bytes[i]);//"11010"
                int temp = bytes[i] | 256;
                string = Integer.toBinaryString(temp);
                //按位或运算以后，是首位为1的9位二进制数，所以需要截取后八位
                substring = string.substring(string.length() - 8);
            } else if (bytes[i] >= 0 && i == bytes.length - 1) {
                //表示是最后一串正数二进制，在压缩的时候可能是不足八位的，所以无需补位。
                substring = Integer.toBinaryString(bytes[i]);
            } else {
                //表示是负数，截取后八位即可
                string = Integer.toBinaryString(bytes[i]);
                substring = string.substring(string.length() - 8);
            }
            //拼接截取后的字符串
            stringBuilder.append(substring);
        }
        return stringBuilder.toString();
    }


    //整合之前的方法
    public static byte[] zip(String str) {
        byte[] bytes = str.getBytes();
        List<Node> nodeList = nodeToList(bytes);
        Node root = creatHuffmanTree(nodeList);
        toHuffmanCodeMap(root, "", stringBuilder);
        byte[] zipCode = toHuffmanCodeByte(bytes, codeMap);
        return zipCode;
    }


    /**
     * 依照编码表 编码 原字符串转换而成的byte数组为二进制字符串
     * 并看做每八位为一补码，压缩其为一个byte数组
     *
     * @param bytes       需要压缩的字符串对应的byte数组
     * @param huffmanCode 根据每次字符出现的频率构建的哈夫曼编码表
     */
    public static byte[] toHuffmanCodeByte(byte[] bytes, Map<Byte, String> huffmanCode) {
        //将字符依照码表转换成二进制数并拼接字符串
        StringBuilder string = new StringBuilder();
        for (byte aByte : bytes) {
            string.append(huffmanCode.get(aByte));
        }
        //byte的数组长度，防止不能整除八的情况下数组长度也足够
        int len = (string.length() + 7) / 8;
        byte[] codeBytes = new byte[len];
        //统计该向codeBytes数组的哪个下标添加数据
        int index = 0;
        for (int i = 0; i < string.length(); i += 8) {
            String str;
            //防止越界，如果剩余长度不足8，则剩余的全部加入数组
            if (i + 8 > string.length()) {
                str = string.substring(i);
            } else {
                str = string.substring(i, i + 8);
            }
            //Integer.parseInt(str, 2),表示输出的是2进制数。
            codeBytes[index] = (byte) Integer.parseInt(str, 2);
            index++;
        }
        return codeBytes;

    }

    //获得哈夫曼编码表
    public static void toHuffmanCodeMap(Node node, String code, StringBuilder stringBuilder) {
        //将上一次递归拼接的字符串获取
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //继续此次拼接
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                //说明不是叶子节点，向左递归
                toHuffmanCodeMap(node.left, "0", stringBuilder2);
                //向右递归
                toHuffmanCodeMap(node.right, "1", stringBuilder2);
            } else {
                //没有左右子节点，说明是叶子节点
                //将上面递归拼接的路径 存入map中
                codeMap.put(node.data, String.valueOf(stringBuilder2));
            }
        }

    }

    //创建哈夫曼树
    public static Node creatHuffmanTree(List<Node> nodes) {
        //当集合中的节点只剩一个的时候，说明构建完成，退出循环
        while (nodes.size() > 1) {
            //首先升序排列
            Collections.sort(nodes);
            //取出最小和次小权值的两个节点，作为左子节点和右子节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //新建一个父节点，权值为左右子节点权值之和
            Node parentsNode = new Node(leftNode.weight + rightNode.weight);
            //将父节点的左右子节点设置为上面的两个左右节点
            parentsNode.left = leftNode;
            parentsNode.right = rightNode;

            //将左右子节点从集合中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将父节点加入集合，在下次循环的时候会重新排序
            nodes.add(parentsNode);
        }
        return nodes.get(0);
    }

    //将每个字符和出现的频率作为数据、权值创建节点并储存入集合中，方便创建哈夫曼树
    public static List<Node> nodeToList(byte[] bytes) {
        //定义存储字符、频次的map集合
        Map<Byte, Integer> hashMap = new HashMap<>();
        //遍历集合
        for (byte aByte : bytes) {
            //判断此字符是否为第一次出现
            //第一次出现
            //不是第一次出现
            hashMap.merge(aByte, 1, Integer::sum);
        }
        //遍历map集合，将其封装成节点存入集合
        List<Node> nodeList = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodeList.add(node);
        }
        return nodeList;
    }
}
