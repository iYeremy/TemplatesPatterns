// Clase de prueba (Main)
// Verifica que siempre se obtiene la misma instancia

public class Main {
    public static void main(String[] args) {

        // Obtener instancia 1
        Singleton s1 = Singleton.getInstancia();

        // Obtener instancia 2
        Singleton s2 = Singleton.getInstancia();

        // Comparacion de referencias (deben ser iguales)
        if (s1 == s2) {
            System.out.println("Es la misma instancia (Singleton funciona)");
        } else {
            System.out.println("Son instancias diferentes (ERROR)");
        }

        // Ejemplo de uso real
        // Aqui podrias llamar metodos del singleton
        // ej: s1.configurarAlgo();
    }
}
