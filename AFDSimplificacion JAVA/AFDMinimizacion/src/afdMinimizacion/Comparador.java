package afdMinimizacion;


//Columna que se genera con cada simbolo del Alfabeto basado en el Teorema de Moore 
public class Comparador {
    protected String nombreColumna;
    protected final nodo nodos [] = new nodo[2];
    


// Recibe como parametros,nombre de la columna y ambos estados 
    public Comparador(String nombreColumna,nodo estado1, nodo estado2){
        this.nombreColumna = nombreColumna;
        nodos[0] = estado1;
        nodos[1] = estado2;
    }
    
    
    // Ayuda a obtener el arreglo de nodos de cada columna 
    public nodo[] getEstadosColumna(String nombreColumna){
        return this.nodos;
    }

}
