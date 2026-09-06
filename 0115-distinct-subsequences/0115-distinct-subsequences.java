//Approach-2 (Bottom UP DP)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        
        long[][] dp = new long[m+1][n+1];
        
        for(int row = 0; row<m+1; row++) {
            dp[row][0] = 1;
        }
        
        for(int col = 1; col<n+1; col++) {
            dp[0][col] = 0;
        }
        
        for(int i = 1; i<m+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if(s.charAt(i-1) == t.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        
        
        return (int) dp[m][n];
    }
}