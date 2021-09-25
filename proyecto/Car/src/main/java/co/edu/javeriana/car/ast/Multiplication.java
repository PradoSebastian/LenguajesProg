package co.edu.javeriana.car.ast;

public class Multiplication implements ASTNode{

	private ASTNode operand1;
	private ASTNode operand2;
	
	
	public Multiplication(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Context context) {
		
		Object o= operand1.execute(context);
		float f1;
		if(o.getClass().getName()== "java.lang.Integer")
		{
			f1 = (int)o;
		}
		else
		{
			f1=(float)o;
		}
		
		o= operand2.execute(context);
		float f2;
		if(o.getClass().getName()== "java.lang.Integer")
		{
			f2 = (int)o;
		}
		else
		{
			f2=(float)o;
		}

		return f1 * f2;
	}

}
