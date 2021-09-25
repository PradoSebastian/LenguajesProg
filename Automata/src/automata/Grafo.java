/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author sistemas
 */
public abstract class Grafo 
{
    boolean dirigido;
    ArrayList<Vertice> vertices = new ArrayList<>();

    public Grafo(boolean dirigido, ArrayList<Vertice> vertices) 
    {
        this.dirigido = dirigido;
        this.vertices.addAll(vertices);
    }
    
    public boolean isDirigido() {
        return dirigido;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }
    
    public void añadirVertice (Vertice v)
    {
        vertices.add(v);
    }
    
    public abstract ArrayList<Vertice> vecinos (Vertice v);
    
}
