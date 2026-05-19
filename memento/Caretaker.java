// Caretaker: gestiona los mementos sin acceder a su contenido
//
// Importante:
// - Almacena y devuelve mementos
// - NUNCA debe modificar o leer el estado dentro del memento
// - Actua como un historial de snapshots

import java.util.Stack;

public class Caretaker {

    private Stack<Memento> historial = new Stack<>();

    public void guardarMemento(Memento memento) {
        historial.push(memento);
    }

    public Memento deshacer() {
        if (!historial.isEmpty()) {
            return historial.pop();
        }
        return null;
    }
}
