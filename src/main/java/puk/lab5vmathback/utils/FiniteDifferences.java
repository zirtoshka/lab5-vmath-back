package puk.lab5vmathback.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FiniteDifferences {

    @Getter
    @Setter
    private BigDecimal[] listX;
    @Getter
    @Setter
    private BigDecimal[] listY;

    public List<BigDecimal> getFiniteDiff(){
        List<BigDecimal> res = new ArrayList<>();
         for (BigDecimal y: listY){
             res.add(y);
         }
         int number=listX.length;
         int shift = 0;
         while (number>0){
             for (int i=1;i<number;i++){
                 res.add(res.get(i+shift).subtract(res.get(i-1+shift)));
             }
             shift+=number;
             number--;
         }
        return res;
    }
}
