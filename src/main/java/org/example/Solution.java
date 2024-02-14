package org.example;

public class Solution {
    public int[] rearrangeArray(int[] nums) {
        int [] res = new int [nums.length];
        int pos = 0, neg = 0, i=0;
        while(pos < nums.length && neg < nums.length){
            while(nums[pos] < 0){pos += 1;}
            while(nums[neg] > 0){neg += 1;}
            res[i] = nums[pos];
            i += 1;
            pos += 1;
            res[i] = nums[neg];
            i+=1;
            neg += 1;
        }
        return res;
    }
}