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
public class M_Expresion {
    String nombreEr;
    boolean esCadena;
    
    M_Expresion listaEr;
    String nombre;
    M_Expresion er_izq;
    M_Expresion er_der;
    boolean anulable;
    ArrayList<Integer> primeros;
    ArrayList<Integer> ultimos;
    
    int numEstado;
    
    //para las er completas, se guardara aqui tamb los patrones
    public M_Expresion(String nombreEr,M_Expresion listaEr){
        this.nombreEr = nombreEr;
        this.listaEr = listaEr;
        
    }
    
    //para las er internas    //tipos : conjunto,cadena,notacion
    public M_Expresion(String nombre,M_Expresion er_izq,M_Expresion er_der){
       this.anulable = false;
       this.nombre = nombre;
       this.er_izq = er_izq;
       this.er_der = er_der;
       this.primeros = new ArrayList<>();
       this.ultimos = new ArrayList<>();
    }
    
    //para las er finales
    public M_Expresion(String nombre){
        this.nombre = nombre;
        this.anulable = false;
        this.primeros = new ArrayList<>();
        this.ultimos = new ArrayList<>();
        boolean esCadena = false;
    }
     //------------------------------------------
    
    public boolean esEsCadena() {
        return esCadena;    
    }

    public void setEsCadena(boolean esCadena) {
        this.esCadena = esCadena;
    }

    public String getNombreEr() {
        return nombreEr;
    }

    public void setNombreEr(String nombreEr) {
        this.nombreEr = nombreEr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public M_Expresion getListaEr() {
        return listaEr;
    }

    public void setListaEr(M_Expresion listaEr) {
        this.listaEr = listaEr;
    }

    /**
     * obtiene hijo izq
     * @return M_Expresion
     */
    public M_Expresion getEr_izq() {
        return er_izq;
    }

    public void setEr_izq(M_Expresion er_izq) {
        this.er_izq = er_izq;
    }

    public M_Expresion getEr_der() {
        return er_der;
    }

    public void setEr_der(M_Expresion er_der) {
        this.er_der = er_der;
    }
    
    public boolean getAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }
    
        public int getNumEstado() {
        return numEstado;
    }

    public void setNumEstado(int numEstado) {
        this.numEstado = numEstado;
    }
    
        public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }
}
