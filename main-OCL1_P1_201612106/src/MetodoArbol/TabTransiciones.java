/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoArbol;

import java.util.ArrayList;

/**
 *
 * @author chana
 */
public class TabTransiciones {
    ArrayList<EstadoA> listaEstados = new ArrayList<>();
    TabSiguientes tabla;
   
    /**
     * @param primeros:recibe primeros de raiz para el estado 0
     */
   public TabTransiciones(ArrayList<Integer> primeros,TabSiguientes tabla){
       listaEstados.add(new EstadoA("S0",primeros));//crea primer estado
       this.tabla = tabla;
       //listaEstados.get(0)
   }
   //////////////////////
   
   public void HacerTabla(EstadoA actual){
       EstadoA eAux = new EstadoA();       
       for(int i=0;i<actual.getContenido().size();i++){//recorre el contenido del estado actual
           
           int numerado = actual.getContenido().get(i)-1; //1 - 1 ,obtengo numerado
           String terminal = tabla.getTerminales().get(numerado);//*veo terminal transicion de tab siguientes: a
           if(actual.getTerminalesTran().indexOf(terminal) == -1){//no hay transicion con ese terminal,agrego
               actual.getTerminalesTran().add(terminal);
           }
           int iTerminal = actual.getTerminalesTran().indexOf(terminal);//obtengo donde esta
//         tabla.getSiguientes().get(numerado); //*veo siguientes arraylist del numerado  {4,2,3}
           for(int iSig:tabla.getSiguientes().get(numerado)){ //obtengo estados de lista siguientes para meter
               actual.getContenidosTran().get(iTerminal).add(iSig);
           }
           
       }//
       //ya llene terminales,transiciones del estado
       //busca estados respecto a  contenido si existen en la listaEstado para nombrarlos en EstadosTran de mi actual
       //for
       // contenido del estado asignado aqui al inizializar objeto
   }
   
   /**
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
                       if(j == EstadoB.getContenido().size()-1){return EstadoB.getNombre();}
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
   
//    //si esta en ultima pos y no lo encontro
//                       if(k == actual.getContenido().size()-1){
//                       //crea estado en listaEstados
//                       EstadoA.numEstadoA++;
//                       EstadoB.setNombre("S"+EstadoA.numEstadoA);
//                       listaEstados.add(EstadoB);//guardo nuevo
//                       return EstadoB.getNombre();
//                       }
   
   /**
    * Verifica los estados aceptacion en la listaEstados
    */
   public void verificarAceptacion(){//con indexOf
       
   }
}
