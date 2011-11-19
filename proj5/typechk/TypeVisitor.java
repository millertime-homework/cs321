package typechk;
import ast.*;
import symbol.*;

public class TypeVisitor implements TypeVI { 
    private Table symTable; 
    private ClassRec currClass; 
    private MethodRec currMethod;
    private boolean hasReturn;

    // convenient aliases
    private BasicType IntType = new BasicType(BasicType.Int);
    private BasicType BoolType = new BasicType(BasicType.Bool);
    private BasicType FloatType = new BasicType(BasicType.Float);
    
    // constructor -- a symbol table is passed in as a parameter 
    public TypeVisitor(Table symtab) {
        symTable = symtab;
        currClass = null;
        currMethod = null;
        hasReturn = false;
    }
    
    // top level visit routine 
    public void visit(Program n) throws Exception {
        n.cl.accept(this);
    }

    // LISTS 
    public void visit(AstList n) throws Exception {}
    public void visit(MethodDeclList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }
    public void visit(VarDeclList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }
    public void visit(FormalList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }
    public void visit(StmtList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }
    public void visit(ExpList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }
    public void visit(ClassDeclList n) throws Exception {
        for (int i = 0; i < n.size(); i++) 
            n.elementAt(i).accept(this);
    }

    // TYPES 
    public Type visit(BasicType n) { return n; } 
    public Type visit(ArrayType n) { return n.et; }
    public Type visit(ObjType n) throws Exception {
        symTable.getClass(n.cid); // throws exception if doesn't exist
        return n;
    }

    // DECLARATIONS 
    public void visit(VarDecl n) throws Exception {
        n.t.accept(this);
        if (n.e != null) {
            Type rhs = n.e.accept(this);
        }
        // need to check class existance if type is ObjType 
        // need to check init expr if it exists
    }
    public void visit(ClassDecl n) throws Exception {
        currClass = symTable.getClass(n.cid);
        if (n.pid != null)
            symTable.getClass(n.pid);
        n.vl.accept(this);
        n.ml.accept(this);
        currClass = null;
    } 
    public void visit(MethodDecl n) throws Exception {
        currMethod = currClass.getMethod(n.mid);
        if (n.t != null)
            n.t.accept(this);
        n.fl.accept(this);
        n.vl.accept(this);
        n.sl.accept(this);
        if (n.t != null && hasReturn == false)
            throw new TypeException("Method "+n.mid.s+
                                    " is missing a Return statment");
        currMethod = null;
    }
    public void visit(Formal n) throws Exception {
        if (n.t != null)
            n.t.accept(this);
    }

    // STATEMENTS 
    public void visit(Assign n) throws Exception {
        Type t1 = n.lhs.accept(this);
        Type t2 = n.rhs.accept(this);
        // check for well-formedness of lhs 
        // check for type compatibility of both sides
        if (!compatible(t1,t2)) {
            String t1str = t1.toString();
            String t2str = t2.toString();
            throw new TypeException("Incompatible types in Assign: "
                                    +t1str+" <- "+t2str);
        }
    }
    public void visit(Block n) throws Exception {
        n.sl.accept(this);
    }
    public void visit(CallStmt n) throws Exception {
        // t should be Id for class we're calling a method from
        Type t = n.obj.accept(this);
        ObjType obj = (ObjType) t;
        // do lookup for class and method
        MethodRec call_method = symTable.getClass(obj.cid).getMethod(n.mid);
        if (call_method == null)
            throw new SymbolException("Method "+n.mid.s+" not defined");
        // make sure args match types
        if (n.args != null) {
            int nargs = n.args.size(), nparams = call_method.paramCnt();
            if (nargs != nparams)
                throw new TypeException("Formals' and actuals' counts don't "+
                                        "match: "+nparams+" vs. "+nargs);
            for (int i = 0; i < call_method.paramCnt(); i++) {
                Type paramtype = call_method.getParamAt(i).type();
                Type vartype = n.args.elementAt(i).accept(this);
                if (!compatible(paramtype, vartype))
                    throw new TypeException("Formal's and actual's types not "+
                                            "compatible: "+paramtype+" vs. "+vartype);
            }
        }
    }
    public void visit(If n) throws Exception {
        Type should_be_bool = n.e.accept(this);
        if (should_be_bool instanceof BasicType) {
            BasicType iftype = (BasicType) should_be_bool;
            if (iftype.typ == BasicType.Bool) {
                n.s1.accept(this);
                if (n.s2 != null)
                    n.s2.accept(this);
                return;  // correct type
            }
        }
        throw new TypeException("Test of If is not of boolean type: "+
                                should_be_bool.toString());
    }
    public void visit(While n) throws Exception {
        Type should_be_bool = n.e.accept(this);
        n.s.accept(this);
    }
    public void visit(Print n) throws Exception {
        if (n.e != null) {
            Type t = n.e.accept(this);
            if (!(t instanceof BasicType))
                throw new TypeException("Argument to Print must be of a basic "+
                                        "type or a string literal: "+
                                        t.toString());
        }
    }
    public void visit(Return n) throws Exception {
        hasReturn = true;
        Type currmethod_rtype = currMethod.rtype();
        if (n.e == null)
            throw new TypeException("Return is missing an expr of type "+
                                    currmethod_rtype.toString());
        Type t = n.e.accept(this);
        if (!compatible(t, currmethod_rtype))
            throw new TypeException("Wrong return type for method "+
                                    currMethod.id().s+": "+
                                    t.toString());
    }

