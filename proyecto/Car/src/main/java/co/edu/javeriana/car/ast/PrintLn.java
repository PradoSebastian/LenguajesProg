package co.edu.javeriana.car.ast;

public class PrintLn implements ASTNode {

	private ASTNode data;
	
	public PrintLn(ASTNode data) {
		super();
		this.data = data;
	}



	@Override
	public Object execute(Context context) {
		System.out.println(data.execute(context));
		return null;
	}

}
