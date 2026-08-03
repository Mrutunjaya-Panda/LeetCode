class Solution {
    int n;
    //we will be returning Alice - bob
    //work from Alice point of view
    //T.C:- O(3^n), S.C:- O(n)
    public int helper(int[] stoneValue,int si){
        if(si >= n) return 0;

        //choices
        int take1 = stoneValue[si] - helper(stoneValue,si+1);
        int take2 = Integer.MIN_VALUE;
        if(si+1 < n){
            take2 = stoneValue[si] + stoneValue[si+1] - helper(stoneValue,si+2);
        }
        int take3 = Integer.MIN_VALUE;
        if(si+2 < n){
            take3 = stoneValue[si] + stoneValue[si+1] + stoneValue[si+2] - helper(stoneValue,si+3);
        }

        return Math.max(take1,Math.max(take2,take3));

    }

    //memoized
    // Total Time
    // O(states)×O(work per state)
    // O(n)×O(1)
    // O(n)​
    static int[] dp;
    public int helperM(int[] stoneValue,int si){
        if(si >= n) return 0;

        if(dp[si] != -1) return dp[si];
        //choices
        int take1 = stoneValue[si] - helperM(stoneValue,si+1);
        int take2 = Integer.MIN_VALUE;
        if(si+1 < n){
            take2 = stoneValue[si] + stoneValue[si+1] - helperM(stoneValue,si+2);
        }
        int take3 = Integer.MIN_VALUE;
        if(si+2 < n){
            take3 = stoneValue[si] + stoneValue[si+1] + stoneValue[si+2] - helperM(stoneValue,si+3);
        }

        return dp[si] = Math.max(take1,Math.max(take2,take3));

    }

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        dp = new int[n];
        Arrays.fill(dp,-1);

        int result = helperM(stoneValue,0);

        if(result > 0){
            return "Alice";
        }
        if(result < 0){
            return "Bob";
        }

        return "Tie";
    }
}