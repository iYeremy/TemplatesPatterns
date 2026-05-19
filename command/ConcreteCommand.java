// Command concreto: encapsula la peticion
//
// Importante:
// - Mantiene referencia al Receiver
// - Llamar a ejecutar() delega en el Receiver

public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void ejecutar() {
        receiver.accion();
    }
}
