package src;

public class Aexp {

    private boolean[] tag = new boolean[6];
    private Integer INTEGER;
    private Float FLOAT;
    private String ID;
    private Boolean BOOL;
    private Args Operands;
    private OneArg OneOperand;
    private int Operator;
    private Type type = new Type();

    public Type getType() {
        return type;
    }

    Aexp(Integer x) {

        int i;
        for (i = 0; i <= 5; i++) {
            if (i == 0) {
                tag[i] = true;
            } else {
                tag[i] = false;
            }
        }
        INTEGER = x;
        type = Type.integer();
    }
    
    Aexp(Float x) {

        int i;
        for (i = 0; i <= 5; i++) {
            if (i == 1) {
                tag[i] = true;
            } else {
                tag[i] = false;
            }
        }
        FLOAT = x;
        type = Type.floating();
    }
     
    Aexp(String x) {

        int i;

        for (i = 0; i <=5; i++) {
            if (i == 2) {
                tag[i] = true;
            } else {
                tag[i] = false;
            }
        }
        if (SymbolTable.globalTable.get(x).getType().isInteger())
        {
            type = Type.integer();
        } 
        else if (SymbolTable.globalTable.get(x).getType().isFloat())
        {
            type = Type.floating();
        } 
        else if (SymbolTable.globalTable.get(x).getType().isBool())
        {
            type = Type.bool();
        } 
        ID = x;
    }
    
    Aexp(Boolean x)
    {
        int i;
        for (i = 0; i <= 5; i++)
        {
            if (i == 3)
            {
                tag[i] = true;
            } else
            {
                tag[i] = false;
            }
        }
        type = Type.bool();
        this.BOOL = x;
    }
    
    Aexp(Args x, int op) {

        int i;

        for (i = 0; i <= 5; i++) {
            if (i == 4) {
                tag[i] = true;
            } else {
                tag[i] = false;
            }
        }
        
        if (x.getfi().getType().isInteger() && x.getse().getType().isInteger()) { 
            
            type = Type.integer();
            
        } 
        else if (x.getfi().getType().isFloat() && x.getse().getType().isFloat()){
               
            type = Type.floating();
        }
        else if (x.getfi().getType().isBool() && x.getse().getType().isBool()){
               
            type = Type.bool();
        }
        
        Operands = x;
        Operator = op;
    }
    
    Aexp(OneArg x, int op) {

        int i;

        for (i = 0; i <= 5; i++) {
            if (i == 5) {
                tag[i] = true;
            } else {
                tag[i] = false;
            }
        }
        
        if (x.getArg().getType().isInteger()) { 
            
            type = Type.integer();
            
        } 
        else if (x.getArg().getType().isFloat()){
               
            type = Type.floating();
        }
        else if (x.getArg().getType().isBool()){
               
            type = Type.bool();
        }
        
        OneOperand = x;
        Operator = op;
    }

