/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocl1_p1_201612106;
import Analizadores.M_Expresion;
import Analizadores.Entrada;
import Analizadores.lexico_j;
import Analizadores.sintactico;
import Analizadores.Conjunto;
import MetodoArbol.Arbol;
import MetodoArbol.Automata;
import MetodoArbol.EstadoA;
import MetodoArbol.TabSiguientes;
import MetodoArbol.TabTransiciones;
import java.io.FileNotFoundException;
import java.util.Formatter;

import java.io.File;
import java.io.StringReader;
import java.util.Scanner;

import analizadores.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 *
 * @author chana
 */
public class Principal extends javax.swing.JFrame {
    sintactico sintactico;
    ArrayList<Automata> listaAutomata = new ArrayList<>();
    /**
     * es global
     */
    ArrayList<Conjunto> listaConjuntos;
    
    /**
     * Creates new form Principal
     */
    public Principal() {      
        initComponents();
    }
    
    /**
     *@param:listaEstados:estados del automata con sus transiciones,el automata es seleccionado de listaAutomatas
     */
    public boolean ValidarCadena(String entrada,ArrayList<EstadoA> listaEstados){
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
    
    public boolean Validar2(Entrada entrada){
     try {
        listaConjuntos = sintactico.lista_Conj;//preparo la lista conjuntos venida del Parser
        Automata automataUsar = null;
        //busca automata
        for (int i = 0; i < listaAutomata.size(); i++) {
            if(entrada.getNombreEr().equals(listaAutomata.get(i).getNombreEr())){//encuentra a q automata se usara
                automataUsar = listaAutomata.get(i);
                break;
            }else{
                System.out.println("NO se encontro Er");
                return false;
            }
         }//busca auto
        //estado inicial
        EstadoA estadoActual = automataUsar.getListaEstados().get(0);
         for (int h = 0; h < entrada.getCadena().length(); h++) {//recorro entrada string
            for (int i = 0; i <estadoActual.getTerminalesTran().size(); i++) {// para transiciones estadoActual             
                //IF 1
                if(String.valueOf(estadoActual.getTerminalesTran().get(i).charAt(0)).equals("\"")){ // si es cadena
                    boolean flagEncontroCad = false;
                //comprobare si es la cadena valida
                    for (int k = 1; k < estadoActual.getTerminalesTran().get(i).length()-1; k++) {//recorre cadena del term transicion
                        if (entrada.getCadena().charAt(h) == estadoActual.getTerminalesTran().get(i).charAt(k)) {//iguales con entrada
                            if(k == estadoActual.getTerminalesTran().get(i).length()-2){//llega al final y es valida
                                if (h == entrada.getCadena().length()-1) {
                                    if (estadoActual.getAceptacion() == true) {
                                        return true;
                                    }else{return false;}
                                }
                                //indice del automata a ir
                                int inuevo = automataUsar.getListaEstados().indexOf(estadoActual.getEstadosTran().get(i));
                                //actual igual al nuevo automata
                                estadoActual = automataUsar.getListaEstados().get(inuevo);
                                h = h+estadoActual.getTerminalesTran().get(i).length()-3;//empizo analizar desde aqui la cadena
                                flagEncontroCad = true;
                            }
                        }else{ // no es igual a la cadena
                            break;
                        }
                    }//k
                    if(flagEncontroCad == true){ //sale for i ,encontro cadena
                        flagEncontroCad=false; break;
                    }else{//no encontro cadena
                        continue;
                    }
                }//if cadena
                else{//else no cadena //IF 2
                    boolean banderaEncontro = false;
                    for (int j = 0; j < listaConjuntos.size(); j++) {// para contenido listaConjuntos
                        //el terminal tran actual esta en la lista de conjuntos
                        if (estadoActual.getTerminalesTran().get(i).indexOf(listaConjuntos.get(j).getNombre()) != -1) {
                            //recorre caracteres validos del conjunto actual
                            for(int g =0;g<listaConjuntos.get(j).getListCaracteres().size();g++){
                                //caracter de Entrada igual a un caracter de conjunto de listaConj
                                if (String.valueOf(entrada.getCadena().charAt(h)).equals(listaConjuntos.get(j).getListCaracteres().get(g) )) {
                                    //ultimo caracter
                                    if (h == entrada.getCadena().length()-1) {
                                        if (estadoActual.getAceptacion() == true) {
                                            return true;
                                        }else{return false;}
                                    }
                                    banderaEncontro = true;
                                    break;
                                }else{ //no es un carcter igual
                                }
                            }//g
                            
                        }
                        if(banderaEncontro){
                            //indice del automata a ir
                                int inuevo = automataUsar.getListaEstados().indexOf(estadoActual.getEstadosTran().get(i));
                                //actual igual al nuevo automata
                                estadoActual = automataUsar.getListaEstados().get(inuevo);
                                //h = h+estadoActual.getTerminalesTran().get(i).length()-3;//empizo analizar desde aqui la cadena
                                break;
                                //flagEncontroCad = true;
                        }else{
                            
                        }
                    }//j                
                    if(banderaEncontro){//paso al siguiente caracter de entrada
                        banderaEncontro = false;
                        break;
                    }else{
                        return false; //el caracter no esta definido ni es cadena
                    }
                }//no cadena

            }//i
     }//h
        return false;
         
         
         } catch (Exception e) {
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_abrir = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_crear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_arbol = new javax.swing.JButton();
        btn_siguientes = new javax.swing.JButton();
        btn_transicion = new javax.swing.JButton();
        btn_automata = new javax.swing.JButton();
        btn_analizar = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        btn_anterior = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btn_abrir.setText("Abrir");
        btn_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirActionPerformed(evt);
            }
        });

