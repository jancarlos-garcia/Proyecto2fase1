
package afdMinimizacion;

import javax.swing.JOptionPane;
//clase main 



public class afdEquivalente {
    protected AFD m1;
    protected AFD m2;
    protected AFD m3;
    public String alfabeto;

    public static void main(String[] args) {
        afdEquivalente e = new afdEquivalente();
     
        
        e.simplificarAFD();
    }
    
    
    
    public void inicio(){ 
        
        
   
    }
    
    
    // Comprobar equivalencia en donde se pasan los automatas que son AFD 
    // Se llama al metodo comparar Automatas y obtiene el nodo inicial de cada automata 
    public void comprobarEquivalencia(){
        Equivalencia eq = new Equivalencia(m1,m2,alfabeto);
        eq.compararAutomatas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial());
    }
    // El Alfabeto que se va a manejar 
    public void simplificarAFD(){
        alfabeto = JOptionPane.showInputDialog("Ingrese el Alfabeto ");  
        
        //numero de estados que se van a manejar 
        int noEstadosM1 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados :"));
        m3 = new AFD(alfabeto,noEstadosM1);
        m3.llenarEstados();
        
        
        //llamamos a la clase y al metodo minimizacion 
        
        Minimizacion sim = new Minimizacion(m3,alfabeto,noEstadosM1);
        sim.simplificarA();
        
    }
}
