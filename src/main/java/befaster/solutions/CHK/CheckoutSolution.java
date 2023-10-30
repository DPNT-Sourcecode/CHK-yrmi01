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
                    break;
                case "B":
                    break;
                case "C":
                    break;
                case "D":
                    break;
                default:
                    break;
            }
        }

        return
    }
}
