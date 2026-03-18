// Maneja ciertos casos, si no puede pasa al siguiente

public class ConcreteHandlerA extends Handler {

    @Override
    public void manejarSolicitud(int valor) {

        if (valor < 10) {
            System.out.println("Handler A maneja el valor: " + valor);
        } else if (siguiente != null) {
            siguiente.manejarSolicitud(valor);
        }
    }
}
