package puk.lab5vmathback.utils;

import java.math.BigDecimal;

public class PolynomialIntervalsChecker {
    public boolean checkStepsX(BigDecimal[] listX) {
        BigDecimal step = listX[1].subtract(listX[0]);
        if (step.compareTo(BigDecimal.ZERO) == 0) return false;
        BigDecimal tmpStep;
        for (int i = 2; i < listX.length; i++) {

            tmpStep = listX[i].subtract(listX[i - 1]);
            if (tmpStep.compareTo(step) != 0) {
                return false;
            }
        }
        return true;
    }

}
