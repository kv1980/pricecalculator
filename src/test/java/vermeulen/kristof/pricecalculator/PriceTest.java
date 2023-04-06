package vermeulen.kristof.pricecalculator;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    @Nested
    class of {

        @Nested
        class createsPrice {
            @Test
            void whenValueAndCurrencyCodeAreCorrect() {
                // option 1 : without lambda
                assertDoesNotThrow(new Executable() {
                    @Override
                    public void execute() {
                        Price.of(1, "EUR");
                    }
                });

                // option 2 : with lambda
                assertDoesNotThrow(() -> Price.of(1, "EUR"));
            }

            @Test
            void whenValueIsZero() {

                // option 1 : without lambda
                assertDoesNotThrow(new Executable() {
                    @Override
                    public void execute() {
                        Price.of(1, "EUR");
                    }
                });

                // option 2 : with lambda
                assertDoesNotThrow(() -> Price.of(1, "EUR"));
            }
        }

        @Nested
        class throwsPriceException {
            @Test
            void whenValueIsNegative() {

                // option 1 : without lambda
                assertThrows(PriceException.class, new Executable() {
                    @Override
                    public void execute() {
                        Price.of(-2, "EUR");
                    }
                });

                // option 2 : with lambda
                assertThatExceptionOfType(PriceException.class)
                    .isThrownBy(() -> Price.of(-2, "EUR"));

                // option 3 : extract to separate method not to repeat yourself
                assertThatPriceExceptionIsThrownWith(-2, "EUR");
            }

            private static void assertThatPriceExceptionIsThrownWith(final double value, final String currencyCode) {
                assertThatExceptionOfType(PriceException.class)
                    .isThrownBy(() -> Price.of(value, currencyCode));
            }

            @Test
            void whenCurrencyCodeIsNull() {
                assertThatPriceExceptionIsThrownWith(1, null);
            }

            @Test
            void whenCurrencyCodeIsEmpty() {
                assertThatPriceExceptionIsThrownWith(1, "");
            }

            @Test
            void whenCurrencyCodeInvalid() {
                assertThatPriceExceptionIsThrownWith(1, "EURO");
            }
        }
    }

    @Nested
    class getCurrency {

        @Test
        void returnsActualCurrency() {
            var price = Price.of(1, "EUR");
            var expectedCurrency = Currency.getInstance("EUR");

            var actualCurrency = price.getCurrency();

            assertThat(actualCurrency).isEqualTo(expectedCurrency);
        }
    }

    @Nested
    class getValue {

        @Test
        void returnsActualValue() {
            var price = Price.of(1.11, "EUR");
            var expectedValue = BigDecimal.valueOf(1.11);

            var actualValue = price.getValue();

            assertThat(actualValue).isEqualTo(expectedValue);
        }

        @Test
        void returnsCorrectlyRoundedValue() {
            var price = Price.of(1.109, "EUR");
            var expectedValue = BigDecimal.valueOf(1.11);

            var actualValue = price.getValue();

            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Nested
    class toString {

        @Test
        void returnsString() {
            var price = Price.of(1.11, "EUR");
            var expectedString = "€ 1.11";

            var actualString = price.toString();

            assertThat(actualString).isEqualTo(expectedString);
        }
    }
}