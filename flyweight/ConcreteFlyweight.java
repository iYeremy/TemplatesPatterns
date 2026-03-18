// Objeto que se comparte
//
// Contiene estado INTRINSECO (comun)

public class ConcreteFlyweight implements Flyweight {

    private String estadoIntrinseco;

    public ConcreteFlyweight(String estadoIntrinseco) {
        this.estadoIntrinseco = estadoIntrinseco;
    }

    @Override
    public void operar(String estadoExtrinseco) {
        System.out.println("Intrinseco: " + estadoIntrinseco +
                           " | Extrinseco: " + estadoExtrinseco);
    }
}
