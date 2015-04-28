/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author manish
 */
public class ErrorType {
    
    static void sem_error(String context, String message) 
    {
       System.err.println("Semantic Error:"+ "<"+context+">" + message);
       System.exit(1);
    }
    
    static void type_mismatch_error(String operator) 
    {
       System.err.println("Type Mismatch Error: Type mismatch at "+ operator +" operator!");
       System.exit(1);
    }
    
    static void type_error (String context,String message)
    {
        System.err.println("Type Error:"+ "<"+context+">" + message);
        System.exit(1);
    }
    
    
}
