package co.edu.javeriana.car.ast;

import java.util.List;

public class Conditional implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;

	public Conditional(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}

	@Override
	public Object execute(Context context) {
		
		Context c = new Context(context);

		if((boolean)condition.execute(c))
		{
			for (ASTNode n : body) {
				n.execute(c);
			}
		}
		else
		{
			for (ASTNode n : elseBody) {
				n.execute(c);
			}
		}
		
		return null;
	}

}
