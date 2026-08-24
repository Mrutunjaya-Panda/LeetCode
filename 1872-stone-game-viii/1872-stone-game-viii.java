class Solution {
    //solving from Alice's perspective
    static int[] dp;
    public int solve(int[] prefixSum,int idx,int n){
        if(idx == n-1){
            //only 1 stone left
            return prefixSum[idx];
        }

        if(dp[idx] != -1) return dp[idx];
        //choices
        int take = prefixSum[idx] - /*Bob*/ solve(prefixSum,idx+1,n);
        int notTake = solve(prefixSum,idx+1,n);

        //return the maximum difference for alice
        return dp[idx] = Math.max(notTake,take);
    }

    //bottom-up
    
    public int solve1(int[] prefixSum,int n){
        int[] dp = new int[n];
        //base case
        dp[n-1] = prefixSum[n-1];

        for(int i=n-2;i>0;i--){
            int take = prefixSum[i] - dp[i+1];
            int notTake = dp[i+1];
            dp[i] = Math.max(notTake,take);
        }
        return dp[1];
    }

    public int stoneGameVIII(int[] stones) {
        
        int n = stones.length;
        dp = new int[n];
        Arrays.fill(dp,-1);
        //let's find the prefix sum first
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];
        for(int i=1;i<n;i++){
            prefixSum[i] = prefixSum[i-1]+stones[i];
        }

        //now recursively
        //return the total Alie's score - total bob's score
        //minimum 2 stones needed
        //return solve(prefixSum,1,n);

        return solve1(prefixSum,n);
    }
}