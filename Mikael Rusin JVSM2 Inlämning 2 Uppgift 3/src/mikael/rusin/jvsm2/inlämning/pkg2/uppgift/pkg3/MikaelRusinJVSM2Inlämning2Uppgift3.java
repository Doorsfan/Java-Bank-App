/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikael.rusin.jvsm2.inlämning.pkg2.uppgift.pkg3;

/**
 *
 * @author Micke
 */
public class MikaelRusinJVSM2Inlämning2Uppgift3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] grades = new int[6]; //Create an array to hold the "Students"
        
        for(int i = 0; i < grades.length; i++){ //create a foor loop, so that we can handle each "student"
            String something = ""; //Put the start grade value of a person to 0, we will modify this later
            grades[i] = (int)(Math.random() * 10 + 1); //The grade for Student i is made on the fly, between 1 and 10
            for(int e = 0; e < grades[i]; e++){ //We count how much the person got in their grade, and add a star for each
                something += "*"; //Adding a star for each iteration of a grade
            }
            System.out.println("Student " + i + " has got " + something.length() + ":" + something); //The output of the students grade
            if(i <= 4){//Formatting, so that we do not print a final line past student 5
                System.out.println();
            }
            }
        }
    }
    
    
    

