package cast;

import java.lang.reflect.Field;
import cast.exceptions.NotStructCompException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Docente;
import model.EmpleadoAdministrativo;
import model.Estudiante;
import model.Persona;

public class CastHelper {
	
    
    public static void main(String args[]) {
        
        Docente d=new Docente(1, "Edna Krabbapel",1000000, "Lenguas");
        EmpleadoAdministrativo em=new EmpleadoAdministrativo(2, "Dwight Kurt Schrute III", 1500000, "Assistant (TO THE) Regional Manager");
        Estudiante es=new Estudiante(3, "Napoleon Dynamite", false);
        Persona p=new Persona(4, "Jane Doe");
        
        try {
            cast(em, Persona.class);
        } catch (Exception e) {
            System.out.println(e); 
       }
        
        
//        System.out.println("Persona");
//        Field[] f = p.getClass().getDeclaredFields();
//        
//        for (Field field : f) {
//            System.out.println(field.getName());
//        }
//        
//        Method[] m = p.getClass().getMethods();
//        
//        for (int i = 0; i<m.length-9;i++) 
//        {
//            System.out.println(m[i].getName());
//        }
//        
//        System.out.println("EmpleadoAdmin");
//        
//        f = em.getClass().getDeclaredFields();
//        
//        for (Field field : f) {
//            System.out.println(field.getName());
//        }
//        
//        m = em.getClass().getMethods();
//        
//        for (int i = 0; i<m.length-9;i++) 
//        {
//            System.out.println(m[i].getName());
//        }
        
        
        
    }
        
	/***
	 * Convierte el objeto o a un nuevo objeto de la clase c
	 * @param o objeto a convertir
	 * @param c nueva clase
	 * @return nuevo objeto de la clase c con los atributos de la 
	 * @throws ClassCastException
	 * @throws NotStructCompException si las clases no son compatibles estructuralmente
	 */
	public static Object cast(Object o,Class<? extends Object> c) throws ClassCastException,NotStructCompException{
            
            Field[] f = o.getClass().getDeclaredFields();
            Field[] f2 = c.getDeclaredFields();
            Object ob = null;
            
            try {            
                Constructor cons = c.getDeclaredConstructor();
                ob = cons.newInstance();
            } catch (Exception ex) {
                throw new ClassCastException();
            }            
            
            for (Field aux2 : f2) 
            {
                Field aux;
                try {
                    aux = o.getClass().getDeclaredField(aux2.getName());

                    if(aux2.getType().equals(aux.getType()))
                    {
                        aux.setAccessible(true);   //Para atributos privados
                        Object auxO = aux.get(o);
                        System.out.println("Atributo: "+ aux.getName() + " -Valor: " + auxO);
                        aux2.setAccessible(true);   //Para atributos privados
                        aux2.set(ob, auxO);
                    }
                    else
                    {
                        throw new NotStructCompException(o.getClass(), c);
                    }
                } catch (Exception ex) {
                    throw new NotStructCompException(o.getClass(), c);
                }
            }
            
            return ob;
	}
}
