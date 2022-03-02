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
       this.tabla = tabla;
       EstadoA aux0 = new EstadoA(primeros);
       aux0.setNombre("S0");
       listaEstados.add(aux0);//crea primer estado S0
       
       HacerTabla();
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
            System.out.println("nomb:"+actual.getNombre());
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
   
//   public void verificarAceptacion(){//con indexOf
//       for (int i = 0; i < listaEstados.size(); i++) {
//           for(int j=0;j<listaEstados.get(i).getContenido().size();j++){//rcorro contenido en busca de #
//               if(listaEstados.get(i).getContenido().get(j) == tabla.getNumerados()){
//               }
//           }
//       }
//   }
}
