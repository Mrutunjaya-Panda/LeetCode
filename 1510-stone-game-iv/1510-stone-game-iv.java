class Solution {
    //solving from Alice's perspective.
    int dp[];
    public boolean solve(int n){
        if(n == 0){
            return false;//whoever turn it is, cannot winn because no n left.
        }

        if(dp[n] != -1) return dp[n] == 1;
        //options
        for(int k=1;k*k <= n;k++){
            //since Alice started first, so alice made his turn with k*k;
            int rem = n-k*k;//bob's turn
            if(!solve(rem)){
                dp[n] = 1;            
                return true;//bob didn't win => alice won.
            }
        }

        //if after every possibility alice couldn't won
        dp[n] = 0;
        return false;
    }
    public boolean winnerSquareGame(int n) {
        dp = new int[n+1];
        Arrays.fill(dp,-1);
        return solve(n);
    }
}

// Time Complexity: O(n√n)
// Space Complexity: O(n)
