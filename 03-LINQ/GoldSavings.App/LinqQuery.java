import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinqQuery {
    public static void main(String[] args) {
        List<GoldPrice> goldPrices = generateRandomGoldPrices(20);

        System.out.println("Generated Gold Prices:");
        goldPrices.forEach(System.out::println);

        List<GoldPrice> last3 = goldPrices.stream()
                .sorted(Comparator.comparing(GoldPrice::getPrice))
                .skip(Math.max(0, goldPrices.size() - 3))
                .collect(Collectors.toList());

        System.out.println("\nLast 3 Lowest Gold Prices:");
        last3.forEach(System.out::println);
    }

    private static List<GoldPrice> generateRandomGoldPrices(int count) {
        Random random = new Random();
        return IntStream.range(0, count)
                .mapToObj(i -> new GoldPrice(
                        LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1), // Random date in 2024
                        1800 + random.nextDouble() * 400
                ))
                .collect(Collectors.toList());
    }

    static class GoldPrice {
        private final LocalDate date;
        private final double price;

        public GoldPrice(LocalDate date, double price) {
            this.date = date;
            this.price = price;
        }

        public LocalDate getDate() {
            return date;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return date + " - $" + String.format("%.2f", price);
        }
    }
}
