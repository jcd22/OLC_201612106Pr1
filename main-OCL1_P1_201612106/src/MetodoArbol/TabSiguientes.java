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
public class TabSiguientes {
    
    int numerados;//tiene el tamano de filas
    ArrayList<String> terminales;
    ArrayList<ArrayList<Integer>> siguientes; // el tamano sera igual al lenght de numerados.
    
    public TabSiguientes(){
        numerados = 0;
        terminales = new ArrayList<>();
        siguientes = new ArrayList<>(); // por el momento
    }

    //----------------
    
    public ArrayList<ArrayList<Integer>> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<ArrayList<Integer>> siguientes) {
        this.siguientes = siguientes;
    }

    public int getNumerados() {
        return numerados;
    }

    public void setNumerados(int numerados) {
        this.numerados = numerados;
    }

    public ArrayList<String> getTerminales() {
        return terminales;
    }

    public void setTerminales(ArrayList<String> terminales) {
        this.terminales = terminales;
    }

}
