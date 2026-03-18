// Clase que queremos construir (objeto complejo)
//
// Ejemplo real:
// - Casa, Computador, Pedido, etc.
// - Tiene muchos atributos y posibles combinaciones

public class Producto {

    private String parteA;
    private String parteB;
    private String parteC;

    // Setters usados por el builder
    public void setParteA(String parteA) {
        this.parteA = parteA;
    }

    public void setParteB(String parteB) {
        this.parteB = parteB;
    }

    public void setParteC(String parteC) {
        this.parteC = parteC;
    }

    @Override
    public String toString() {
        return "Producto: " + parteA + ", " + parteB + ", " + parteC;
    }
}
