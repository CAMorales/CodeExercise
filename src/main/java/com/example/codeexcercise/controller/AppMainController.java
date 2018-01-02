package com.example.codeexcercise.controller;

import com.example.codeexcercise.model.BodySum;
import com.example.codeexcercise.service.BodySumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMainController {

    private BodySumService bodySumService;

    public AppMainController(BodySumService bodySumService) {
        this.bodySumService = bodySumService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public BodySum provideQuestion() {
        return bodySumService.genBodySUm();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity result(@RequestBody BodySum bodySum) {
        int tmpRes = 0;
        for (int i : bodySum.getNumbers()) {
            tmpRes += i;
        }
        return tmpRes == bodySum.getSumResult() ?
                new ResponseEntity(HttpStatus.OK) :
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
