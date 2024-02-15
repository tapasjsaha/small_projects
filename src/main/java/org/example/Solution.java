package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int numOfEdges = nums.length;
        long sumOfEdges = 0;
        for(int i = 0; i<numOfEdges; i++){
            sumOfEdges += nums[i];
        }
        int largestSide;
        while(numOfEdges >= 3){
            largestSide = nums[numOfEdges-1];
            if(sumOfEdges-largestSide > largestSide){
                return sumOfEdges;
            } else {
                sumOfEdges -= largestSide;
                numOfEdges -= 1;
            }
        }
        return (long) -1;
    }
}