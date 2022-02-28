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
public class M_Error {

    String tipo, lexema, descripcion;
    int linea, columna;

    public M_Error(String tipo, String lexema, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
    }

    public String show() {
        String data = "";
        data += "\ntipo:" + tipo;
        data += "\nlexema:" + lexema;
        data += "\ndescripcion:" + descripcion;
        data += "\nlinea:" + String.valueOf(linea);
        data += "\ncolumna:" + String.valueOf(columna);
        return data;
    }    
    
}
