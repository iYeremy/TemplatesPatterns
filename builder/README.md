# Patrón de Diseño: Builder

### Constructor (Builder)

### 2. ¿Qué problema resuelve?

Imagina que quieres crear un objeto complejo, como una pizza. Una pizza tiene muchos ingredientes (masa, salsa, queso, pepperoni, champiñones, etc.), y las combinaciones pueden ser infinitas. Si intentas crear una pizza usando un constructor con muchos parámetros, se vuelve un lío:

```java
// ¡Un constructor con muchísimos parámetros!
Pizza pizza = new Pizza(
    "delgada", // tipo de masa
    "tomate",  // tipo de salsa
    true,      // queso
    true,      // pepperoni
    false,     // champiñones
    true,      // cebolla
    ... // ¡Y sigue!
);
```

Esto es difícil de leer, de mantener y propenso a errores (¿qué parámetro era el de los champiñones?). El patrón Builder resuelve este problema, especialmente cuando el objeto final puede tener muchas configuraciones diferentes.

### 3. Idea principal (explicación corta)

El patrón Builder te permite construir objetos complejos paso a paso. Separa la construcción de la representación final del objeto. Así, puedes usar el mismo proceso de construcción para crear diferentes representaciones del objeto.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes un objeto complejo que quieres construir.** Digamos una `Pizza`.
2.  **Creas una interfaz para un "constructor" (Builder).** Esta interfaz define los métodos para añadir cada parte a la `Pizza` (ej: `addMasa()`, `addSalsa()`, `addQueso()`).
3.  **Haces una implementación concreta de ese constructor.** Por ejemplo, `PizzaBuilder`, que sabe cómo construir la `Pizza` paso a paso.
4.  **Creas un "director" (Director) (opcional, pero útil).** El `Director` sabe cómo construir una configuración específica del objeto usando el `Builder`. Por ejemplo, `Chef` sabe cómo hacer una "Pizza Hawaiana" llamando a los métodos del `PizzaBuilder`.
5.  **El cliente interactúa con el `Builder` (o el `Director`)** llamando a sus métodos para construir el objeto. Al final, el `Builder` te devuelve el objeto completo.

### 5. Estructura (clases y relaciones)

*   **Producto (Product):**
    *   Es el objeto complejo que se está construyendo.
    *   No necesita saber nada del proceso de construcción.
    *   *Ejemplo:* `Pizza`
*   **Builder (Abstract Builder):**
    *   Define la interfaz para crear las partes del objeto `Producto`.
    *   *Ejemplo:* `PizzaBuilder` (interfaz o clase abstracta)
*   **ConcreteBuilder:**
    *   Implementa la interfaz `Builder`.
    *   Construye y ensambla las partes del `Producto`.
    *   Mantiene una referencia al objeto `Producto` que está construyendo.
    *   Proporciona un método `getResult()` para obtener el objeto `Producto` final.
    *   *Ejemplo:* `HawaiianPizzaBuilder`, `VegetarianPizzaBuilder`
*   **Director:** (Opcional, pero recomendado para encapsular lógicas de construcción comunes)
    *   Construye un objeto usando la interfaz `Builder`.
    *   Sabe en qué secuencia llamar a los métodos del `Builder` para construir variaciones específicas del `Producto`.
    *   *Ejemplo:* `Chef` que construye una "pizza estándar".

**Relación:** El `Director` trabaja con un `Builder` para construir el `Producto`. El `Cliente` puede trabajar directamente con un `Builder` o usar el `Director`.

### 6. Mini ejemplo

Vamos a construir un objeto `Producto` (que en este caso es una `Hamburguesa`) usando el patrón Builder.

