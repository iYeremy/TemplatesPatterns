import java.util.List;

// Sujeto (el que notifica)
//
// Tiene:
// - lista de observers
// - metodos para suscribir, eliminar y notificar

public interface Subject {

    void agregar(Observer o);
    void eliminar(Observer o);
    void notificar();
}
