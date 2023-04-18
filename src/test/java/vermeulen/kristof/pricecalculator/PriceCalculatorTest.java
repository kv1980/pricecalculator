package vermeulen.kristof.pricecalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private static String currency_EUR = "EUR";
    private static Price price_100;
    private static Price price_200;
    private static Price price_300;
    private static Price price_100_USD;
    private static float quantity;

    @BeforeAll
    public static void beforeAll() {
        price_100 = Price.of(100, currency_EUR);
        price_200 = Price.of(200, currency_EUR);
        price_300 = Price.of(300, currency_EUR);
        price_100_USD = Price.of(100, "USD");
        quantity = 2.5F;
    }

    @BeforeEach
    public void beforeEach() {
        this.priceCalculator = new PriceCalculator();
    }


    @Nested
    class testMultiply {
        @Test
        void whenPriceAndQuantityAreCorrect(){
            var calculatedPrice = priceCalculator.multiply(price_100, quantity).getResult();
            assertEquals(new BigDecimal(250).setScale(Currency.getInstance(currency_EUR).getDefaultFractionDigits(), RoundingMode.HALF_UP), calculatedPrice.getValue());
        }
    }

    @Nested
    class testSum {
        @Test
        void whenPriceAndCurrencyAreCorrect() {
            List<Price> prices = getValidPriceList();
            var calculatedPrice = priceCalculator.sum(prices).getResult();
            assertEquals(new BigDecimal(600).setScale(Currency.getInstance(currency_EUR).getDefaultFractionDigits(), RoundingMode.HALF_UP), calculatedPrice.getValue());
        }

        @Test
        void errorWhenMultipleCurrencies() {
            List<Price> prices = getInvalidPriceList();
            assertThrows(PriceException.class, () -> priceCalculator.sum(prices));
        }
    }

    @Nested
    class testSubstractPercentage{
        @Test
        void whenPriceAndPercentageAreCorrect(){
            List<Price> prices = getValidPriceList();
            var calculatedPrice = priceCalculator.sum(prices).substractPercentage(10).getResult();
            assertEquals(new BigDecimal(540).setScale(Currency.getInstance(currency_EUR).getDefaultFractionDigits(), RoundingMode.HALF_UP), calculatedPrice.getValue());
        }

        @Test
        void errorWhenNoPriorCalculation() {
            // priceCalculator.value must be a valid object before you can call substractPercentage
            assertThrows(PriceException.class, () -> priceCalculator.substractPercentage(10));
        }
    }

    private List<Price> getValidPriceList(){
        List<Price> prices = new ArrayList<Price>();
        prices.add(price_100);
        prices.add(price_200);
        prices.add(price_300);
        return prices;
    }

    private List<Price> getInvalidPriceList(){
        List<Price> prices = new ArrayList<Price>();
        prices.add(price_100);
        prices.add(price_100_USD);
        return prices;
    }

}