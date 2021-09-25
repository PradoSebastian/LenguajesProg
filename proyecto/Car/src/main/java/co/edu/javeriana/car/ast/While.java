package co.edu.javeriana.car.ast;

import java.util.List;

public class While implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	
	
	
	public While(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}



	@Override
	public Object execute(Context context) {
		
		Context c = new Context(context);
		
		while((boolean)condition.execute(c))
		{
			for (ASTNode n : body) {
				n.execute(c);
			}
		}
		return null;
	}

}
