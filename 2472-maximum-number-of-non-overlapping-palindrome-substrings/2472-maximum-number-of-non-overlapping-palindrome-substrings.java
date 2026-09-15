class Solution {
    public boolean isPalindrome(String s,int st,int e){
        if(st>e) return false;
        while(st<=e){
            if(s.charAt(st) == s.charAt(e)){
                st++;
                e--;
            }else{
                return false;
            }
        }
        return true;
    }
    // public boolean isPalindrome(String s, int i, int j) {
    //     while (i < j) {
    //         if (s.charAt(i++) != s.charAt(j--)) return false;
    //     }
    //     return true;
    // }
    //let's memoize this.
    int[][] dp;
    public int solve(String s,int k,int i,int j){
        //base case, out-of-bound.
        if(j >= s.length() || i >= s.length()) return 0;

        if(dp[i][j] != -1) return dp[i][j];
        if(isPalindrome(s,i,j)){
            //possibilities
            //it is palindrome & take, try on next non-overlapping substring.
            int take = 1 + solve(s,k,j+1,j+k);
            //first extend current substring as len can be >= k.
            int grow = solve(s,k,i,j+1);
            //else just slide, with the smae length i.e =k
            int slide = solve(s,k,i+1,j+1);

            return dp[i][j] = Math.max(take, Math.max(grow,slide));
        }
        //else if the current is not a palindrome, we basically have two options.
        int grow = solve(s,k,i,j+1);
        //else just slide, with the smae length i.e =k
        int slide = solve(s,k,i+1,j+1);
        return dp[i][j] = Math.max(grow,slide);
    }
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if(k == 1) return n;//because for 1 len every char can be palindrome & since we needed maximum, therefore returned n.
        dp = new int[n][n];
        for(int[] row: dp){
            Arrays.fill(row,-1);
        }

        return solve(s,k,0,k-1);
    }
}
//T.C : O(n^3)
//S.C : O(n^2)