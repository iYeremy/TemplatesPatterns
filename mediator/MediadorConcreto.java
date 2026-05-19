// Mediador concreto: implementa la logica de comunicacion
//
// Importante:
// - Conoce a todos los colegas
// - Decide como reaccionar ante cada evento
// - Evita que los colegas se comuniquen directamente

public class MediadorConcreto implements Mediador {

    private ColegaA colegaA;
    private ColegaB colegaB;

    public void setColegaA(ColegaA colegaA) {
        this.colegaA = colegaA;
    }

    public void setColegaB(ColegaB colegaB) {
        this.colegaB = colegaB;
    }

    @Override
    public void notificar(Colega colega, String evento) {
        if (evento.equals("eventoA")) {
            System.out.println("Mediator reacciona a eventoA");
            colegaB.recibir("ColegaA hizo eventoA");
        } else if (evento.equals("eventoB")) {
            System.out.println("Mediator reacciona a eventoB");
            colegaA.recibir("ColegaB hizo eventoB");
        }
    }
}
