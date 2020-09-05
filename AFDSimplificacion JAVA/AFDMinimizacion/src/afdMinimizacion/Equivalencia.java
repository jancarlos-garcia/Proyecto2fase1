package afdMinimizacion;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Equivalencia {

    private AFD m1;
    private AFD m2;
    private String alfabeto;
    private int k = 0;
    private String cadenaM = "";

    protected Map<Integer, nodo[]> columna0;
    protected Comparador[] columnas;

    private String listaAlfabeto = "";

    private boolean compatible = true;
    private boolean completo = false;
    int z = 0;
// Recibe como parametros las 2 clases AFD y tambien el alfabeto
    public Equivalencia(AFD a1, AFD a2, String alfa) {
        this.m1 = a1;
        this.m2 = a2;
        this.alfabeto = alfa;
    }
// Estos metodos sirven para la impresion del Alfabeto como el de los estados 
    public String cadenaAlfabeto() {
        String cad = "  ALFABETO        \n|";
        int i = 0;
        for (String s : this.alfabeto.split(",")) {
            cad += "    " + s + "    |";
            i++;
        }
        return cad;
    }

    public String cadenaColumna0() {
        String cad = "  ESTADOS\n| M1   M2 |\n";
        for (int r = 0; r < columna0.size(); r++) {
            nodo[] t = columna0.get(r);
            cad += (" [" + t[0].nombre + ", " + t[1].nombre + "]\n");
        }
        return cad;
    }


// Este Metodo es llamado en el Main 
    
  // llama a un booleano 
    public boolean compararAutomatas(nodo iM1, nodo iM2) {
        boolean equiv = false;
        nodo[] aux = new nodo[2];
        aux[0] = iM1;
        aux[1] = iM2;
        this.z = this.alfabeto.split(",").length;

        this.columna0 = new TreeMap();
        boolean b = false;

        columna0.put(0, aux);

        int i = 0;
        
        // Realiza un ciclo while mientras se va haciendo compatible  y mientras sea completo 
        // Completo es para la columna 0 para que no se vayan repitiendo y asi hacer bien la comparacion, mientras sean compatibles todos los estados 
        while (this.compatible == true && completo == false) {
            cadenaM = "";
            cadenaM = cadenaColumna0();

          
            aux = columna0.get(i);
        
            b = verificarEstadosEnColumna0(aux[0], aux[1]);
            i = i + 1;
        }
// Este if verifica si los automatas son equivalentes o compatibles 
        if (this.compatible == true) {
            System.out.println("\nLOS AUTOMATAS SI SON EQUIVALENTES\n");
            equiv = true;
            aux[0] = columna0.get(columna0.size() - 1)[0];
            aux[1] = columna0.get(columna0.size() - 1)[1];

            llenarFilaDeSimbolos(aux[0], aux[1]);

            System.out.println(cadenaM);
            System.out.println(cadenaAlfabeto());
            System.out.println(listaAlfabeto);
        
        // verifica si no son equivalentes 
        } else {
            System.out.println();
            
        }

        return equiv;
    }



//Verifica los estados en la columna 0 
    public boolean verificarEstadosEnColumna0(nodo iM1, nodo iM2) {
        nodo[] aux = new nodo[2];
      
        
        aux[0] = iM1;
        aux[1] = iM2;

        int t = 0;
        int w = 0;
        boolean c = true;

        c = llenarFilaDeSimbolos(aux[0], aux[1]);
        boolean v = true;

        if (c == true) {
            for (int i = 0; i < z; i++) {
                for (int y = 0; y < columna0.size(); y++) {
                 
                    
            // Se hace la comparacion si ya estan o no estan en el arreglo de nodos         
                    v = Arrays.equals(columna0.get(y), columnas[i].nodos);
                    if (v == true) {
                        w = w + 1;
                    }
                  
                }
             
                if (w == 0) {
                  
                    k = k + 1;
                    columna0.put(k, columnas[i].nodos);
                } else {
                    
                    t = t + 1;
                   
                }
            }
        }
        if (t == z) {
            completo = true;
        }
        return completo;
    }
 
    
    // Este medotodo es llamado por el metodo de arriba 
    public boolean llenarFilaDeSimbolos(nodo m1, nodo m2) {
        columnas = new Comparador[z];
        nodo[] aux = new nodo[2];
        nodo a = null;
        nodo b = null;
        aux[0] = m1;
        aux[1] = m2;
        int x = 0;

        for (String simbolo : this.alfabeto.split(",")) {

            a = this.m1.obtenerNodo(aux[0].getTransicionDeSimbolo(simbolo));
            b = this.m2.obtenerNodo(aux[1].getTransicionDeSimbolo(simbolo));

            if ((a.fina && b.fina) || (!a.fina && !b.fina)) {
                columnas[x] = new Comparador(simbolo, a, b);
              
                x++;
            } else {
                System.out.println("En el simbolo " + simbolo + " los estados "
                        + a.nombre + " y " + b.nombre + " NO son compatibles");
                this.compatible = false;
              
            }

            listaAlfabeto += (" [" + a.nombre + ", " + b.nombre + "]  ");
        }

        listaAlfabeto += "\n";
        //en este for se genera el array de columnas del alfabeto UNA FILA en moore
       
        return this.compatible;
    }
}
