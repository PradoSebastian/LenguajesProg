grammar Car;

@parser::members {

private Car car;

public CarParser(TokenStream input, Car car) {
    this(input);
    this.car = car;
}

}

start: program;

program: PROGRAM
		{
			List<ASTNode> body = new ArrayList<ASTNode>();
			Context context = new Context();
		} (sentence {body.add($sentence.node);})*
		{
			for(ASTNode n: body)
			{
				n.execute(context);
			}
		} END_PROGRAM;

sentence returns [ASTNode node]: 
moveFw {$node= $moveFw.node;}
| moveBk {$node= $moveBk.node;}
| voltearLt {$node= $voltearLt.node;}
| voltearRt {$node= $voltearRt.node;}
| cambiarC {$node = $cambiarC.node;}
| conditional {$node= $conditional.node;} 
| println {$node = $println.node;}
| var_decl {$node = $var_decl.node;}
| var_assign {$node = $var_assign.node;}
| repeat {$node = $repeat.node;}
| proc_decl {$node = $proc_decl.node;}
| proc_ref {$node = $proc_ref.node;}
;

moveFw returns [ASTNode node]: MOVE_FW expression
	{$node = new MoveFw($expression.node, car);};
	
moveBk returns [ASTNode node]: MOVE_BK expression
	{$node = new MoveBk($expression.node, car);};
	
voltearLt returns [ASTNode node]: TURN_LT expression
	{$node = new VoltearLt($expression.node, car);};
	
voltearRt returns [ASTNode node]: TURN_RT expression
	{$node = new VoltearRt($expression.node, car);};
	
cambiarC returns [ASTNode node]: SET_RGBA e1=expression COMA e2=expression
	{
		$node = new CambiarColor($e1.node, $e2.node, car);
	};

println returns [ASTNode node]: ECHO expressionSuperior
		{$node = new PrintLn($expressionSuperior.node);};
	
conditional returns [ASTNode node]: (IF PAR_OPEN expressionSuperior PAR_CLOSE
			{
				List<ASTNode> body = new ArrayList<>();
			}
			(s1=sentence {body.add($s1.node);})*
			ELSE
			{
				List<ASTNode> elseBody = new ArrayList<>();
			}
			(s2=sentence {elseBody.add($s2.node);})*
			{
				$node = new Conditional($expressionSuperior.node, body, elseBody);
			} 
			ENDIF)
			|(IF PAR_OPEN expressionSuperior PAR_CLOSE
			{
				List<ASTNode> body = new ArrayList<>();
			}
			(s1=sentence {body.add($s1.node);})*
			{
				List<ASTNode> elseBody = new ArrayList<>();
				$node = new Conditional($expressionSuperior.node, body, elseBody);
			} 
			ENDIF);
			
repeat returns [ASTNode node]: WHILE PAR_OPEN expressionSuperior PAR_CLOSE
		{
			List<ASTNode> body = new ArrayList<>();
		}
		(sentence {body.add($sentence.node);})*
		{
			$node = new While($expressionSuperior.node, body);
		} 
		END_WHILE
		;
		
proc_decl returns [ASTNode node]:
			PROC i1=ID PAR_OPEN
			{
				List<String> param = new ArrayList<>();
			}
			(i2=ID {param.add($i2.text);}
				(COMA i3=ID {param.add($i3.text);})*
			)?
			PAR_CLOSE
			{
				List<ASTNode> body = new ArrayList<>();
			}
			(sentence {body.add($sentence.node);})*
			END
			{
				$node = new ProcedureDecl($i1.text, param, body);
			}
			;

proc_ref returns [ASTNode node]:
			ID PAR_OPEN
			{
				List<ASTNode> param = new ArrayList<>();
			}
			(e1=expression {param.add($e1.node);} 
				(COMA e2=expression {param.add($e2.node);})*
			)?
			PAR_CLOSE
			{
				$node = new ProcedureRef($ID.text, param);
			}
			;
			
var_decl returns [ASTNode node]:
	DEF_VAR ID {$node = new VarDecl($ID.text);}
	;
	
