package co.edu.javeriana.car.ast;

public class GreaterEquals implements ASTNode {

	private ASTNode object1;
	private ASTNode object2;
	
	public GreaterEquals(ASTNode object1, ASTNode object2) {
		super();
		this.object1 = object1;
		this.object2 = object2;
	}

	@Override
	public Object execute(Context context) {
		
		Object o1 = object1.execute(context);
		Object o2 = object2.execute(context);
		if(o1.getClass().getName() == "java.lang.Integer" || o1.getClass().getName() == "java.lang.Float")
		{
			float f1;
			if(o1.getClass().getName() == "java.lang.Integer")
			{
				f1 = (int)o1;
			}
			else
			{
				f1= (float)o1;
			}
			if(o2.getClass().getName() == "java.lang.Integer" || o2.getClass().getName() == "java.lang.Float")
			{
				float f2;
				if(o2.getClass().getName() == "java.lang.Integer")
				{
					f2 = (int)o2;
				}
				else
				{
					f2= (float)o2;
				}
				return f1 >= f2;
			}
		}
		return null;
	}

}
