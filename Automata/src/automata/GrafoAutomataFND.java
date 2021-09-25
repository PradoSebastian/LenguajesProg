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
public class GrafoAutomataFND extends GrafoAristas
{
    
    private static final String EPSILON = "Epsilon";
    
    private ArrayList<Vertice> Q;
    private ArrayList<String> E;
    private ArrayList<Arista> p;
    private Vertice q0;
    private ArrayList<Vertice> F;

    public GrafoAutomataFND(boolean dirigido, ArrayList<Vertice> Q, ArrayList<String> E, ArrayList<Arista> p, Vertice q0, ArrayList<Vertice> F) 
    {
        super(dirigido, Q, p);
        this.Q = Q;
        this.E = E;
        this.p = p;
        this.q0 = q0;
        this.F = F;
    }
    
    public ArrayList<Vertice> funcionDelta(ArrayList<Vertice> estados, String c)
    {
        ArrayList<Vertice> actuales = new ArrayList<>();
        Vertice aux = null;
        for (Vertice estado : estados) 
        {
            ArrayList<Arista> aristas = aristasVecinas(estado);

            for (Arista a : aristas) 
            {   
                if(stringContenido(c, (ArrayList<String>)a.getCosto()))
                {
                    aux = a.getDestino();
                    actuales.add(aux);
                    actuales.addAll(validarEpsilon(aux));
                    
                }
            }
        }   
        return actuales;
    }
    
    public ArrayList<Vertice> funcionDeltaExtendida(Vertice estadoI, String s)
    {
        String aux;
        ArrayList<Vertice> actuales = new ArrayList<>();
        actuales.add(estadoI);
        for (int i = 0; i < s.length(); i++) 
        {
            aux = s.substring(i, i+1);
            if(!stringContenido(aux, E))
                return new ArrayList<>();
            actuales = funcionDelta(actuales, aux);
            if(actuales.isEmpty())
            {
                return new ArrayList<>();
            }
        }
        
        return actuales;     
    }
    
    public boolean validarPalabra(String palabra)
    {
        boolean resp = false;
        ArrayList<Vertice> actuales = funcionDeltaExtendida(q0, palabra);
        if(actuales.isEmpty())
            return false;
        for (Vertice estado : actuales) 
        {
            if(perteneceVertice(estado, F))
                resp = true;
        }

        return resp;
    }
    
    public boolean perteneceVertice(Vertice v, ArrayList<Vertice> lista)
    {
        for (Vertice l : lista) 
        {
            if(v.equals(l))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean stringContenido(String c, ArrayList<String> list)
    {
        for (String l : list) 
        {
            if(c.equals(l))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vertice> validarEpsilon(Vertice estado)
    {
        ArrayList<Vertice> array = new ArrayList<>();
        ArrayList<Arista> aristasE = aristasVecinas(estado);
        for (Arista arista : aristasE) 
        {
            if(stringContenido(EPSILON, (ArrayList<String>)arista.getCosto()))
            {
                array.addAll(validarEpsilon(arista.getDestino()));
                if(!perteneceVertice(arista.getDestino(), array))
                    array.add(arista.getDestino());
            }
        }
        return array;
    }
    
}
