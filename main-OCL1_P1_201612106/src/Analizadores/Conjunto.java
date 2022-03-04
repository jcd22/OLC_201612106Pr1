/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.util.ArrayList;

/**
 *
 * @author chana
 */
public class Conjunto {
   String nombre;
   ArrayList<String> listCaracteres;
    
   public Conjunto(){       
       nombre ="";
       listCaracteres = new ArrayList<>();
   }
    //////////////////

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getListCaracteres() {
        return listCaracteres;
    }

    public void setListCaracteres(ArrayList<String> listCaracteres) {
        this.listCaracteres = listCaracteres;
    }
   
}
