package puk.lab5vmathback;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puk.lab5vmathback.utils.InterpolationManager;

import java.math.BigDecimal;

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
    public ResponseEntity<String> getInterpolation(String string){
        String response="";
        final HttpHeaders httpHeaders=new HttpHeaders();
        interpolationManager.setMethod(1);

        //todo
        BigDecimal[] listX=new BigDecimal[]{
                BigDecimal.valueOf(0.1),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(0.3),
                BigDecimal.valueOf(0.4),
                BigDecimal.valueOf(0.5),
                //----
                BigDecimal.valueOf(0.6),
                BigDecimal.valueOf(0.7),
                BigDecimal.valueOf(0.8)

                //-----
//                BigDecimal.valueOf(0.15),
//                BigDecimal.valueOf(0.2),
//                BigDecimal.valueOf(0.33),
//                BigDecimal.valueOf(0.47)
        };
        BigDecimal[] listY=new BigDecimal[]{
                BigDecimal.valueOf(1.25),
                BigDecimal.valueOf(2.38),
                BigDecimal.valueOf(3.79),
                BigDecimal.valueOf(5.44),
                BigDecimal.valueOf(7.14),
                //---
                BigDecimal.valueOf(8.64),
                BigDecimal.valueOf(10.39),
                BigDecimal.valueOf(12.09)

                //----
//                BigDecimal.valueOf(1.25),
//                BigDecimal.valueOf(2.38),
//                BigDecimal.valueOf(3.79),
//                BigDecimal.valueOf(5.44)
        };
//        BigDecimal x=BigDecimal.valueOf(0.35);
//        BigDecimal x=BigDecimal.valueOf(0.22);
        BigDecimal x=BigDecimal.valueOf(0.28);


        interpolationManager.setListX(listX);
        interpolationManager.setListY(listY);
        interpolationManager.setX(x);

        response+=interpolationManager.getSolve().toString();

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

    }

}
