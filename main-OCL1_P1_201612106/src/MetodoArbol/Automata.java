/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoArbol;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
        this.listaEstados = listaEstados;//estados con transiciones
       
    }
    
    public void graficarAutomata(){
     try {          
        String concatText = "digraph finite_state_machine {\n" +
                            "fontname=\"Helvetica,Arial,sans-serif\"\n" +
                            "node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                            "edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                            "rankdir=LR;\n" +
//                            "node [shape = doublecircle]; 0 3 4 8;\n" +
                            "node [shape = circle];\n";
        for (int i = 0; i < listaEstados.size(); i++) { //recorre todos los estados
            for (int j = 0; j < listaEstados.get(i).getEstadosTran().size(); j++) {//hace transiciones del estado
                String carTran = "";
                if(listaEstados.get(i).getTerminalesTran().get(j).charAt(0) == '\"'){
                    carTran = listaEstados.get(i).getTerminalesTran().get(j).substring(1,listaEstados.get(i).getTerminalesTran().get(j).length()-1);
                }else{
                    carTran = listaEstados.get(i).getTerminalesTran().get(j);
                }
                if(listaEstados.get(i).getAceptacion()) concatText += listaEstados.get(i).getNombre()+" [shape = doublecircle];\n";
                concatText +=  listaEstados.get(i).getNombre()+" -> "+listaEstados.get(i).getEstadosTran().get(j)+" [label = \""+carTran+"\"];\n";                                                          
            }
        }
        concatText +=       "}";
        
        FileOutputStream arch = new FileOutputStream(new File(".\\Automatas\\Auto"+Arbol.numArbol+".dot"));
                    
            try (PrintStream imprimir = new PrintStream(arch))
            {
                imprimir.println(concatText);
            }
               //limpia concatText                   
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd .\\Automatas && dot -Tpng Auto"+Arbol.numArbol+".dot -o Arbol"+Arbol.numArbol+".png");
            //Runtime.getRuntime().exec("dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
            Thread.sleep(1000);
         
     } catch (Exception e) {
     }
    }//fin
    
    public void Transicion(char caracter,String nomEstado){
        
    }
    
    ///////////////////////////////////
    
    public ArrayList<EstadoA> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(ArrayList<EstadoA> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public String getNombreEr() {
        return nombreEr;
    }

    public void setNombreEr(String nombreEr) {
        this.nombreEr = nombreEr;
    }
    
    
}
