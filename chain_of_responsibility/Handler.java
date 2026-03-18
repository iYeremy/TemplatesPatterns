// Patron Chain of Responsibility
// Permite pasar una solicitud a traves de una cadena de objetos
// hasta que uno la maneje
//
// Cuando usar:
// - No sabes quien procesara la solicitud
// - Quieres evitar acoplar emisor y receptor
//
// Idea clave:
// - Cada handler tiene referencia al siguiente
// - Decide si maneja o delega

public abstract class Handler {

    protected Handler siguiente;

    // Enlazar cadena
    public void setSiguiente(Handler siguiente) {
        this.siguiente = siguiente;
    }

    // Metodo principal
    public abstract void manejarSolicitud(int valor);
}
