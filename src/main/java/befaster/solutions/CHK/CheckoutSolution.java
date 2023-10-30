package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus.isEmpty() || skus.isBlank()) return 0;

        Map<String, Integer> skuToQuantity = new HashMap<>();
        for (String c: skus.split("")){
            if(skuToQuantity.containsKey(c)){
                skuToQuantity.put(c, skuToQuantity.get(c) + 1);
            } else {
                skuToQuantity.put(c, 1);
            }
        }

        int total = 0;
        int freeB = 0;

        if (skuToQuantity.containsKey("E")) {
            Integer e = skuToQuantity.remove("E");
            total += (e * 40);
            freeB += e/2;
        }

        for(Map.Entry<String, Integer> e: skuToQuantity.entrySet()) {
            Integer quantity = e.getValue();
            switch (e.getKey()){
                case "A":
                    int multiBuyFive = quantity / 5;
                    int multiBuyThree = (quantity % 5) / 3;
                    int single = (quantity % 5) % 3;
                    total += (multiBuyFive * 200) + (multiBuyThree * 130) + (single * 50);
                    break;
                case "B":
                    int afterEMultiBuyPromotion = quantity - freeB;
                    int multiBuyB = afterEMultiBuyPromotion / 2;
                    int singleB = afterEMultiBuyPromotion % 2;
                    total += (multiBuyB * 45) + (singleB * 30);
                    break;
                case "C":
                    total += quantity * 20;
                    break;
                case "D":
                    total += quantity * 15;
                    break;
                case "F":
                    int BuyTwoGetOneFree = quantity / 3;
                    int singleF = quantity % 3;
                    total += (BuyTwoGetOneFree * 20) + (singleF * 10);
                    break;
                default:
                    return -1;
            }
        }

        return total;
    }
}

