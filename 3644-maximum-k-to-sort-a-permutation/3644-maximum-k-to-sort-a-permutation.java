class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        //initially keep k a very large value.
        int K = -1; //i.e 11111

        //Take & of all the misplaced numbers.
        for(int i=0;i<n;i++){
            if(nums[i] != i){
                //it is a misplaced number
                K = (K & nums[i]);
            }
        }
        //k==-1 => already sorted, no bit in K needed to be set.
        return (K == -1) ? 0 : K;
    }
}