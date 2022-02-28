/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoArbol;

import Analizadores.M_Expresion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chana
 */
public class Arbol {
    M_Expresion raiz;
    String dotArbol="";
    int numEstados=0;
    int numTerminales=0;
    
    TabSiguientes tabla;               

    public static int numArbol=0;
    
    /**
     * @param ListaEr, recibe el param simulando una hereda
     */
    public Arbol(M_Expresion raiz){
        this.raiz = raiz;
        this.tabla = new TabSiguientes();
    }
    
    public void GraficaArbol() throws InterruptedException{
         try
                {           
                    FileOutputStream arch = new FileOutputStream("Arbol"+numArbol+".dot");
            
                    try (PrintStream imprimir = new PrintStream(arch))
                    {
                        dotArbol += "graph g{"+"\n";
                        dotArbol += "graph [pad=\"0.15\", nodesep=\"0.15\", ranksep=\"1\"];"+"\n";
                        postOrderGrafA(this.raiz);
                        dotArbol += "}";
                        imprimir.println(dotArbol);
                    }
                       //limpia dotArbol 
                    Runtime.getRuntime().exec("dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
                    Thread.sleep(1000);
                    Runtime.getRuntime().exec("nomacs Arbol"+numArbol+".png");
                    Thread.sleep(1000);
                
                }catch(IOException e){ }      
       
    }
    
    /**
     * @param this.raiz de tipo M_Expresion
     */
    public void postOrderGrafA(M_Expresion raiz){
        if(raiz != null){
            postOrderGrafA(raiz.getEr_izq());
            postOrderGrafA(raiz.getEr_der());
            //es un no terminal
            if(raiz.getEr_izq() != null || raiz.getEr_der() != null){
                // para los dif caracteres
                if(raiz.getNombre().equals(".")){
                    if(raiz.getEr_izq().getAnulable()==true && raiz.getEr_der().getAnulable()==true){
                        raiz.setAnulable(true);                      
                    }else{                       
                    }
                    ////////primeros/////////
                    if(raiz.getEr_izq().getAnulable() == true){
                        for(int i:raiz.getEr_izq().getPrimeros()){//P izq
                        raiz.getPrimeros().add(i);
                        }
                        for(int i:raiz.getEr_der().getPrimeros()){//P der
                            raiz.getPrimeros().add(i);
                        }
                    }else{
                        for(int i:raiz.getEr_izq().getPrimeros()){//P der
                        raiz.getPrimeros().add(i);
                        }
                    }
                    ////////ultimos/////////
                    if(raiz.getEr_der().getAnulable() == true){
                        for(int i:raiz.getEr_izq().getUltimos()){//U izq
                        raiz.getUltimos().add(i);
                        }
                        for(int i:raiz.getEr_der().getUltimos()){//U der
                            raiz.getUltimos().add(i);
                        }
                    }else{
                        for(int i:raiz.getEr_der().getUltimos()){//U der
                        raiz.getUltimos().add(i);
                        }
                    }
                    // guarda para grafvis
                    String prim = raiz.getPrimeros()+"";
                    String ult =  raiz.getUltimos()+"";
                    String anu = "";
                    if(raiz.getAnulable() == true){anu="A";}
                    else{anu="N";}
                    
                    raiz.setNumEstado(numEstados++);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ "+anu+" |{"+prim+"|<here>"+raiz.getNombre()+"|"+ult+"}| _ }\"];"+"\n";
                    
                }else if(raiz.getNombre().equals("|")){
                    if(raiz.getEr_izq().getAnulable()==true || raiz.getEr_der().getAnulable()==true){
                        raiz.setAnulable(true);
                    }else{
                      
                    }
                    ///////////primeros////////////
                    for(int i:raiz.getEr_izq().getPrimeros()){//P izq
                        raiz.getPrimeros().add(i);
                    }
                    for(int i:raiz.getEr_der().getPrimeros()){//P der
                        raiz.getPrimeros().add(i);
                    }
                    //////////ultimos///////////
                    for(int i:raiz.getEr_izq().getUltimos()){//U izq
                        raiz.getUltimos().add(i);
                    }
                    for(int i:raiz.getEr_der().getUltimos()){//U der
                        raiz.getUltimos().add(i);
                    }
                    
                    // guarda para grafvis
                    String prim = raiz.getPrimeros()+"";
                    String ult =  raiz.getUltimos()+"";
                    String anu = "";
                    if(raiz.getAnulable() == true){anu="A";}
                    else{anu="N";}
                    
                    raiz.setNumEstado(numEstados++);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ "+anu+" |{"+prim+"|<here>"+raiz.getNombre()+"|"+ult+"}| _ }\"];"+"\n";
                    
                }else if(raiz.getNombre().equals("*")){                    
                    raiz.setAnulable(true);
                    /////////primeros/////////
                    for(int i:raiz.getEr_izq().getPrimeros()){//P izq
                        raiz.getPrimeros().add(i);
                    }
                    /////////ultimos//////////
                    for(int i:raiz.getEr_izq().getUltimos()){//U izq
                        raiz.getUltimos().add(i);
                    }
                    
                    // guarda para grafvis
                    String prim = raiz.getPrimeros()+"";
                    String ult =  raiz.getUltimos()+"";
                    String anu = "";
                    if(raiz.getAnulable() == true){anu="A";}
                    else{anu="N";}
                    
                    raiz.setNumEstado(numEstados++);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ "+anu+" |{"+prim+"|<here>"+raiz.getNombre()+"|"+ult+"}| _ }\"];"+"\n";
                    
                }else if(raiz.getNombre().equals("+")){
                    if(raiz.getEr_izq().getAnulable()==true){
                        raiz.setAnulable(true);      
                    }else{
                    }
                    /////////primeros/////////
                    for(int i:raiz.getEr_izq().getPrimeros()){//P izq
                        raiz.getPrimeros().add(i);
                    }
                    /////////ultimos//////////
                    for(int i:raiz.getEr_izq().getUltimos()){//U izq
                        raiz.getUltimos().add(i);
                    }
                    
                    // guarda para grafvis
                    String prim = raiz.getPrimeros()+"";
                    String ult =  raiz.getUltimos()+"";
                    String anu = "";
                    if(raiz.getAnulable() == true){anu="A";}
                    else{anu="N";}
                    
                    raiz.setNumEstado(numEstados++);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ "+anu+" |{"+prim+"|<here>"+raiz.getNombre()+"|"+ult+"}| _ }\"];"+"\n";
                    
                } else if(raiz.getNombre().equals("?")){
                    raiz.setAnulable(true);
                    /////////primeros/////////
                    for(int i:raiz.getEr_izq().getPrimeros()){//P izq
                        raiz.getPrimeros().add(i);
                    }
                    /////////ultimos//////////
                    for(int i:raiz.getEr_izq().getUltimos()){//U izq
                        raiz.getUltimos().add(i);
                    }
                    
                    // guarda para grafvis
                    String prim = raiz.getPrimeros()+"";
                    String ult =  raiz.getUltimos()+"";
                    String anu = "";
                    if(raiz.getAnulable() == true){anu="A";}
                    else{anu="N";}
                    
                    raiz.setNumEstado(numEstados++);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ "+anu+" |{"+prim+"|<here>"+raiz.getNombre()+"|"+ult+"}| _ }\"];"+"\n";
                }

                //Enlaces              
                if(raiz.getEr_izq() != null){dotArbol += raiz.getNumEstado()+"--"+raiz.getEr_izq().getNumEstado()+";"+ "\n";}
                if(raiz.getEr_der() != null){dotArbol += raiz.getNumEstado()+"--"+raiz.getEr_der().getNumEstado()+";"+ "\n";}    

            
            } else{// si es un terminal
                raiz.setNumEstado(numEstados++);
                numTerminales++;
                dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ N |{"+numTerminales+"|<here>"+raiz.getNombre()+"|"+numTerminales+"}|"+numTerminales+"}\"];"+"\n";                
                
                //primeros y utlimos
                raiz.getPrimeros().add(numTerminales);
                raiz.getUltimos().add(numTerminales);
                
                //tabla
                tabla.setNumerados(numTerminales);//obtengo numerados n
                tabla.terminales.add(raiz.getNombre());//obtengo terminales
            }

        }
    }//postOrd
    
    
    //--------------------------------------
    
    public int getNumTerminales() {
        return numTerminales;
    }

    public void setNumTerminales(int numTerminales) {
        this.numTerminales = numTerminales;
    }
      
    public TabSiguientes getTabla() {
        return tabla;
    }

    public void setTabla(TabSiguientes tabla) {
        this.tabla = tabla;
    }
    
}
