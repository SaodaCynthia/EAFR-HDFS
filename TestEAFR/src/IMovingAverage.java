import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class IMovingAverage<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public IMovingAverage() {
        this(new Random());
    }

    public IMovingAverage(Random random) {
        this.random = random;
    }

    public IMovingAverage<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        System.out.println(total);
        map.put(total, result);
        System.out.println(map);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        System.out.println(value);
        return map.higherEntry(value).getValue();
    }
    
    public static void main(String argv []){
    	IMovingAverage<Object> rc = new IMovingAverage<>()
                .add(70, "dog").add(20, "cat").add(10, "horse");

for (int i = 0; i < 1; i++) {
System.out.println(rc.next());
} 
    	
    }
}