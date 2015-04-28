package src;


import java.util.Hashtable;


public class SymbolTable extends Hashtable<String,TypeValue>{

    static SymbolTable globalTable;

    static {globalTable = new SymbolTable();}

    static void setValue(String id, TypeValue value){
        globalTable.put(id,value);
    }

    static TypeValue getValue(String id){
        return globalTable.get(id);
    }
}
