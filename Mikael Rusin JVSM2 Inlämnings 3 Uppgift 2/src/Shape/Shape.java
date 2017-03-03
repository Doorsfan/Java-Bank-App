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
public class Shape {
    protected double x,y; //X and Y co-ordinates
    public static void main(String[] args) {
        //No need to have anything in Main, unless creating objects to Display
    }
    
    public Shape(double x, double y){
        this.x = x; //constructor
        this.y = y;
    }
    
    @Override
    public String toString(){ //overriding the toString
        return "X:" + x + ", Y:" + y;
    }
}
