import java.util.Scanner;

public class BibliotecaApp {
    
    private static Scanner scanner = new Scanner(System.in);
    private static CatalogoLibros libros = CatalogoLibros.getInstancia(scanner);
    private static CatalogoUsuarios usuarios = CatalogoUsuarios.getInstancia(scanner);
    private static GestorPrestamos gestorPrestamos = GestorPrestamos.getInstancia(scanner);

    public static void main(String[] args) throws Exception {
        // Agregar algunos datos de ejemplo
        inicializarDatos();
        
        boolean salir = false;
        while (!salir) {
            System.out.println("--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Buscar libros");
            System.out.println("6. Ver todos los libros");
            System.out.println("7. Ver todos los usuarios");
            System.out.println("8. Ver préstamos activos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            
            switch (opcion) {
                case 1:
                    libros.registrarElemento();;
                    break;
                case 2:
                    usuarios.registrarElemento();
                    break;
                case 3:
                    gestorPrestamos.prestarLibro(libros, usuarios);
                    break;
                case 4:
                    gestorPrestamos.devolverLibro(libros);
                    break;
                case 5:
                    libros.buscarLibros();
                    break;
                case 6:
                    libros.mostrarLibros();
                    break;
                case 7:
                    usuarios.mostrarUsuarios();
                    break;
                case 8:
                    gestorPrestamos.mostrarPrestamosActivos(libros, usuarios);
                    break;
                case 9:
                    salir = true;
                    System.out.println("¡Sistema de biblioteca! sesión finalizada");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void inicializarDatos() {
        // Libros de ejemplo
        libros.añadirElemento(new Libro(1, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605, "Ficción", true));
        libros.añadirElemento(new Libro(2, "Cien años de soledad", "Gabriel García Márquez", 1967, "Novela", true));
        libros.añadirElemento(new Libro(3, "El principito", "Antoine de Saint-Exupéry", 1943, "Fábula", true));
        
        // Usuarios de ejemplo
        usuarios.añadirElemento(new Usuario(101, "Jose Camacho", "jantonio@gmail.com", "123456789"));
        usuarios.añadirElemento(new Usuario(102, "Patricia Moreno", "patricia@gmail.com", "987654321"));
    }

}
