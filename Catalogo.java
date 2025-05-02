import java.util.ArrayList;
import java.util.Scanner;

public abstract class Catalogo<T> {
    protected ArrayList<T> elementos;
    protected static Scanner scanner;
    
    protected Catalogo(Scanner scanner) {
        this.elementos = new ArrayList<T>();
        Catalogo.scanner = scanner;
    }

    public void añadirElemento(T elemento) {
        elementos.add(elemento);
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public abstract void registrarElemento();
    public abstract T buscarElemento(int idElemento);
}
