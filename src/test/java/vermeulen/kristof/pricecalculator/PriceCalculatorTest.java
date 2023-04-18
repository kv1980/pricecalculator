package vermeulen.kristof.pricecalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculator;
    private static String currency_EUR = "EUR";
    private static Price price_100;
    private static Price price_200;
    private static Price price_300;
    private static float quantity;

    @BeforeAll
    public static void beforeAll() {
        price_100 = Price.of(100, currency_EUR);
        price_200 = Price.of(200, currency_EUR);
        price_300 = Price.of(300, currency_EUR);
        quantity = 2.5F;
    }

    @BeforeEach
    public void beforeEach() {
        this.priceCalculator = new PriceCalculator();
    }


    @Nested
    class of {
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
            void whenPriceAndCurrencyAreCorrect(){
                List<Price> prices = new ArrayList<Price>();
                prices.add(price_100);
                prices.add(price_200);
                prices.add(price_300);
                var calculatedPrice = priceCalculator.sum(prices).getResult();
                assertEquals(new BigDecimal(600).setScale(Currency.getInstance(currency_EUR).getDefaultFractionDigits(), RoundingMode.HALF_UP), calculatedPrice.getValue());
            }
        }

        @Nested
        class testSubstractPercentage{
            @Test
            void whenPriceAndPercentageAreCorrect(){
                List<Price> prices = new ArrayList<Price>();
                prices.add(price_100);
                prices.add(price_200);
                prices.add(price_300);
                var calculatedPrice = priceCalculator.sum(prices).substractPercentage(10).getResult();
                assertEquals(new BigDecimal(540).setScale(Currency.getInstance(currency_EUR).getDefaultFractionDigits(), RoundingMode.HALF_UP), calculatedPrice.getValue());
            }
        }

    }



}