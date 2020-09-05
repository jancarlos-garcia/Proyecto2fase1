package afdMinimizacion;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class nodo {
    
    
//Atributos Principales 
// Atributo nombre         
    protected String nombre;
    
//Atributos Booleanos que son el inicial y el final 
    protected boolean inicial, fina;
    //protected nodo apuntado;
    
 //mapa con 2 string, este realiza las transicicones 
 // Para asociar hacia que otro estado va segun el simbolo 
    protected Map<String, String> transiciones;
    
    public nodo(nodo n){
        this(n.getNombre(), n.isInicial(), n.isFina(), n.getTransiciones());
    }
    
    
    //constructor
    //recibe los atributos 
    public nodo(String nombre, boolean inicial, boolean fina, Map<String, String> transiciones){
        this.nombre = nombre;
        this.inicial = inicial;
        this.fina = fina;
        this.transiciones = transiciones;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isInicial() {
        return inicial;
    }

    public boolean isFina() {
        return fina;
    }

    public Map<String, String> getTransiciones() {
        return transiciones;
    }


// recibe el nombre si es inicial o final 
    public nodo(String name, boolean i, boolean f) {
        this.nombre = name;
        this.inicial = i;
        this.fina = f;
        // inicia la instacia con un Hash map de las transiciones 
        this.transiciones = new HashMap();
    }

    // manda a llamar el metodo para llenar las transiciones 
    public void llenarTransiciones(String alfabeto) {
        for (String simbolo : alfabeto.split(",")) {
            transiciones.put(simbolo, JOptionPane.showInputDialog(
                    "La transicion de " + this.nombre + " cuando es (" + simbolo + ") va hacia:"));
        }
    }
   
    // las obtiene segun su simbolo que se esta buscando 
    public String getTransicionDeSimbolo(String simbolo){
        return this.transiciones.get(simbolo);
    }
}
