// Element: interfaz para elementos visitables
//
// Importante:
// - Define el metodo accept que recibe un Visitor
// - Permite que el visitor ejecute la operacion correcta

public interface Element {
    void accept(Visitor visitor);
}
