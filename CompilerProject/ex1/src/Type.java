/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.HashMap;

/**
 *
 * @author manish
 */
public class Type {
    
    public static final int INTEGER = 1;
    public static final int FLOATING = 2;
    public static final int BOOLEAN = 3;
    
    int tag;
    String code;
    static HashMap<String, Type> types = new HashMap<String, Type>();
    
    public Type() {
        types.put(INTEGER + "", new Type(INTEGER, "int"));
        types.put(FLOATING + "", new Type(FLOATING, "float"));
        types.put(BOOLEAN + "", new Type(BOOLEAN, "bool"));
    }
    
    public Type(int t, String c) {
        tag = t;
        code = c;
    }
    
    public String getCode() {
        return code;
    }

    public static Type integer() {
        return (Type) types.get("" + INTEGER);
    }

    public boolean isInteger() {
        if (tag == INTEGER) {
            return true;
        } else {
            return false;
        }
    }

    public static Type floating() {
        return (Type) types.get("" + FLOATING);
    }

    public boolean isFloat() {
        if (tag == FLOATING) {
            return true;
        } else {
            return false;
        }
    }

    public static Type bool() {
        return (Type) types.get("" + BOOLEAN);
    }

    public boolean isBool() {
        if (tag == BOOLEAN) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean equals(Type t1, Type t2){
        boolean result = false;
        
        if (t1.getCode().equals(t2.getCode())){
            result = true;
        }
        
        return result;     
        
        
    }
    
}
