# Patrón de Diseño: Visitor

### Visitante (Visitor)

### 2. ¿Qué problema resuelve?

Imagina que tienes una estructura de objetos (como un árbol de archivos y carpetas) y quieres realizar múltiples operaciones sobre ellos: calcular tamaño total, listar nombres, exportar a JSON. Si agregas un método para cada operación en cada clase, terminas con clases sobrecargadas. El patrón Visitor resuelve esto separando las operaciones de las clases sobre las que operan.

### 3. Idea principal (explicación corta)

El patrón Visitor permite agregar nuevas operaciones a una estructura de objetos sin cambiar las clases de los elementos sobre los que opera. Separa el algoritmo de la estructura de datos.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines la interfaz Visitor.** Con un método `visit` para cada tipo de elemento concreto.
2.  **Defines la interfaz Element.** Con un método `accept(Visitor)`.
3.  **Cada Elemento concreto implementa `accept()`.** Llama al método `visit` correspondiente, pasándose a sí mismo (`this`).
4.  **Crea VisitorConcreto.** Implementa las operaciones para cada tipo de elemento.
5.  **El cliente** crea un visitor y lo pasa a los elementos mediante `accept()`.

### 5. Estructura (clases y relaciones)

*   **Visitor:**
    *   Interfaz que declara un `visit` para cada Elemento concreto.
    *   *Ejemplo:* `Visitor` con `visitElementA()` y `visitElementB()`
*   **VisitorConcreto:**
    *   Implementa las operaciones definidas en `Visitor`.
    *   Contiene la lógica real de las operaciones.
    *   *Ejemplo:* `VisitorConcreto`
*   **Element:**
    *   Interfaz que declara `accept(Visitor)`.
    *   *Ejemplo:* `Element`
*   **ElementConcreto:**
    *   Implementa `accept()` llamando al método `visit` correcto.
    *   Se pasa a sí mismo (`this`) al visitor (double dispatch).
    *   *Ejemplo:* `ElementConcretoA`, `ElementConcretoB`

**Relación:** El `Cliente` pasa un `VisitorConcreto` a los elementos mediante `accept()`. Cada elemento llama al método `visit` correspondiente, permitiendo que el visitor ejecute la operación correcta.

### 6. Mini ejemplo

Vamos a crear un sistema para calcular impuestos sobre diferentes tipos de productos.

```java
// 1. Visitor: define operaciones por tipo
interface TaxVisitor {
    void visitFood(Food food);
    void visitElectronics(Electronics electronics);
}

// 2. Element: interfaz visitable
interface Product {
    void accept(TaxVisitor visitor);
    double getPrice();
}

// 3. Elementos concretos
class Food implements Product {
    private String name;
    private double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() { return price; }

    public String getName() { return name; }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visitFood(this);
    }
}

class Electronics implements Product {
    private String model;
    private double price;

    public Electronics(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public double getPrice() { return price; }

    public String getModel() { return model; }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visitElectronics(this);
    }
}

// 4. Visitor concreto: calcula impuestos
class TaxCalculator implements TaxVisitor {
    @Override
    public void visitFood(Food food) {
        double tax = food.getPrice() * 0.05; // 5% para comida
        System.out.println(food.getName() + " tax: $" + tax);
    }

    @Override
    public void visitElectronics(Electronics elec) {
        double tax = elec.getPrice() * 0.18; // 18% para electronicos
        System.out.println(elec.getModel() + " tax: $" + tax);
    }
}

// 5. Otro visitor: exporta a string
class ProductExporter implements TaxVisitor {
    @Override
    public void visitFood(Food food) {
        System.out.println("Food: " + food.getName() + ", $" + food.getPrice());
    }

    @Override
    public void visitElectronics(Electronics elec) {
        System.out.println("Electronics: " + elec.getModel() + ", $" + elec.getPrice());
    }
}

// 6. Uso en Main
public class Main {
    public static void main(String[] args) {
        Product milk = new Food("Milk", 2.50);
        Product phone = new Electronics("Phone", 500.00);

        TaxCalculator taxCalc = new TaxCalculator();
        milk.accept(taxCalc);
        phone.accept(taxCalc);

        System.out.println("---");

        ProductExporter exporter = new ProductExporter();
        milk.accept(exporter);
        phone.accept(exporter);
    }
}
```

En este ejemplo:
*   **`TaxVisitor`** define métodos para cada tipo de producto.
*   **`Food`** y **`Electronics`** implementan `accept()` haciendo double dispatch.
*   **`TaxCalculator`** y **`ProductExporter`** son visitors diferentes.
*   Puedes **agregar nuevos visitors** sin modificar `Food` o `Electronics`.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevos Visitors:** Sin tocar las clases de elementos.
    *   **La lógica de cada visitor:** Cada visitor es independiente.
    *   **Visitors que acumulan resultados:** Sumar totales, generar reportes, etc.
*   **No debes cambiar:**
    *   **El double dispatch:** Cada elemento debe llamar al método `visit` correcto.
    *   **Las clases de Element:** Si agregas un nuevo tipo de elemento, debes actualizar TODOS los visitors existentes.

### 8. Cuándo usarlo (muy importante)

*   **Cuando tienes una estructura de objetos estables** pero necesitas muchas operaciones diferentes sobre ellos.
*   **Cuando quieres separar la lógica de operaciones** de las clases de datos.
*   **Cuando necesitas realizar operaciones que dependen del tipo concreto** sin usar `instanceof`.
*   **Ejemplos típicos:** Calcular totales en árboles, exportar formatos, validaciones, compiladores (AST).
*   **NO usar** si frecuentemente agregas nuevos tipos de elementos (tendrás que modificar todos los visitors).

### 9. Resumen en una frase

El patrón Visitor separa algoritmos de la estructura de datos, permitiendo agregar nuevas operaciones sin modificar las clases de los elementos mediante double dispatch.
