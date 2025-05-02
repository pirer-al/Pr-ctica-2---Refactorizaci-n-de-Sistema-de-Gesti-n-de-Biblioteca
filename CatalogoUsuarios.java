import java.util.Scanner;

public class CatalogoUsuarios extends Catalogo<Usuario>{
    private static CatalogoUsuarios instancia;

    private CatalogoUsuarios(Scanner scanner) {
        super(scanner);
    }

    public static CatalogoUsuarios getInstancia(Scanner s) {
        if (instancia == null) {
            instancia = new CatalogoUsuarios(s);
        }
        return instancia;
    }

    @Override
    public Usuario buscarElemento(int idElemento) {
        // TODO Auto-generated method stub
        for (Usuario u : elementos) {
            if (u.getId() == idElemento) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void registrarElemento() {
        System.out.println("--- REGISTRAR NUEVO USUARIO ---");
        Usuario nuevoUsuario = solicitarDatosUsuario();
        elementos.add(nuevoUsuario);
        System.out.println("Usuario registrado con éxito.");
    }

    public void mostrarUsuarios() {
        System.out.println("--- LISTADO DE USUARIOS ---");
        
        if (elementos.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        
        for (Usuario usuario : elementos) {
            System.out.println("ID: " + usuario.getId() + " | Nombre: " + usuario.getNombre() + 
                             " | Email: " + usuario.getEmail() + " | Teléfono: " + usuario.getTelefono());
        }
    }

    private Usuario solicitarDatosUsuario() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea
    
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
    
        System.out.print("Email: ");
        String email = scanner.nextLine();
    
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
    
        return new Usuario(id, nombre, email, telefono);
    }

}
