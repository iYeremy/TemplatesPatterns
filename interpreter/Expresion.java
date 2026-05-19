// Expresion: interfaz para todas las expresiones
//
// Idea:
// - Define el metodo interpretar
// - Tanto expresiones terminales como no terminales implementan esto

public interface Expresion {
    int interpretar(Contexto contexto);
}
