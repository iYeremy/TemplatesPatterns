// Prueba del patron Command

public class Main {
    public static void main(String[] args) {

        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();

        // El invoker ejecuta sin saber que hace el command
        invoker.setCommand(command);
        invoker.ejecutarCommand();
    }
}
