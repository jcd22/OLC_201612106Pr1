/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

/**
 *
 * @author chana
 */
public class Entrada {
    
    String nombreEr;
    String cadena;
    
    public Entrada(String nombreEr,String cadena){
        this.nombreEr = nombreEr;
        this.cadena = cadena;
    }
    ///////////////

    public String getNombreEr() {
        return nombreEr;
    }

    public void setNombreEr(String nombreEr) {
        this.nombreEr = nombreEr;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
}
