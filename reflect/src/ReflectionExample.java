import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {

    @MyAnnotation(value = "hello")
    private String message;

    public ReflectionExample(String message) {
        this.message = message;
    }

    @MyAnnotation(value = "world")
    public void printMessage() {
        System.out.println(message);
    }

    public static void main(String[] args) throws Exception {
//        ReflectionExample obj = new ReflectionExample();
//
//        // 获取类上的注解信息
//        Class<?> clazz = obj.getClass();
//        Annotation[] annotations = clazz.getAnnotations();
//        for (Annotation annotation : annotations) {
//            if (annotation instanceof MyAnnotation) {
//                MyAnnotation myAnnotation = (MyAnnotation) annotation;
//                System.out.println("类上的注解信息：" + myAnnotation.value());
//            }
//        }
//
//        // 获取字段上的注解信息
//        Field field = clazz.getDeclaredField("message");
//        field.setAccessible(true);
//        Annotation[] fieldAnnotations = field.getAnnotations();
//        for (Annotation annotation : fieldAnnotations) {
//            if (annotation instanceof MyAnnotation) {
//                MyAnnotation myAnnotation = (MyAnnotation) annotation;
//                System.out.println("字段上的注解信息：" + myAnnotation.value());
//            }
//        }
//
//        // 获取方法上的注解信息
//        Method method = clazz.getDeclaredMethod("printMessage");
//        Annotation[] methodAnnotations = method.getAnnotations();
//        for (Annotation annotation : methodAnnotations) {
//            if (annotation instanceof MyAnnotation) {
//                MyAnnotation myAnnotation = (MyAnnotation) annotation;
//                System.out.println("方法上的注解信息：" + myAnnotation.value());
//            }
//        }
//
//        // 调用方法
//        method.invoke(obj);


        // 获取构造函数
        Constructor<?> constructor = ReflectionExample.class.getConstructor(String.class);

        // 创建对象
        ReflectionExample obj = (ReflectionExample) constructor.newInstance("Hello, world!");

        // 调用方法
        Method method = ReflectionExample.class.getMethod("printMessage");
        method.invoke(obj);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value();
}

