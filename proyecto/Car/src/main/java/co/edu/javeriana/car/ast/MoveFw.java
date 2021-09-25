package co.edu.javeriana.car.ast;

import co.edu.javeriana.car.Car;

public class MoveFw implements ASTNode {

	private ASTNode valor;
	private Car carro;
	
	
	
	public MoveFw(ASTNode valor, Car carro) {
		super();
		this.valor = valor;
		this.carro = carro;
	}



	@Override
	public Object execute(Context context) {
		Object o= valor.execute(context);
		float f;
		if(o.getClass().getName()== "java.lang.Integer")
		{
			f = (int)o;
		}
		else
		{
			f=(float)o;
		}
		 
		carro.forward(f);
		return null;
	}

}
