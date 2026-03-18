// Otro manejador en la cadena

public class ConcreteHandlerB extends Handler {

    @Override
    public void manejarSolicitud(int valor) {

        if (valor >= 10) {
            System.out.println("Handler B maneja el valor: " + valor);
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(valor);
        }
    }
}
