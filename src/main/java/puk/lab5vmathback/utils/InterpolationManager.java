package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;
import puk.lab5vmathback.exc.IncorrectNumberOfPoints;
import puk.lab5vmathback.exc.WrongStepsListXException;

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

    private final LagrangePolynomial lagrangePolynomial = new LagrangePolynomial();
    private final FiniteDifferences finiteDifferences = new FiniteDifferences();
    private final NewtonSeparatedDiffPolynomial newtonSeparatedDiffPolynomial = new NewtonSeparatedDiffPolynomial();
    private final GaussPolynomial gaussPolynomial = new GaussPolynomial();
    private final StirlingPolynomial stirlingPolynomial = new StirlingPolynomial();
private final BesselPolynomial besselPolynomial=new BesselPolynomial();
    public BigDecimal getSolve() {
        if (method == 1) {
            finiteDifferences.setListX(listX);
            finiteDifferences.setListY(listY);
            List<BigDecimal> finiteDiff = finiteDifferences.getFiniteDiff();
            System.out.println("diff:");
            for (BigDecimal diff : finiteDiff) {
                System.out.println(diff);
            }
            System.out.println();
//            newtonSeparatedDiffPolynomial.setListX(listX);
//            newtonSeparatedDiffPolynomial.setListY(listY);
//            System.out.println(newtonSeparatedDiffPolynomial.getFun(listX,listY,x)+" it's newton");
            try {
                gaussPolynomial.setFiniteDiff(finiteDiff);
                System.out.println(gaussPolynomial.getFun(listX, listY, x) + " it's gauss");

                stirlingPolynomial.setFiniteDiff(finiteDiff);
                System.out.println(stirlingPolynomial.getFun(listX, listY, x) + " it's stirling");

                besselPolynomial.setFiniteDiff(finiteDiff);
                System.out.println(besselPolynomial.getFun(listX, listY, x) + " it's bessel");

            } catch (WrongStepsListXException e) {
                System.out.println("error aaaaaa");
            }catch (IncorrectNumberOfPoints e){
                System.out.println("aaaaaaa");
            }

            return lagrangePolynomial.getFun(listX, listY, x);
        }
        return BigDecimal.ONE;
    }
}
