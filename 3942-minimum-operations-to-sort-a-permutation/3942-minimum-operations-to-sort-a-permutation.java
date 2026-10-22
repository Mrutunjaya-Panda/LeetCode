class Solution {
    public int minOperations(int[] nums) {
         int n = nums.length;

         int asc_cnt=0;
         int dsc_cnt=0;

         for(int i=1;i<n;i++){
            asc_cnt += (nums[i] == (nums[i-1] + 1)%n) ? 1 : 0;
            dsc_cnt += (nums[i-1] == (nums[i] + 1)%n) ? 1 : 0;
         }

         if(asc_cnt == n-1){
            if(nums[0] == 0){
                //it is in the order 0,1,2..
                //already sorted.
                return 0;
            }
            //else
            //check ops from left_rot, right_rot, and take min. of them.
            int left_rot = n - nums[0];
            int right_rot = 1 + (nums[n-1] + 1) + 1;
            return Math.min(left_rot,right_rot);
         }

         //else desc check
         if(dsc_cnt == n-1){
            if(nums[0] == n-1){
                //just 1 rev is needed and everything will be sorted, i.e
                //it is in the order n-1,n-2,...,2,1,0
                return 1;
            }
            //else
            //NOTE:- we are not actually doing rev_must in nums, so accordingly we need to modify
            //first offcourse we need to do 1 rev, to make it in asc nature.
            //combining 1 rev_must and right_rot and keeping left_rot separate
            //we came to the conclusion..

            int left_rot = n - nums[n-1] + 1;//this extra 1 is for rev_must
            int right_rot_rev = (nums[0] + 1) + 1;
            return Math.min(left_rot,right_rot_rev);
         }

         //if not asc, or desc nature, then it is a bad array and can never be sorted with the
         //given set of ops.
         return -1;
    }
}