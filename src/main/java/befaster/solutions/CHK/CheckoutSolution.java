package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        Map<String, Integer> skuToQuantity = new HashMap<>();
        for (String c: skus.split(",")){
            if(skuToQuantity.containsKey(c)){
                skuToQuantity.put(c, skuToQuantity.get(c) + 1);
            } else {
                skuToQuantity.put(c, 1);
            }
        }

        int total = 0;

        for(Map.Entry<String, Integer> e: skuToQuantity.entrySet()) {
            switch (e.getKey()){
                case "A":
                    int multiBuy = e.getValue() / 3;
                    int single = e.getValue() % 3;
                    total += (multiBuy * 130) + (single * 50);
                    break;
                case "B":
                    int multiBuyB = e.getValue() / 2;
                    int singleB = e.getValue() % 2;
                    total += (multiBuyB * 45) + (singleB * 30);
                    break;
                case "C":
                    total += e.getValue() * 20;
                    break;
                case "D":
                    total += e.getValue() * 15;
                    break;
                default:
                    return -1;
            }
        }

        return total;
    }
}

