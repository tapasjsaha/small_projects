package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.example.Solution;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        int [] nums = {2, 11, 7, 15};
//        int target = 9;
        String [] st = {"abc", "abdse","ababaa"};
        Solution sol = new Solution();
        String ans = sol.firstPalindrome(st);
        System.out.println(ans);
    }
}