        btn_guardar.setText("Guardar/Guarda como");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_crear.setText("Nuevo");
        btn_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_crearActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btn_arbol.setText("Genera\nArboles");
        btn_arbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_arbolActionPerformed(evt);
            }
        });

        btn_siguientes.setText("Siguientes");

        btn_transicion.setText("Transiciones");

        btn_automata.setText("GeneraAutomata");
        btn_automata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_automataActionPerformed(evt);
            }
        });

        btn_analizar.setText("Analizar");
        btn_analizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_analizarActionPerformed(evt);
            }
        });

        btn_siguiente.setText("siguiente");
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });

        btn_anterior.setText("anterior");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel1.setText("Salida Validacion o no");

        jLabel3.setText("Entrada    :");

        jButton1.setText("Validar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btn_abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_guardar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btn_anterior)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_siguiente))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(302, 302, 302)))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btn_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_siguientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_transicion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(btn_automata, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 44, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_abrir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_arbol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_siguientes)
                        .addComponent(btn_automata)
                        .addComponent(btn_transicion)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_anterior)
                            .addComponent(btn_siguiente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_analizar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );

        jLabel2.setText("ABRIR GRAFICAS:");

        jLabel4.setText("Genera    :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(56, 56, 56)
                        .addComponent(jLabel2)
                        .addGap(272, 272, 272))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrirActionPerformed
        try {
            jTextArea1.setText("");
            JFrame jFrame = new JFrame();
            String getMessage = JOptionPane.showInputDialog(jFrame, "Abrir Archivo:");
            Scanner x;
            x = new Scanner(new File(".\\"+getMessage+".exp"));
            while(x.hasNextLine()){
                String a = x.nextLine();
                jTextArea1.append(a+"\n");
                
               // System.out.printf("%s\n",a);
            }
            x.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Err: "+ex.getMessage());
        }
    }//GEN-LAST:event_btn_abrirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        try {
            JFrame jFrame = new JFrame();
             String getMessage = JOptionPane.showInputDialog(jFrame, "Guarda Archivo:");
            String ruta = ".\\"+getMessage+".exp";
            String contenido = jTextArea1.getText();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_crearActionPerformed
        try {
        JFrame jFrame = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jFrame, "IngresaNombre:");           
            final Formatter x;
            x = new Formatter(getMessage+".exp");
            //System.out.println("se creo: ");
        } catch (FileNotFoundException ex) {
            System.out.println("Err: "+ ex.getMessage());
        }
    }//GEN-LAST:event_btn_crearActionPerformed

    private void btn_analizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_analizarActionPerformed
        // TODO add your handling code here:
        String entrada = jTextArea1.getText();
        lexico_j lexico = new lexico_j(new StringReader(entrada));
        sintactico = new sintactico(lexico);
//        int pp = 33;
//        int[]p = {pp,3};
//        int[] d={3,3};
//        M_Expresion car = new M_Expresion("d",p,d);
//        boolean f = car.getAnulable();
        try {
            sintactico.parse();
            System.out.println("-------Fin analisis-----");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("No se pudo analizar la entrada correctamente");
            System.out.println("debido a:"+e.getCause());
        }
        
    }//GEN-LAST:event_btn_analizarActionPerformed

    private void btn_arbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_arbolActionPerformed
        // TODO add your handling code here:
                // Boton GENERAR arbol
        if(sintactico.lista_ER.size() !=0){
            //actualArchivo = Arbol.numArbol;
            //hacer un for aqui          
//            for (int i = 0; i <sintactico.lista_ER.size(); i++) {//recorro lista_ER para los arboles
                M_Expresion expresionA = new M_Expresion("#");
                // raiz del arbol
                M_Expresion raiz = new M_Expresion(".",sintactico.lista_ER.get(0).getListaEr(),expresionA);
                Arbol arbol = new Arbol(raiz);
                try {               
                   arbol.GraficaArbol();
//                 System.out.println(raiz.getPrimeros());                  
                   //obj para tab siguientes
                   TabSiguientes tabSig = arbol.getTabla();//obtengo toda la tabla siguientes
                   //grafica tab siguietnes
                   tabSig.GraficarTabla();
                   //se crea la tabla Trans
                   TabTransiciones tabTran = new TabTransiciones(raiz.getPrimeros(),tabSig);
                   tabTran.GraficarTabla();
                   
                   //iguala a var global listaEstado ^^^ <--x
                   //obj para automata generado el 0 substity por i
                   Automata automata = new Automata(sintactico.lista_ER.get(0).getNombre(),tabTran.getListaEstados());
                   //graficar automa
                   automata.graficarAutomata();
                   //guardo en Lista Automatas aqui
                   //automata.ValidarCadena("letra idd");
                   Arbol.numArbol++;
                   System.out.println("jfdj");

                } catch (InterruptedException ex) {
                    Logger.getLogger(aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
//            }//for                      
           
        }//fin boton
    }//GEN-LAST:event_btn_arbolActionPerformed

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void btn_automataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_automataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_automataActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        //ValidarCadena(entrada, listaEstados);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_abrir;
    private javax.swing.JButton btn_analizar;
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_arbol;
    private javax.swing.JButton btn_automata;
    private javax.swing.JButton btn_crear;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JButton btn_siguientes;
    private javax.swing.JButton btn_transicion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
