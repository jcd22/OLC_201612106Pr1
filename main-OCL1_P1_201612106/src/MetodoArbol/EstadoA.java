/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoArbol;

import java.util.ArrayList;

/**
 *
 * @author chana
 */
public class EstadoA {
    
    public static int numEstadoA=0;
    String nombre;
    ArrayList<Integer> contenido;
    ArrayList<String>  terminalesTran;//terminales con los q se viaja
    ArrayList<String>  estadosTran;//estados a los q se vija con los term
    ArrayList<ArrayList<Integer>> contenidosTran;//contenido del estado a ir
    boolean aceptacion;//cuando ya este todo lleno se verificara esta var
    
    public EstadoA(){
        this.nombre ="";
        this.contenido = new ArrayList<Integer>();
        this.terminalesTran = new ArrayList<String>();
        this.estadosTran = new ArrayList<String>();
        aceptacion = false;
    }
    
    public EstadoA(ArrayList<Integer> contenido){       
        this.nombre ="";
        this.contenido = contenido;
        this.terminalesTran = new ArrayList<String>();
        this.estadosTran = new ArrayList<String>();
        aceptacion = false;
    }  

    public ArrayList<ArrayList<Integer>> getContenidosTran() {
        return contenidosTran;
    }

    public void setContenidosTran(ArrayList<ArrayList<Integer>> contenidosTran) {
        this.contenidosTran = contenidosTran;
    }

    
    
    public ArrayList<String> getTerminalesTran() {
        return terminalesTran;
    }

    public void setTerminalesTran(ArrayList<String> terminalesTran) {
        this.terminalesTran = terminalesTran;
    }

    public ArrayList<String> getEstadosTran() {
        return estadosTran;
    }

    public void setEstadosTran(ArrayList<String> estadosTran) {
        this.estadosTran = estadosTran;
    }
    
    ////////////
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<Integer> contenido) {
        this.contenido = contenido;
    }

    public boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }
    
}
