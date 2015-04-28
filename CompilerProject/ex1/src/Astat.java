package src;

public class Astat {
    
    String statementType;

    String assVariable;
    Aexp assExpr;
    Type type;

    Aexp printE;

    Aexp ifcondition;
    Astat ifbody;
    Astat elsebody;

    Lstat lstat;

    Aexp whileCondition;
    Astat whileBody;

    String functionId1;
    String functionId2;
    
    public Astat(Lstat l) 
    {
        this.lstat = l;
    }
    
    public Astat(String id, Lstat l) 
    {
        this.functionId1 = id;
        this.lstat = l;
    }
    
    public Astat()
    {
        
    }

    public static Astat assignment(String variable, Aexp expr) {
        Astat statement = new Astat();
        statement.statementType = "assignment";
        statement.assVariable = variable;
        statement.assExpr = expr;
        return statement;
    }
    
      public static Astat assignment(Type type, String variable, Aexp expr) 
    {
        if (SymbolTable.globalTable.containsKey(variable)) 
        {
            ErrorType.sem_error(variable,"Duplicate declaration "+variable);
            return null;
        } 
        else 
        {
            if (type.isInteger()) 
            {
                SymbolTable.globalTable.put(variable, new TypeValue(0));
            } 
            else if (type.isFloat()) 
            {
                SymbolTable.globalTable.put(variable, new TypeValue(0.0f));
            } 
            else if (type.isBool())
            {
                SymbolTable.globalTable.put(variable, new TypeValue(false));
            }

            Astat statement = new Astat();
            statement.statementType = "assignment_whole";
            statement.type = type;
            statement.assVariable = variable;
            statement.assExpr = expr;
            return statement;
        }

    }

    public static Astat declaration(Type type, String variable) 
    {
        if (SymbolTable.globalTable.containsKey(variable)) 
        {
            ErrorType.sem_error(variable,"Duplicate declaration "+variable);
            return null;
        } 
        else 
        {
            if (type.isInteger()) 
            {
                SymbolTable.globalTable.put(variable, new TypeValue(0));
            } else if (type.isFloat()) 
            {
                SymbolTable.globalTable.put(variable, new TypeValue(0.0f));
            } else if (type.isBool()) 
            {
                SymbolTable.globalTable.put(variable, new TypeValue(false));
            }

            Astat statement = new Astat();
            statement.statementType = "declaration";
            statement.type = type;
            statement.assVariable = variable;
            return statement;
        }
    }

    public static Astat print(Aexp expr) {

        Astat statement = new Astat();
        statement.statementType = "print";
        statement.printE = expr;
        return statement;

    }

    public static Astat ifthen(Aexp Condition, Astat Ifbody) {

        Astat ifthen = new Astat();
        ifthen.statementType = "ifthen";
        ifthen.ifcondition = Condition;
        ifthen.ifbody = Ifbody;

        return ifthen;

    }

    public static Astat ifthenelse(Aexp Condition, Astat Ifbody, Astat Else) {

        Astat ifthenelse = new Astat();
        ifthenelse.statementType = "ifthenelse";
        ifthenelse.ifcondition = Condition;
        ifthenelse.ifbody = Ifbody;
        ifthenelse.elsebody = Else;

        return ifthenelse;

    }

    public static Astat whileloop(Aexp condition, Astat WhileBody) {
        Astat whileloop = new Astat();
        whileloop.statementType = "whileloop";
        whileloop.whileCondition = condition;
        whileloop.whileBody = WhileBody;
        return whileloop;

    }

    public static Astat block(Lstat l) {

        Astat block = new Astat(l);
        block.statementType = "block";
        return block;

    }

    public static Astat functiondecl(String id, Lstat l) {
        if (Function.functionTable.containsKey(id)) 
        {
            return null;
        } 
        else 
        {
            Function.functionTable.put(id, l);
            Astat function = new Astat(id,l);
            function.statementType = "functiondecl_void";
            return function;
        }   
    }
    
     public static Astat functiondecl(Type t,String id, Lstat l, Aexp expr) 
     {
        if(Function.functionTable.containsKey(id)) 
        {
            System.err.println("Semantic Error:"+ "<"+id+"> Duplicate function declaration");
            System.exit(1);
                        return null;
        }
        else 
        {
            Astat st = null; 
            boolean b =false;

            if(expr.getType().isInteger() && t.isInteger()){
                b = true;
                SymbolTable.globalTable.put(id,new TypeValue(0));
            }else if(expr.getType().isFloat() && t.isFloat()){
                b = true;
                SymbolTable.globalTable.put(id,new TypeValue(0.0f));
            }else if(expr.getType().isBool() && t.isBool()){
                b = true;
                SymbolTable.globalTable.put(id,new TypeValue(false));
            }

            if(b)
            {
                st = Astat.assignment(id,expr);                                
                Function.functionTable.put(id,new Lstat(l,st));   
            }
            else
            {
                ErrorType.sem_error(id,"Function return type mismatch");
            }
            return st;        
        }
    }
    
    
   

