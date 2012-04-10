/* The following code was generated by JFlex 1.4.3 on 4/10/12 12:11 AM */

/*stl.l*/

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/10/12 12:11 AM from the specification file
 * <tt>stl.l</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\2\36\25\0\1\36\1\20\1\33\2\0\1\30\1\0\1\32"+
    "\6\0\1\34\1\0\12\31\2\0\1\26\1\21\1\27\2\0\1\7"+
    "\1\23\1\22\1\12\1\3\1\2\1\35\1\16\1\1\2\35\1\4"+
    "\1\35\1\11\1\6\1\14\1\25\1\13\1\5\1\10\1\17\1\24"+
    "\1\15\3\35\4\0\1\35\1\0\1\7\1\23\1\22\1\12\1\3"+
    "\1\2\1\35\1\16\1\1\2\35\1\4\1\35\1\11\1\6\1\14"+
    "\1\25\1\13\1\5\1\10\1\17\1\24\1\15\3\35\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\14\2\2\1\4\2\1\3\1\4\1\5"+
    "\1\6\2\1\1\7\1\10\6\2\1\11\5\2\1\12"+
    "\1\13\4\2\1\14\1\15\1\16\3\0\1\17\1\20"+
    "\4\2\1\21\1\22\1\2\1\23\6\2\1\24\1\25"+
    "\1\2\1\26\1\27\1\30\2\2\1\31\1\2\1\32"+
    "\1\2\1\33\1\2\1\34\2\2\1\35\2\2\1\36"+
    "\1\2\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\37\0\76\0\135\0\174\0\233\0\272\0\331"+
    "\0\370\0\u0117\0\u0136\0\u0155\0\u0174\0\u0193\0\u01b2\0\u01d1"+
    "\0\u01f0\0\u020f\0\u022e\0\u024d\0\u026c\0\u028b\0\37\0\u02aa"+
    "\0\u02c9\0\u02e8\0\u0307\0\u0155\0\u0326\0\u0345\0\u0364\0\u0383"+
    "\0\u03a2\0\u03c1\0\u0155\0\u03e0\0\u03ff\0\u041e\0\u043d\0\u045c"+
    "\0\37\0\37\0\u047b\0\u049a\0\u04b9\0\u04d8\0\37\0\37"+
    "\0\37\0\u04f7\0\u0516\0\u02e8\0\37\0\u0155\0\u0535\0\u0554"+
    "\0\u0573\0\u0592\0\u0155\0\u0155\0\u05b1\0\u0155\0\u05d0\0\u05ef"+
    "\0\u060e\0\u062d\0\u064c\0\u066b\0\u04f7\0\37\0\u068a\0\u0155"+
    "\0\u0155\0\u0155\0\u06a9\0\u06c8\0\u0155\0\u06e7\0\u0155\0\u0706"+
    "\0\u0155\0\u0725\0\u0155\0\u0744\0\u0763\0\u0155\0\u0782\0\u07a1"+
    "\0\u0155\0\u07c0\0\u0155";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\14\1\16\2\14\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\2\1\14\1\33\40\0\1\14"+
    "\1\34\6\14\1\35\6\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\3\14\1\36\2\14\1\37\10\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\3\14"+
    "\1\40\13\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\5\14\1\41\11\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\2\14\1\42\14\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\12\14\1\43\4\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\10\14"+
    "\1\44\6\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\12\14\1\45\4\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\5\14\1\46\11\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\17\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\2\14\1\47\14\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\15\14"+
    "\1\50\1\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\22\0\1\51\36\0\1\52\16\0\15\14\1\53\1\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\5\14"+
    "\1\54\11\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\5\14\1\55\11\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\16\14\1\56\2\0\4\14\3\0"+
    "\1\14\3\0\1\14\22\0\1\57\4\0\1\60\31\0"+
    "\1\61\46\0\1\30\2\0\1\62\2\0\32\63\1\0"+
    "\4\63\33\64\1\65\3\64\36\0\1\33\1\0\7\14"+
    "\1\66\7\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\5\14\1\67\11\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\3\14\1\70\13\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\4\14\1\71\12\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\5\14"+
    "\1\72\11\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\7\14\1\73\7\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\11\14\1\74\5\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\16\14\1\75\2\0"+
    "\4\14\3\0\1\14\3\0\1\14\2\0\7\14\1\76"+
    "\7\14\2\0\4\14\3\0\1\14\3\0\1\14\2\0"+
    "\7\14\1\77\7\14\2\0\4\14\3\0\1\14\3\0"+
    "\1\14\2\0\1\100\16\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\6\14\1\101\10\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\5\14\1\102\11\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\1\103"+
    "\16\14\2\0\4\14\3\0\1\14\3\0\1\14\2\0"+
    "\2\14\1\104\14\14\2\0\4\14\3\0\1\14\3\0"+
    "\1\14\32\0\1\105\37\0\1\106\5\0\6\14\1\107"+
    "\10\14\2\0\4\14\3\0\1\14\3\0\1\14\2\0"+
    "\4\14\1\75\12\14\2\0\4\14\3\0\1\14\3\0"+
    "\1\14\2\0\2\14\1\110\14\14\2\0\4\14\3\0"+
    "\1\14\3\0\1\14\2\0\13\14\1\111\3\14\2\0"+
    "\4\14\3\0\1\14\3\0\1\14\2\0\2\14\1\112"+
    "\14\14\2\0\4\14\3\0\1\14\3\0\1\14\2\0"+
    "\16\14\1\113\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\3\14\1\114\13\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\12\14\1\115\4\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\3\14\1\116\13\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\11\14"+
    "\1\117\5\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\4\14\1\120\12\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\7\14\1\121\7\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\12\14\1\122\4\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\2\14"+
    "\1\123\14\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\2\14\1\124\14\14\2\0\4\14\3\0\1\14"+
    "\3\0\1\14\2\0\7\14\1\125\7\14\2\0\4\14"+
    "\3\0\1\14\3\0\1\14\2\0\10\14\1\126\6\14"+
    "\2\0\4\14\3\0\1\14\3\0\1\14\2\0\6\14"+
    "\1\127\10\14\2\0\4\14\3\0\1\14\3\0\1\14"+
    "\2\0\1\130\16\14\2\0\4\14\3\0\1\14\3\0"+
    "\1\14\2\0\10\14\1\131\6\14\2\0\4\14\3\0"+
    "\1\14\3\0\1\14\2\0\5\14\1\132\11\14\2\0"+
    "\4\14\3\0\1\14\3\0\1\14\2\0\10\14\1\133"+
    "\6\14\2\0\4\14\3\0\1\14\3\0\1\14\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2015];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\24\1\1\11\21\1\2\11\4\1\3\11"+
    "\3\0\1\11\20\1\1\11\25\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 138) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 27: 
          { System.out.println("lex: found float\n"); return (FLOAT);
          }
        case 32: break;
        case 18: 
          { System.out.println("lex: found and\n"); return (AND);
          }
        case 33: break;
        case 31: 
          { System.out.println("lex: found question\n"); return (QUESTION);
          }
        case 34: break;
        case 7: 
          { System.out.println("lex: found whitespace\n");/* ignore */
          }
        case 35: break;
        case 29: 
          { System.out.println("lex: found return\n"); return (RETURN);
          }
        case 36: break;
        case 26: 
          { System.out.println("lex: found void\n"); return (VOID);
          }
        case 37: break;
        case 9: 
          { System.out.println("lex: found or\n"); return (OR);
          }
        case 38: break;
        case 25: 
          { System.out.println("lex: found char\n"); return (CHAR);
          }
        case 39: break;
        case 3: 
          { System.out.println("lex: found <\n"); return (LT);
          }
        case 40: break;
        case 15: 
          { System.out.println("lex: found string_literal\n"); return (STRINGLITERAL);
          }
        case 41: break;
        case 1: 
          { System.out.println("lex: found individual char\n"); return yytext();
          }
        case 42: break;
        case 4: 
          { System.out.println("lex: found >\n"); return (GT);
          }
        case 43: break;
        case 28: 
          { System.out.println("lex: found while\n"); return (WHILE);
          }
        case 44: break;
        case 11: 
          { System.out.println("lex: found ==\n"); return (EQUALEQUAL);
          }
        case 45: break;
        case 6: 
          { System.out.println("lex: found int_literal\n"); return (INTLITERAL);
          }
        case 46: break;
        case 24: 
          { System.out.println("lex: found bool_literal\n"); return (BOOLLITERAL);
          }
        case 47: break;
        case 14: 
          { System.out.println("lex: found >=\n"); return (GE);
          }
        case 48: break;
        case 2: 
          { System.out.println("lex: found id\n"); return (ID);
          }
        case 49: break;
        case 30: 
          { System.out.println("lex: found boolean\n"); return (BOOLEAN);
          }
        case 50: break;
        case 13: 
          { System.out.println("lex: found <<\n"); return (INSERT);
          }
        case 51: break;
        case 20: 
          { System.out.println("lex: found float_literal\n"); return (FLOATLITERAL);
          }
        case 52: break;
        case 19: 
          { System.out.println("lex: found not\n"); return (NOT);
          }
        case 53: break;
        case 12: 
          { System.out.println("lex: found <=\n"); return (LE);
          }
        case 54: break;
        case 10: 
          { System.out.println("lex: found !=\n"); return (NOTEQUAL);
          }
        case 55: break;
        case 16: 
          { System.out.println("lex: found int\n"); return (INT);
          }
        case 56: break;
        case 17: 
          { System.out.println("lex: found set\n"); return (SET);
          }
        case 57: break;
        case 5: 
          { System.out.println("lex: found percent\n"); return (MOD);
          }
        case 58: break;
        case 21: 
          { System.out.println("lex: found char_literal\n"); return (CHARLITERAL);
          }
        case 59: break;
        case 22: 
          { System.out.println("lex: found else\n"); return (ELSE);
          }
        case 60: break;
        case 8: 
          { System.out.println("lex: found if\n");
          }
        case 61: break;
        case 23: 
          { System.out.println("lex: found loop\n"); return (LOOP);
          }
        case 62: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
