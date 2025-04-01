/*
Main difference between generics in .NET and Java is that Java does not allow primitives as generics types. 
Java also supports only wildcards as constraints on the generics and .NET provides generic constraints e.g.
"where T : SomeClass, new()" compared to "? extends T"
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomList<T> {
    private List<T> items;
    private Random random;

    public RandomList() {
        items = new ArrayList<>();
        random = new Random();
    }

    public void add(T element) {
        if (random.nextBoolean()) {
            items.add(0, element);
        } else {
            items.add(element);
        }
    }

    public T get(int index) {
        if (items.isEmpty()) {
            throw new IllegalStateException("The list is empty.");
        }
        if (index >= items.size()) {
            index = items.size() - 1;
        }
        int randomIndex = random.nextInt(index + 1);
        return items.get(randomIndex);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

}

public class Main {
    public static void main(String[] args) {
        RandomList<Integer> randomList = new RandomList<>();

        randomList.add(1);
        randomList.add(2);
        randomList.add(3);
        randomList.add(4);

        System.out.println("Random Get (Index 2): " + randomList.get(2));
        System.out.println("Is Empty? " + randomList.isEmpty());
    }
}