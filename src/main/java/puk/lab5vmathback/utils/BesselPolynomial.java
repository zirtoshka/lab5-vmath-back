package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;
import puk.lab5vmathback.exc.IncorrectNumberOfPoints;
import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class BesselPolynomial implements Polynomial {
    @Getter
    @Setter
    private List<BigDecimal> finiteDiff;
    private final PolynomialIntervalsChecker polynomialIntervalsChecker = new PolynomialIntervalsChecker();


    @Override
    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException, IncorrectNumberOfPoints {
        int number = listX.length;
        if (number%2!=0) throw new IncorrectNumberOfPoints();
        BigDecimal mediumX = listX[(number + 1) / 2 - 1];
        BigDecimal res = BigDecimal.ZERO;
        boolean check = polynomialIntervalsChecker.checkStepsX(listX);
        if (!check) {
            throw new WrongStepsListXException();
        }
        BigDecimal step = listX[1].subtract(listX[0]);
        BigDecimal t = (x.subtract(mediumX)).divide(step, MathContext.DECIMAL32);
        BigDecimal multiplierTEven = BigDecimal.ONE;  //chet
        BigDecimal multiplierTOdd = t.subtract(BigDecimal.valueOf(0.5));
        BigDecimal tmpSummand;
        int j = (number + 1) / 2 - 1;
        int fact = 1;
        int diffMultiT = 0;
        for (int i = 0; i < number; i++) {
            if (i != 0) {
                fact *= i;
            }
            if (i != 0 && i != 1) {
                if (i % 2 != 0) {
                    diffMultiT++;
                } else {
                    multiplierTEven = multiplierTEven.multiply(
                            (t.add(BigDecimal.valueOf(diffMultiT)))
                                    .multiply(
                                            t.subtract(BigDecimal.valueOf(diffMultiT + 1))));
                }
            }
            if (i % 2 == 0) {
                tmpSummand = (((finiteDiff.get(j).add(finiteDiff.get(j + 1)))
                        .multiply(multiplierTEven)
                        .divide(BigDecimal.valueOf(2), MathContext.DECIMAL32))
                );
            } else {
                tmpSummand = (finiteDiff.get(j)
                        .multiply(multiplierTOdd.multiply(multiplierTEven)));
                j -= 1;
            }
            tmpSummand = tmpSummand.divide(BigDecimal.valueOf(fact), MathContext.DECIMAL32);
            res = res.add(tmpSummand);
            j += number - i;
        }
        return res;
    }
}
