package sentinel;

/**
 * 在这个代理类中，通过添加一个 boolean 类型的哨兵 sentinel，控制对象的访问权限。
 * 如果哨兵为 true，则允许访问；如果哨兵为 false，则拒绝访问，
 * 并打印 Access denied 的错误信息。
 */
public class Proxy implements Subject {
    private RealSubject realSubject;
    private boolean sentinel;

    public Proxy() {
        this.realSubject = new RealSubject();
        this.sentinel = false;
    }

    public void doSomething() {
        if (sentinel) {
            realSubject.doSomething();
        } else {
            System.out.println("Access denied");
        }
    }

    public void setSentinel(boolean sentinel) {
        this.sentinel = sentinel;
    }
}

