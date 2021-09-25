/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;

/**
 *
 * @author aulasingenieria
 */
public class Automata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
//        Automata a = new Automata();
//        
//         String[] emails={
//            "alejandro-sierra@javeriana.edu.co",
//            "alejandrosierra@javeriana.edu.co",
//            "alejandrosierra@javeriana.edu.co.",
//            "alejandrosierra@javeriana",
//            "alejandrosierra",
//            "@javeriana.edu.co"
//        };
//        for (String email : emails){
//            System.out.println(email+"-"+(a.testEmailAFD(email)?"OK":"NOK"));
//        }
        
        Automata a = new Automata();

        String[] cadenas={
            "001", "000", "00", "010", "0010"
        };
        for (String cadena : cadenas){
            System.out.println(cadena+"-"+(a.testEmailAFND(cadena)?"OK":"NOK"));
        }
        
    }
    
    private GrafoAutomataFD grafoAFD;
    private GrafoAutomataFND grafoAFND;
    
    public Automata()
    {
//        ArrayList<Vertice> vertices = new ArrayList<>();
//        vertices.add(new Vertice("Estado 1Inicial"));
//        vertices.add(new Vertice("Estado 2"));
//        vertices.add(new Vertice("Estado 3"));
//        vertices.add(new Vertice("Estado 4"));
//        vertices.add(new Vertice("Estado 5"));
//        vertices.add(new Vertice("Estado 6Final"));
//        
//        ArrayList<String> entradas = new ArrayList<>();
//        ArrayList<String> abc = new ArrayList<>();
//        for(int i = 0; i<26; i++)
//        {
//            char c = (char)('a'+i);
//            String a = String.valueOf(c);
//            abc.add(a);
//        }
//        for(int i = 0; i<26; i++)
//        {
//            char c = (char)('A'+i);
//            String a = String.valueOf(c);
//            abc.add(a);
//        }
//        //abc.add("-");
//        ArrayList<String> arroba = new ArrayList<>();
//        arroba.add("@");
//        ArrayList<String> punto = new ArrayList<>();
//        punto.add(".");
//        entradas.addAll(abc);
//        entradas.addAll(arroba);
//        entradas.addAll(punto);
//        
//        ArrayList<Arista> funcion = new ArrayList<>();
//        funcion.add(new Arista(vertices.get(0), vertices.get(1), abc));
//        funcion.add(new Arista(vertices.get(1), vertices.get(1), abc));
//        funcion.add(new Arista(vertices.get(1), vertices.get(2), arroba));
//        funcion.add(new Arista(vertices.get(2), vertices.get(3), abc));
//        funcion.add(new Arista(vertices.get(3), vertices.get(3), abc));
//        funcion.add(new Arista(vertices.get(3), vertices.get(4), punto));
//        funcion.add(new Arista(vertices.get(4), vertices.get(5), abc));
//        funcion.add(new Arista(vertices.get(5), vertices.get(5), abc));
//        funcion.add(new Arista(vertices.get(5), vertices.get(4), punto));
//        
//        Vertice inicial = vertices.get(0);
//        
//        ArrayList<Vertice> finales = new ArrayList<>();
//        finales.add(vertices.get(5));
//        
//        grafoAFD = new GrafoAutomataFD(true, vertices, entradas, funcion, inicial, finales);
        
        ArrayList<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice("Estado 1Inicial"));
        vertices.add(new Vertice("Estado 2"));
        vertices.add(new Vertice("Estado 3Final"));
        
        ArrayList<String> entradas = new ArrayList<>();
        ArrayList<String> cero = new ArrayList<>();
        cero.add("0");
        ArrayList<String> ceroUno = new ArrayList<>();
        ceroUno.add("0");
        ceroUno.add("1");
        entradas.addAll(ceroUno);
        
        ArrayList<Arista> funcion = new ArrayList<>();
        funcion.add(new Arista(vertices.get(0), vertices.get(1), cero));
        funcion.add(new Arista(vertices.get(1), vertices.get(1), cero));
        funcion.add(new Arista(vertices.get(1), vertices.get(2), ceroUno));
        
        Vertice inicial = vertices.get(0);
        
        ArrayList<Vertice> finales = new ArrayList<>();
        finales.add(vertices.get(2));
        
        grafoAFND = new GrafoAutomataFND(true, vertices, entradas, funcion, inicial, finales);
        
    }
    
    public boolean testEmailAFD(String email) 
    {
        return grafoAFD.validarPalabra(email);
    }
    
    public boolean testEmailAFND (String email) 
    {
        return grafoAFND.validarPalabra(email);
    }
    
}
