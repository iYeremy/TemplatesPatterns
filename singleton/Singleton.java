// Patron Singleton
// Asegura que una clase tenga UNA sola instancia en toda la aplicacion
// y proporciona un punto de acceso global a ella.
//
// Cuando usar:
// - Cuando necesitas una unica instancia compartida
// - Ej: configuracion global, logger, conexion unica (controlada)
//
// Idea clave:
// - Constructor privado
// - Instancia estatica
// - Metodo publico para obtener la instancia
//
// Ejemplo :
// - Un "presidente": solo puede existir uno, todos acceden al mismo

public class Singleton {

    // Instancia unica 
	private static Singleton instancia;

    // Constructor privado para evitar new desde afuera
    private Singleton() {
        // inicializacion
    }

    // Metodo de acceso global
    public static Singleton getInstancia() {
        if (instancia == null) {
            instancia = new Singleton(); // creacion lazy
        }
        return instancia;
    }
}
