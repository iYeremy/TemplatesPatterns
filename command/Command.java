// Interfaz Command: define el metodo ejecutar
//
// Idea:
// - Encapsula una peticion como un objeto
// - Permite parametrizar, encolar o deshacer operaciones

public interface Command {
    void ejecutar();
}
