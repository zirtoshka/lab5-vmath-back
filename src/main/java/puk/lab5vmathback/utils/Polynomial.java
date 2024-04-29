package puk.lab5vmathback.utils;

import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;

public interface Polynomial {
    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException;
}
