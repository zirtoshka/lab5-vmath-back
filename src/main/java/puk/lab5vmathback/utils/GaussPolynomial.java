package puk.lab5vmathback.utils;

import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;

public class GaussPolynomial implements Polynomial {


    @Override
    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException {
        boolean check = checkStepsX(listX);
        if (!check) {
            throw new WrongStepsListXException();
        }
        //todo check 1 or 2 formula

        return BigDecimal.ONE;
    }

    private boolean checkStepsX(BigDecimal[] listX) {
        BigDecimal step = listX[1].subtract(listX[0]);
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