    // EXPRESSIONS 
    public Type visit(Binop n) throws Exception {
        // based on op type, perform corresponding checks
        Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        if (t1 instanceof BasicType && t2 instanceof BasicType) {
            BasicType t1b = (BasicType) t1;
            BasicType t2b = (BasicType) t2;
            switch (n.op) {
            case Binop.ADD:
            case Binop.SUB:
            case Binop.MUL:
            case Binop.DIV:
                if (t1b.typ==BasicType.Int && t2b.typ==BasicType.Int)
                    return IntType;
                else if ((t1b.typ==BasicType.Int && t2b.typ==BasicType.Float) ||
                         (t1b.typ==BasicType.Float && t2b.typ==BasicType.Int) ||
                         (t1b.typ==BasicType.Float && t2b.typ==BasicType.Float))
                    return FloatType;
                break;
            case Binop.AND:
            case Binop.OR:
                if (t1b.typ==BasicType.Bool && t2b.typ==BasicType.Bool)
                    return BoolType;
            }
            String t1str = t1.toString();
            String t2str = t2.toString();
            String opstr = n.opName(n.op);
            throw new TypeException("Binop operands' type mismatch: "
                                    +t1str+opstr+t2str);
        }
        throw new TypeException("not using BasicType in binop");
    }

    public Type visit(Id n) throws Exception {
        VarRec v = null;
        if (currMethod != null) {
            v = currMethod.getParam(n);
            if (v == null)
                v = currMethod.getLocal(n);
        }
        if (v == null)
            v = currClass.getClassVar(n);
        if (v == null)
            throw new SymbolException("Var "+n.s+" not defined");
        else
            return v.type();
    }
    public Type visit(This n) {
        // lookup current Object's type, return a corresponding ObjType
        return new ObjType(currClass.id());
    }
    public Type visit(Relop n) throws Exception {
    	Type t1 = n.e1.accept(this);
        Type t2 = n.e2.accept(this);
        switch (n.op) {
        case Relop.EQ:
        case Relop.NE:
            if (compatible(t1,t2))
                return BoolType;
            break;
        case Relop.LT:
        case Relop.LE:
        case Relop.GT:
        case Relop.GE:
            if (t1 instanceof BasicType && t2 instanceof BasicType) {
                BasicType t1basic = (BasicType) t1;
                BasicType t2basic = (BasicType) t2;
                if ((t1basic.typ==BasicType.Int || t1basic.typ==BasicType.Float) &&
                    (t2basic.typ==BasicType.Int || t2basic.typ==BasicType.Float))
                    return BoolType;
            }
            else
                throw new TypeException("not using basic type for relop");
        }
        String t1str = t1.toString();
        String t2str = t2.toString();
        String opstr = n.opName(n.op);
        throw new TypeException("Incorrect operand types in Relop: "+
                                t1str+opstr+t2str);
    }
    public Type visit(Unop n) throws Exception {
        Type t = n.e.accept(this);
        BasicType tbasic;
        if (n.op==Unop.NEG) {
            if (t instanceof BasicType) {
                tbasic = (BasicType) t;
                if (tbasic.typ==BasicType.Int || tbasic.typ==BasicType.Float)
                    return t;
            }
        }
        else if (n.op==Unop.NOT) {
            if (t instanceof BasicType) {
                tbasic = (BasicType) t;
                if (tbasic.typ==BasicType.Bool)
                    return t;
            }
        }
        // not tested
        String tstr = t.toString();
        String opstr = n.opName(n.op);
        throw new TypeException("Invalid type for Unop: "+opstr+tstr);
    }
    public Type visit(ArrayElm n) throws Exception {
        Type should_be_array = n.array.accept(this);
        Type should_be_int = n.idx.accept(this);
        if (!(should_be_array instanceof ArrayType))
            throw new TypeException("ArrayElm object is not an array: "+
                                    should_be_array.toString());
        if (should_be_int instanceof BasicType) {
            BasicType idx = (BasicType) should_be_int;
            if (idx.typ==BasicType.Int) {
                ArrayType array = (ArrayType) should_be_array;
                return array.et;
            }
        }
        throw new TypeException("used non-integer to index an array");

    }    
    public Type visit(ArrayLen n) throws Exception {
    	return IntType; 
    }
    public Type visit(Field n) throws Exception {
        // a class's field. lookup class, then lookup classvar,
        // return classvar's type
    	Type t = n.obj.accept(this);
        if (!(t instanceof ObjType))
            throw new TypeException("Object in Field is not ObjType: "+
                                    t.toString());
        ObjType obj = (ObjType) t;
        ClassRec obj_cr = symTable.getClass(obj.cid);
        VarRec classvar = obj_cr.getClassVar(n.var);
        if (classvar != null)
            return classvar.type();
        else {
            ClassRec parent = obj_cr.parent();
            classvar = parent.getClassVar(n.var);
            if (classvar != null)
                return classvar.type();
        }
        throw new TypeException("Var "+n.var.s+" not found in class "+obj.cid.s);
    }
    public Type visit(Call n) throws Exception {
        // calling a class's method. use symbol table to verify class
        // exists, use class's method table to get the methodrec
        // and get its return type with the type() inspector
        // return that, since this expression will be evaluated
        // as that type.
        Type t = n.obj.accept(this);
        ObjType obj = (ObjType) t;
        ClassRec call_class = symTable.getClass(obj.cid); // throws exception
        MethodRec call_method = call_class.getMethod(n.mid); // could be null
        if (call_method == null) {
            ClassRec parent = call_class.parent();
            if (parent != null)
                call_method = parent.getMethod(n.mid);
        }
        if (call_method == null)
            throw new TypeException("Couldn't find "+n.mid.s+" in "+obj.cid.s+" class.");
        // make sure args match types
        if (n.args != null) {
            int nargs = n.args.size(), nparams = call_method.paramCnt();
            if (nargs != nparams)
                throw new TypeException("Formals' and actuals' counts don't "+
                                        "match: "+nparams+" vs. "+nargs);
            for (int i = 0; i < call_method.paramCnt(); i++) {
                Type paramtype = call_method.getParamAt(i).type();
                Type vartype = n.args.elementAt(i).accept(this);
                if (!compatible(paramtype, vartype))
                    throw new TypeException("Formal's and actual's types not "+
                                            "compatible: "+paramtype+" vs. "+vartype);
            }
        }
        return call_method.rtype();
    }
    public Type visit(NewArray n) throws Exception {
        return new ArrayType(n.et);
    }
    public Type visit(NewObj n) throws Exception {
        symTable.getClass(n.cid);
    	return new ObjType(n.cid); 
    }

