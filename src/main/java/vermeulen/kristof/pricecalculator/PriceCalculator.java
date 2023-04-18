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
        throw new UnsupportedOperationException();
    }

    public PriceCalculator substractPercentage(int percentage) {
        throw new UnsupportedOperationException();
    }

    public Price getResult() {
        return result;
    }
}

