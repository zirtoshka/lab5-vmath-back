package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
    private final FiniteDifferences finiteDifferences=new FiniteDifferences();
    private final NewtonSeparatedDiffPolynomial newtonSeparatedDiffPolynomial=new NewtonSeparatedDiffPolynomial();

    public BigDecimal getSolve(){
        if (method==1){
//            finiteDifferences.setListX(listX);
//            finiteDifferences.setListY(listY);
//            List<BigDecimal> finiteDiff=finiteDifferences.getFiniteDiff();
//            for (BigDecimal diff: finiteDiff){
//                System.out.println(diff);
//            }
            newtonSeparatedDiffPolynomial.setListX(listX);
            newtonSeparatedDiffPolynomial.setListY(listY);
            System.out.println(newtonSeparatedDiffPolynomial.getFun(listX,listY,x)+" it's newton");
            return lagrangePolynomial.getFun(listX,listY,x);
        }
        return BigDecimal.ONE;
    }
}
