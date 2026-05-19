// Colega concreto A
//
// Envía eventos al mediador cuando algo pasa
// El mediador decide como reaccionar y notificar a otros

public class ColegaA extends Colega {

    public ColegaA(Mediador mediador) {
        super(mediador);
    }

    public void accionA() {
        System.out.println("ColegaA ejecuto accionA");
        mediador.notificar(this, "eventoA");
    }

    public void recibir(String mensaje) {
        System.out.println("ColegaA recibio: " + mensaje);
    }
}
