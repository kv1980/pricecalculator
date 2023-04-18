package vermeulen.kristof.pricecalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private Price price_100;
    private Price price_200;
    private Price price_300;
    private float quantity;

    @BeforeEach
    public void beforeEach() {
        this.price_100 = Price.of(100, "EUR");
        this.price_200 = Price.of(200, "EUR");
        this.price_300 = Price.of(300, "EUR");
        this.quantity = 2.5F;
        this.priceCalculator = new PriceCalculator();
    }

    @Nested
    class of {
        @Nested
        class testMultiply {
            @Test
            void whenPriceAndQuantityAreCorrect(){
                var priceResult = priceCalculator.multiply(price_100, quantity).getResult();
                assertEquals(new BigDecimal(250).setScale(price_100.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_UP), priceResult.getValue());
            }
        }

        @Nested
        class testSum {
            @Test
            void whenPriceAndQuantityAreCorrect(){
                List<Price> prices = new ArrayList<Price>();
                prices.add(price_100);
                prices.add(price_200);
                prices.add(price_300);
                var priceResult = priceCalculator.sum(prices).getResult();
                assertEquals(new BigDecimal(600).setScale(price_100.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_UP), priceResult.getValue());
            }
        }

    }



}