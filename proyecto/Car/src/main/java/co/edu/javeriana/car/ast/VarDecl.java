package co.edu.javeriana.car.ast;

public class VarDecl implements ASTNode {

	private String name;
	
	
	
	public VarDecl(String name) {
		super();
		this.name = name;
	}



	@Override
	public Object execute(Context context) {
		context.put(name, new Object());
		return null;
	}

}
