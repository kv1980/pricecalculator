package vermeulen.kristof.pricecalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
                assertEquals(new BigDecimal(250).setScale(price.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_UP), priceResult.getValue());
            }
        }

    }



}