    public static Astat functioncall(String id)
    {
        Lstat l;
        Astat functionstatement = null;
        l = Function.functionTable.get(id);
        System.out.println(1);
        if(l != null){
           System.out.println(2);
           //System.out.println(lstat.statementList.size());
           functionstatement = new Astat();
           functionstatement.statementType = "function";
           functionstatement.functionId1 = id;
        }
        return functionstatement;

    }

    public static Astat functioncall(String id1, String id2)
    {
       
       Astat functionstatement = new Astat();
       functionstatement.statementType = "function_return";
       functionstatement.functionId1 = id1;
       functionstatement.functionId2 = id2;
       return functionstatement;
    } 
     
    public String getstat() {

        if (statementType.equals("assignment")) {
            return assVariable + "=" + assExpr.getexp();
        } else if (statementType.equals("assignment_whole")) {
            return type.getCode() + " " + assVariable + "=" + assExpr.getexp();
        } else if (statementType.equals("print")) {
            return "print " + printE.getexp();
        } else if (statementType.equals("ifthen")) {
            return "if " + ifcondition.getexp() + " " + ifbody.getstat();
        } else if (statementType.equals("ifthenelse")) {
            return "else " + ifcondition.getexp() + " " + elsebody.getstat();
        } else if (statementType.equals("whileloop")) {
            return "while " + whileCondition.getexp() + " do " + whileBody.getstat();
        } else if (statementType.equals("block")) {
            return "block";
        } else {
            return "unknown";
        }
    }

    public void execute() {
       
        if (statementType.equals("assignment")) {
            if (SymbolTable.globalTable.get(assVariable).getType().isInteger()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Integer) assExpr.getval().getValue()));
            }
            if (SymbolTable.globalTable.get(assVariable).getType().isFloat()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Float) assExpr.getval().getValue()));
            }
            if (SymbolTable.globalTable.get(assVariable).getType().isBool()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Boolean) assExpr.getval().getValue()));
            }
        } else if (statementType.equals("assignment_whole")) {
            if (type.isInteger()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Integer) assExpr.getval().getValue()));
            }
            if (type.isFloat()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Float) assExpr.getval().getValue()));
            }
            if (type.isBool()) {
                SymbolTable.globalTable.put(assVariable, new TypeValue((Boolean) assExpr.getval().getValue()));
            }
        } else if (statementType.equals("print")) {
            if (printE.getType().isInteger()) {
                System.out.println((Integer) printE.getval().getValue());
            }
            if (printE.getType().isFloat()) {
                System.out.println((Float) printE.getval().getValue());
            }
            if (printE.getType().isBool()) {
                System.out.println((Boolean) printE.getval().getValue());
            }

        } 
        else if (statementType.equals("ifthen")) 
        {
            if (ifcondition.getval().getType().isBool()) 
            {
                if ((Boolean) ifcondition.getval().getValue()) 
                {
                    ifbody.execute();
                }
            } 
            else 
            {
                ErrorType.type_error("", "if expression must be boolean.");
            }
        } 
        else if (statementType.equals("ifthenelse")) 
        {
            if (ifcondition.getval().getType().isBool())
            {
                if ((Boolean) ifcondition.getval().getValue())
                {
                    ifbody.execute();
                } else {
                    elsebody.execute();
                }
            } 
            else 
            {
                ErrorType.type_error("", "if expression must be boolean.");

            }
        } else if (statementType.equals("whileloop")) {
            if (whileCondition.getval().getType().isBool()) {
                for (;;) {

                    if ((Boolean) whileCondition.getval().getValue()) {
                        whileBody.execute();
                    } else {
                        break;
                    }
                }
            }

        } else if (statementType.equals("block")) {
            for (Astat s : lstat.statementList) {
                s.execute();
            }
        }
        
        else if (statementType.equals("function"))
        {
            for (Astat s : lstat.statementList)
            {
                s.execute();
            }
        }
        
        else if (statementType.equals("function_return"))
        {
            Lstat l;
            l = Function.functionTable.get(functionId2);
            if(l != null)
            {
               for (Astat s : l.statementList)
                {
                    s.execute();
                }

               SymbolTable.globalTable.put(functionId1, SymbolTable.globalTable.get(functionId2));
            }            
        }

    }
}
