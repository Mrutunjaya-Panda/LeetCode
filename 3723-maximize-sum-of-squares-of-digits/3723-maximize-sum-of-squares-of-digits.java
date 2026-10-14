//import java.util.*;
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum > num*9){
            //not possible to form the string as sum cannot be reached with the given number of digits
            return "";
        }
        StringBuilder sb = new StringBuilder();
        //find the no. of 9's that can be appended at each pos.
        int numNine = sum/9;
        int rem = sum%9;
        //int remain = num - numNine;
        while(numNine > 0){
            sb.append(9);
            numNine--;
        }

        //append the rem too.
        if(rem > 0){
            sb.append(rem);
        }

        //if still some positions are empty.
        
        int left = num - sb.length();
        while(left > 0){
            sb.append(0);
            left--;
        }

        return sb.toString();
    }
}

//T.C : O(num), we form a string of num digits
//S.C : O(1)