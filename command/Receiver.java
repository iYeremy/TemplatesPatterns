// Receptor: sabe COMO realizar la operacion real
//
// El Command delega la ejecucion al Receiver
// El Receiver no sabe nada del Command

public class Receiver {

    public void accion() {
        System.out.println("Receiver ejecutando accion");
    }
}
