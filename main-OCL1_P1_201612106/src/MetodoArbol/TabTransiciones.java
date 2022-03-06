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
public class TabTransiciones {
    ArrayList<EstadoA> listaEstados = new ArrayList<>();//estados con sus transiciones
    TabSiguientes tabla;
    ArrayList<String> listaColumnas;
   
    /**
     * @param primeros:recibe primeros de raiz para el estado 0
     */
   public TabTransiciones(ArrayList<Integer> primeros,TabSiguientes tabla){
       this.tabla = tabla;
       this.listaColumnas = new ArrayList<>();
       EstadoA aux0 = new EstadoA(primeros);
       aux0.setNombre("S0");
       listaEstados.add(aux0);//crea primer estado S0
       
       HacerTabla();
       //llena array columnas Hojas
       LlenaColumnas();
   }
   //////////////////////
   //recibe raiz
   public void HacerTabla(){
       for(int h=0;h<listaEstados.size();h++){
        EstadoA actual = listaEstados.get(h);       
        for(int i=0;i<actual.getContenido().size();i++){//recorre el contenido del estado actual {1,3,5}

            int numerado = actual.getContenido().get(i)-1; //1 - 1 ,obtengo numerado
            if((numerado+1) == tabla.getNumerados()){
                actual.setAceptacion(true);
                continue;
            }
            String terminal = tabla.getTerminales().get(numerado);//terminal transicion de tab siguientes: a           
            if(actual.getTerminalesTran().indexOf(terminal) == -1){//no hay transicion con ese terminal,agrego
                actual.getTerminalesTran().add(terminal);
                actual.getContenidosTran().add(new ArrayList<Integer>());
            }
            int iTerminal = actual.getTerminalesTran().indexOf(terminal);//obtengo posicion terminal 
            for(int iSig:tabla.getSiguientes().get(numerado)){ //obtengo estados de lista siguientes para meter
                actual.getContenidosTran().get(iTerminal).add(iSig);//agrego contenidoTran al estado en iTerminal
            }

        }//
        //ya llene terminales y transiciones del estado  
        //for ,nombrar estados del actua
        for (int j = 0; j <actual.getTerminalesTran().size(); j++) {
            //aux solo para mandarlo a existeEstado
            EstadoA auxTran = new EstadoA(actual.getContenidosTran().get(j));
            String obtenerName = existeEstado(auxTran);//obtengo el nombre,si no existe lo creo
            actual.getEstadosTran().add(obtenerName);
        }
        
       }//fin h
   }//hacerTabla
   
   /**
    * se usa en HacerTabla
    * @param EstadoB:estado se berificara si existe,viene sin nombre
    */
    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
   public String existeEstado(EstadoA EstadoB){
       for (int i = 0; i < listaEstados.size(); i++) {//para ver toda la lista
           boolean flagSalirEstado = false;
           EstadoA actual = listaEstados.get(i);//estado
           for (int j = 0; j < EstadoB.getContenido().size(); j++) {//para contenido estadoB
               for (int k = 0; k < actual.getContenido().size(); k++) { //para contenido de actual
                   //compara 
                   if (EstadoB.getContenido().get(j) == actual.getContenido().get(k) ) {//compara uno por uno el contenido
                       //estadoB igual al actual
                       if(j == EstadoB.getContenido().size()-1){return actual.getNombre();}
                       break;
                   } else{ //no esta el numero
                        // esta en ultima pos y no lo encontro
                       if(k == actual.getContenido().size()-1){//EstadoB diferente al actual
                           flagSalirEstado = true;
                       }  
                   }
               }//con k
               if(flagSalirEstado == true){break;}
           }//con j 
       }
       //es diferente a todos los estados
       //crea estado en listaEstados
       EstadoA.numEstadoA++;
       EstadoB.setNombre("S"+EstadoA.numEstadoA);
       listaEstados.add(EstadoB);//guardo nuevo
       return EstadoB.getNombre();
   }//BuscaEstado  
   
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
                    
                                 "<th>Estado</th>\n";
                        for (int i = 0; i < listaColumnas.size(); i++) {
                            concatText += "<th>"+listaColumnas.get(i)+"</th>";
                        }

            concatText +=       "</tr>\n" +
                         "</thead>\n"+
                         "<tbody>\n";
                       for (int i = 0; i < listaEstados.size(); i++) { //transiciones cada estado
                           concatText += "<tr>\n" ;
                           concatText += "<td>"+listaEstados.get(i).getNombre()+"</td>\n";//inserta nombre estado
                            for (int j = 0; j < listaColumnas.size(); j++) {//for lista terminales
                                // si encuentra esta columna en las transiciones 
                                if (listaEstados.get(i).getTerminalesTran().indexOf(listaColumnas.get(j)) != -1) {
                                 concatText += "<td> "+listaEstados.get(i).getEstadosTran().get(listaEstados.get(i).getTerminalesTran().indexOf(listaColumnas.get(j)))+" </td>\n";
                                } else{
                                 concatText += "<td> ♣------♣ </td>\n";//inserta vacia
                                }
                            }
                           concatText +=  "</tr>\n";
                       }
            
            concatText +="</tbody>\n"+
                        "</table>\n"+
                        "</div>\n" +
                        "</body>\n" +
                        "<script src=\"masEscuchados1.js\"></script>\n" +
                        "</html>";
            
            FileOutputStream arch = new FileOutputStream(new File(".\\Transiciones\\TabTran"+Arbol.numArbol+".html"));
                    
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
   
   /**
    * Llena columnas tabla transiciones
    */
   public void LlenaColumnas(){
       try {
       String auxTerminal="";
       for (int i = 0; i <tabla.getTerminales().size(); i++) {
           auxTerminal = tabla.getTerminales().get(i);
           if (listaColumnas.indexOf(auxTerminal) == -1) {//no esta entonces incerto
               listaColumnas.add(auxTerminal);
           }
       }
       
       } catch (Exception e) {
       }

   }
   
   
//   public void verificarAceptacion(){//con indexOf
//       for (int i = 0; i < listaEstados.size(); i++) {
//           for(int j=0;j<listaEstados.get(i).getContenido().size();j++){//rcorro contenido en busca de #
//               if(listaEstados.get(i).getContenido().get(j) == tabla.getNumerados()){
//               }
//           }
//       }
//   }

    public ArrayList<EstadoA> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(ArrayList<EstadoA> listaEstados) {
        this.listaEstados = listaEstados;
    }
}
