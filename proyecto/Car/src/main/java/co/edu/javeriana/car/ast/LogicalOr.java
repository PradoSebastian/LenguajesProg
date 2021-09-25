package co.edu.javeriana.car.ast;

public class LogicalOr implements ASTNode {

	private ASTNode operand1;
	private ASTNode operand2;
	
	public LogicalOr(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}


	public Object execute(Context context) {
		return (boolean)operand1.execute(context) || (boolean)operand2.execute(context);
	}

}
