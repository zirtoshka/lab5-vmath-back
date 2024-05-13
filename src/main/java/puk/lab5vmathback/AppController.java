package puk.lab5vmathback;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puk.lab5vmathback.utils.InterpolationManager;
import puk.lab5vmathback.utils.RequestInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.LongBinaryOperator;

@RestController
@RequestMapping("/app-controller")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AppController {
    private final InterpolationManager interpolationManager=new InterpolationManager();
    @GetMapping
    public ResponseEntity<String> sayHello() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("it's method sayHello");
        return new ResponseEntity<>("{\"message\": \"Hello from secured endpoint\"}", httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> getInterpolation(@Valid @RequestBody RequestInfo requestInfo){
        String response="";
        final HttpHeaders httpHeaders=new HttpHeaders();

        BigDecimal[] listX = new ArrayList<>(Arrays.asList(requestInfo.getX())).toArray(new BigDecimal[0]);
        BigDecimal[] listY = new ArrayList<>(Arrays.asList(requestInfo.getY())).toArray(new BigDecimal[0]);
        BigDecimal xInterpol = requestInfo.getInterpolX();

        long uniqueCount = Arrays.stream(listX)
                .distinct()
                .count();
        if (listX.length!=uniqueCount || listX.length==0){
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        interpolationManager.setListX(listX);
        interpolationManager.setListY(listY);
        interpolationManager.setX(xInterpol);

        response+=  "{\"resiki\": "+ Arrays.toString(interpolationManager.getSolve())+",\n";
        response+="\"grafiki\": "+interpolationManager.getGraphs()+",\n";
         response+= "\"finiteDiff\": "+interpolationManager.getFiniteDiff()+ "}";

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

    }

}
