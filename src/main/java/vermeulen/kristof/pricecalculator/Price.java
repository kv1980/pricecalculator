package vermeulen.kristof.pricecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class Price {
    private final Currency currency;
    private final BigDecimal value;

    private Price(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Price of(double value, String currencyCode) {
        try {
            BigDecimal validValue = requireValidValue(value);
            Currency validCurrency = Currency.getInstance(currencyCode);

            return new Price(validValue, validCurrency);
        } catch (Exception exception) {
            throw new PriceException(exception);
        }
    }

    private static BigDecimal requireValidValue(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("The provided value should be a positive value.");
        }
        return BigDecimal.valueOf(value);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%s %s", currency.getSymbol(Locale.getDefault()), value);
    }
}
