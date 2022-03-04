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
 * Automata AFD
 */
public class Automata {
    ArrayList<EstadoA> listaEstados;
    String nombreEr;
    /**
     * @param nombreEr: nombre er del automata
     * @param listaEstados: la lista E de la TabTransiciones objeto
     */
    public Automata(String nombreEr,ArrayList<EstadoA> listaEstados){
        this.nombreEr = nombreEr;
        this.listaEstados = listaEstados;
        //lista conjuntos
    }
    
    public void graficarAutomata(){
        
    }
    
    public void Transicion(char caracter,String nomEstado){
        
    }
}
