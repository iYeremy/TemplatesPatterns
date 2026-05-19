// Prueba del patron Memento

public class Main {
    public static void main(String[] args) {

        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        // Cambiar estado y guardar
        originator.setEstado("Estado 1");
        caretaker.guardarMemento(originator.guardar());

        originator.setEstado("Estado 2");
        caretaker.guardarMemento(originator.guardar());

        originator.setEstado("Estado 3");

        // Deshacer al estado anterior
        System.out.println("--- Deshaciendo ---");
        Memento memento = caretaker.deshacer();
        if (memento != null) {
            originator.restaurar(memento);
        }

        // Deshacer de nuevo
        System.out.println("--- Deshaciendo de nuevo ---");
        memento = caretaker.deshacer();
        if (memento != null) {
            originator.restaurar(memento);
        }
    }
}
