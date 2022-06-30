package mx.tc.j2se.evaluation;
import java.lang.Math.*;
import java.lang.IllegalArgumentException;

public class Circle {
    private int radius;
    private double area;

    public Circle () {
        radius = 1;
    }

    public Circle (int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Argument Invalid: circle must exist ;-) ");
        } else {
            radius = r;
        }
    }

    public void setRadius (int r) {
        if (r < 0) {
            throw new IllegalArgumentException("Argument Invalid: circle must exist ;-) ");
        } else {
            radius = r;
        }
    }

    public int getRadius () {
        return radius;
    }

    public double getArea() {
        area = Math.PI * radius * radius;
        return area;
    }
}

