class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int smallIdx = Integer.MAX_VALUE;
        int score=0;
        for(int i=0;i<n;i++){
            int currIdx = i;
            int scoreM = Integer.MIN_VALUE, scoreMi = Integer.MAX_VALUE;
            for(int j=0;j<=currIdx;j++){
                scoreM = Math.max(nums[j],scoreM);
            }
            for(int j=currIdx;j<n;j++){
                scoreMi = Math.min(nums[j],scoreMi);
            }
            score = scoreM - scoreMi;
            if(score <= k){
                smallIdx = Math.min(smallIdx,currIdx);
            }
        }
        return (smallIdx != Integer.MAX_VALUE) ? smallIdx : -1;
    }
}