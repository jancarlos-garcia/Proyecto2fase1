package afdMinimizacion;

import javax.swing.JOptionPane;

public class AFD implements Cloneable {

//Esta clase consta con 5 atributos 
    
    protected String alfabeto;
    protected int noEstados; // Numero de Estados 
    protected String estadoInicial;
    protected String estadosFinales;
    protected nodo[] estados; // nodos que constan este AFD 

    
    
    //recibe una instancia de la misma clase 
    public AFD(AFD a) {
        this(a.getAlfabeto(), a.getEstados().length, a.getEstadoInicial(), a.getEstadosFinales(), a.getEstados());
    }

// se llenan todos los atributos     
    public AFD(String alfabeto, int noEstados, String ei, String ef, nodo[] estados) {
        this.alfabeto = alfabeto;
        this.noEstados = noEstados;
        this.estadoInicial = ei;
        this.estadosFinales = ef;
        this.estados = new nodo[this.noEstados];
        
        for(int i=0;i<this.noEstados;i++){
            this.estados[i] = new nodo(estados[i]);
        }
    }

    public void setEstadoInicial(String ei) {
        this.estadoInicial = ei;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public int getNoEstados() {
        return noEstados;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public String getEstadosFinales() {
        return estadosFinales;
    }

    public nodo[] getEstados() {
        return estados;
    }

    
    // se recibe el alfabeto y el numero de estados 
    public AFD(String alfabeto, int noEstados) {
        this.alfabeto = alfabeto;
        this.noEstados = noEstados;
        this.estadoInicial = "";
        this.estadosFinales = "";
        estados = new nodo[noEstados];
    }

    
    // LLena los Estados 
    
    public void llenarEstados() {
        for (int i = 0; i < this.noEstados; i++) {
            String name = JOptionPane.showInputDialog("Nombre del Estado No. " + (i + 1));
            boolean ini, fina;
            if (this.estadoInicial.equals("")) {
                ini = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                        + "' ¿Es Inicial?", "Estado " + (i + 1), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                this.estadoInicial = ini ? name : "";
            } else {
                ini = false;
            }

            fina = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                    + "' ¿Es Final?", "Estado " + i, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            this.estadosFinales += fina ? name + "," : "";

            estados[i] = new nodo(name, ini, fina);

            estados[i].llenarTransiciones(this.alfabeto);
        }
    }

    // Obtiene el nodo segun el nombre del Nodo
    
   
    public nodo obtenerNodo(String nombreNodo) {
        nodo estado = null;
        for (nodo tmp : this.estados) {
            if (tmp.nombre.equals(nombreNodo)) {
                estado = tmp;
            }
        }
        return estado;
    }

    
    //Obtiene el nodo inicial 
    
    public nodo obtenerNodoInicial() {
        nodo estado = null;
        for (nodo tmp : this.estados) {
            if (tmp.inicial) {
                estado = tmp;
            }
        }
        return estado;
    }
// Verifica si el nodo es final pasandole como parametro un nodo 
    
    public boolean nodoEsFinal(nodo n) {
        boolean f = false;
        for (nodo tmp : this.estados) {
            if (n.fina == true) {
                f = true;
            }
        }
        return f;
    }

    
    //reace el arreglo de nodos 
    //disminuyendolo 
    public boolean eliminarNodo(nodo n) {
        boolean eliminado = false;
        try {

            nodo[] nn = new nodo[noEstados - 1];
            int k = 0;
            for (int i = 0; i < noEstados; i++) {
                if (!estados[i].nombre.equals(n.nombre)) {
                    nn[k] = estados[i];
                    k++;
                } else {
                    eliminado = true;
                }
            }
            estados = nn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eliminado;
    }

    //imprime el automata se manda a llamar cuando terminan los procesos 
    public void imprimirAutomata() {
        for (int i = 0; i < noEstados; i++) {
            String edoTransiciones = "";
            edoTransiciones = ("El estado " + estados[i].nombre + " va hacia --> \n");
            for (String s : this.alfabeto.split(",")) {
                //edoTransiciones += " en " + s + "\n"
                edoTransiciones += (estados[i].transiciones.get(s) + " en " + s + "\n");
            }
            System.out.println(" " + edoTransiciones);
        }
    }
}
