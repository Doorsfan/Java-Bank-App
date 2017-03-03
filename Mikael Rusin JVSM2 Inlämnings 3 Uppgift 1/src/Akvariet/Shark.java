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

//The Shark Class, is merely an Object that inherits from Fish - It's meant to act and function as a 
//form of Fish that is a Shark, which has a different constructor from Octupus, for instance.

//We will also see later, in the Main class Akvariet, that there are some different operations as well
//in regards to how Shark interacts.

public class Shark extends Fish {
    private String colour;
    private int Teeth;
    //Species, Food, Speed, Size
    Shark(String Species, String Food, String Speed, String Size, String Name, int Teeth, int min_food, int max_food, String colour){
        //Species, Speed, Size, Food
        
        super(Species, Speed, Size, Food, Name, min_food, max_food);
        this.Teeth = Teeth;
        this.colour = colour;
    }
    
    @Override
    public void Swim(){
        
        System.out.println(this.name + ",the " + this.Species + " is swimming at a " + this.Speed + " pace.");
        
        
    }
    
    @Override
    public void Hunt(){
        System.out.println(this.name + " happens to be a " + this.Speed + " " + this.Species + " that is coloured " + this.colour + ".");
        System.out.println(this.name + " the " + this.Size + " " + this.Species + " usually sustains itself by eating about " + this.min_food + " to " + this.max_food + " " + this.Food + " per day.");
        
        System.out.println("This " + this.Species + " happens to have " + this.Teeth + " teeth.");
        System.out.println();
    
    }
    
    public void setName(String name){
        this.name = name;
    }
}
