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
}

