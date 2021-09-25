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
public class GrafoAutomataFD extends GrafoAristas
{
    private ArrayList<Vertice> Q;
    private ArrayList<String> E;
    private ArrayList<Arista> p;
    private Vertice q0;
    private ArrayList<Vertice> F;

    public GrafoAutomataFD(boolean dirigido, ArrayList<Vertice> Q, ArrayList<String> E, ArrayList<Arista> p, Vertice q0, ArrayList<Vertice> F) 
    {
        super(dirigido, Q, p);
        this.Q = Q;
        this.E = E;
        this.p = p;
        this.q0 = q0;
        this.F = F;
    }
    
    public Vertice funcionDelta(Vertice estado, String c)
    {
        ArrayList<Arista> aristas = aristasVecinas(estado);
        Vertice resp = null;
        for (Arista a : aristas) 
        {
            if(stringContenido(c, (ArrayList<String>)a.getCosto()))
            {
                resp = a.getDestino();
                break; 
            }
        }
        return resp;
    }
    
    public Vertice funcionDeltaExtendida(Vertice estadoI, String s)
    {
        String aux;
        Vertice estado = estadoI;
        for (int i = 0; i < s.length(); i++) 
        {
            aux = s.substring(i, i+1);
            if(!stringContenido(aux, E))
                return null;
            estado = funcionDelta(estado, aux);
            if(estado == null)
            {
                return null;
            }
        }
        
        return estado;
        
    }
    
    public boolean validarPalabra(String palabra)
    {
//        Vertice estado = q0;
//        String caracter = palabra.substring(0, 1);
//        int i = 1;
//        boolean hayCamino = false;
//        boolean resp = false;
//        ArrayList<Arista> aristas;
//        while(i<=correo.length())
//        {            
//            if(!stringContenido(caracter, E))
//                return false;
//            aristas = new ArrayList<>();
//            aristas = aristasVecinas(estado);
//            for (Arista a : aristas) 
//            {
//                if(stringContenido(caracter, (ArrayList<String>)a.getCosto()))
//                {
//                    estado = a.getDestino();
//                    hayCamino = true;
//                    break;
//                }
//            }
//            if(!hayCamino)
//            {
//                return false;
//            }
//            if(i<correo.length())
//                caracter = palabra.substring(i, i+1);
//            i++;     
//            hayCamino = false;
//        }
//        
//        if(perteneceVertice(estado, F))
//            resp = true;
//        
//        return resp;
        
        boolean resp = false;
        Vertice actual = funcionDeltaExtendida(q0, palabra);
        if(actual == null)
            return false;
        if(perteneceVertice(actual, F))
            resp = true;

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

    
}