    // CONSTANTS
    public Type visit(IntVal n){ return IntType; }
    public Type visit(FloatVal n) { return FloatType; }
    public Type visit(BoolVal n) { return BoolType; }
    // We borrow BasictType(BasicType.Int) to represent the type of a StrVal 
    // node. Since StrVal nodes only appear in a print statement, this is not 
    // going to cause any problem.
    public Type visit(StrVal n) { return IntType; }

    private boolean compatible(Type t1, Type t2) throws Exception {
        // same
        if (t1==t2)
            return true;
        // matching basictype
        else if (t1 instanceof BasicType && t2 instanceof BasicType) {
            BasicType t1b = (BasicType) t1; 
            BasicType t2b = (BasicType) t2;
            return (t1b.typ==t2b.typ);
        }
        // matching class-name objtype
        else if (t1 instanceof ObjType && t2 instanceof ObjType) {
            ObjType t1o = (ObjType) t1;
            ObjType t2o = (ObjType) t2;
            if (t1o.cid.s.equals(t2o.cid.s))
                return true;
            else {
                // fetch t2's parent id, recursively compare with t1
                ClassRec parent = symTable.getClass(t2o.cid).parent();
                if (parent != null) {
                    if (compatible(t1o, new ObjType(parent.id())))
                        return true;
                }
            }
        } 
        else if (t1 instanceof ArrayType && t2 instanceof ArrayType) {
            ArrayType t1a = (ArrayType) t1;
            ArrayType t2a = (ArrayType) t2;
            return compatible(t1a.et, t2a.et);
        }
        return false;
    }
}