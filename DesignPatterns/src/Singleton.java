public class Singleton {

    //饿汉式
    private static Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return instance;
    }


    //懒汉式
    private static Singleton instance2 = null;
    //private Singleton(){}
    public static Singleton getInstance2(){
        if(instance2 == null){
            instance2 = new Singleton();
        }
        return instance2;
    }

    //懒汉式（线程安全）
    private static Singleton instance3 = null;
    //private Singleton(){}
    public static synchronized Singleton getInstance3(){
        if(instance3 == null){
            instance3 = new Singleton();
        }
        return instance3;
    }

    //懒汉式（双重检查锁定）
    private static Singleton instance4 = null;
    //private Singleton(){}
    public static Singleton getInstance4(){
        if(instance4 == null){
            synchronized(Singleton.class){
                if(instance4 == null){
                    instance4 = new Singleton();
                }
            }
        }
        return instance4;
    }

    //静态内部类
    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }


    //private Singleton(){}
    public static final Singleton getInstance5(){
        return SingletonHolder.INSTANCE;
    }

    //枚举
    public enum SingletonEnum{
        INSTANCE;
        public void whateverMethod(){

        }
    }

    //测试

    public static void main(String[] args) {
        SingletonEnum.INSTANCE.whateverMethod();
    }

}
