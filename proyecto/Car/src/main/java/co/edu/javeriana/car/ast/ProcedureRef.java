package co.edu.javeriana.car.ast;

import java.util.List;

public class ProcedureRef implements ASTNode {

	private String name;
	private List<ASTNode> parametros;
	
	public ProcedureRef(String name, List<ASTNode> parametros) {
		super();
		this.name = name;
		this.parametros = parametros;
	}

	@Override
	public Object execute(Context context) {

		Procedure p = (Procedure) context.get(name);
		
		Context contextProc = new Context(context.getContextGlobal());
		
		List<String> paramName = p.getParametros();
		
		for (int i = 0; i< paramName.size(); i++) 
		{
			contextProc.put(paramName.get(i), parametros.get(i).execute(context));
		}
		
		List<ASTNode> bodyProc = p.getBody();
		
		for (ASTNode node : bodyProc) 
		{
			node.execute(contextProc);
		}
		
		return null;
	}

}
