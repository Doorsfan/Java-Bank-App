/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.projekt;

/**
 *
 * @author Micke
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.Scanner;

public class CreateTextFile
{
	private static Formatter output;

	

	public static void openFile(String file, File file2, String toPutIn, boolean override)
	{
            
            
            boolean text = false;
            Scanner input = new Scanner(System.in);
            
            
            
                if(override){ //We want to override the File
                    if(file2.exists() && !file2.isDirectory()) { //Check to see if the file is null or not
                        
                
                {
                    try (
                            
                PrintStream out = new PrintStream(new FileOutputStream(file))) {out.print(toPutIn);} //Override occurs by default
                catch(SecurityException securityException) //SecurityException is the type of Exception that occurs when we try to write to a File that is either protected or unaccessible
	    {
                    
            System.err.println("Writer permission: Denied. Terminating."); //The File is Protected from writing access
            System.exit(1); //Terminates due to input of 1, denoting error occured
	    }
          
	    catch(FileNotFoundException fileNotFoundException) //Could not find the specified File
                
	    {
                
	        System.err.println("404, could not find the file. Terminating.");
	        System.exit(1);//Terminates due to input of 1, denoting error occured
	    }
            catch(Exception e){ //Security measure
                
                System.err.println("Something went wrong - please check the output you are trying to write to.");
                System.exit(1);//Terminates with input of 1, denoting error occured
            }
                
            }
            
                     
                    String toPrint = MainpageController.special.replaceAll("\\\\\\\\", "\\\\"); //Convert back so that the \\ become \ for display
                     //Message that writing was succesful to Directory
            }
                
                }
                else{ //If we do not wan to override
                    if(file2.exists() && !file2.isDirectory()) { 

                {
                    try (
                PrintStream out = new PrintStream(new FileOutputStream(file, true)) //Runs the printStream but with Append enabled
                            
                            ) {out.print(toPutIn);
                    }
                catch(SecurityException securityException) //SecurityException is the type of Exception that occurs when we try to write to a File that is either protected or unaccessible
	    {
          
            System.err.println("Writer permission: Denied. Terminating."); //Access Denied
            System.exit(1); //Terminates with input of 1, denoting Error
	    }
          
	    catch(FileNotFoundException fileNotFoundException)
	    {
                
	        System.err.println("404, could not find the file. Terminating.");
	        System.exit(1);//Terminates with input of 1, denoting Error
	    }
            catch(Exception e){
                
                System.err.println("Something went wrong - please check the output you are trying to write to.");
                System.exit(1);//Terminates with input of 1, denoting Error
            }
                 
            }  
                
               
                    String toPrint = MainpageController.special.replaceAll("\\\\\\\\", "\\\\"); //Convert back so that the \\ become \ for display
                    
          
            }
                }                
            }
    
}



