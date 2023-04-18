package vermeulen.kristof.pricecalculator;

import java.math.BigDecimal;
import java.util.List;

public final class PriceCalculator {

    private Price result;

    public PriceCalculator multiply(Price price, float quantity) {
        BigDecimal multipliedValue = price.getValue().multiply(BigDecimal.valueOf(quantity));
        result = price.of(multipliedValue.doubleValue(), price.getCurrency().getCurrencyCode());
        return this;
    }

    public PriceCalculator sum(List<Price> prices) {
        BigDecimal sumValue = BigDecimal.valueOf(0);

        String currencyCode = prices.get(0).getCurrency().getCurrencyCode();
        for (Price price : prices) {
            sumValue = sumValue.add(((Price) price).getValue());
        }
        result = Price.of(sumValue.doubleValue(), currencyCode);
        return this;
    }

    public PriceCalculator substractPercentage(int percentage) {
        double multiplier = (100 - percentage) / 100.0;
        BigDecimal newValue = result.getValue().multiply(BigDecimal.valueOf(multiplier));
        result = Price.of(newValue.doubleValue(), result.getCurrency().getCurrencyCode());
        return this;
    }

    public Price getResult() {
        return result;
    }
}

