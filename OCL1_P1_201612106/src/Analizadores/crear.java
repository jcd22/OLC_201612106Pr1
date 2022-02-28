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
public class crear {
    public static void main(String[] args){
       generarArchivos();
    }
    
    private static void generarArchivos(){
        try {
            String ruta = "src/Analizadores/";
            String opcFlex[] = {ruta + "lexico.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            
            String opcCUP[] = {"-destdir", ruta, "-parser", "sintactico", ruta + "sintactico.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    
    }
}
