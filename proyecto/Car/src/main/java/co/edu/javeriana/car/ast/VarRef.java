package co.edu.javeriana.car.ast;

public class VarRef implements ASTNode {

	private String name;
	
	
	public VarRef(String name) {
		super();
		this.name = name;
	}


	@Override
	public Object execute(Context context) {
		Object o = context.get(name);
		return o;
	}

}
