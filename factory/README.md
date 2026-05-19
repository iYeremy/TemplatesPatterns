# Patrón de Diseño: Factory

### Fábrica (Factory)

### 2. ¿Qué problema resuelve?

Imagina que tienes una aplicación que necesita crear diferentes tipos de objetos (por ejemplo, botones de UI: Windows, Mac, Linux). Si el código crea directamente las instancias con `new`, terminas con código acoplado que es difícil de mantener cuando agregas nuevos tipos:

```java
// Código acoplado que depende de clases concretas
if (sistemaOperativo.equals("Windows")) {
    boton = new BotonWindows();
} else if (sistemaOperativo.equals("Mac")) {
    boton = new BotonMac();
}
```

El patrón Factory resuelve esto centralizando la lógica de creación en un solo lugar, permitiendo que el código trabaje con interfaces en lugar de clases concretas.

### 3. Idea principal (explicación corta)

El patrón Factory proporciona una interfaz para crear objetos sin especificar la clase concreta exacta que se va a instanciar. Delega la decisión de qué objeto crear a una clase "fábrica".

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines una interfaz de producto.** Esta es la interfaz común que tendrán todos los objetos que puedes crear.
2.  **Creas las implementaciones concretas del producto.** Cada una representa un tipo diferente de objeto.
3.  **Creas la clase Factory.** Esta clase tiene un método (o varios) que decide qué implementación concreta instanciar basándose en algún parámetro o condición.
4.  **El cliente usa la Factory.** En lugar de hacer `new ClaseConcreta()`, llama al método de la Factory y recibe un objeto de tipo interfaz.

### 5. Estructura (clases y relaciones)

*   **Producto (Product):**
    *   Interfaz común para todos los objetos que la fábrica puede crear.
    *   Define el comportamiento que el cliente espera.
    *   *Ejemplo:* `Producto`
*   **ProductoConcreto (ConcreteProduct):**
    *   Implementaciones específicas de la interfaz `Producto`.
    *   Cada una tiene su propia lógica.
    *   *Ejemplo:* `ProductoConcretoA`, `ProductoConcretoB`
*   **Factory (Creator):**
    *   Clase o interfaz que declara el método de fabricación.
    *   Contiene la lógica para decidir qué `ProductoConcreto` instanciar.
    *   Devuelve objetos de tipo `Producto` (interfaz).
    *   *Ejemplo:* `Factory`

**Relación:** El `Cliente` llama a `Factory` para obtener un `Producto`. `Factory` decide qué `ProductoConcreto` crear y devolverlo como tipo `Producto`.

### 6. Mini ejemplo

Vamos a crear un sistema de notificaciones donde podemos enviar notificaciones por Email, SMS o Push.

```java
// 1. Producto: Interfaz comun
interface Notificacion {
    void enviar(String mensaje);
}

// 2. Productos Concretos
class EmailNotificacion implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}

class SMSNotificacion implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}

// 3. Factory
class NotificacionFactory {
    public static Notificacion crear(String tipo) {
        switch (tipo.toLowerCase()) {
            case "email":
                return new EmailNotificacion();
            case "sms":
                return new SMSNotificacion();
            default:
                throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}

// 4. Uso en Main
public class Main {
    public static void main(String[] args) {
        Notificacion email = NotificacionFactory.crear("email");
        email.enviar("Hola desde Email");

        Notificacion sms = NotificacionFactory.crear("sms");
        sms.enviar("Hola desde SMS");
    }
}
```

En este ejemplo:
*   **`Notificacion`** es la interfaz del producto.
*   **`EmailNotificacion`** y **`SMSNotificacion`** son los productos concretos.
*   **`NotificacionFactory`** decide qué tipo de notificación crear.
*   El **`Main`** nunca usa `new EmailNotificacion()` directamente.

Podemos agregar `PushNotificacion` sin cambiar el código del `Main`, solo agregando un caso en la `Factory`.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Nuevos `ProductoConcreto`s:** Agrega nuevos tipos sin modificar el cliente.
    *   **La lógica interna de la Factory:** Cambia cómo se decide qué producto crear.
    *   **Agregar parámetros al método factory:** Para configurar el producto al crearlo.
*   **No debes cambiar:**
    *   **La interfaz `Producto`:** Si la cambias, todas las implementaciones concretas deben actualizarse.
    *   **El hecho de que el cliente trabaje con la interfaz:** Si el cliente empieza a depender de clases concretas, se pierde el beneficio del patrón.

### 8. Cuándo usarlo (muy importante)

*   **Cuando no sabes de antemano qué clases concretas necesitas.**
*   **Cuando quieres centralizar la lógica de creación** para evitar duplicación de código.
*   **Cuando quieres que el código sea extensible:** Agregar nuevos tipos debe ser fácil (agregar clase concreta + actualizar factory).
*   **Cuando quieres desacoplar el código:** El cliente no debe conocer las clases concretas.

### 9. Resumen en una frase

El patrón Factory centraliza la creación de objetos, permitiendo al código cliente trabajar con interfaces sin conocer las clases concretas que se instancian.
