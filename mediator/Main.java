// Prueba del patron Mediator

public class Main {
    public static void main(String[] args) {

        MediadorConcreto mediador = new MediadorConcreto();

        ColegaA colegaA = new ColegaA(mediador);
        ColegaB colegaB = new ColegaB(mediador);

        mediador.setColegaA(colegaA);
        mediador.setColegaB(colegaB);

        // Los colegas se comunican a traves del mediador
        colegaA.accionA();
        System.out.println("---");
        colegaB.accionB();
    }
}
