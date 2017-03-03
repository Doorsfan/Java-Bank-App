/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Akvariet;

/**
 *
 * @author User
 */

//The Octupus Class, is merely an Object that inherits from Fish - It's meant to act and function as a 
//form of Fish that is an Octupus, which has a different constructor from Shark, for instance.

//We will also see later, in the Main class Akvariet, that there are some different operations as well
//in regards to how Octupus interacts.
public class Octupus extends Fish{
    private int Len_Beak;
    private int arms;
    public Octupus(String Species, String Speed, String Size, String Food, String name, int min_food, int max_food,
            int Len_Beak, int arms){
        super(Species, Speed, Size, Food, name, min_food, max_food);
        this.Len_Beak = Len_Beak;
        this.arms = arms;
    }
    
    @Override 
    public void Hunt(){
        System.out.println(this.name + ", the octupus, uses it's Beak to hunt " + this.Food + ".");
        System.out.println(this.name + " is a " + this.Size + " octupus, thus it eats " + this.min_food + " to " + this.max_food + " " + this.Food + " each day.");
        System.out.println();
        
        
    }
    
    @Override
    public void Swim(){
        
        System.out.println(this.name + ", the octupus,  uses it's " + arms + " arms to swim.");
        System.out.println("In case it feels threatened, it has a " + Len_Beak + " cm long beak to bite with!");
        
        System.out.println(this.name + ", happens to be a " + this.Speed + " Octupus, swimming at a " + this.Speed + " pace.");
        
        
        
    }
}
