class Solution {
    public int minMoves(int[] nums) {
        int maxi=nums[0];
        int n = nums.length;

        for(int i=0;i<n;i++){
            maxi = Math.max(maxi,nums[i]);
        }

        int ops=0;
        for(int i=0;i<n;i++){
            int gap = Math.abs(nums[i] - maxi);
            ops += gap;
        }
        return ops;
    }
}

//T.C:- O(n)