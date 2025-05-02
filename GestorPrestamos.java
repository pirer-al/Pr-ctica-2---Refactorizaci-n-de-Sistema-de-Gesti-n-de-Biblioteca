import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GestorPrestamos {
    private static GestorPrestamos instancia;
    private ArrayList<Prestamo> prestamos;
    private static Scanner scanner;

    private GestorPrestamos(Scanner scanner) {
        this.prestamos = new ArrayList<Prestamo>();
        GestorPrestamos.scanner = scanner;
    }

    public static GestorPrestamos getInstancia(Scanner s) {
        if (instancia == null) {
            instancia = new GestorPrestamos(s);
        }
        return instancia;
    }

    public void prestarLibro(CatalogoLibros libros, CatalogoUsuarios usuarios) {
        System.out.println("--- PRESTAR LIBRO ---");
        
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        System.out.print("ID del usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        Libro libro = libros.buscarElemento(idLibro);
        Usuario usuario = usuarios.buscarElemento(idUsuario);
        
        if (confirmarPrestamo(libro, usuario, idUsuario)) {
            // Realizar el préstamo
            Date fechaPrestamo = new Date();
            Prestamo nuevoPrestamo = new Prestamo(prestamos.size() + 1, idLibro, idUsuario, fechaPrestamo, null, false);
            prestamos.add(nuevoPrestamo);
            
            // Marcar libro como no disponible
            libro.setDisponible(false);
            
            System.out.println("Préstamo realizado con éxito.");
        }
    }

    public boolean confirmarPrestamo(Libro libro, Usuario usuario, int idUsuario) {
        if (libro == null) {
            System.out.println("Error: Libro no encontrado.");
            return false;
        }
        
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return false;
        }
        
        if (!libro.isDisponible()) {
            System.out.println("Error: El libro no está disponible actualmente.");
            return false;
        }
        
        // Verificar si el usuario tiene más de 3 libros prestados
        int librosUsuario = 0;
        for (Prestamo p : prestamos) {
            if (p.getIdUsuario() == idUsuario && !p.isDevuelto()) {
                librosUsuario++;
            }
        }
        
        if (librosUsuario >= 3) {
            System.out.println("Error: El usuario ya tiene 3 libros prestados.");
            return false;
        }

        return true;
    }

    public void devolverLibro(CatalogoLibros libros) {
        System.out.println("--- DEVOLVER LIBRO ---");
        
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
        
        Prestamo prestamo = buscarPrestamoActivo(idLibro);
    
        if (prestamo == null) {
            System.out.println("Error: No hay préstamos activos para este libro.");
            return;
        }
        
        // Marcar préstamo como devuelto
        prestamo.setDevuelto(true);
        prestamo.setFechaDevolucion(new Date());
        
        libros.modificarDisponibilidadLibro(idLibro);

        System.out.println("Libro devuelto con éxito.");
    }

    private Prestamo buscarPrestamoActivo(int idLibro) {
        // Buscar préstamo activo para este libro
        for (Prestamo p : prestamos) {
            if (p.getIdLibro() == idLibro && !p.isDevuelto()) {
                return p;
            }
        }

        return null;
    }

    public void mostrarPrestamosActivos(CatalogoLibros libros, CatalogoUsuarios usuarios) {
        System.out.println("--- PRÉSTAMOS ACTIVOS ---");
        
        boolean hayPrestamos = false;
        
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()) {
                Libro libro = libros.buscarElemento(prestamo.getIdLibro());
                Usuario usuario = usuarios.buscarElemento(prestamo.getIdUsuario());
                
                if (libro != null && usuario != null) {
                    System.out.println("ID Préstamo: " + prestamo.getId() + 
                                     " | Libro: " + libro.getTitulo() + 
                                     " | Usuario: " + usuario.getNombre() + 
                                     " | Fecha: " + prestamo.getFechaPrestamo());
                    hayPrestamos = true;
                }
            }
        }
        
        if (!hayPrestamos) {
            System.out.println("No hay préstamos activos.");
        }
    }

}
