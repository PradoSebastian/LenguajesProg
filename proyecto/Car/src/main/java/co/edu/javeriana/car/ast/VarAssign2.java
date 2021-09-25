package co.edu.javeriana.car.ast;

public class VarAssign2 implements ASTNode {

	private String name;
	private ASTNode expression;
	
	
	
	public VarAssign2(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(Context context) {
		Context c = context.buscarContext(name);
		c.put(name, expression.execute(context));
		return null;
	}

}
