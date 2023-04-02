package sentinel;

public class Client {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();

        // Access denied
        proxy.doSomething();

        proxy.setSentinel(true);

        // RealSubject do something
        proxy.doSomething();
    }
}

