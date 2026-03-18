// Prueba del Chain of Responsibility

public class Main {
    public static void main(String[] args) {

        // Crear handlers
        Handler h1 = new ConcreteHandlerA();
        Handler h2 = new ConcreteHandlerB();

        // Construir la cadena
        h1.setSiguiente(h2);

        // Enviar solicitudes
        h1.manejarSolicitud(5);   // lo maneja A
        h1.manejarSolicitud(20);  // pasa a B
    }
}