```java
// 1. Producto: La hamburguesa que queremos construir
class Producto {
    private String tipoPan;
    private String carne;
    private boolean queso;
    private boolean lechuga;
    private boolean tomate;

    public void setTipoPan(String tipoPan) { this.tipoPan = tipoPan; }
    public void setCarne(String carne) { this.carne = carne; }
    public void setQueso(boolean queso) { this.queso = queso; }
    public void setLechuga(boolean lechuga) { this.lechuga = lechuga; }
    public void setTomate(boolean tomate) { this.tomate = tomate; }

    @Override
    public String toString() {
        return "Hamburguesa con pan " + tipoPan + ", carne de " + carne +
               (queso ? ", queso" : "") + (lechuga ? ", lechuga" : "") +
               (tomate ? ", tomate" : "") + ".";
    }
}

// 2. Builder: Interfaz para construir partes de la hamburguesa
interface Builder {
    void buildPan();
    void buildCarne();
    void buildQueso();
    void buildLechuga();
    void buildTomate();
    Producto getProducto();
}

// 3. ConcreteBuilder: Constructor específico de una Hamburguesa de Res
class ConcreteBuilder implements Builder {
    private Producto producto;

    public ConcreteBuilder() {
        this.producto = new Producto();
    }

    @Override
    public void buildPan() {
        producto.setTipoPan("Brioche");
    }

    @Override
    public void buildCarne() {
        producto.setCarne("Res");
    }

    @Override
    public void buildQueso() {
        producto.setQueso(true);
    }

    @Override
    public void buildLechuga() {
        producto.setLechuga(true);
    }

    @Override
    public void buildTomate() {
        producto.setTomate(true);
    }

    @Override
    public Producto getProducto() {
        return this.producto;
    }
}

// 4. Director: Sabe cómo ensamblar una hamburguesa estándar
class Director {
    public void construct(Builder builder) {
        builder.buildPan();
        builder.buildCarne();
        builder.buildQueso();
        builder.buildLechuga();
        builder.buildTomate();
    }
}

// Uso en Main
public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        ConcreteBuilder builder = new ConcreteBuilder();

        // El director usa el builder para construir la hamburguesa
        director.construct(builder);
        Producto hamburguesa = builder.getProducto();
        System.out.println(hamburguesa.toString());

        // También podemos construirla manualmente sin un director
        ConcreteBuilder veggieBuilder = new ConcreteBuilder();
        veggieBuilder.buildPan();
        veggieBuilder.producto.setCarne("Vegetal"); // Modificamos el tipo de carne
        veggieBuilder.buildLechuga();
        // No añadimos queso ni tomate si no queremos
        Producto hamburguesaVeggie = veggieBuilder.getProducto();
        System.out.println(hamburguesaVeggie.toString());
    }
}
```

En este ejemplo:
*   **`Producto`** es la `Hamburguesa`.
*   **`Builder`** es la interfaz que define los pasos (`buildPan`, `buildCarne`, etc.).
*   **`ConcreteBuilder`** es el que sabe cómo construir una `Hamburguesa` de res específica, paso a paso.
*   **`Director`** es quien sabe cómo pedirle al `Builder` que cree una hamburguesa "completa" siguiendo un orden.

Podemos cambiar el `ConcreteBuilder` (por ejemplo, `PolloBuilder`) para obtener una hamburguesa de pollo, sin cambiar el `Director` ni la forma en que el `Main` (cliente) la usa.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Nuevos `ConcreteBuilder`s:** Puedes crear diferentes tipos de constructores (ej: `HamburguesaVeganaBuilder`) sin afectar al `Producto` ni al `Director`.
    *   **Nuevos `Director`s:** Puedes tener diferentes `Director`s que ensamblen el producto de distintas maneras (ej: `ChefDeComidaRapida` vs `ChefGourmet`).
    *   **Las partes del `Producto`:** Puedes añadir más ingredientes a tu `Pizza` o `Hamburguesa` sin cambiar la lógica principal del constructor.
*   **No debes cambiar:**
    *   **La interfaz `Builder`:** Esta define los métodos esenciales para construir el `Producto`. Modificarla podría romper los `ConcreteBuilder`s y `Director`s existentes.
    *   **La responsabilidad del `Producto`:** El `Producto` debe seguir siendo un objeto pasivo que solo almacena sus partes, sin lógica de construcción propia.

### 8. Cuándo usarlo (muy importante)

*   **Cuando el algoritmo de construcción de un objeto es complejo:** Si un objeto tiene muchos campos opcionales o diferentes formas de inicializarse.
*   **Cuando quieres que un objeto se construya inmutablemente:** Puedes construir todas las partes con el `Builder` y luego obtener el objeto final, asegurándote de que no se modifique después.
*   **Cuando necesitas construir diferentes representaciones del mismo objeto:** Por ejemplo, una `Pizza` con masa fina, otra con masa gruesa, etc., usando el mismo proceso de construcción.
*   **Tipo parcial:** Si te encuentras con un constructor con 4 o más parámetros, o si ves que hay muchas combinaciones para crear un objeto.

### 9. Resumen en una frase

El patrón Builder separa la construcción de un objeto complejo de su representación, permitiendo construir diferentes versiones del mismo objeto paso a paso.

