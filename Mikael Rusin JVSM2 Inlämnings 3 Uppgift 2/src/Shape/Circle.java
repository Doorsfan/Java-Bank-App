package Shape;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Micke
 */
public class Circle extends Shape {
    private double radius;
    public Circle(double x, double y, double radius) { 
        super(x, y); //use the Superclasses constructor to inherit values of x and y
        this.radius = radius; //put in a new radius to Circle
    }
    
    @Override
    public String toString(){
        return "X:" + x + ", Y:" + y + ", Area: " + Area(radius); //Display the Co-ordinates and the radius
    }
    
    public double Area(double radius){
        return (Math.PI * (radius * radius)); //calculate and return the Area
    }
    
}
