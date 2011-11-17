package typechk;
import ast.*;
import symbol.*;

public class TypeVisitor implements TypeVI { 
    private Table symTable; 
    private ClassRec currClass; 
    private MethodRec currMethod;
    private boolean hasReturn;
    
    // constructor -- a symbol table is passed in as a parameter 
    public TypeVisitor(Table symtab) { ... }
    
    // top level visit routine 
    public void visit(Program n) throws Exception {
    n.cl.accept(this);
    }

    // LISTS 
    public void visit(ClassDeclList n) throws Exception {
    for (int i = 0; i < n.size(); i++) 
        n.elementAt(i).accept(this);
    }

    // TYPES 
    public Type visit(BasicType n) { return n; } 
    public Type visit(ArrayType n) { return n; } 
    public Type visit(ObjType n){
        // need to verify that the class exists
    }

    // DECLARATIONS 
    public void visit(VarDecl n) throws Exception {
        // need to check class existance if type is ObjType 
        // need to check init expr if it exists
    }
    public void visit(ClassDecl n) throws Exception {
        // don't forget to set 'curClass' to the class table
    } 
    public void visit(MethodDecl n) throws Exception {
        // don't forget to set 'curMethod' to the method table
    }

    // STATEMENTS 
    public void visit(Assign n) throws Exception {
        Type t1 = n.lhs.accept(this); 
        Type t2 = n.rhs.accept(this); 
        // check for well-formedness of lhs 
        // check for type compatibility of both sides
    }

    // EXPRESSIONS 
    public Type visit(Binop n) throws Exception {
        // based on op type, perform corresponding checks
    }
    public Type visit(Id n) throws Exception {
        // lookup the Id's type, and return it
    }
    public Type visit(This n) {
        // lookup current Object's type, return a corresponding ObjType
    }

    // CONSTANTS
    public Type visit(IntVal n){ return new BasicType(BasicType.Int); }
    public Type visit(FloatVal n) { return new BasicType(BasicType.Float); }
    public Type visit(BoolVal n) { return new BasicType(BasicType.Bool); }

    private boolean compatible(Type t1, Type t2) throws Exception {
        // if t1==t2 or both are the same basic type 
        //     return true
        // else if both are ObjType    // name equivalence 
        //     if (their class ids are the same, or
        //             t1's cid matches an ancestor's cid of t2) 
        //         return true
        // else if both are ArrayType  // structure equivalence 
        //     recursively test the compatibility of their elements' types
        // else 
        //     return false
    }