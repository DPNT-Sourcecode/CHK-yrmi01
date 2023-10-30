package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

//| A    | 50    | 3A for 130, 5A for 200 |
//| B    | 30    | 2B for 45              |
//| C    | 20    |                        |
//| D    | 15    |                        |
//| E    | 40    | 2E get one B free      |
//| F    | 10    | 2F get one F free      |
//| G    | 20    |                        |
//| H    | 10    | 5H for 45, 10H for 80  |
//| I    | 35    |                        |
//| J    | 60    |                        |
//| K    | 80    | 2K for 150             |
//| L    | 90    |                        |
//| M    | 15    |                        |
//| N    | 40    | 3N get one M free      |
//| O    | 10    |                        |
//| P    | 50    | 5P for 200             |
//| Q    | 30    | 3Q for 80              |
//| R    | 50    | 3R get one Q free      |
//| S    | 30    |                        |
//| T    | 20    |                        |
//| U    | 40    | 3U get one U free      |
//| V    | 50    | 2V for 90, 3V for 130  |
//| W    | 20    |                        |
//| X    | 90    |                        |
//| Y    | 10    |                        |
//| Z    | 50    |                        |

public class CheckoutSolution {

    Map<String, Integer> priceList;
    Map<String, Offer> offers;

    public CheckoutSolution() {
        this.priceList = new HashMap<>();
        priceList.put("A", 50);
        priceList.put("B", 30);
        priceList.put("C", 20);
        priceList.put("D", 15);
        priceList.put("E", 40);
        priceList.put("F", 10);
        priceList.put("G", 20);
        priceList.put("H", 10);
        priceList.put("I", 35);
        priceList.put("J", 60);
        priceList.put("K", 80);
        priceList.put("L", 90);
        priceList.put("M", 15);
        priceList.put("N", 40);
        priceList.put("O", 10);
        priceList.put("P", 50);
        priceList.put("Q", 30);
        priceList.put("R", 50);
        priceList.put("S", 30);
        priceList.put("T", 20);
        priceList.put("U", 40);
        priceList.put("V", 50);
        priceList.put("W", 20);
        priceList.put("X", 90);
        priceList.put("Y", 10);
        priceList.put("Z", 50);

//        | A    | 50    | 3A for 130, 5A for 200 |
//| B    | 30    | 2B for 45              |
//| C    | 20    |                        |
//| D    | 15    |                        |
//| E    | 40    | 2E get one B free      |
//| F    | 10    | 2F get one F free      |
//| G    | 20    |                        |
//| H    | 10    | 5H for 45, 10H for 80  |
//| I    | 35    |                        |
//| J    | 60    |                        |
//| K    | 80    | 2K for 150             |
//| L    | 90    |                        |
//| M    | 15    |                        |
//| N    | 40    | 3N get one M free      |
//| O    | 10    |                        |
//| P    | 50    | 5P for 200             |
//| Q    | 30    | 3Q for 80              |
//| R    | 50    | 3R get one Q free      |
//| S    | 30    |                        |
//| T    | 20    |                        |
//| U    | 40    | 3U get one U free      |
//| V    | 50    | 2V for 90, 3V for 130  |

        offers.put("A", new Offer(3, 130));
    }

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


