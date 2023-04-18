package vermeulen.kristof.pricecalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private Price price;
    private float quantity;

    @BeforeEach
    public void beforeEach() {
        this.price = Price.of(100, "EUR");
        this.quantity = 2.5F;
        this.priceCalculator = new PriceCalculator();
    }

    @Nested
    class of {
        @Nested
        class testMultiply {
            @Test
            void whenPriceAndQuantityAreCorrect(){
                var priceResult = priceCalculator.multiply(price, quantity).getResult();
                assertEquals(250, priceResult.getValue());
            }
        }

    }



}