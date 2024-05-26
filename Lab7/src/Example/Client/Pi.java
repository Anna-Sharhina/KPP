package Example.Client;

import Example.compute.Task;

import java.io.Serial;
import java.math.BigDecimal;

public class Pi implements Task<BigDecimal> {
    @Serial
    private static final long serialVersionUID = 227L;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;

    public Pi() {
    }

    public BigDecimal execute() {
        return BigDecimal.valueOf(Math.PI);
    }

}
