/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shape;

/**
 *
 * @author Micke
 */
public class Cylinder extends Shape {
    private double radius, height;
    public Cylinder(double x, double y, double radius, double height){ //Constructor with radius and height as well
        super(x, y); //Inherit x and y from Shape
        this.radius = radius;
        
        this.height = height;
    }
    
    @Override
    public String toString(){
        return "X: " + x + ", Y: " + y + ", Area: " + Area() + ", Volume: " + Volume();
    } //Display the Area, volume, X and Y
    
    public double Area(){ //Method for Area
        return (2 * Math.PI * (radius * radius) + (2 * Math.PI * radius * height));
    }
    
    public double Volume(){ //Method for Volume
        return (Math.PI * (radius * radius) * height);
    }
}
