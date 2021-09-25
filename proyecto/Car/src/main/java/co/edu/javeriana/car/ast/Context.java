package co.edu.javeriana.car.ast;

import java.util.HashMap;

public class Context 
{
	private HashMap<String, Object> tabla;
	private Context anterior;
	
	public Context(HashMap<String, Object> tabla, Context anterior) {
		super();
		this.tabla = tabla;
		this.anterior = anterior;
	}
	
	public Context()
	{
		this.tabla = new HashMap<>();
		this.anterior = null;
	}
	
	public Context(Context ant)
	{
		this.anterior = ant;
		this.tabla = new HashMap<>();
	}
	
	public void put(String nombre, Object valor)
	{
		tabla.put(nombre, valor);
	}
	
	public Object get(String nombre)
	{
		boolean bandera = false;
		Object r = null;
		Context busqueda = this;
		do
		{
			r = busqueda.tabla.get(nombre);
			if(r!=null)
			{
				bandera = true;
			}
			else
			{
				busqueda = busqueda.anterior;
			}
		}while(busqueda!=null && !bandera);
		return r;
	}
	
	public Context buscarContext(String variable)
	{
		boolean bandera = false;
		Object r = null;
		Context busqueda = this;
		do
		{
			r = busqueda.tabla.get(variable);
			if(r!=null)
			{
				bandera = true;
			}
			else
			{
				busqueda = busqueda.anterior;
			}
		}while(busqueda!=null && !bandera);
		return busqueda;
	}
	
	public Context getContextGlobal()
	{
		Context busqueda = this;
		while(busqueda.anterior != null)
		{
			busqueda = busqueda.anterior;
		}
		return busqueda;
	}

}