    public String getexp() {
        String s = "";
        if (tag[0]) {
            s = "" + INTEGER;
        } 
        else if (tag[1]) {
            s = "" + FLOAT;
        }
        else if (tag[2]) {
            s = ID;
        }
        else if (tag[3]) {
            s = "" + BOOL;
        } 
        else if (tag[4]) {
            if (Operator == sym.PLUS) {
                s = "PLUS(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            } 
            
            else if (Operator == sym.MINUS) 
            {
                s = "MINUS(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            }
            
            else if (Operator == sym.TIMES) 
            {
                s = "TIMES(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            } 
             
            else if (Operator == sym.DIVIDE) 
            {
                s = "DIVIDE(" + Operands.getfi().getexp() + "," + Operands.getse().getexp() + ")";
            } 
        }

        return s;
    }

    public TypeValue getval() {
        TypeValue num1,num2;
        Type type1, type2;
        TypeValue val = null;;
        if (tag[0]) {
            val = new TypeValue(INTEGER);
        }
        else if (tag[1]) {
            val = new TypeValue(FLOAT);
        }
        else if (tag[2]) {
            val = SymbolTable.globalTable.get(ID);
        }
        else if (tag[3]) {
            val = new TypeValue(BOOL);
        }
        else if (tag[4]) {
            num1 = Operands.getfi().getval();
            num2 = Operands.getse().getval();
            
            type1 = Operands.getfi().getType();
            type2 = Operands.getse().getType();

            if (Operator == sym.PLUS)
            {
                if(type1.isInteger() && type2.isInteger())
                {
                    int thisval = (Integer)num1.getValue() + (Integer)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else if(type1.isFloat() && type2.isFloat())
                {
                    float thisval = (Float)num1.getValue() + (Float)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else 
                {
                    System.err.println("Error: Type mismatch at PLUS operator!");
                    System.exit(0);
                }
                
//                if (type1 == 0 && type2 == 0) {
//                   val =Integer.toString(Integer.parseInt(num1) + Integer.parseInt(num2));
//                } else if (type1 == 1 && type2 == 1) {
//                   val = Float.toString(Float.parseFloat(num1) + Float.parseFloat(num2));
//                } else {
//                    System.err.println("Error: Type mismatch at PLUS operator!");
//                    System.exit(0);
//                }
            } 
            
            if (Operator == sym.MINUS) 
            {
               if(type1.isInteger() && type2.isInteger())
                {
                    int thisval = (Integer)num1.getValue() - (Integer)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else if(type1.isFloat() && type2.isFloat())
                {
                    float thisval = (Float)num1.getValue() - (Float)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else 
                {
                    System.err.println("Error: Type mismatch at MINUS operator!");
                    System.exit(0);
                }
            } 
            
            else if (Operator == sym.TIMES) 
            {
               if(type1.isInteger() && type2.isInteger())
                {
                    int thisval = (Integer)num1.getValue() * (Integer)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else if(type1.isFloat() && type2.isFloat())
                {
                    float thisval = (Float)num1.getValue() * (Float)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else 
                {
                    System.err.println("Error: Type mismatch at TIMES operator!");
                    System.exit(0);
                }
            }
            
            else if (Operator == sym.DIVIDE) 
            {
                if(type1.isInteger() && type2.isInteger())
                {
                    int thisval = (Integer)num1.getValue() / (Integer)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else if(type1.isFloat() && type2.isFloat())
                {
                    float thisval = (Float)num1.getValue() / (Float)num2.getValue(); 
                    val = new TypeValue(thisval);
                }
                else 
                {
                    System.err.println("Error: Type mismatch at DIVIDE operator!");
                    System.exit(0);
                }
            }
            
            else if (Operator == sym.EQUALTO)
            {
                   if ( type1.isInteger() && type2.isInteger())
                   {
                        boolean thisval = (Integer) num1.getValue() == (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() == (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            
            else if (Operator == sym.LESSTHAN)
            {
                   if ( type1.isInteger() && type2.isInteger() )
                   {
                        boolean thisval = (Integer) num1.getValue() < (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() < (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            
            else if (Operator == sym.GREATERTHAN)
            {
                   if ( type1.isInteger() && type2.isInteger() )
                   {
                        boolean thisval = (Integer) num1.getValue() > (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() > (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            
            else if (Operator == sym.LESSTHANEQ)
            {
                   if ( type1.isInteger() && type2.isInteger() )
                   {
                        boolean thisval = (Integer) num1.getValue() <= (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() <= (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            
            else if (Operator == sym.GREATERTHANEQ)
            {
                   if ( type1.isInteger() && type2.isInteger() )
                   {
                        boolean thisval = (Integer) num1.getValue() >= (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() >= (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            else if (Operator == sym.NOTEQUAL)
            {
                   if ( type1.isInteger() && type2.isInteger() )
                   {
                        boolean thisval = (Integer) num1.getValue() != (Integer) num2.getValue();
                        val = new TypeValue(thisval);

                   } else if ( type1.isFloat() && type2.isFloat())
                   {
                       boolean thisval = (Float) num1.getValue() != (Float) num2.getValue();
                       val = new TypeValue(thisval);
                   }
             }
            
             else if (Operator == sym.AND)
            {
                    if ( type1.isBool() && type2.isBool() )
                   {
                        boolean thisval = (Boolean) num1.getValue() && (Boolean) num2.getValue();
                        val = new TypeValue(thisval);
                   }
             }
            else if (Operator == sym.OR)
            {
                   if ( type1.isBool() && type2.isBool() )
                   {
                        boolean thisval = (Boolean) num1.getValue() || (Boolean) num2.getValue();
                        val = new TypeValue(thisval);
                   }

             }

        }
        else if (tag[5]) {
            if (Operator == sym.NOT)
            {
                if ( OneOperand.getArg().getType().isBool())
                {
                        boolean thisval = !(Boolean) OneOperand.getArg().getval().getValue();
                        val = new TypeValue(thisval);
                }
            }
        }
        return val;
    }
    
    public boolean isInt(String value) {
    try {
        Integer.parseInt(value);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
}
