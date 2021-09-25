package co.edu.javeriana.car.ast;

import co.edu.javeriana.car.Car;

public class CambiarColor implements ASTNode {

	private ASTNode color;
	private ASTNode valor;
	private Car carro;
	
	
	public CambiarColor(ASTNode color, ASTNode valor, Car carro) {
		super();
		this.color = color;
		this.valor = valor;
		this.carro = carro;
	}

	@Override
	public Object execute(Context context) {
		Object o= valor.execute(context);
		String c = (String) color.execute(context);
		float f;
		if(o.getClass().getName()== "java.lang.Integer")
		{
			f = (int)o;
		}
		else
		{
			f=(float)o;
		}

		carro.color(getR(c),getG(c),getB(c),(f*255));
		return null;
	}
	
	private int getR(String rgba)
	{
		return Integer.parseInt(rgba.substring(1, 3) , 16); 
	}

	private int getG(String rgba)
	{
		return Integer.parseInt(rgba.substring(3, 5) , 16); 
	}

	private int getB(String rgba)
	{
		return Integer.parseInt(rgba.substring(5, 7) , 16); 
	}

}
