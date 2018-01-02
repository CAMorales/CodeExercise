package com.example.codeexcercise.service;

import com.example.codeexcercise.model.BodySum;
import org.springframework.stereotype.Service;

@Service
public class BodySumServiceImpl implements BodySumService {
    public BodySum genBodySUm(){

        int numSize = (int)(Math.random() * 5) + 2;
        int[] nums = new int[numSize];
        for (int i = 0; i < numSize; i++) {
            nums[i] = (int)(Math.random() * 100);
        }
        return new BodySum(nums,"Please sum the numbers ");
    }
}
