package co.edu.javeriana.car.ast;

public class Constant implements ASTNode {

	private Object value;
	
	public Constant(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object execute(Context context) {
		return value;
	}

}
