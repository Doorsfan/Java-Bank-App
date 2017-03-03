/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikael.rusim.jvsm2.inlämning.pkg1;

import java.util.Scanner;
import java.lang.Math;

/**
 *
 * @author Micke
 */
public class MikaelRusimJVSM2Inlämning1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Drive(); //Make the function call to Drive
        
        orderBooks(); //Here, we order the books.
        
        //The following, is for the method call of Hypotenusan.
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ange längd för Katet A:");
        double first = sc.nextDouble();
        System.out.println();
        System.out.print("Ange längd för Katet B:");
        double second = sc.nextDouble();
        
        Hypotenusan(first, second);
          

           
        
    }
    
    public static void Drive(){
        Scanner sc = new Scanner(System.in); //We initialize the Scanner Object
        
        System.out.println("Ange medelhastighet <km/h> :"); //Instructions regarding Speed
        double speed = sc.nextDouble(); //We ask for a input that is the speed in Km/h
        
        System.out.println("Ange återstående körsträcka <mil>:");//Instructions regarding Length
        double length = sc.nextDouble(); //We ask for a input for the Length value
        
        double actualLength = length*10; //We convert the value into KM's
        
        
        double result = (actualLength/speed)*60; //This is the amount of time, it takes to travel
        //the length, displayed in Minutes. 
        
        
        
        
        
        int hours = (int)result/60;
        int minutes = (int)result % 60;
        
        System.out.println("Återstående körtid är: " + hours + " timme/timmar och " + minutes + " minuter.");
        
        
        
        //We then print out the result.
//        System.out.println("Återstående körtid är: " + hours + " timme/timmar och " + minutes + " minuter");  
        
    }
    
    public static void orderBooks(){
        Scanner sc = new Scanner(System.in);
        
        //Print out a Wholesome menu of all the things that the Customer needs to know
        System.out.println("Välkommen till Bokaffären.");
        System.out.println("Regel #1: Du måste beställa åtminstonde 1 bok!");
        System.out.println();
        System.out.println("Regel #2: Vi fraktar våra varor till er i kartonger! (Max 5 böcker per kartong!)");
        System.out.println("Avgiften är 8 kr/kartong, om det är färre än 6 Kartonger!");
        System.out.println("Om du beställer åtminstonde 6 kartonger (minst 26 böcker), kostar det 5 kr/Kartong!");
        System.out.println();
        System.out.println("Vi summerar detta och visar det åt dig under processen, så du behöver ej oroa dig!");
        System.out.println();
        System.out.println("Regel #3: Max 50 kartonger (250 böcker), kan ingå per beställning!");
        System.out.print("Hur många böcker vill du beställa? ");
        
        //Declare variables for usage in the Program
        int kartonger = 0; //The amount of Boxes
        int lastbox = 0; //This is a Special value, that differs how many there are in each box
        int books = 0; //The amount of books the person wishes to Order
        
        int pris = 0; //The price of each book, we put in the right value, later
        int sum = 0; //The total Sum to pay
        
        
        
        boolean book = false; //A boolean called book to use in the Loop when we ask for input from the User
        while(book == false){ 
            int input = sc.nextInt(); //The input
            if(input > 0 && input <= 250){ //At least 1 book, and max 250
                books = input; //If the amount of books input, is valid, we assign the value of the Input to Books.
                break;
            }
            else{ //Ask the user to try again, if they were out of boundry
                System.out.println();
                if(input <= 0){ //In case the user tried to use a negative or 0 input
                    System.out.println("Du måste beställa åtminstonde 1 bok!");
                }
                if(input > 250){ //If the user tried to go above the max input
                    System.out.println("Du kan beställa max 250 böcker per beställning!");
                }
                System.out.println("Var god och försök igen."); //Ask the user to try again
                System.out.println();
                System.out.print("Hur många böcker vill du beställa? ");
            }
        }
        
        for(int i = 0; i < books ; i++){ //Create a For loop where we sort how many boxes are going to be needed.
            if(i % 5 == 0){
                kartonger += 1; //Each time we end on a even amount, we create a new box.
            }
            if(((i + 1) == books) && ((i +1) % 5 != 0)){ //If we are about to hit the limit, and it's not a Even number, we create the Last box.
                lastbox = (i - ((kartonger-1)*5)) + 1; //The last box, always contains (AMOUNT OF BOOKS - (CARTONS-1)*5 + 1) amount of books.
            }
            
            
        }
        System.out.println(); //Print out some information for the User, so that it knows what is about to happen.
        System.out.println("Du vill beställa " + books + " böcker. Detta är " + kartonger + " kartonger.");
        System.out.println();
        if(lastbox > 0){ //In case we have something in the last box, we print it's content out along with the rest.
            System.out.println("Du har då: ");
            System.out.println((kartonger - 1) + " kartonger med 5 böcker i.");
            System.out.println("1 kartong med " + lastbox + " böcker i.");
        }
        if(lastbox == 0){ //In case we ended on a even note, and we never filled the last box, we need to just show the total amount of boxes.
            System.out.println("Du har då, " + kartonger + " kartonger, med 5 böcker i varje.");
        }
        
        
        if(kartonger > 5){ //If the ordered amount of boxes, is greater than 6, we give the user a Discount!
            pris = 5; //The price for each box, is set to 5.
        }
        if(kartonger <= 5){ //if the Order amount of boxes, is less than or equal to 5, we give no Discount.
            pris = 8; //The price for each box, is set to 8.
        }
        
        for(int i = kartonger; i > 0; i--){ 
            sum += pris; //We calculate the sum of the price for all the boxes.
        }
        
        System.out.println();
        if(books > 25){ //If the amount of books is greater than 25, we give the user a discount, and let them know.
            System.out.println("Eftersom du beställde mer än 5 kartonger, får du Rabbat!");
            System.out.println("Priset blir då 5 kr/kartong, och total priset för " + kartonger + " kartonger blir: " + sum + " kr.");
                               //We present the user with the amount of boxes, and the total price for said boxes.
        }
        if(books <= 25){ //If the amount of books is less than 25, we don't give a discount, and let the user know.
            System.out.println("Eftersom du beställde 5 eller färre kartonger, får du ingen Rabbat.");
            System.out.println("Priset blir då 8 kr/kartong, och total priset för " + kartonger + " kartonger blir: " + sum + " kr.");
                                //We present the user with the amount of boxes, and the total price for said boxes.
        }

    }
    public static double Hypotenusan(double a, double b){
        //We declare the variables in Double, for greater precision and flexibility
        double result = 0.0; //We declare a temporary value of Result
        double c = 0.0; //This is the value we will return
        
        result = (a * a) + (b * b); //We put the result of the two numbers Squared
        
        c = Math.sqrt(result); //This is the result that we pull Square Root out of.
        
        System.out.println("Hypotenusan för en Triangel med Katet A(" + a + ") och Katet B(" + b + ") är: " + c); //The length of the Hypotenuse
        return c; //We return the c value
        
    }
    
}
