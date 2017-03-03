/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

import javafx.scene.control.TextField;

/**
 *
 * @author Micke
 */
public class capitalize {

    public static String capitalize(String input){
        String to_return = "";
        int count = 0;
        
        if(!input.equals("")){
            String[] parts = new String[2];
            try{
                parts = input.split(" ");
          

                for(int i = 0; i < parts.length ; i++){
                    parts[i] = parts[i].toLowerCase();
                    
                to_return += Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1);
                if(i < 1){
                    to_return += " ";
                }
                count += 1;
                
            }
                if(count == 1 || count > 2){
                    return " ";
                }
            }
            catch(Exception e){
                return to_return;
            }
            
            
        }
        
        
        
    return to_return;
    }
    
    public static String Sortera(TextField box1){
        
            
            String toUse = box1.getText();
            toUse = toUse.replaceAll("[^a-zA-Z -]", "");
            
           
            toUse = toUse.trim().replaceAll(" +", " ");
       
            return toUse;

    }
    
    public static String SorteraNummer(TextField box2){
        String toUse = box2.getText();
        toUse = toUse.replaceAll("[^0-9]", "");
        
        toUse = toUse.trim().replaceAll(" +", "");
        return toUse;
    }
    
  

}