var_assign returns [ASTNode node]:
	(ID ASSIGN expressionSuperior){$node = new VarAssign2($ID.text, $expressionSuperior.node);}
	| (DEF_VAR ID ASSIGN expressionSuperior) {$node = new VarAssign($ID.text, $expressionSuperior.node);}
	;
			
expressionSuperior returns [ASTNode node]:
					expression {$node = $expression.node;}
					| logicaOr {$node = $logicaOr.node;}
					;
			
expression returns [ASTNode node]:
		(f1=factor {$node = $f1.node;}
		((PLUS f2=factor{$node = new Addition($node, $f2.node);})
			|(MINUS f2=factor{$node = new Substraction($node, $f2.node);})
		)*)
		
		;
		
factor returns [ASTNode node]: t1=term {$node = $t1.node;}
					((MULT t2=term {$node = new Multiplication($node, $t2.node);})
						|(DIV t2=term {$node = new Division($node, $t2.node);})
					)*;

term returns [ASTNode node]:
		NUMBERINT {$node = new Constant(Integer.parseInt($NUMBERINT.text));}
		| BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));}
		| STRING {
			String s = $STRING.text;
			$node = new Constant(s.substring(1, s.length()-1));
		}
		| NUMBERDEC {$node = new Constant(Float.parseFloat($NUMBERDEC.text));}
		| COLOR {$node = new Constant($COLOR.text);}
		| ID {$node = new VarRef($ID.text);}		
		| PAR_OPEN expressionSuperior {$node = $expressionSuperior.node;} PAR_CLOSE
		| COLOR {$node = new Constant($COLOR.text);}
		;
		
logicaOr returns [ASTNode node]:
		o1=logicaAnd {$node = $o1.node;}
		(OR o2=logicaAnd{$node = new LogicalOr($node, $o2.node);})*
		;
		
logicaAnd returns [ASTNode node]:
		a1=comparacion_EQ_NEQ {$node = $a1.node;}
		(AND a2=comparacion_EQ_NEQ{$node = new LogicalAnd($node, $a2.node);})*
		;		

logicaNot returns [ASTNode node]:
		NOT term {$node = new Negation($term.node);}
		| term {$node = $term.node;}
		;
		
comparacion_EQ_NEQ returns [ASTNode node]:
					t1=comparacion_GL {$node = $t1.node;}
					(EQ t2=comparacion_GL {$node = new Equals($node, $t2.node);}
					| NEQ t2=comparacion_GL {$node = new NotEquals($node, $t2.node);}
					)*;

comparacion_GL returns [ASTNode node]:
				t1=expression {$node = $t1.node;}
				(GT t2=expression {$node = new GreaterThan($node, $t2.node);}
				| LT t2=expression {$node = new LowerThan($node, $t2.node);}
				| GEQ t2=expression {$node = new GreaterEquals($node, $t2.node);}
				| LEQ t2=expression {$node = new LowerEquals($node, $t2.node);}
				)*
				| logicaNot {$node = $logicaNot.node;};


PROGRAM: 'program';
END_PROGRAM: 'endprogram';
PROC: 'proc';
END: 'end';
DEF_VAR: 'def_var';
WHILE: 'while';
END_WHILE: 'endwhile';
ECHO: 'echo';
IF: 'if';
ENDIF: 'endif';
ELSE: 'else';
MOVE_FW: 'move_fw';
MOVE_BK: 'move_bk';
TURN_LT: 'turn_lt';
TURN_RT: 'turn_rt';
SET_RGBA: 'set_rgba';

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';

AND: 'and';
OR: 'or';
NOT: 'not';

GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '=';
NEQ: '<>';

ASSIGN: ':=';

PAR_OPEN: '(';
PAR_CLOSE: ')';
BRACKET_OPEN: '{';
BRACKET_CLOSE: '}'; 

COMA: ',';

BOOLEAN: 'true' | 'false';
ID: [A-Za-z_][a-zA-Z0-9_]*;
NUMBERDEC: [0-9]*'.'[0-9]+;
NUMBERINT: [0-9]+;
STRING: ('"')~['"']*('"');
COLOR: '#'[a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9][a-fA-F0-9];

WS: [ \t\r\n]+ -> skip;


