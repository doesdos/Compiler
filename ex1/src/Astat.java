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

    Lstat blockBody;

    Aexp whileCondition;
    Astat whileBody;

    String functionId;
    Lstat functionBody;

    public static Astat assignment(String variable, Aexp expr) {
        Astat statement = new Astat();
        statement.statementType = "assignment";
        statement.assVariable = variable;
        statement.assExpr = expr;
        return statement;
    }

    public static Astat assignment(Type type, String variable) {
        if (SymbolTable.globalTable.containsKey(variable)) {
            //parser.sem_error(variable,"Duplicate declaration "+variable);
            return null;
        } else {
            if (type.isInteger()) {
                SymbolTable.globalTable.put(variable, new TypeValue(0));
            } else if (type.isFloat()) {
                SymbolTable.globalTable.put(variable, new TypeValue(0.0f));
            } else if (type.isBool()) {
                SymbolTable.globalTable.put(variable, new TypeValue(false));
            }

            Astat statement = new Astat();
            statement.statementType = "declaration";
            statement.type = type;
            statement.assVariable = variable;
            return statement;
        }
    }

    public static Astat assignment(Type type, String variable, Aexp expr) {
        if (SymbolTable.globalTable.containsKey(variable)) {
            //parser.sem_error(variable,"Duplicate declaration "+variable);
            return null;
        } else {
            if (type.isInteger()) {
                SymbolTable.globalTable.put(variable, new TypeValue(0));
            } else if (type.isFloat()) {
                SymbolTable.globalTable.put(variable, new TypeValue(0.0f));
            } else if (type.isBool()) {
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

        Astat block = new Astat();
        block.statementType = "block";
        block.blockBody = l;
        return block;

    }

    public static Astat functiondecl(String id, Lstat l) {
        if (Function.functionTable.containsKey(id)) 
        {
                //parser.sem_error(i,"Duplicate function declaration");
            return null;
        } 
        else 
        {
            Function.functionTable.put(id, l);
            Astat function = new Astat();
            function.statementType = "functiondeclaration";
            function.functionId = id;
            function.functionBody = l;
            return function;
        }   
            
        
    }

//     public static Astat functioncall(String id)
//    {
//        Lstat l;
//        Astat p = null;
//        l = FunctionList.funTable.get(id);
//        if(l != null){
//           p = new Astat(l); 
//           p.statementType = "function";
//        }
//        return p;
//
//    }
//    
//    public static Astat functionReturn(String id1, String id2)
//    {
//       
//       Astat p = new Astat(id1,id2);
//       p.statementType = "function-return";
//       
//       return p;
//    }
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

        } else if (statementType.equals("ifthen")) {
            if (ifcondition.getval().getType().isBool()) {
                if ((Boolean) ifcondition.getval().getValue()) {
                    ifbody.execute();
                }
            } else {
                //parser.type_error("", "if expression must be boolean.");
            }
        } else if (statementType.equals("ifthenelse")) {
            if (ifcondition.getval().getType().isBool()) {
                if ((Boolean) ifcondition.getval().getValue()) {
                    ifbody.execute();
                } else {
                    elsebody.execute();
                }
            } else {
                //parser.type_error("", "if expression must be boolean.");
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
            for (Astat s : blockBody.statementList) {
                s.execute();
            }
        }

    }
}
