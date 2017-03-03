/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Akvariet;

//The SeaWeed Class, is merelly a Small object class - It's meant to act and function as a 
//form of decoration - It has very few attributes and very few methods, in regards to what it can do.

//In general, it acts as a "filling" agent, in terms of the Aquarium.
public class SeaWeed {
    private String colour;
    private int length;
    
    public SeaWeed(String colour, int length){
        this.colour = colour;
        this.length = length;
    }
    
    public String getColour(){
        return colour;
    }
    
    public int getLength(){
        return length;
    }
}
