// Originator: crea y restaura mementos
//
// Importante:
// - Sabe como guardar su estado en un Memento
// - Sabe como restaurar su estado desde un Memento
// - Es el unico que puede acceder al contenido del Memento

public class Originator {

    private String estado;

    public void setEstado(String estado) {
        this.estado = estado;
        System.out.println("Estado actual: " + estado);
    }

    public String getEstado() {
        return estado;
    }

    // Guarda el estado actual en un Memento
    public Memento guardar() {
        return new Memento(estado);
    }

    // Restaura el estado desde un Memento
    public void restaurar(Memento memento) {
        this.estado = memento.getEstado();
        System.out.println("Estado restaurado: " + estado);
    }
}
