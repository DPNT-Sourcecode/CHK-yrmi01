package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private Map<Character, Integer> prices;
    private Map<Character, Offer> offers;

//    | Item | Price | Special offers |
//+------+-------+----------------+
//| A    | 50    | 3A for 130     |
//| B    | 30    | 2B for 45      |
//| C    | 20    |                |
//| D    | 15    |                |
    public CheckoutSolution() {
        prices = new HashMap<>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);

        offers = new HashMap<>();
        offers.put('A', new Offer(3, 130));
        offers.put('B', new Offer(2, 45));
    }

    public Integer checkout(String skus) {
        Map<Character, Integer> basketStockCount = new HashMap<>();

        for(Character stock: skus.toCharArray()){
            if(!prices.containsKey(stock)) return -1;

            basketStockCount.put(stock, basketStockCount.getOrDefault(stock, 0) + 1);
        }

        int total = 0;
        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            int price = prices.get(entry.getKey());
            if (offers.containsKey(entry.getKey())) {
                Offer offer = offers.get(entry.getKey());
                int offerPrice = offer.getPrice();
                int offerCount = offer.getCount();

                int offerQuantity = count / offerCount;
                int fullPriceQuantity = count % offerCount;

                total += (offerQuantity * offerPrice) + (fullPriceQuantity * price);
            } else {
                total += (count * price);
            }
        }

        return total;
    }
}

