// Invoker: solicita la ejecucion del command
//
// Importante:
// - NO sabe que hace el command
// - Solo sabe que puede llamar a ejecutar()
// - Puede almacenar commands para ejecutarlos despues

public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void ejecutarCommand() {
        command.ejecutar();
    }
}
