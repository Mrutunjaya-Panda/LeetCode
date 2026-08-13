class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer,Integer> mp = new HashMap<>();
        int i=0,j=0;
        int maxLen = Integer.MIN_VALUE;
        //classic sliding window
        while(j<n){
            //cnt freq.
            mp.put(nums[j],mp.getOrDefault(nums[j],0)+1);
            //keep in valid range i.e <=k
            while(i<j && mp.get(nums[j]) > k){
                mp.put(nums[i],mp.get(nums[i]) - 1);//dec frq. to keep it in valid range
                i++;//reduce search space from left as freq. is getting increased.
            }

            int curLen = j-i+1;
            maxLen = Math.max(maxLen,curLen);//longest subarray till now.
            j++;
        }
        return maxLen;
    }
}