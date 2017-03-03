/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Akvariet;


import java.util.InputMismatchException;
import java.util.Scanner;

//In the main Class Akvariet, is where we will create the Objects of the Program.
//We will also do some forms of Input handling and method calling, in terms of creating
//the objects.
public class Akvariet {
    protected static int Amount_Sharks;
    protected static String[] Name_Sharks;
    protected static String[] Name_Octipi;
    protected static int Amount_Octipi;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        start_aquarium(); //Seperated a Method that is responsible for the larger interactions
        
        //The rest of the Main method, is for construction of the SeaWeed class and the Castle class
        //Which merely is that of getting details from the Castle object and Seaweed object.
        
        Castle castle = new Castle("Small Castle", "Black Tower");
        SeaWeed plankton = new SeaWeed("Black", 5);
        
        String name = castle.getName();
        String detail = castle.getDetail();
        
        String colour = plankton.getColour();
        int length = plankton.getLength();
        
        System.out.println("There's also a " + name + ", that has a " + detail + "!");
        
        System.out.println("Around the " + name + " with a " + detail + ", There is some Seaweed!");
        System.out.println("The seaweed is " + colour +  " and about " + length + " cm long!");
      
       
    }
    
    public static Fish Sharks(String[] array, int count, String input){
        
        //Sharks is a Method that acts as a "Factory" of creating Objects, both Octupus and Shark.
        
        //The main idea, is that based on the input of the String input, we differ the construction of the object.
        
        //We return the type Fish, to be able to convert later on, into the subclass we wish to have/need.
        
        //Thus, the method can handle both Shark construction and Octupus construction.
        
        String species = "";
        String food = "";
        String speed = "";
        String size = "";       
        String name = "derp";
        
        int min_food = 0;
        int max_food = 0;
        
        String colour = "";
        int teeth = 0;
        
        int Len_Beak = 0;
        int arms = 0;
        
        
        //A loop that has 2 switch cases and a randomness factor to it.
        //The randomness factor of temp, allows us to assign random attributes to
        //the different fish, so that it varies from time to time. 
        
        
        for(int i = 0; i < 8; i++){
            int temp = (int)(Math.random() * 3 + 1);
            
            switch(i){ //The first Switch, which is based on i, is for what attribute we are currently handling.
                case 0:
                    
                    
                    switch(temp){ //Temp, is what random element we are handling.
                        
                        case 1:
                            if(input.equals("Sharks")){ //These Input checks, are to see if it's a Shark or a Octupus that we are dealing with.
                                species = "Tiger Shark";
                            }
                            else{
                                species = "Reef Octupus";
                            }
                            break;
                        case 2:
                            if(input.equals("Sharks")){
                                species = "White Shark";
                            }
                            else{
                                species = "Kraken";
                            }
                            break;
                        case 3:
                            if(input.equals("Sharks")){
                                species = "Basking Shark";
                            }
                            else{
                                species = "Giant Squid";
                            }
                            break;
                    }
                    break;
                case 1:
                    switch(temp){
                        case 1:
                            food = "Smaller Fish"; //Some attributes are shared between the two, and need not be distinguished.
                            break;
                        case 2:
                            food = "Larger Fish";
                            break;
                        case 3:
                            if(input.equals("Sharks")){
                            food = "Tons of Plankton";
                            }
                            else{
                                food = "SeaWeed";
                            }
                            break;
                    }
                    break;
                case 2:
                    switch(temp){
                        case 1:
                            speed = "Fast";
                            break;
                        case 2:
                            speed = "Slow";
                            break;
                        case 3:
                            speed = "Very Slow";
                            break;
                    }
                    break;
                case 3:
                    switch(temp){
                        case 1:
                            size = "Small";
                            break;
                        case 2:
                            size = "Large";
                            break;
                        case 3:
                            size = "Huge";
                            break;
                        
                    }
                    break;
                case 4:
                    switch(temp){
                        case 1:
                            if(input.equals("Sharks")){
                            teeth = 100;
                            }
                            else{
                                arms = 3;
                            }
                            break;
                        case 2:
                            if(input.equals("Sharks")){
                            teeth = 2000;
                            }
                            else{
                                arms = 5;
                            }
                            break;
                        case 3:
                            if(input.equals("Sharks")){
                            teeth = 500;
                            }
                            else{
                                arms = 7;
                            }
                            break;
                    }
                    break;
                case 5:
                    switch(temp){
                        case 1:
                            min_food = 10;
                            break;
                        case 2:
                            min_food = 20;
                            break;
                        case 3:
                            min_food = 5;
                            break;
                    }
                    break;
                case 6:
                    switch(temp){
                        case 1:
                            max_food = 30;
                            break;
                        case 2:
                            max_food = 35;
                            break;
                        case 3:
                            max_food = 40;
                            break;
                    }
                    break;
                case 7:
                    switch(temp){
                        case 1:
                            if(input.equals("Sharks")){
                            colour = "Brown Speckeled";
                            }
                            else{
                                Len_Beak = 3;
                            }
                            break;
                        case 2:
                            if(input.equals("Sharks")){
                            colour = "White";
                            }
                            else{
                                Len_Beak = 5;
                            }
                            break;
                        case 3:
                            if(input.equals("Sharks")){
                            colour = "Dark Blue";
                            }
                            else{
                                Len_Beak = 8;
                            }
                            break;
                    }
                    break;
            
            }
        }
        name = array[count]; //we retrieve the designated name of the Object from the name array that we passed in.
        //Count is to assert what object we are handling.
        if(input.equals("Sharks")){ //If it's a Shark, make a Shark.
            Shark sharks = new Shark(species, food, speed, size, name, teeth, min_food, max_food, colour);
            return sharks;
        }
        else{ //Otherwise, it's an octupus - Make a octupus.
            Octupus octupi = new Octupus(species, speed, size, food, name, min_food, max_food, Len_Beak, arms);
            return octupi;
        }
   
        
       
        
    }
    
    public static void start_aquarium(){ //The meat of the program.
        System.err.println("Welcome to the Aquarium!");
        
        Scanner sc = new Scanner(System.in);
        
        String temp = "N/A";
        
        
        boolean flag = false;
        while(!flag){ //Loop for Input
            try{
                System.err.println("How many sharks would you like to have in the Aquarium?");
                Amount_Sharks = sc.nextInt();
                if(Amount_Sharks <= 0){ //Must have at least 1 Shark
                    throw new Exception();
                }
                Name_Sharks = new String[Amount_Sharks]; //create an Array for the name of the Sharks, based on the size of the Input.
                System.out.println();
                
                flag = true;
            }
            catch (InputMismatchException e){ //In case the User tried to write a String value, when asked for a Amount
                
               
                System.err.println("That is not a valid input, please try again.");
                
                sc.nextLine();
            }
            catch(Exception e){ //The exception of us having recieved less than 1 Shark
                
                System.err.println("You must have at least one Shark, in the aquarium!");
            }
        }
        flag = false;
        while(!flag){ //Repeat the process, but with Octupus
            try{
                System.err.println("And how many Octipi would you like to have?");
                Amount_Octipi = sc.nextInt();
                if(Amount_Octipi <= 0){
                    throw new Exception(); //if we recieved 0 or less octipi
                }
                Name_Octipi = new String[Amount_Octipi]; //Names of the Octipi
                System.out.println();
                flag = true;
            }
            catch (InputMismatchException e){ //Case of String input
                
               
                System.err.println("That is not a valid input, please try again.");
                
                sc.nextLine();
            }
            catch(Exception e){ //Case of  <= 0 input
                
                System.err.println("You must have at least 1 Octupus, in this Aquarium!");
               
                sc.nextLine();
            }
        }
        
        Shark[] returned_sharks = new Shark[Amount_Sharks]; //An array to hold the Shark objects
        Octupus[] returned_octipi = new Octupus[Amount_Octipi]; //An array to hold the Octupus objects
        for(int i = 0; i < Name_Sharks.length; i++){
            Name_Sharks[i] = "<UNDECIDED>"; //Fill the Name array of Shark with a temporary value
        }
        for(int i = 0; i < Name_Octipi.length; i++){
            Name_Octipi[i] = "<UNDECIDED>"; //Fill the name Array of Octupus with a temporary value
        }
        
        if(Amount_Sharks > 1){ //Formatting and providing info of that Input that is empty, will not be accepted
            System.err.println("Please provide a name for the " + Amount_Sharks + " Sharks:");
            System.err.println("Keep in mind, you cannot enter a Empty name for the animals.");
        }
        else{
            System.err.println("Please provide a name for the Shark:");
            System.err.println("Keep in mind, you cannot enter a Empty name for the animals.");
        }
        
        int count = 0;
        flag = false;
        while(!flag){ //input loop
            try{
                
                
                System.out.print("So far you have these names: ");
                    
                
                Name_Sharks[count] = "<Currently Deciding>"; //Formatting and presenting what name is currently being decided
                for(int i = 0; i < Name_Sharks.length ; i++){
                    if(!(Name_Sharks[i]).equals("<UNDECIDED>")){
                        
                        
                        if(i == 0){
                            System.out.print(Name_Sharks[i]);
                        }
                        else{
                            System.out.print("," + Name_Sharks[i]);
                        }
                        
                    }
                    
                }
                System.out.println();
                
                
                System.out.print("I want to name this Shark: ");
                
                temp = sc.next();
                
                
                
                
                if(temp.length() > 0){ //Only if the input is not empty, do we accept it as an actual input and assign it
                Name_Sharks[count] = temp;
                System.out.println();
                count += 1;
                }
              
                
                if(count == Name_Sharks.length){ //if count is the length of the array, we have the amount of names we need, break the loop
                    flag = true;
                }
                
                
                
            }
            catch (Exception e){ //safety measure
                System.out.println("That is not a valid name of a Shark, please try again.");
                sc.nextLine();
                
            }
        }
        
        
        
        
        if(Amount_Octipi > 1){ //Rinse and repeat for Octupus
            System.err.println("Please provide a name for the " + Amount_Octipi + " Octipi:");
            System.err.println("Keep in mind, you cannot enter a Empty name for the animals.");
        }
        else{
            System.err.println("Please provide a name for the Octupus:");
            System.err.println("Keep in mind, you cannot enter a Empty name for the animals.");
        }
        flag = false;
        count = 0;
        while(!flag){ //input loop
            try{
                
                
                System.out.print("So far you have these names: ");
                    
                
                Name_Octipi[count] = "<Currently Deciding>";
                for(int i = 0; i < Name_Octipi.length ; i++){ //Formatting and presentation
                    if(!(Name_Octipi[i]).equals("<UNDECIDED>")){
                        
                        
                        if(i == 0){
                            System.out.print(Name_Octipi[i]);
                        }
                        else{
                            System.out.print("," + Name_Octipi[i]);
                        }
                        
                    }
                    
                }
                System.out.println();
                
                
                System.out.print("I want to name this Octupus: ");
                temp = sc.next(); //ask for input
                
                if(temp.length() > 0){ //Only if the input is not empty, do we accept it
                    Name_Octipi[count] = temp;
                    System.out.println();
                    count += 1;
                }
                
                if(count == Name_Octipi.length){ //We have the amount of names we need
                    flag = true;
                }
                
                
                
            }
            catch (Exception e){ //SAfety measure
                System.out.println("That is not a valid name of a Octupus, please try again.");
                sc.nextLine();
                
            }
        }
        
        
        
        for(int i = 0; i < Name_Sharks.length; i++){ //Run a for loop to feed input to constructing the Shark objects
            returned_sharks[i] = (Shark) Sharks(Name_Sharks, i, "Sharks"); //Type cast the objects to Shark, since it returns Fish objects
            
        }
        
        for(Shark e : returned_sharks){ //Run a for each loop, on the respective object and run methods for them
            e.Swim();
            e.Hunt();
        }
        
        for(int i = 0; i < Name_Octipi.length; i++){
            returned_octipi[i] = (Octupus) Sharks(Name_Octipi, i, "N/A"); //Type cast to Octupus, since it returns fish objects
        }
        
        for(Octupus e : returned_octipi){ //Run a for each loop, on the respective object and run methods for them
            e.Swim();
            e.Hunt();
        }
    }
    
    
}



