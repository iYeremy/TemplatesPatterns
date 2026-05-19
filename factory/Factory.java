// Factory: decide que producto crear
//
// Importante:
// - Encapsula la logica de creacion
// - El cliente no sabe que clase concreta se instancia

public class Factory {

    public static Producto crearProducto(String tipo) {
        switch (tipo.toLowerCase()) {
            case "a":
                return new ProductoConcretoA();
            case "b":
                return new ProductoConcretoB();
            default:
                throw new IllegalArgumentException("Tipo desconocido: " + tipo);
        }
    }
}
