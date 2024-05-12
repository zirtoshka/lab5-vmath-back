package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;
import puk.lab5vmathback.exc.IncorrectNumberOfPoints;
import puk.lab5vmathback.exc.NoDataException;
import puk.lab5vmathback.exc.WrongStepsListXException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    @Getter
    @Setter
    private BigDecimal lagrangeRes;
    private final FiniteDifferences finiteDifferences = new FiniteDifferences();
    private final NewtonSeparatedDiffPolynomial newtonSeparatedDiffPolynomial = new NewtonSeparatedDiffPolynomial();
    @Getter
    @Setter
    private BigDecimal newtonRes;
    private final GaussPolynomial gaussPolynomial = new GaussPolynomial();
    @Getter
    @Setter
    private BigDecimal gaussRes;
    private final StirlingPolynomial stirlingPolynomial = new StirlingPolynomial();
    @Getter
    @Setter
    private BigDecimal stirlingRes;
    private final BesselPolynomial besselPolynomial = new BesselPolynomial();
    @Getter
    @Setter
    private BigDecimal besselRes;

    public BigDecimal[] getSolve() {
        updateRes();
        finiteDifferences.setListX(listX);
        finiteDifferences.setListY(listY);
        newtonSeparatedDiffPolynomial.setListX(listX);
        newtonSeparatedDiffPolynomial.setListY(listY);
        List<BigDecimal> finiteDiff = finiteDifferences.getFiniteDiff();

        lagrangeRes = lagrangePolynomial.getFun(listX, listY, x);
        newtonRes = newtonSeparatedDiffPolynomial.getFun(listX, listY, x);
        try {
            gaussPolynomial.setFiniteDiff(finiteDiff);
            gaussRes = gaussPolynomial.getFun(listX, listY, x);
            System.out.println(gaussPolynomial + " it's gauss");
            try {
                stirlingPolynomial.setFiniteDiff(finiteDiff);
                stirlingRes = stirlingPolynomial.getFun(listX, listY, x);
                System.out.println(stirlingRes + " it's stirling");
            } catch (IncorrectNumberOfPoints e) {
                System.out.println("stirling bebebeb");
            }
            besselPolynomial.setFiniteDiff(finiteDiff);
            besselRes = besselPolynomial.getFun(listX, listY, x);
            System.out.println(besselRes + " it's bessel");

        } catch (WrongStepsListXException e) {
            System.out.println("error aaaaaa");
        } catch (IncorrectNumberOfPoints e) {
            System.out.println("bessel bebebebe");
        }


        return new BigDecimal[]{
                lagrangeRes,
                newtonRes,
                gaussRes,
                stirlingRes,
                besselRes
        };
    }

    public ArrayList<ArrayList<BigDecimal>> getGraphs()  {
        ArrayList<ArrayList<BigDecimal>> dataGraphs = new ArrayList<>();
        BigDecimal miniX , maxiX;
        try {
             miniX = Arrays.stream(listX).reduce(BigDecimal::min).orElseThrow(() -> new NoDataException());
             maxiX = Arrays.stream(listX).reduce(BigDecimal::max).orElseThrow(() -> new NoDataException());
        }catch (NoDataException e){
            System.out.println("pukpuk no data");
            return null;
        }

        ArrayList<BigDecimal> newtonRes = new ArrayList<>();
        ArrayList<BigDecimal> gaussRes=new ArrayList<>();
        ArrayList<BigDecimal> xRes=new ArrayList<>();
        while (miniX.compareTo(maxiX)<0){
            newtonRes.add(newtonSeparatedDiffPolynomial.getFun(listX,listY,miniX));
            gaussRes.add(newtonSeparatedDiffPolynomial.getFun(listX,listY,miniX));
            xRes.add(miniX);
            miniX=miniX.add(BigDecimal.valueOf(0.01));
        }

        dataGraphs.add(xRes);
        dataGraphs.add(newtonRes);
        dataGraphs.add(gaussRes);
        return dataGraphs;
    }


    private void updateRes() {
        lagrangeRes = null;
        newtonRes = null;
        gaussRes = null;
        stirlingRes = null;
        besselRes = null;
    }
}
