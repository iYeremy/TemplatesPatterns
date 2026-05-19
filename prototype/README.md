# Patrón de Diseño: Prototype

### Prototipo (Prototype)

### 2. ¿Qué problema resuelve?

Imagina que quieres crear copias de objetos complejos, pero no quieres depender de sus clases concretas (usando `new ClaseConcreta()`). Además, algunos objetos son costosos de crear (consultas a BD, lecturas de archivos). El patrón Prototype resuelve esto permitiendo clonar objetos existentes en lugar de crearlos desde cero.

### 3. Idea principal (explicación corta)

El patrón Prototype permite crear nuevos objetos clonando un objeto existente (prototipo), sin depender de sus clases concretas. Es como hacer una fotocopia en lugar de escribir el documento desde cero.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines la interfaz Prototype.** Con un método `clone()`.
2.  **Cada clase concreta implementa `clone()`.** Retorna una copia de sí misma.
3.  **El cliente** mantiene referencias a prototipos y los clona cuando necesita nuevos objetos.
4.  **No usa `new` directamente** para crear variantes, solo clona prototipos existentes.

### 5. Estructura (clases y relaciones)

*   **Prototype:**
    *   Interfaz que declara el método `clone()`.
    *   *Ejemplo:* `Prototype`
*   **ConcretePrototype:**
    *   Implementa `clone()` para crear una copia de sí mismo.
    *   Puede haber múltiples tipos de prototipos.
    *   *Ejemplo:* `ConcretePrototypeA`, `ConcretePrototypeB`
*   **Cliente:**
    *   Mantiene referencias a prototipos.
    *   Clona prototipos en lugar de crear objetos con `new`.
    *   *Ejemplo:* `Main`

**Relación:** El `Cliente` tiene prototipos y llama a `clone()` para crear copias, sin conocer la clase concreta del prototipo.

### 6. Mini ejemplo

Vamos a crear un sistema de formas geométricas donde podemos clonar formas existentes.

```java
// 1. Prototype: interfaz
interface Shape extends Cloneable {
    Shape clone();
    void draw();
}

// 2. ConcretePrototypes
class Circle implements Shape {
    private int radius;

    public Circle(int radius) { this.radius = radius; }

    @Override
    public Shape clone() {
        return new Circle(this.radius);
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }
}

class Rectangle implements Shape {
    private int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this.width, this.height);
    }

    @Override
    public void draw() {
        System.out.println("Drawing rectangle " + width + "x" + height);
    }
}

// 3. Cliente: usa prototipos
public class Main {
    public static void main(String[] args) {
        // Crear prototipos iniciales
        Shape circleProto = new Circle(10);
        Shape rectProto = new Rectangle(5, 8);

        // Clonar en lugar de crear desde cero
        Shape circle1 = circleProto.clone();
        Shape circle2 = circleProto.clone();
        Shape rect1 = rectProto.clone();

        circle1.draw();
        circle2.draw();
        rect1.draw();
    }
}
```

En este ejemplo:
*   **`Shape`** es la interfaz Prototype.
*   **`Circle`** y **`Rectangle`** implementan su propio `clone()`.
*   El **`Main`** crea prototipos y los clona sin usar `new` para cada copia.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevos prototipos:** Sin modificar el cliente.
    *   **Clonación profunda vs superficial:** Dependiendo si el objeto tiene referencias a otros objetos.
    *   **Registry de prototipos:** Un mapa que almacena prototipos por nombre/tipo.
*   **No debes cambiar:**
    *   **La interfaz `clone()`:** Debe retornar un nuevo objeto independiente.
    *   **La independencia:** Modificar la copia NO debe afectar al original.

### 8. Cuándo usarlo (muy importante)

*   **Cuando crear un objeto es costoso:** Consultas a BD, parsing de archivos, cálculos complejos.
*   **Cuando quieres evitar subclases de creación:** En lugar de `new ClaseEspecifica()`, clonas un prototipo.
*   **Cuando el sistema debe ser independiente de cómo se crean sus productos.**
*   **Cuando necesitas muchas instancias similares:** Clonar es más eficiente que crear desde cero.
*   **Ejemplos típicos:** Copiar configuraciones, clonar documentos, duplicar formas en editores gráficos.

### 9. Resumen en una frase

El patrón Prototype permite crear nuevos objetos clonando un prototipo existente, evitando la creación costosa y el acoplamiento a clases concretas.
