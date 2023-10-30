package befaster.solutions.CHK;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CheckoutSolutionTest {

    public CheckoutSolution checkoutSolution;

    @BeforeEach
    void setUp() {
        checkoutSolution = new CheckoutSolution();
    }

    @Test
    public void checkoutTest() {
        assertThat(checkoutSolution.checkout(""), equalTo(0));
        assertThat(checkoutSolution.checkout("ABCD"), equalTo(115));
        assertThat(checkoutSolution.checkout("ABCDE"), equalTo(155));
        assertThat(checkoutSolution.checkout("ABCDEE"), equalTo(165));
        assertThat(checkoutSolution.checkout("ABCDEF"), equalTo(165));
        assertThat(checkoutSolution.checkout("ABCDEFF"), equalTo(175));
        assertThat(checkoutSolution.checkout("ABCDEFFF"), equalTo(175));

    }
}

