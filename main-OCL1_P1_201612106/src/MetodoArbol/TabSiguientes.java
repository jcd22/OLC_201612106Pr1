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
    public void GraficarTabla(){
        try {
            String concatText= "";
            concatText += "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "	<title></title>\n" +
                            "	<link rel=\"stylesheet\" type=\"text/css\" href=\"masEscuchados1.css\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                        "<div class=\"container\">\n"+
                            "<table>\n";
            concatText += "<thead>\n" +
                                "<tr>\n" +
                                 "<th>num hoja</th>\n" +
                                 "<th>hoja</th>\n" +
                                 "<th>Siguientes</th>\n" +
                                "</tr>\n" +
                         "</thead>\n"+
                         "<tbody>\n";
            for (int i = 0; i <numerados; i++) {
                concatText += "<tr>\n" +
                                "<td> "+(i+1)+" </td>\n" +
                                "<td> "+terminales.get(i)+" </td>\n" +
                                "<td> "+siguientes.get(i)+" </td>\n" +
                             "</tr>\n";
            }
            concatText +="</tbody>\n"+
                        "</table>\n"+
                        "</div>\n" +
                        "</body>\n" +
                        "<script src=\"masEscuchados1.js\"></script>\n" +
                        "</html>";
            
            FileOutputStream arch = new FileOutputStream(new File(".\\Siguientes\\TabSig"+Arbol.numArbol+".html"));
                    
            try (PrintStream imprimir = new PrintStream(arch))
            {
                imprimir.println(concatText);
            }
               //limpia dotArbol                   
            //Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd .\\Arboles && dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
            //Runtime.getRuntime().exec("dot -Tpng Arbol"+numArbol+".dot -o Arbol"+numArbol+".png");
            Thread.sleep(1000);
                    
        } catch (Exception e) {
        }
    }
    
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
