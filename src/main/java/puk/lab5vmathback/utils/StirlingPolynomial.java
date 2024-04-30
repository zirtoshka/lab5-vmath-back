package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;
import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class StirlingPolynomial  implements Polynomial{
    @Getter
    @Setter
    private List<BigDecimal> finiteDiff;
    private final PolynomialIntervalsChecker polynomialIntervalsChecker=new PolynomialIntervalsChecker();
    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException {
        BigDecimal res= BigDecimal.ZERO;
        boolean check = polynomialIntervalsChecker.checkStepsX(listX);
        if (!check) {
            throw new WrongStepsListXException();
        }
        int numberOfPoints = listX.length;

        BigDecimal mediumX = listX[(numberOfPoints + 1) / 2 - 1];

//more
        BigDecimal multiplierTMore = BigDecimal.ONE;
        BigDecimal multiplierTLess = BigDecimal.ONE;
        BigDecimal step = listX[1].subtract(listX[0]);
        BigDecimal t = x.subtract(mediumX).divide(step, MathContext.DECIMAL32);
        int jMore = (numberOfPoints + 1) / 2 - 1;
        int shiftJMore = numberOfPoints;
        BigDecimal k = BigDecimal.ZERO;
        BigDecimal tmpFromMore, tmpFromLess;
         res = finiteDiff.get((numberOfPoints + 1) / 2 - 1); //уже ср арифм
        int fact = 1;
        for (int i = 1; i < numberOfPoints; i++) {
            if (i % 2 == 0) {
                shiftJMore -= 2;
            }
            fact *= i;
            jMore += shiftJMore;
            if (i % 2 == 0) {
                multiplierTMore = multiplierTMore.multiply(t.subtract(k));
                multiplierTLess = multiplierTLess.multiply(t.add(k));

            } else {
                multiplierTMore = multiplierTMore.multiply(t.add(k));
                multiplierTLess = multiplierTLess.multiply(t.subtract(k));
                k = k.add(BigDecimal.ONE);
            }
           tmpFromMore= multiplierTMore.multiply(finiteDiff.get(jMore))
                    .divide(BigDecimal.valueOf(fact), MathContext.DECIMAL32);
            tmpFromLess= multiplierTLess.multiply(finiteDiff.get(i%2==0? jMore: jMore-1))
                    .divide(BigDecimal.valueOf(fact), MathContext.DECIMAL32);

//            System.out.println(multiplierWithT+" "+fact+" "+finiteDiff.get(j));
            res = res.add(
                    tmpFromMore.add(tmpFromLess).divide(BigDecimal.valueOf(2), MathContext.DECIMAL32)
            );


        }


        return res;
    }
}
