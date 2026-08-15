class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;

        int resXor = 0;
        //for corner case when all elems are 0
        boolean allZero = true;

        for(int x : nums){
            //resXor ^= x;
            if(x!=0){
                //a elem. is non-zero
                allZero = false;
                break;
            }
        }
        for(int x : nums){
            resXor ^= x;
        }

        if(resXor != 0){
            //simply return length
            return n;
        }
        //else resXor == 0
        if(allZero == false){
            //return len-1
            return n-1;
        }
        //else, i.e allZero case
        return 0;
    }
}