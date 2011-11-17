/* Generated By:JavaCC: Do not edit this line. astParser.java */
package astpsr;
import ast.*;
public class astParser implements astParserConstants {

  static final public Program Program() throws ParseException {
  ClassDeclList cl;
    jj_consume_token(59);
    jj_consume_token(kwProgram);
    cl = astClassDeclList();
    jj_consume_token(60);
    jj_consume_token(0);
    {if (true) return new Program(cl);}
    throw new Error("Missing return statement in function");
  }

  static final public ClassDeclList astClassDeclList() throws ParseException {
  ClassDecl c; ClassDeclList cl = new ClassDeclList();
    jj_consume_token(59);
    jj_consume_token(kwClassDeclList);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      c = astClassDecl();
                                           cl.add(c);
    }
    jj_consume_token(60);
    {if (true) return cl;}
    throw new Error("Missing return statement in function");
  }

  static final public ClassDecl astClassDecl() throws ParseException {
  Exp e1, e2; VarDeclList vl; MethodDeclList ml;
    jj_consume_token(59);
    jj_consume_token(kwClassDecl);
    e1 = astExp();
    e2 = astExp();
    vl = astVarDeclList();
    ml = astMethodDeclList();
    jj_consume_token(60);
    {if (true) return new ClassDecl((Id)e1,(Id)e2,vl,ml);}
    throw new Error("Missing return statement in function");
  }

  static final public VarDeclList astVarDeclList() throws ParseException {
  VarDecl v; VarDeclList vl = new VarDeclList();
    jj_consume_token(59);
    jj_consume_token(kwVarDeclList);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      v = astVarDecl();
                                       vl.add(v);
    }
    jj_consume_token(60);
    {if (true) return vl;}
    throw new Error("Missing return statement in function");
  }

  static final public VarDecl astVarDecl() throws ParseException {
  Type t; Exp e1, e2;
    jj_consume_token(59);
    jj_consume_token(kwVarDecl);
    t = astType();
    e1 = astExp();
    e2 = astExp();
    jj_consume_token(60);
    {if (true) return new VarDecl(t,(Id)e1,e2);}
    throw new Error("Missing return statement in function");
  }

  static final public MethodDeclList astMethodDeclList() throws ParseException {
  MethodDecl m; MethodDeclList ml = new MethodDeclList();
    jj_consume_token(59);
    jj_consume_token(kwMethodDeclList);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      m = astMethodDecl();
                                             ml.add(m);
    }
    jj_consume_token(60);
    {if (true) return ml;}
    throw new Error("Missing return statement in function");
  }

  static final public MethodDecl astMethodDecl() throws ParseException {
  Type t; Exp e; FormalList fl; VarDeclList vl; StmtList sl;
    jj_consume_token(59);
    jj_consume_token(kwMethodDecl);
    t = astType();
    e = astExp();
    fl = astFormalList();
    vl = astVarDeclList();
    sl = astStmtList();
    jj_consume_token(60);
    {if (true) return new MethodDecl(t,(Id)e,fl,vl,sl);}
    throw new Error("Missing return statement in function");
  }

  static final public FormalList astFormalList() throws ParseException {
  Formal f; FormalList fl = new FormalList();
    jj_consume_token(59);
    jj_consume_token(kwFormalList);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      f = astFormal();
                                     fl.add(f);
    }
    jj_consume_token(60);
    {if (true) return fl;}
    throw new Error("Missing return statement in function");
  }

  static final public Formal astFormal() throws ParseException {
  Type t; Exp e;
    jj_consume_token(59);
    jj_consume_token(kwFormal);
    t = astType();
    e = astExp();
    jj_consume_token(60);
    {if (true) return new Formal(t,(Id)e);}
    throw new Error("Missing return statement in function");
  }

  static final public StmtList astStmtList() throws ParseException {
  Stmt s; StmtList sl = new StmtList();
    jj_consume_token(59);
    jj_consume_token(kwStmtList);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_5;
      }
      s = astStmt();
                                 sl.add(s);
    }
    jj_consume_token(60);
    {if (true) return sl;}
    throw new Error("Missing return statement in function");
  }

  static final public ExpList astExpList() throws ParseException {
  Exp e; ExpList el = new ExpList();
    jj_consume_token(59);
    jj_consume_token(kwExpList);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 59:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_6;
      }
      e = astExp();
                               el.add(e);
    }
    jj_consume_token(60);
    {if (true) return el;}
    throw new Error("Missing return statement in function");
  }

  static final public Type astType() throws ParseException {
  Type t; Exp e;
    jj_consume_token(59);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case kwBasicType:
      jj_consume_token(kwBasicType);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case kwInt:
        jj_consume_token(kwInt);
                                t = new BasicType(BasicType.Int);
        break;
      case kwFloat:
        jj_consume_token(kwFloat);
                                t = new BasicType(BasicType.Float);
        break;
      case kwBool:
        jj_consume_token(kwBool);
                                t = new BasicType(BasicType.Bool);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case kwObjType:
      jj_consume_token(kwObjType);
      e = astExp();
                              t = new ObjType((Id)e);
      break;
    case kwArrayType:
      jj_consume_token(kwArrayType);
      t = astType();
                              t = new ArrayType(t);
      break;
    case kwNullType:
      jj_consume_token(kwNullType);
                              t = null;
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(60);
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public Stmt astStmt() throws ParseException {
  Exp e1, e2; Stmt s, s1, s2; ExpList el; StmtList sl;
    jj_consume_token(59);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case kwBlock:
      jj_consume_token(kwBlock);
      sl = astStmtList();
                                       s = new Block(sl);
      break;
    case kwAssign:
      jj_consume_token(kwAssign);
      e1 = astExp();
      e2 = astExp();
                                       s = new Assign(e1,e2);
      break;
    case kwCallStmt:
      jj_consume_token(kwCallStmt);
      e1 = astExp();
      e2 = astExp();
      el = astExpList();
                                       s = new CallStmt(e1,(Id)e2,el);
      break;
    case kwIf:
      jj_consume_token(kwIf);
      e1 = astExp();
      s1 = astStmt();
      s2 = astStmt();
                                       s = new If(e1,s1,s2);
      break;
    case kwWhile:
      jj_consume_token(kwWhile);
      e1 = astExp();
      s1 = astStmt();
                                       s = new While(e1,s1);
      break;
    case kwPrint:
      jj_consume_token(kwPrint);
      e1 = astExp();
                                       s = new Print(e1);
      break;
    case kwReturn:
      jj_consume_token(kwReturn);
      e1 = astExp();
                                       s = new Return(e1);
      break;
    case kwNullStmt:
      jj_consume_token(kwNullStmt);
                                       s = null;
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(60);
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  static final public int binOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 61:
      jj_consume_token(61);
             {if (true) return ast.Binop.ADD;}
      break;
    case 62:
      jj_consume_token(62);
             {if (true) return ast.Binop.SUB;}
      break;
    case 63:
      jj_consume_token(63);
             {if (true) return ast.Binop.MUL;}
      break;
    case 64:
      jj_consume_token(64);
             {if (true) return ast.Binop.DIV;}
      break;
    case 65:
      jj_consume_token(65);
             {if (true) return ast.Binop.AND;}
      break;
    case 66:
      jj_consume_token(66);
             {if (true) return ast.Binop.OR;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public int relOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 67:
      jj_consume_token(67);
             {if (true) return ast.Relop.EQ;}
      break;
    case 68:
      jj_consume_token(68);
             {if (true) return ast.Relop.NE;}
      break;
    case 69:
      jj_consume_token(69);
             {if (true) return ast.Relop.LT;}
      break;
    case 70:
      jj_consume_token(70);
             {if (true) return ast.Relop.LE;}
      break;
    case 71:
      jj_consume_token(71);
             {if (true) return ast.Relop.GT;}
      break;
    case 72:
      jj_consume_token(72);
             {if (true) return ast.Relop.GE;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public int unOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 62:
      jj_consume_token(62);
             {if (true) return ast.Unop.NEG;}
      break;
    case 73:
      jj_consume_token(73);
             {if (true) return ast.Unop.NOT;}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Exp astExp() throws ParseException {
  Type t; Token n; int op, size; Exp e, e1, e2; ExpList el;
    jj_consume_token(59);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case kwArrayElm:
      jj_consume_token(kwArrayElm);
      e1 = astExp();
      e2 = astExp();
                                  e = new ArrayElm(e1,e2);
      break;
    case kwArrayLen:
      jj_consume_token(kwArrayLen);
      e1 = astExp();
                                  e = new ArrayLen(e1);
      break;
    case kwBinop:
      jj_consume_token(kwBinop);
      op = binOp();
      e1 = astExp();
      e2 = astExp();
                                  e = new Binop(op,e1,e2);
      break;
    case kwBoolVal:
      jj_consume_token(kwBoolVal);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case kwTrue:
        jj_consume_token(kwTrue);
                                  e = new BoolVal(true);
        break;
      case kwFalse:
        jj_consume_token(kwFalse);
                                  e = new BoolVal(false);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case kwCall:
      jj_consume_token(kwCall);
      e1 = astExp();
      e2 = astExp();
      el = astExpList();
                                  e = new Call(e1,(Id)e2,el);
      break;
    case kwFloatVal:
      jj_consume_token(kwFloatVal);
      n = jj_consume_token(FLOATVAL);
                                  e = new FloatVal(Float.parseFloat(n.image));
      break;
    case kwId:
      jj_consume_token(kwId);
      n = jj_consume_token(ID);
                                  e = new Id(n.image);
      break;
    case kwIntVal:
      jj_consume_token(kwIntVal);
      n = jj_consume_token(INTVAL);
                                  e = new IntVal(Integer.parseInt(n.image));
      break;
    case kwField:
      jj_consume_token(kwField);
      e1 = astExp();
      e2 = astExp();
                                  e = new Field(e1,(Id)e2);
      break;
    case kwNewArray:
      jj_consume_token(kwNewArray);
      t = astType();
      n = jj_consume_token(INTVAL);
                                  e = new NewArray((Type)t,Integer.parseInt(n.image));
      break;
    case kwNewObj:
      jj_consume_token(kwNewObj);
      e1 = astExp();
      el = astExpList();
                                  e = new NewObj((Id)e1,el);
      break;
    case kwRelop:
      jj_consume_token(kwRelop);
      op = relOp();
      e1 = astExp();
      e2 = astExp();
                                  e = new Relop(op,e1,e2);
      break;
    case kwStrVal:
      jj_consume_token(kwStrVal);
      n = jj_consume_token(STRVAL);
                                  String s = n.image;
                                  e = new StrVal(s.substring(1,s.length()-1));
      break;
    case kwThis:
      jj_consume_token(kwThis);
                                  e = new This();
      break;
    case kwUnop:
      jj_consume_token(kwUnop);
      op = unOp();
      e1 = astExp();
                                   e = new Unop(op,e1);
      break;
    case kwNullExp:
      jj_consume_token(kwNullExp);
                                  e = null;
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(60);
    {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public astParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x20802000,0x500,0x10011200,0x0,0x0,0x0,0x200000,0x4940c8c0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x8000000,0x8000000,0x8000000,0x8000000,0x8000000,0x8000000,0x0,0x40008,0xa0090,0xe0000000,0x0,0x40000000,0x1000,0x104c46,};
   }
   private static void jj_la1_init_2() {
      jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x7,0x1f8,0x200,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public astParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public astParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new astParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public astParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new astParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public astParser(astParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(astParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[74];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 74; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
