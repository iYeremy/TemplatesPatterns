// Memento: almacena el estado del Originator
//
// Idea:
// - Contiene una snapshot del estado interno
// - No permite que otros objetos accedan al estado directamente
// - Es inmutable una vez creado

public class Memento {

    private String estado;

    public Memento(String estado) {
        this.estado = estado;
    }

    String getEstado() {
        // Package-private: solo Originator puede acceder
        return estado;
    }
}
