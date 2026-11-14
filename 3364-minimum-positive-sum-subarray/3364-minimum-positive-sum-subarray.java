class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        //int i=0,j=0;
        int ans = Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            //check all valid subarrays with starting index i.
            int sum=0;
            for(int j=i;j<n;j++){
                sum += nums.get(j);
                int len = j-i+1;
                if(len >= l && len <= r && sum > 0){
                    ans = Math.min(ans,sum);
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}

//T.C:- O(n^2)