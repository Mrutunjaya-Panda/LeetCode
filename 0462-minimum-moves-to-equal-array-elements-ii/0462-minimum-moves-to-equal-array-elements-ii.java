class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int moves=0;
        int mid = nums[n/2];
        for(int i=0;i<n/2;i++){
            moves += Math.abs(nums[i] - mid);
            moves += Math.abs(nums[n-i-1] - mid);
        }
        return moves;
    }
}
//T.C:- O(nlogn)