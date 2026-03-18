// Define los pasos para construir el objeto
//
// Idea:
// - No construye directamente
// - Solo define el proceso

public interface Builder {

    void buildParteA();
    void buildParteB();
    void buildParteC();

    Producto getResultado();
}
