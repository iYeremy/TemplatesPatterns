// Prueba del patron Decorator

public class Main {
    public static void main(String[] args) {

        // Componente base sin decoracion
        Componente base = new ComponenteConcreto();
        System.out.println(base.operacion());

        // Componente con un decorador
        Componente conA = new DecoradorConcretoA(base);
        System.out.println(conA.operacion());

        // Componente con dos decoradores encadenados
        Componente conAB = new DecoradorConcretoB(conA);
        System.out.println(conAB.operacion());
    }
}
