//Approach-1 (Recursion + MEmoization)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    int[][] dp = new int[1001][1001];
    int solve(String s, String t, int m, int n) {
        if(n == 0)
            return dp[m][n] = 1;
        if(m == 0)
            return dp[m][n] = 0;
        
        if(dp[m][n] != -1)
            return dp[m][n];
        
        if(s.charAt(m-1) == t.charAt(n-1))
            return dp[m][n] = solve(s, t, m-1, n) + solve(s, t, m-1, n-1);
        else
            return dp[m][n] = solve(s, t, m-1, n);
    }
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m < n)
            return 0;
        for(int[] row : dp)
            Arrays.fill(row, -1);
        return solve(s, t, m, n);
    }
}
