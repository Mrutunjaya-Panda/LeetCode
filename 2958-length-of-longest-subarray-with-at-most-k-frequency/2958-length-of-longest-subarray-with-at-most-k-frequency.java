class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int culprit = 0;
        int maxLen = 1;
        int left = 0;
        Map<Integer,Integer> freq = new HashMap<>();
        for(int right =0;right<n;right++){
            int cnt = freq.getOrDefault(nums[right],0)+1;
            freq.put(nums[right],cnt);

            if(cnt == k+1) culprit++;
            if(culprit == 0) continue;

            //if culprit is present
            int d = nums[left];
            int dec = freq.get(d) - 1;
            freq.put(d,dec);
            if(dec == k) culprit--;
            //shrink from left with keeping maximum window open till now.
            left++;
        }
        // Since the window never shrinks below a size it has already reached, the final window size is the answer.
        return n - left;
    }
}

//subarray length we found, it may be wrong, but the maxLen is correct.
//T.C:- O(n)
