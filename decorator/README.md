# Patrón de Diseño: Decorator

### Decorador (Decorator)

### 2. ¿Qué problema resuelve?

Imagina que tienes un objeto básico (como un café simple) y quieres agregarle extras (leche, azúcar, crema). Si usas herencia, tendrías que crear una clase para cada combinación: `CafeConLeche`, `CafeConAzucar`, `CafeConLecheYAzucar`, etc. Esto crea una explosión de clases. El patrón Decorator resuelve esto permitiendo agregar responsabilidades a un objeto de forma dinámica y combinable.

### 3. Idea principal (explicación corta)

El patrón Decorator envuelve un objeto dentro de otro objeto decorador, agregando comportamiento sin modificar la clase original. Los decorators se pueden encadenar para combinar múltiples comportamientos.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes una interfaz común.** Tanto el objeto base como los decorators la implementan.
2.  **Creas el componente concreto.** Este es tu objeto base sin decoraciones.
3.  **Creas una clase base Decorator.** Implementa la misma interfaz y mantiene una referencia a otro objeto de esa interfaz.
4.  **Creas Decorators concretos.** Cada uno agrega un comportamiento específico, delegando al objeto envuelto.
5.  **Encadenas decorators.** Puedes envolver un decorator con otro para combinar comportamientos.

### 5. Estructura (clases y relaciones)

*   **Componente (Component):**
    *   Interfaz común para objetos base y decorators.
    *   Define las operaciones que pueden ser decoradas.
    *   *Ejemplo:* `Componente`
*   **ComponenteConcreto (ConcreteComponent):**
    *   El objeto base que se puede decorar.
    *   Implementa la interfaz `Componente`.
    *   *Ejemplo:* `ComponenteConcreto`
*   **Decorator (Base Decorator):**
    *   Clase abstracta que implementa `Componente`.
    *   Mantiene una referencia a otro `Componente` (que puede ser base u otro decorator).
    *   Delega las operaciones al componente envuelto.
    *   *Ejemplo:* `Decorador`
*   **DecoratorConcreto (ConcreteDecorator):**
    *   Implementa el comportamiento adicional.
    *   Puede agregar lógica antes o después de delegar.
    *   *Ejemplo:* `DecoradorConcretoA`, `DecoradorConcretoB`

**Relación:** Los `DecoratorConcreto` envuelven un `Componente` (que puede ser `ComponenteConcreto` u otro `Decorator`). Todos implementan la misma interfaz `Componente`.

### 6. Mini ejemplo

Vamos a crear un sistema de precios para un café con diferentes extras.

```java
// 1. Componente: Interfaz comun
interface Cafe {
    double costo();
    String descripcion();
}

// 2. Componente Concreto
class CafeSimple implements Cafe {
    @Override
    public double costo() { return 2.0; }

    @Override
    public String descripcion() { return "Cafe simple"; }
}

// 3. Decorador Base
abstract class CafeDecorador implements Cafe {
    protected Cafe cafe;

    public CafeDecorador(Cafe cafe) {
        this.cafe = cafe;
    }
}

// 4. Decoradores Concretos
class LecheDecorador extends CafeDecorador {
    public LecheDecorador(Cafe cafe) { super(cafe); }

    @Override
    public double costo() { return cafe.costo() + 0.5; }

    @Override
    public String descripcion() { return cafe.descripcion() + ", leche"; }
}

class AzucarDecorador extends CafeDecorador {
    public AzucarDecorador(Cafe cafe) { super(cafe); }

    @Override
    public double costo() { return cafe.costo() + 0.3; }

    @Override
    public String descripcion() { return cafe.descripcion() + ", azucar"; }
}

// 5. Uso en Main
public class Main {
    public static void main(String[] args) {
        Cafe miCafe = new CafeSimple();
        System.out.println(miCafe.descripcion() + " = $" + miCafe.costo());

        miCafe = new LecheDecorador(miCafe);
        System.out.println(miCafe.descripcion() + " = $" + miCafe.costo());

        miCafe = new AzucarDecorador(miCafe);
        System.out.println(miCafe.descripcion() + " = $" + miCafe.costo());
    }
}
```

En este ejemplo:
*   **`Cafe`** es la interfaz componente.
*   **`CafeSimple`** es el componente base.
*   **`LecheDecorador`** y **`AzucarDecorador`** son decoradores que agregan costo y descripción.
*   Se **encadenan** los decorators para combinar extras.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevos Decorators:** Sin modificar el componente base ni otros decorators.
    *   **El orden de los decorators:** Cambia el comportamiento final.
    *   **Agregar lógica antes/después:** Cada decorator decide cuándo ejecutar su comportamiento.
*   **No debes cambiar:**
    *   **La interfaz `Componente`:** Es el contrato que todos deben seguir.
    *   **La delegación en el Decorador base:** Si no delega, se rompe la cadena.

### 8. Cuándo usarlo (muy importante)

*   **Cuando necesitas agregar responsabilidades a objetos individualmente**, sin afectar a otros objetos de la misma clase.
*   **Cuando la herencia no es práctica:** Demasiadas combinaciones crearían una explosión de subclases.
*   **Cuando quieres poder agregar/quitar responsabilidades en tiempo de ejecución.**
*   **Cuando el comportamiento es opcional o configurable por el usuario.**

### 9. Resumen en una frase

El patrón Decorator permite agregar comportamiento a objetos dinámicamente envolviéndolos en decorators que implementan la misma interfaz, evitando la explosión de subclases por herencia.
