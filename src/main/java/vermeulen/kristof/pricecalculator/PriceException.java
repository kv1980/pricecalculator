package vermeulen.kristof.pricecalculator;

public class PriceException extends RuntimeException {

    public static final String PREFIX = "Price could not be created, since ";

    public PriceException(final Throwable cause) {
        super(PREFIX + cause.getMessage(), cause);
    }
}
