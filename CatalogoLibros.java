import java.util.Scanner;

public class CatalogoLibros extends Catalogo<Libro> {

    private static CatalogoLibros instancia;

    private CatalogoLibros(Scanner scanner) {
        super(scanner);
    }

    public static CatalogoLibros getInstancia(Scanner s) {
        if (instancia == null) {
            instancia = new CatalogoLibros(s);
        }
        return instancia;
    }

    @Override
    public Libro buscarElemento(int idElemento) {
        // Buscar elemento
        for (Libro l : elementos) {
            if (l.getId() == idElemento) {
                return l;
            }
        }
        return null;
    }

    @Override
    public void registrarElemento() {
        System.out.println("--- REGISTRAR NUEVO LIBRO ---");
        Libro nuevoLibro = solicitarDatosLibro();
        elementos.add(nuevoLibro);
        System.out.println("Libro registrado con éxito.");
    }

    public void modificarDisponibilidadLibro(int idLibro) {
        // Marcar libro como disponible
        for (Libro l : elementos) {
            if (l.getId() == idLibro) {
                l.setDisponible(true);
                return;
            }
        }
    }
    
    public void buscarLibros() {
        System.out.println("--- BUSCAR LIBROS ---");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por género");
        System.out.print("Seleccione una opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("Ingrese término de búsqueda: ");
        String termino = scanner.nextLine().toLowerCase();
        
        boolean encontrado = false;
        
        System.out.println("Resultados:");
        for (Libro libro : elementos) {
            boolean coincide = false;
            
            switch (opcion) {
                case 1:
                    coincide = libro.getTitulo().toLowerCase().contains(termino);
                    break;
                case 2:
                    coincide = libro.getAutor().toLowerCase().contains(termino);
                    break;
                    case 3:
                    coincide = libro.getGenero().toLowerCase().contains(termino);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            
            if (coincide) {
                System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() + 
                                 " | Autor: " + libro.getAutor() + " | Año: " + libro.getAnio() + 
                                 " | Género: " + libro.getGenero() + 
                                 " | Disponible: " + (libro.isDisponible() ?"Sí" : "No"));
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        }
    }

    public void mostrarLibros() {
        System.out.println("--- LISTADO DE LIBROS ---");
        
        if (elementos.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        
        for (Libro libro : elementos) {
            System.out.println("ID: " + libro.getId() + " | Título: " + libro.getTitulo() + 
                             " | Autor: " + libro.getAutor() + " | Año: " + libro.getAnio() + 
                             " | Género: " + libro.getGenero() + 
                             " | Disponible: " + (libro.isDisponible() ?"Sí" : "No"));
        }
    }

    private Libro solicitarDatosLibro() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
    
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
    
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
    
        System.out.print("Año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
    
        System.out.print("Género: ");
        String genero = scanner.nextLine();
    
        return new Libro(id, titulo, autor, anio, genero, true);
    }

}