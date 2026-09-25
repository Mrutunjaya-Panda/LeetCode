class Solution {
    //it just reduces down to finding longest subarray with sum = total_sum - x length finding.
    //then subtracting from whole length.
    //Prefix_sum+hashMap.
    public int solve(int[] nums,int tar){
        int n = nums.length;
        Map<Integer,Integer> mp = new HashMap<>();//prefix_sum,index.
        mp.put(0,-1);

        int sum =0;
        for(int i=0;i<n;i++){
            sum += nums[i];
            mp.put(sum,i);
        }

        sum=0;
        int longestLen = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            sum += nums[i];
            int remSumReq = sum-tar;

            if(mp.containsKey(remSumReq)){
                //means we found a subarray with target = tar.
                //whhose length
                longestLen = Math.max(longestLen, i-mp.get(remSumReq));
            }
        }
        return longestLen;
    }
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int tot_sum=0;
        for(int num:nums){
            tot_sum += num;
        }

        //now our target = tot_sum -x.
        int tar = tot_sum-x;
        if(tar < 0){
            return -1;
        }
        //find longest subarray length with sum = tar.
        int len = solve(nums,tar);

        if(len == Integer.MIN_VALUE){
            return -1;
        }
        //since we need the other side of len or min. ops.
        return n-len;
    }
}

//Using longest subarray Sum logic
//T.C : O(n)
//S.C : O(n)