// Prueba del patron Observer

public class Main {
    public static void main(String[] args) {

        ConcreteSubject sujeto = new ConcreteSubject();

        Observer obs1 = new ConcreteObserver("Observer 1");
        Observer obs2 = new ConcreteObserver("Observer 2");

        // Suscribirse
        sujeto.agregar(obs1);
        sujeto.agregar(obs2);

        // Cambiar estado (dispara notificaciones)
        sujeto.setEstado("Nuevo estado disponible");
    }
}
