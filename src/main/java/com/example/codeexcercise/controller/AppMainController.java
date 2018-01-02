package com.example.codeexcercise.controller;

import com.example.codeexcercise.model.BodySum;
import com.example.codeexcercise.service.BodySumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class AppMainController {
    private final static String AUTH_HEADER = "Authorization";

    private Map<String, BodySum> sesion = new ConcurrentHashMap<>();

    private BodySumService bodySumService;

    public AppMainController(BodySumService bodySumService) {
        this.bodySumService = bodySumService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BodySum provideQuestion(HttpServletResponse httpServletResponse) {
        UUID idOne = UUID.randomUUID();
        BodySum bodySum = bodySumService.genBodySUm();
        sesion.put(String.valueOf(idOne), bodySum);
        httpServletResponse.setHeader(AUTH_HEADER, String.valueOf(idOne));
        return bodySum;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity result(HttpServletRequest httpServletRequest, @RequestBody BodySum bodySum) {
        String authHeader = httpServletRequest.getHeader(AUTH_HEADER);
        if(authHeader == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        BodySum tmpSum = sesion.get(authHeader);
        if(tmpSum == null || !tmpSum.equals(bodySum)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        int tmpRes = 0;
        for (int i : bodySum.getNumbers()) {
            tmpRes += i;
        }
        //need to delete already processed when result is OK from our temporary Map
        return tmpRes == bodySum.getSumResult() ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
