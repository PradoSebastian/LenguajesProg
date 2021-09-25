package co.edu.javeriana.car.ast;

import java.util.List;

public class ProcedureDecl implements ASTNode {

	private String name;
	private List<String> parametros;
	private List<ASTNode> body;
	
	
	public ProcedureDecl(String name, List<String> parametros, List<ASTNode> body) {
		super();
		this.name = name;
		this.parametros = parametros;
		this.body = body;
	}


	@Override
	public Object execute(Context context)
	{
		Procedure p = new Procedure(name, parametros, body);
		context.put(name, p);
		return null;
	}

}
