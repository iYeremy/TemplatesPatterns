// Observador concreto
//
// Reacciona cuando recibe la notificacion

public class ConcreteObserver implements Observer {

    private String nombre;

    public ConcreteObserver(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibio: " + mensaje);
    }
}
