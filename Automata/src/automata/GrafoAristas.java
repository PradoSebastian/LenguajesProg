/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;

/**
 *
 * @author prado
 */
public abstract class GrafoAristas extends Grafo
{
    
    ArrayList<Arista> aristas = new ArrayList<>();
    
    public GrafoAristas(boolean dirigido, ArrayList<Vertice> vertices, ArrayList<Arista> arista) 
    {
        super(dirigido, vertices);
        this.aristas.addAll(arista);
    }
    
    @Override
    public ArrayList<Vertice> vecinos(Vertice v)
    {
        ArrayList<Vertice> vecinos = new ArrayList<>();
        
        for (Arista a : aristas) 
        {
            if(dirigido == true)
            {
                if(a.getOrigen().equals(v))
                {
                    vecinos.add(a.getDestino());
                }
            }
            else
            {
                if(a.getOrigen().equals(v))
                {
                    vecinos.add(a.getDestino());
                }
                else if(a.getDestino().equals(v))
                {
                    vecinos.add(a.getOrigen());
                }
            }
        }
        
        return vecinos;
        
    }
    
    public ArrayList<Arista> aristasVecinas(Vertice v)
    {
         ArrayList<Arista> aristasVecinas = new ArrayList<>();
        
        for (Arista a : aristas) 
        {
            if(dirigido == true)
            {
                if(a.getOrigen().equals(v))
                {
                    aristasVecinas.add(a);
                }
            }
            else
            {
                if(a.getOrigen().equals(v))
                {
                    aristasVecinas.add(a);
                }
                else if(a.getDestino().equals(v))
                {
                    aristasVecinas.add(a);
                }
            }
        }
        
        return aristasVecinas;
    }
    
    public void añadirArista(Vertice origen , Vertice destino, int costo)
    {
        Arista aux = new Arista(origen, destino, costo);
        aristas.add(aux);     
    }

    public void eliminarAristas(Vertice v1)
    {
        ArrayList<Arista> aux = new ArrayList<>();
        aux.addAll(aristas);
        for (Arista a :aristas) 
        {
            if((a.getOrigen().equals(v1) || a.getDestino().equals(v1)))
            {
                aux.remove(a);
            }
        }
        aristas.clear();
        aristas.addAll(aux);
        aux.clear();
    }
    
    public void agregarArista (Arista a)
    {
        aristas.add(a);
    }
    
}

