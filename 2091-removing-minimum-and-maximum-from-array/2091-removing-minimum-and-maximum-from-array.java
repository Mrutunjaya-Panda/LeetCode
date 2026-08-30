class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minEleIdx = 0;
        int maxEleIdx = 0;

        for(int i=0;i<n;i++){
            if(nums[i] < nums[minEleIdx]) minEleIdx = i;
            if(nums[i] > nums[maxEleIdx]) maxEleIdx = i;
        }

        // for(int i=0;i<n;i++){
        //     if(nums[i] > nums[maxEleIdx]) maxEleIdx = i;
            
        // }

        //leftmostIdx
        int leftIdx = Math.min(minEleIdx,maxEleIdx);
        //rightmostIdx
        int rightIdx = Math.max(minEleIdx,maxEleIdx);

        //now 3 options
        //both opposite ends
        int oppSteps = (leftIdx +1) + (n-rightIdx);
        //both removed from left
        int leftSteps = (rightIdx+1);
        //both removed from right
        int rightSteps = (n-leftIdx);

        return Math.min(oppSteps,Math.min(leftSteps,rightSteps));
    }
}