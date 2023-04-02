public class Factory {

    //Shape interface
    public interface Shape {
        void draw();
    }

    //Circle class
    public static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    //Rectangle class
    public static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    //Square class
    public static class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }
        return null;
    }

    //Implement factory pattern
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Factory factory = new Factory();
        factory.getShape("circle").draw();
        factory.getShape("rectangle").draw();
        factory.getShape("square").draw();
    }

}
