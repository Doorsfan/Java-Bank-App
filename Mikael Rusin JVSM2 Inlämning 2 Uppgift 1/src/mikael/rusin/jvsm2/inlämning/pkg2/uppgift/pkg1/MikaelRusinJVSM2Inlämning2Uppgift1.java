/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikael.rusin.jvsm2.inlämning.pkg2.uppgift.pkg1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Micke
 */
public class MikaelRusinJVSM2Inlämning2Uppgift1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        System.out.println("Hi! Please provide the following information: ");
        System.out.print("Your first name, please: ");
        
        //Ask for the input from the user, of their First Name.
        Scanner sc = new Scanner(System.in);
        
        String name = sc.next();
        
        //Ask for the input from the user, of their last name.

        System.out.print("Your last name, please: ");
        
        String last_name = sc.next();
        
        //Ask for the input from the user, of their Year of Birth.
        
        System.out.print("Your year of Birth, please: ");
        
        int year = sc.nextInt();
        
        
        //We don't need to ask the user for the current year, when we can just
        //retrieve it.
        
        //We access the Current year, by getting an instance of a a Calendar from the Calendar class
        //And then retrieve the Year from the calendar instance, with a inheret get method.
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);   
        
        System.out.println();
        
        //We instansiate the Object, with the parameters that are needed.

        HeartRates thing = new HeartRates(name, last_name, year, currentYear);
        
        int age = thing.calculateYear(); //calculate the year of the person, through the objects calculateYear method.
        
        String name_out = thing.getFirstName();
        System.out.println("Your first name is: " + name_out + ".");
        
        name_out = thing.getLastName(); //Since we already have a container for name, and the value has been used,
        //we might as well re-use the same container.
        System.out.println("Your last name is: " + name_out + ".");
        
        System.out.println("Your age is : " + age + " year/years old!"); //print out the Age of the object.
        
        int MaxRate = thing.calculateMaxHeartrate(); //Retrieve the absolute max value
        
        System.out.println("Your maximum heart rate is : " + MaxRate + "."); //Print out the aboslute max value
        
        int minRecommended = thing.calculateMinRangeHeart(); //Assign recommended minimum value
        
        int maxRecommended = thing.calculateMaxRangeHeart(); //Assign recommended maximum value
        
        System.out.println("Your recommended heart range, is : " + minRecommended + " to " + maxRecommended + "."); //Print out the values.
        
   
        
    }
    
}

class HeartRates{
    //First, we declare the private variables, that will be held within HeartRates.
    //These act as "Containers", until the point of where we can put Data into them.
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private int currentYear;
    
    
    public HeartRates( String input_firstName, String input_lastName, int input_yearOfBirth, int input_currentYear){
        
        //This is the constructor of the HeartRates class, that allows us to initialize it with attributes.
        
        firstName = input_firstName;
        lastName = input_lastName;
        yearOfBirth = input_yearOfBirth;
        currentYear = input_currentYear;
         
    }
    
    
    public void setFirstName(String firstName){ //a Set function to be able to assign the name of the Object.
        this.firstName = firstName;
    }
    
    public String getFirstName(){ //A get function, to find the first name of the Object.
        return firstName;
    }
    
    public void setLastName(String lastName){ //a Set function to be able to assign the name of the Object.
        this.lastName = lastName;
    }
    
    public String getLastName(){ //A get function to return the last name of the Object.
        return lastName;
    }
    
    public void setYearOfBirth(int yearOfBirth){ //a Set function to be able to assign the name of the Object.
        this.yearOfBirth = yearOfBirth;
    }
    
    public int getYearOfBirth(){ //a Get function to be able to return the Year of the Object.
        return yearOfBirth;
    }
    
    
    public void setCurrentYear(int currentYear){ //a Set function to be able to assign the Current year of the Object.
        this.currentYear = currentYear;
    }
    
    public int getCurrentYear(){ //a Get function to be able to return the Current year of the Object.
        return currentYear;
    }
    
    
    public int calculateYear(){
        return (currentYear - yearOfBirth); //calculating how old the Object is, based on what year it is - year of birth.
    }
    
    public int calculateMaxHeartrate(){
        return (220 - (currentYear - yearOfBirth)); //A simple calculation of finding the "Max Heart Rate", overall.
    }
    
    public int calculateMinRangeHeart(){
        
        //We calculate the minimum of the recommended range, where of we round of to the closest
        //integer.
        double toCount = ((220 - (currentYear - yearOfBirth)) * 0.5); //First we implicitly change the int to Double.
        double rest = (toCount % 1); //The remainder that is past the . point
        if(rest >= 0.5){ //if the remainder is .5 or greater, we know that we should round up
            
            return (int)toCount + 1; //We just let Java implicitly convert for us, and add 1, if the remainder is equal to or larger than 0.5
        }
        else{
            return (int)toCount; //otherwise, Java rounds down for us, automatically
        }
    }
    
    public int calculateMaxRangeHeart(){
        double toCount = ((220 - (currentYear - yearOfBirth)) * 0.85); //First we implicitly change the int to Double.
        double rest = (toCount % 1); //The remainder that is past the . point
        if(rest >= 0.5){ //if the remainder is .5 or greater, we know that we should round up
            
            
            return (int) toCount + 1; //We just let Java implicitly convert for us, and add 1, if the remainder is equal to or larger than 0.5
        }
        else{
            return (int) toCount; //otherwise, Java rounds down for us, automatically
        }
    }
    
}

