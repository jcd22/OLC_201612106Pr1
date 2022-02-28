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
    
    int numerados;
    ArrayList<String> terminales;
    ArrayList[] siguientes; // el tamano sera igual al lenght de numerados.
    
    public TabSiguientes(){
        numerados = 0;
        terminales = new ArrayList<>();
        siguientes = new ArrayList[3]; // por el momento
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

    public ArrayList[] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList[] siguientes) {
        this.siguientes = siguientes;
    }
}
