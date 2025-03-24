using GoldSavings.App.Model;
using GoldSavings.App.Client;
namespace GoldSavings.App;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("Hello, Gold Saver!");

        GoldClient goldClient = new GoldClient();

        GoldPrice currentPrice = goldClient.GetCurrentGoldPrice().GetAwaiter().GetResult();
        Console.WriteLine($"The price for today is {currentPrice.Price}");

        List<GoldPrice> thisMonthPrices = goldClient.GetGoldPrices(new DateTime(2024, 03, 01), new DateTime(2024, 03, 11)).GetAwaiter().GetResult();
        foreach(var goldPrice in thisMonthPrices)
        {
            Console.WriteLine($"The price for {goldPrice.Date} is {goldPrice.Price}");
        }

        Console.WriteLine("### Satisfactory task ###");
        
        Console.WriteLine("Task 1: Leap Year");
        Func<int, bool> isLeap = x => (x % 4 == 0 && x % 100 != 0) || (x % 400 == 0);
        var years = Enumerable.Range(1900, 125);
        foreach (var year in years)
        {
            Console.WriteLine($"Year: {year} is leap year: {isLeap(year)}");
        }
        
        RandomList<int> randomList = new RandomList<int>();

        randomList.Add(1);
        randomList.Add(2);
        randomList.Add(3);
        randomList.Add(4);

        Console.WriteLine($"Random Get (Index 2): {randomList.Get(2)}");
        Console.WriteLine($"Is Empty? {randomList.IsEmpty()}");
    }

    class RandomList<T>
    {
        private List<T> items;
        private Random random;

        public RandomList()
        {
            items = new List<T>();
            random = new Random();
        }

        public void Add(T element)
        {
            if (random.Next(2) == 0)
            {
                items.Insert(0, element);
            }
            else
            {
                items.Add(element);
            }
        }

        public T Get(int index)
        {
            if (items.Count == 0)
                throw new InvalidOperationException("The list is empty.");

            if (index >= items.Count)
            {
                index = items.Count - 1;
            }

            int randomIndex = random.Next(0, index + 1);
            return items[randomIndex];
        }

        public bool IsEmpty()
        {
            return items.Count == 0;
        }
    }
}
