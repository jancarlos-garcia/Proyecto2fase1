package afdMinimizacion;

import java.util.Map;
import java.util.TreeMap;

public class Minimizacion {

    private AFD m;
    private AFD copia;
    private AFD m1;
    private AFD m2;
    private int noEstados;
    private String alfabeto;
    protected Map<String, String> transicion;
    private int cont;

    public Minimizacion(AFD a1, String alfa, int noEstados) {
        this.m = a1;
        this.copia = a1;
        this.alfabeto = alfa;
        this.noEstados = noEstados;
    }

    public void simplificarA() {
        AFD t = (copia);
        
        while(t!=null){
            t = simplifica();
            if(t!=null){
                copia= new AFD(t);
                System.out.println("-------------------------------");
                copia.imprimirAutomata();
                System.out.println("-------------------------------");
              
            }
        }
        
        System.out.println("AUTOMATA ORIGINAL");
        m.imprimirAutomata();
        System.out.println("AUTOMATA MINIMIZADO");
        copia.imprimirAutomata();
    }
    
      
    private AFD simplifica(){
        AFD temp = null;
        m1 = new AFD(copia); //automata minimizado
        m2 = new AFD(copia);//automata minimizado
        

        boolean cambio = false;
        
        //va  a ir iterando sus estados iniciales en cada uno de estos automatas nuevos 
        
            for (int i = 0; i < copia.noEstados; i++) {
                
                // cuando este automata con estado inicial pase con otro estado inicial y sean comparados 
                // y sean equivalentes estos estados iniciales 
                for (int j = i+1; j < copia.noEstados; j++) {
                  
                        cambiarEdoInicial(m1, i,1);
                        
                        cambiarEdoInicial(m2, j,2);
                        
                        Equivalencia eq = new Equivalencia(m1, m2, alfabeto);
                        
                   
                        
                        if (eq.compararAutomatas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial()) == true) {
                   
                            // se llama a este metodo cambiar entradas                            
                            cambiarEntradas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial());

                            eliminarSalidas(m2.obtenerNodoInicial(), m1.obtenerNodoInicial());
                            temp = new AFD(m2);

                            cambio = true;
                            break;

                        }
                    
                }
                if (cambio == true) {
                    break;
                }
            }
            
            return temp;
    }
    //Este metodo nos permite cambiar los estados iniciales 
    private void cambiarEdoInicial(AFD a, int it, int k) {
        nodo n;
        n = a.obtenerNodoInicial();
     
        n.inicial = false;
        a.estados[it].inicial = true;
        a.setEstadoInicial(a.estados[it].nombre);
  
      
    }

    private void cambiarEntradas(nodo n1, nodo n2) {
        transicion = new TreeMap();
        for (int i = 0; i < noEstados; i++) {
            for (String s : this.alfabeto.split(",")) {
                if (m2.estados[i].transiciones.get(s).equals(n2.nombre)) {
                    m2.estados[i].transiciones.replace(s, n1.nombre);
                }
            }

        }
 
    }

    //elimina las salidas del estado que se esta repitiendo 
    private void eliminarSalidas(nodo n1, nodo inicial) {
    
        for (int i = 0; i < noEstados; i++) {
            if(m2.estados[i].nombre.equals(inicial.nombre)){
                m2.estados[i].inicial = true;
                m2.estadoInicial = inicial.nombre;
            }
        }
        m2.eliminarNodo(n1);
       
        this.noEstados = m2.getEstados().length;
       
    }

}
