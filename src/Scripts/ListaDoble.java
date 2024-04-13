package Scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ivan_
 */
public class ListaDoble {

    private NodoDoble inicio, fin;
    private int size;

    public void add(String val[]) {
        add(new NodoDoble(val));
    }

    public void add(NodoDoble nuevo) {
        if (inicio == null) {
            inicio = fin = nuevo;
            size++;
        } else {
            fin.next = nuevo;
            nuevo.prev = fin;
            fin = fin.next;
            size++;
        }
    }

    public NodoDoble getNodoInicio() {
        return inicio;
    }

    public NodoDoble getNodoNext(NodoDoble actual) {
        return actual.next;
    }

    public NodoDoble getNodoPrev(NodoDoble actual) {
        return actual.prev;
    }

    public String getNombre(NodoDoble actual) {
        return actual.dato[0];
    }

    public String getEdad(NodoDoble actual) {
        return actual.dato[1];
    }

    public String getPeso(NodoDoble actual) {
        return actual.dato[2];
    }

    public String getAltura(NodoDoble actual) {
        return actual.dato[3];
    }

    public String getIMC(NodoDoble actual) {
        return actual.dato[4];
    }

    public NodoDoble removeLast() {
        NodoDoble borrado = null;
        if (inicio != null) {
            borrado = fin;
            fin = fin.prev;
            borrado.prev = fin.next = null;
            size--;
        }
        return borrado;
    }

    public int size() {
        return size;
    }

    public NodoDoble buscar(String valor) {
        NodoDoble actual = inicio;
        while (actual != null) {
            if (actual.dato[0].equals(valor)) {
                return actual;
            }
            actual = actual.next;
        }
        return null;
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try {
            File file = new File(nombreArchivo);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter escritor = new FileWriter(nombreArchivo);
            NodoDoble actual = inicio;
            while (actual != null) {
                for (int i = 0; i < actual.dato.length; i++) {
                    escritor.write(actual.dato[i]);
                    if (i < actual.dato.length - 1) {
                        escritor.write(",");
                    }
                }
                escritor.write("\n");
                actual = actual.next;
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerDeArchivo(String nombreArchivo) {
        try {
            File file = new File(nombreArchivo);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                add(datos);
            }
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean archivoNulo(String nombreArchivo) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea = lector.readLine();
            lector.close();
            if (linea == null){
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public class NodoDoble {

        protected String dato[];
        protected NodoDoble next;
        protected NodoDoble prev;

        public NodoDoble(String dato[]) {
            this.dato = dato;
        }

        @Override
        public String toString() {
            return "" + dato + " ";
        }
    }
}
