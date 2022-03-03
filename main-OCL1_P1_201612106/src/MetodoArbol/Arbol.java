/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoArbol;

import Analizadores.M_Expresion;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
         try{
//                  final String os = System.getProperty("os.name");
//                    if (os.contains("Windows")) {
//                    Runtime.getRuntime().exec("cd Arboles");
//                    } else {
//                    Runtime.getRuntime().exec("clear");
//                    }            
                    //Thread.sleep(1000);       
                    FileOutputStream arch = new FileOutputStream(new File(".\\Arboles\\Arbol"+numArbol+".dot"));
                    
                    try (PrintStream imprimir = new PrintStream(arch))
                    {
                        dotArbol += "graph g{"+"\n";
                        dotArbol += "graph [pad=\"0.15\", nodesep=\"0.15\", ranksep=\"1\"];"+"\n";
                        postOrderGrafA(this.raiz);
                        dotArbol += "}";
                        imprimir.println(dotArbol);
                    }
                       //limpia dotArbol                   
                    Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd .\\Arboles && dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
                    //Runtime.getRuntime().exec("dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
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
                   
                    // Siguientes
                    calcSiguientes(raiz.getEr_izq().getUltimos(),raiz.getEr_der().getPrimeros());
                    
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
                    
                    // Siguientes
                    calcSiguientes(raiz.getEr_izq().getUltimos(),raiz.getEr_izq().getPrimeros());
                    
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
                    // Siguientes
                    calcSiguientes(raiz.getEr_izq().getUltimos(),raiz.getEr_izq().getPrimeros());
                    
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
                //solo para generar el grafvis
                if(raiz.esEsCadena()){
                    String auxNombre = raiz.getNombre().substring(1,raiz.getNombre().length()-1);
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ N |{"+numTerminales+"|<here>"+auxNombre+"|"+numTerminales+"}|"+numTerminales+"}\"];"+"\n";
                }else{
                    dotArbol += raiz.getNumEstado()+"[shape=Mrecord,color=blue1,label=\"{ N |{"+numTerminales+"|<here>"+raiz.getNombre()+"|"+numTerminales+"}|"+numTerminales+"}\"];"+"\n";                
                }
                //primeros y utlimos
                raiz.getPrimeros().add(numTerminales);
                raiz.getUltimos().add(numTerminales);
                
                //tabla
                tabla.setNumerados(numTerminales);//obtengo numerados n
                tabla.terminales.add(raiz.getNombre());//obtengo terminales
                tabla.getSiguientes().add(new ArrayList<Integer>()); //inserta lista hasta n
            }

        }
    }//postOrd

    /**
     * @param actual: array(primeros o ultimos)con actuales asignara siguientes
     * @param sigActual: array(primeros o ultimos)de siguietes a asignar a actual
     */
    public void calcSiguientes(ArrayList<Integer> actual,ArrayList<Integer> SigActual){
        for(int i =0;i<actual.size();i++){
            int posS =actual.get(i)-1;//pos en columna siguientes
            for(int j=0;j<SigActual.size();j++){//recorro los Siguientes meterlos en la posS de Tabla Siguientes
                
             tabla.getSiguientes().get(posS).add(SigActual.get(j));
             
            }//for           
        }//for actual
        
    }
    
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
