package mx.tc.j2se.evaluation;

public class Evaluation1 extends Circle {

    private Circle[] array;
    private int index;

    private int biggestCircle (Circle[] array) {
        int i;
        int big = array[0].getRadius();
        for (i=1; i < array.length; i++) {
            if (array[i].getRadius() > big) {
                big = array[i].getRadius();
                index = i;
            }
        }
    return index;
    }


    public void main() {
        array = new Circle[3];
        array [0] = new Circle(2);
        array [1] = new Circle(7);
        array [2] = new Circle(1);
        biggestCircle(array);

        System.out.println("Biggest Circle Index: " + biggestCircle(array));

        Circle circle1 = new Circle(-3);

    }



}
