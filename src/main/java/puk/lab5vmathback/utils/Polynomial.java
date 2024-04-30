package puk.lab5vmathback.utils;

import puk.lab5vmathback.exc.IncorrectNumberOfPoints;
import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;

public interface Polynomial {
    BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x) throws WrongStepsListXException, IncorrectNumberOfPoints;
}
