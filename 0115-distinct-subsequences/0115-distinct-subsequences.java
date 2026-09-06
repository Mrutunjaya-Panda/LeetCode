class Solution {
    //recursive
    public int solve(String s, String t,int i,int j){
        //base
        if(j == t.length()){
            //we have found 1 subsequence in j = t.
            return 1;
        }
        if(i == s.length()){
            //this path didn't lead me to t.
            return 0;
        }

        //choices
        if(s.charAt(i) == t.charAt(j)){
            return solve(s,t,i+1,j) + solve(s,t,i+1,j+1);
        }
        //else
        //only move s pointer.
        return solve(s,t,i+1,j);
    }

    //memoized
    static int[][] dp;
    public int solve1(String s, String t,int i,int j){
        //base
        if(j == t.length()){
            //we have found 1 subsequence in j = t.
            return 1;
        }
        if(i == s.length()){
            //this path didn't lead me to t.
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];
        //choices
        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] = solve1(s,t,i+1,j) + solve1(s,t,i+1,j+1);
        }
        //else
        //only move s pointer.
        return dp[i][j] = solve1(s,t,i+1,j);
    }

    public int numDistinct(String s, String t) {
        
        dp = new int[1001][1001];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return solve1(s,t,0,0);
    }
}

//T.C:- O(2^n), n = s.lenth()