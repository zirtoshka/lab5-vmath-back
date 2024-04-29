package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class NewtonSeparatedDiffPolynomial  implements Polynomial{
    @Getter
    @Setter
    private BigDecimal[] listX;
    @Getter
    @Setter
    private BigDecimal[] listY;

    private BigDecimal separatedDiffs(int i, int k) {
        if (k == 0) {
            return listY[i];
        }
        if (k == 1) {
            return (listY[i + 1].subtract(listY[i])).divide(listX[i + 1].subtract(listX[i]), MathContext.DECIMAL32);
        }
        return separatedDiffs(i + 1, k - 1).subtract(separatedDiffs(i, k - 1)).divide(listX[i + k].subtract(listX[i]), MathContext.DECIMAL32);
    }

    private List<BigDecimal> getSeparatedDiff() {
        List<BigDecimal> diff = new ArrayList<>();
        for (int k = 0; k < listX.length; k++) {
            diff.add(separatedDiffs(0, k));
        }
        return diff;
    }

    public BigDecimal getFun(BigDecimal[] listX, BigDecimal[] listY, BigDecimal x){
        List<BigDecimal> diff=getSeparatedDiff();
        BigDecimal res = BigDecimal.ZERO;
        BigDecimal directSumOfX ;
        for(int i = 0; i<diff.size();i++){
                directSumOfX=BigDecimal.ONE;
                for (int j=0;j<i;j++){
                    directSumOfX=directSumOfX.multiply(x.subtract(listX[j]));
                }

                res=res.add(diff.get(i).multiply(directSumOfX));

        }
        return res;
    }
}
