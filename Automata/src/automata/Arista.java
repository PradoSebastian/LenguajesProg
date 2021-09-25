/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

/**
 *
 * @author sistemas
 */
public class Arista
{
    private Vertice origen;
    private Vertice destino;
    private Object costo;

    public Arista (Vertice v1, Vertice v2, Object costo)
    {
        this.origen = v1;
        this.destino = v2;
        
        this.costo = costo;
    }

    public Vertice getOrigen() {
        return origen;
    }

    public Vertice getDestino() {
        return destino;
    }

    public Object getCosto() {
        return costo;
    }

    public void setOrigen(Vertice origen) {
        this.origen = origen;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
    public String toString()
    {
        return "O: "+ origen + " D: " + destino + " costo : " + costo;
    }
    
}
