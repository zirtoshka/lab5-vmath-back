package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;

public class LagrangePolynomial implements Polynomial {
    @Getter
    @Setter
    private BigDecimal[] listY;
    @Getter
    @Setter
    private BigDecimal[] listX;

    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) {
        BigDecimal res = BigDecimal.ZERO;
        BigDecimal currValue;
        int numberOfPoints = listX.length;
        for (int i = 0; i < numberOfPoints; i++) {
            currValue = BigDecimal.ONE;
            for (int j = 0; j < numberOfPoints; j++) {
                if (j != i) {
                    currValue=currValue.multiply(
                            x.subtract(listX[j])
                                    .divide(
                                            listX[i].subtract(listX[j]),
                                            MathContext.DECIMAL32)

                    );
                }
            }
            res=res.add(currValue.multiply(listY[i]));
        }
        return res;
    }



}
