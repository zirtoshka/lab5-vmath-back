package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class InterpolationManager {
    @Getter
    @Setter
    private int method;
    @Getter
    @Setter
    private BigDecimal[] listX;
    @Getter
    @Setter
    private BigDecimal[] listY;
    @Getter
    @Setter
    private BigDecimal x;

    private final LagrangePolynomial lagrangePolynomial=new LagrangePolynomial();

    public BigDecimal getSolve(){
        if (method==1){
            return lagrangePolynomial.getFun(listX,listY,x);
        }
        return BigDecimal.ONE;
    }
}
