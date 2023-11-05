package befaster.solutions.CHK;

import java.util.*;

public class CheckoutSolution {

    private Map<Character, Integer> prices;
    private Map<Character, List<Offer>> offers;
    private Map<Character, FreeItemOffer> freeItemOffers;
    private Map<Character, Integer> weightedGroupBuy;


    public CheckoutSolution() {
        prices = new HashMap<>();
        prices.put('A', 50);
        prices.put('B', 30);
        prices.put('C', 20);
        prices.put('D', 15);
        prices.put('E', 40);
        prices.put('F', 10);
        prices.put('G', 20);
        prices.put('H', 10);
        prices.put('I', 35);
        prices.put('J', 60);
        prices.put('K', 70);
        prices.put('L', 90);
        prices.put('M', 15);
        prices.put('N', 40);
        prices.put('O', 10);
        prices.put('P', 50);
        prices.put('Q', 30);
        prices.put('R', 50);
        prices.put('S', 20);
        prices.put('T', 20);
        prices.put('U', 40);
        prices.put('V', 50);
        prices.put('W', 20);
        prices.put('X', 17);
        prices.put('Y', 20);
        prices.put('Z', 21);

        offers = new HashMap<>();
        offers.put('A', List.of(new Offer(5, 200), new Offer(3, 130)));
        offers.put('B', List.of(new Offer(2, 45)));
        offers.put('F', List.of(new Offer(3, 20)));
        offers.put('H', List.of(new Offer(10, 80), new Offer(5, 45)));
        offers.put('K', List.of(new Offer(2, 120)));
        offers.put('P', List.of(new Offer(5, 200)));
        offers.put('Q', List.of(new Offer(3, 80)));
        offers.put('U', List.of(new Offer(4, 120)));
        offers.put('V', List.of(new Offer(3, 130), new Offer(2, 90)));

        freeItemOffers = new HashMap<>();
        freeItemOffers.put('E', new FreeItemOffer(2, 'B'));
        freeItemOffers.put('N', new FreeItemOffer(3, 'M'));
        freeItemOffers.put('R', new FreeItemOffer(3, 'Q'));

        weightedGroupBuy = new HashMap<>();
        weightedGroupBuy.put('S', 20);
        weightedGroupBuy.put('T', 20);
        weightedGroupBuy.put('X', 17);
        weightedGroupBuy.put('Y', 20);
        weightedGroupBuy.put('Z', 21);
    }


    public Integer checkout(String skus) {
        Map<Character, Integer> basketStockCount = new HashMap<>();
        int total = 0;


        for(Character stock: skus.toCharArray()){
            if(!prices.containsKey(stock)) return -1;
            basketStockCount.put(stock, basketStockCount.getOrDefault(stock, 0) + 1);
        }

        total += calculateGroupBuy(basketStockCount);


        // calculate free Items and remove from stock count
        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            if (freeItemOffers.containsKey(entry.getKey())) {
                FreeItemOffer freeItemOffer = freeItemOffers.get(entry.getKey());
                if(count >= freeItemOffer.getCount()) {
                    if(basketStockCount.containsKey(freeItemOffer.getFreeItem())) {
                        basketStockCount.put(
                                freeItemOffer.getFreeItem(),
                                basketStockCount.get(freeItemOffer.getFreeItem()) - count/freeItemOffer.getCount()
                        );
                    }
                }
            }
        }

        for(Map.Entry<Character, Integer> entry: basketStockCount.entrySet()) {
            int count = entry.getValue();
            int price = prices.get(entry.getKey());

            while (count > 0) {
                if (offers.containsKey(entry.getKey())) {
                    List<Offer> ItemOffers = offers.get(entry.getKey());
                    for(Offer offer: ItemOffers) {
                        int offerPrice = offer.getPrice();
                        int offerCount = offer.getCount();
                        if(count >= offerCount) {
                            int offerQuantity = count / offerCount;
                            total += (offerQuantity * offerPrice);
                            count -= offerCount * offerQuantity;
                        }
                    }
                }

                total += (count * price);
                count = 0;
            }

        }

        return total;
    }

    private int calculateGroupBuy(Map<Character, Integer> basketStockCount) {
        int groupSize = 3;
        int groupCost = 45;

        List<Map.Entry<Character, Integer>> collect = basketStockCount.entrySet().stream()
                .filter(entry -> weightedGroupBuy.containsKey(entry.getKey()))
                .sorted((o2, o1) -> weightedGroupBuy.get(o1.getKey()).compareTo(weightedGroupBuy.get(o2.getKey())))
                .toList();


        List<Character> groupedList = new ArrayList<>();

        for(Map.Entry<Character, Integer> entry: collect) {
            for (int i = 0; i < entry.getValue(); i++) {
                groupedList.add(entry.getKey());
            }
        }

        int groups = groupedList.size() / groupSize;
        int remainder = groupedList.size() % groupSize;

        if(groupedList.size() >= groupSize) {
            groupedList.stream().limit(groupedList.size() - remainder).forEach(character -> {
                if(basketStockCount.containsKey(character)) {
                    basketStockCount.put(
                            character,
                            basketStockCount.get(character) - 1
                    );
                }
            });
        }
        return groupCost * groups;
    }
}





