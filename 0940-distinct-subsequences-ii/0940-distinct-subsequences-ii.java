class Solution {
    int M = 1000000007;
    int[] dp = new int[2001];
    int[] prev; //prev[n] = last time when we saw this nth character (1-based indexing)

    //memoize
    public int solve(int n){
        if(n == 0){
            return 1;//empty subsequence
        }
        if(dp[n] != -1) return dp[n];

        int total = (int)(2L*solve(n-1)%M);
        //remove duplicates
        if(prev[n] != 0){
            int duplicates = solve(prev[n] - 1);
            total = (total - duplicates+M)%M;
        }
        return dp[n] = total;
    }
    public int distinctSubseqII(String s) {
        int n = s.length();
        Arrays.fill(dp,-1);
        prev = new int[n+1];//1-based indexing
        int lastSeen[] = new int[26];
        //form prev array
        for(int i=1;i<=n;i++){
            int charIdx = s.charAt(i-1) - 'a';
            prev[i] = lastSeen[charIdx];
            lastSeen[charIdx] = i;
        }

        return (solve(n) - 1+M)%M;//removing empty subsequence
    }
}