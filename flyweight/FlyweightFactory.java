import java.util.HashMap;
import java.util.Map;

// Fabrica que controla las instancias
//
// CLAVE:
// - Si existe -> reutiliza
// - Si no -> crea

public class FlyweightFactory {

    private Map<String, Flyweight> pool = new HashMap<>();

    public Flyweight getFlyweight(String clave) {

        if (!pool.containsKey(clave)) {
            pool.put(clave, new ConcreteFlyweight(clave));
        }

        return pool.get(clave);
    }
}
