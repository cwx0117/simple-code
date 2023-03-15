import java.io.*;

public class IOUtils {

    /**
     * 字符流
     * @param path
     * @param content
     */
    public static void f1(String path,String content){
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(path);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Hello, world!");
            bufferedWriter.newLine();
            bufferedWriter.write("This is a new line.");
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 字节流
     * @param target
     * @param source
     */
    public static void copy(String target,String source){
        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            FileOutputStream fileOutputStream = new FileOutputStream(target);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 数据流
     */
    public static void f3(){
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("path/to/data.bin"));

            dataOutputStream.writeInt(123);
            dataOutputStream.writeDouble(3.14);
            dataOutputStream.writeUTF("Hello, world!");

            dataOutputStream.close();

            DataInputStream dataInputStream = new DataInputStream(new FileInputStream("path/to/data.bin"));

            int intData = dataInputStream.readInt();
            double doubleData = dataInputStream.readDouble();
            String stringData = dataInputStream.readUTF();

            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Person{
        private String name;
        private int age;

        private Person(String name,int age){
            this.name=name;
            this.age=age;
        }
    }

    /**
     * 对象流
     */
    public static void f4(){
        // 序列化
        Person person = new Person("John", 25);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("path/to/person.ser"));
            objectOutputStream.writeObject(person);
            objectOutputStream.close();

            // 反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("path/to/person.ser"));

            Person deserializedPerson = (Person) objectInputStream.readObject();

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 管道流
     */
    public static void f5(){
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        byte[] buffer = new byte[0];
        int bytesRead = 0;
        try {
            PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

            new Thread(() -> {
                try {
                    pipedOutputStream.write("Hello, world!".getBytes());
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


//            new Thread(() -> {
//                try {
//                    byte[] buffer0 = new byte[1024];
//                    int bytesRead0 = pipedInputStream.read(buffer0);
//                    System.out.println(new String(buffer0, 0, bytesRead0));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();

            buffer = new byte[1024];
            bytesRead = pipedInputStream.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(new String(buffer, 0, bytesRead));

    }

    public static void main(String[] args) {
        f5();
    }
}
