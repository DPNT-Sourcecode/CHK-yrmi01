package befaster.solutions.CHK;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    void checkoutReturnsExpectedValueForSingleItemWithoutOfferTest() {
        assertThat(checkoutSolution.checkout("A"), equalTo(50));
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("C"), equalTo(20));
        assertThat(checkoutSolution.checkout("D"), equalTo(15));
        assertThat(checkoutSolution.checkout("ABCD"), equalTo(115));
    }

    @Test
    void checkoutReturnsExpectedValueForSingleItemWithOfferTest() {
        assertThat(checkoutSolution.checkout("A"), equalTo(50));
        assertThat(checkoutSolution.checkout("AA"), equalTo(100));
        assertThat(checkoutSolution.checkout("AB"), equalTo(80));
        assertThat(checkoutSolution.checkout("AAA"), equalTo(130));
        assertThat(checkoutSolution.checkout("AAAB"), equalTo(160));
    }

    @Test
    void checkoutReturnsExpectedValueForMulitpleItemsWithOfferTest() {
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("BB"), equalTo(45));
        assertThat(checkoutSolution.checkout("AAABB"), equalTo(175));
    }

    @Test
    void checkoutReturnsExpectedValueForFreeItemsTest() {
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("BE"), equalTo(70));
        assertThat(checkoutSolution.checkout("BEE"), equalTo(80));
    }

    @Test
    void whenFreeItemPromoButFreeItemNotPurchased() {
        assertThat(checkoutSolution.checkout("EE"), equalTo(80));
        assertThat(checkoutSolution.checkout("EEEE"), equalTo(160));
    }

    @Test
    void checkoutReturnsExpectedValueForSpecialOfferAndFreeItemsTest() {
        assertThat(checkoutSolution.checkout("B"), equalTo(30));
        assertThat(checkoutSolution.checkout("BE"), equalTo(70));
        assertThat(checkoutSolution.checkout("BEE"), equalTo(80));
        assertThat(checkoutSolution.checkout("BBEE"), equalTo(110));
        assertThat(checkoutSolution.checkout("EEEEBB"), equalTo(160));
        assertThat(checkoutSolution.checkout("BEBEEE"), equalTo(160));
        assertThat(checkoutSolution.checkout("ABCDEABCDE"), equalTo(280));
    }
//    +------+-------+------------------------+
//| Item | Price | Special offers         |
//+------+-------+------------------------+
//| A    | 50    | 3A for 130, 5A for 200 |
//| B    | 30    | 2B for 45              |
//| C    | 20    |                        |
//| D    | 15    |                        |
//| E    | 40    | 2E get one B free      |
//| F    | 10    | 2F get one F free      |
//+------+-------+------------------------+

    @Test
    void checkoutReturnsExpectedValueForSelfFreeItemsTest() {
        assertThat(checkoutSolution.checkout("F"), equalTo(10));
        assertThat(checkoutSolution.checkout("FF"), equalTo(20));
        assertThat(checkoutSolution.checkout("FFF"), equalTo(20));
        assertThat(checkoutSolution.checkout("FFFF"), equalTo(30));
        assertThat(checkoutSolution.checkout("FFFFF"), equalTo(40));
        assertThat(checkoutSolution.checkout("FFFFFF"), equalTo(40));
    }
}
