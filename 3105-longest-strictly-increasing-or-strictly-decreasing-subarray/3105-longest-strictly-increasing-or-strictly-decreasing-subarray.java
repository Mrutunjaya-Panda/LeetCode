class Solution {
    //let's see optimal
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        //decide if it is inc. or dec. on the fly and reset each time.
        int inc=1;
        int dec=1;
        int res = 1;
        for(int i=1;i<n;i++){
            if(nums[i] > nums[i-1]){
                //inc. has started in this window.
                inc++;
                dec=1;
                // res = Math.max(res,Math.max(inc,dec));
                res = Math.max(res,inc);
            }else if(nums[i] < nums[i-1]){
                //dec window started.
                dec++;
                //reset inc. to 1, since this is a dec window.
                inc=1;
                // res = Math.max(res,Math.max(inc,dec));
                res = Math.max(res,dec);
            }else{
                inc=1;
                dec=1;
            }
        }
        return res;
    }
}
//T.C:- O(n);
//S.C:- O(1)