package co.edu.javeriana.car.ast;

public class Negation implements ASTNode {

	private ASTNode booleano;
	
	public Negation(ASTNode booleano) {
		super();
		this.booleano = booleano;
	}



	@Override
	public Object execute(Context context) {
		return !((boolean)booleano.execute(context));
	}

}
