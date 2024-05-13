package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;
import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class GaussPolynomial implements Polynomial {

    @Getter
    @Setter
    private List<BigDecimal> finiteDiff;

    private final PolynomialIntervalsChecker polynomialIntervalsChecker=new PolynomialIntervalsChecker();

    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException {
        boolean check = polynomialIntervalsChecker.checkStepsX(listX);
        if (!check) {
            throw new WrongStepsListXException();
        }
        int numberOfPoints = listX.length;
        BigDecimal mediumX = listX[(numberOfPoints + 1) / 2 - 1];

        BigDecimal step = listX[1].subtract(listX[0]);
        BigDecimal res;
        if (x.compareTo(mediumX) > 0) {
            res = xMoreMediumFormula(x, mediumX, step, numberOfPoints);
        } else if (x.compareTo(mediumX) == 0) {
            return listY[(numberOfPoints + 1) / 2];
        } else {

            res = xLessMediumFormula(x, mediumX, step, numberOfPoints);
        }

        return res;
    }


    private BigDecimal xMoreMediumFormula(BigDecimal x, BigDecimal medium, BigDecimal step, int numberOfPoints) {//first formula
        BigDecimal multiplierWithT = BigDecimal.ONE;
        BigDecimal t = getT(x, medium, step);
        int j = (numberOfPoints + 1) / 2 - 1;
        int shiftJ = numberOfPoints;
        BigDecimal k = BigDecimal.ZERO;
        BigDecimal res = finiteDiff.get((numberOfPoints + 1) / 2 - 1);
        int fact = 1;
        for (int i = 1; i < numberOfPoints; i++) {
            if (i % 2 == 0) {
                shiftJ -= 2;
            }
            fact *= i;
            j += shiftJ;
            if (i % 2 == 0) {
                multiplierWithT = multiplierWithT.multiply(t.subtract(k));
            } else {
                multiplierWithT = multiplierWithT.multiply(t.add(k));
                k = k.add(BigDecimal.ONE);

            }

            res = res.add(
                    multiplierWithT.multiply(finiteDiff.get(j))
                            .divide(BigDecimal.valueOf(fact), MathContext.DECIMAL32)
            );


        }

        return res;
    }

    private BigDecimal xLessMediumFormula(BigDecimal x, BigDecimal medium, BigDecimal step, int numberOfPoints) {//first formula
        BigDecimal multiplierWithT = BigDecimal.ONE;
        BigDecimal t = getT(x, medium, step);
        int j = (numberOfPoints + 1) / 2 - 1;
        int shiftJ = numberOfPoints - 1;
        BigDecimal k = BigDecimal.ZERO;
        BigDecimal res = finiteDiff.get((numberOfPoints + 1) / 2 - 1);
        int fact = 1;
        for (int i = 1; i < numberOfPoints; i++) {
            if (i > 1 && i % 2 != 0) {
                shiftJ -= 2;
            }
            fact *= i;
            j += shiftJ;
            if (i % 2 == 0) {
                multiplierWithT = multiplierWithT.multiply(t.add(k));
            } else {
                multiplierWithT = multiplierWithT.multiply(t.subtract(k));
                k = k.add(BigDecimal.ONE);

            }

            res = res.add(
                    multiplierWithT.multiply(finiteDiff.get(j))
                            .divide(BigDecimal.valueOf(fact), MathContext.DECIMAL32)
            );


        }
        return res;
    }

    private BigDecimal getT(BigDecimal x, BigDecimal medium, BigDecimal step) {
        return x.subtract(medium).divide(step, MathContext.DECIMAL32);
    }
}
