// Interfaz Mediator: define la comunicacion entre colegas
//
// Idea:
// - Centraliza la comunicacion
// - Los colegas no se comunican directamente entre si

public interface Mediador {
    void notificar(Colega colega, String evento);
}
