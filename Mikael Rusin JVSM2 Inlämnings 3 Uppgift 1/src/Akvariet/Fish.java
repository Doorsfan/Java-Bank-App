/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Akvariet;

/**
 *
 * @author Micke
 */

//The Abstract Class Fish, is the Class that all of the other fish classes, inherit from.
//It contains some variables, a Constructor - and two abstract methods that are to be overwritten.

//They are as follows;

//Swim(); Which is for the swimming pattern of the Fish

//Hunt(); Which is for the eating/behaviour pattern, of the fish.
public abstract class Fish {
    protected String Species, Size, Food, name, Speed;
    protected int min_food, max_food;
    public Fish(String Species, String Speed, String Size, String Food, String name, int min_food, int max_food){ 
        this.Species = Species;
        this.Speed = Speed;
        this.Size = Size;
        this.Food = Food;
        this.name = name;
        this.min_food = min_food;
        this.max_food = max_food;
    }
    
    public abstract void Swim();
    
    public abstract void Hunt();
}
