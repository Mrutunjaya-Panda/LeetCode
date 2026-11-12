class Solution {
    //brute
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int inc=0,dec=0;
        int res=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            //it will be either strictly inc.
            inc=1;
            for(int j=i+1;j<n;j++){
                if(nums[j] > nums[j-1]){
                    inc += 1;
                }else{
                    break;
                }
            }

            //or strictly dec.
            dec=1;
            for(int j=i+1;j<n;j++){
                if(nums[j] < nums[j-1]){
                    dec += 1;
                }else{
                    break;
                }
            }
            res = Math.max(res, Math.max(inc,dec));
        }
        return res;
    }
}

//T.C:- O(n2)