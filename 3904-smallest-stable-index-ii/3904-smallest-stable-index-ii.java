class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suf = new int[n];
        suf[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            suf[i] = Math.min(suf[i+1],nums[i]);
        }

        int maxSofar = 0;
        int smallIdx = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            maxSofar = Math.max(maxSofar,nums[i]);
            int score = maxSofar - suf[i];
            if(score <= k){
                smallIdx = Math.min(smallIdx,i);
            }
        }

        return (smallIdx == Integer.MAX_VALUE) ? -1 : smallIdx;
    }
}