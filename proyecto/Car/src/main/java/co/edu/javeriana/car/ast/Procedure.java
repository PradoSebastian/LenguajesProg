package co.edu.javeriana.car.ast;

import java.util.List;

public class Procedure 
{
	private String name;
	private List<String> parametros;
	private List<ASTNode> body;
	
	public Procedure(String name, List<String> parametros, List<ASTNode> body) {
		super();
		this.name = name;
		this.parametros = parametros;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getParametros() {
		return parametros;
	}

	public void setParametros(List<String> parametros) {
		this.parametros = parametros;
	}

	public List<ASTNode> getBody() {
		return body;
	}

	public void setBody(List<ASTNode> body) {
		this.body = body;
	}

}
