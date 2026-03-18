import java.util.ArrayList;
import java.util.List;

// Sujeto concreto
//
// Cuando cambia su estado → notifica a todos

public class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String estado;

    @Override
    public void agregar(Observer o) {
        observers.add(o);
    }

    @Override
    public void eliminar(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notificar() {
        for (Observer o : observers) {
            o.actualizar(estado);
        }
    }

    // Metodo que cambia el estado
    public void setEstado(String estado) {
        this.estado = estado;
        notificar(); // CLAVE: notifica automaticamente
    }
}
