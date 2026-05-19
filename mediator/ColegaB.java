// Colega concreto B
//
// Misma estructura que ColegaA pero con su propia logica
// Tambien se comunica solo a traves del mediador

public class ColegaB extends Colega {

    public ColegaB(Mediador mediador) {
        super(mediador);
    }

    public void accionB() {
        System.out.println("ColegaB ejecuto accionB");
        mediador.notificar(this, "eventoB");
    }

    public void recibir(String mensaje) {
        System.out.println("ColegaB recibio: " + mensaje);
    }
}
