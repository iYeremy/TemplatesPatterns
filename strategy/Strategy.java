// Patron Strategy
// Poder cambiar la "forma de hacer algo" en medio del proceso sin romper nada
//
// Cuando usar:
// - Cuando tienes varias formas de hacer lo mismo
// - Quieres evitar muchos if/else
// - Quieres cambiar comportamiento en tiempo de ejecucion
//
// Ejemplo: 
// - Metodos de pago (tarjeta, paypal, crypto)

public interface Strategy {
    void ejecutar(); // metodo comun para todos los algoritmos
}
