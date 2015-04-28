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
public class OneArg {
    private Aexp arg;

  
    OneArg(Aexp x){
        arg = x;
    }

      public Aexp getArg() {
        return arg;
    }

    public void setArg(Aexp arg) {
        this.arg = arg;
    }
    
}
