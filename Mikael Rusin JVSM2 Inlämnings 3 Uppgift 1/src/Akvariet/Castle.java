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

//The Castle Class, is merelly a Small object class - It's meant to act and function as a 
//form of decoration - It has very few attributes and very few methods, in regards to what it can do.

//In general, it acts as a "filling" agent, in terms of the Aquarium.
public class Castle {
    private String name;
    private String detail;
    
    
    public Castle(String name, String detail){
        this.name = name;
        this.detail = detail;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDetail(){
        return detail;
    }
    
    
    
}
