class Solution {
    public int helper(int[] piles,int si,int ei){
        //base case
        if(si > ei) return 0;
        if(si == ei) return piles[si];

        //choices
        int take_si = piles[si] + Math.min(helper(piles,si+2,ei),helper(piles,si+1,ei-1));
        int take_ei = piles[ei] + Math.min(helper(piles,si+1,ei-1),helper(piles,si,ei-2));

        //take maximum of both
        return Math.max(take_si,take_ei);
    }

    //memoized
    static int[][] dp;
    public int helperM(int[] piles,int si,int ei){
        //base case
        if(si > ei) return 0;
        if(si == ei) return piles[si];

        if(dp[si][ei] != -1) return dp[si][ei];
        //choices
        int take_si = piles[si] + Math.min(helperM(piles,si+2,ei),helperM(piles,si+1,ei-1));
        int take_ei = piles[ei] + Math.min(helperM(piles,si+1,ei-1),helperM(piles,si,ei-2));

        //take maximum of both
        return dp[si][ei] = Math.max(take_si,take_ei);
    }

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        //doing from alice perspective
        dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        int tot = 0;
        for(int pile : piles){
            tot += pile;
        }
        //find the alices best o/p
        int bestAlice = helperM(piles,0,n-1);
        if(tot - bestAlice > bestAlice){
            //tot - bestAlice = bob
            return false;
        }
        return true;//Alice can win
    }
}