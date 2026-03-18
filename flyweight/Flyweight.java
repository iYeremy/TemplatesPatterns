// Patron Flyweight
// Permite compartir objetos para ahorrar memoria
//
// Cuando usar:
// - MUCHOS objetos similares
// - Alto consumo de memoria
//
// Idea clave:
// - Reutilizar instancias existentes

public interface Flyweight {

    // recibe el estado extrinseco (variable)
    void operar(String estadoExtrinseco);
}
