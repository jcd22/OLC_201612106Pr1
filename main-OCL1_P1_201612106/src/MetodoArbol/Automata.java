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
    
    public boolean ValidarCadena(String entrada){
        int numEstado = 0;
        for (int i = 0; i < entrada.length(); i++) {
          EstadoA auxEstado = listaEstados.get(numEstado);//el estado actual
          if(i == entrada.length()-1){//ultimo caracter entrada
              if (auxEstado.getAceptacion() == true) {
                  return true;
              }else{
                  return false;
              }
          }
          // indice en terminalesTran auxEstado
          int icaracter = auxEstado.getTerminalesTran().indexOf(entrada.charAt(i));
          if(icaracter == -1){//caracter invalida,cadena invalida
              return false;
          }
          else{//si existe caracter
              System.out.println("transicion a:"+auxEstado.getEstadosTran().get(icaracter));
              numEstado = icaracter;
          } 
          
        }//i
        return false;
    }
    
    public void graficarAutomata(){
        
    }
    
    public void Transicion(char caracter,String nomEstado){
        
    }
}
