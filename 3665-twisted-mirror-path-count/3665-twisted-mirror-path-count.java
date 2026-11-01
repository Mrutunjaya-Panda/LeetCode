class Solution {
    int left=0;
    int up=1;
    int m;
    int n;
    int MOD = (int)(1e9+7);
    //int tot = 0;
    //let's memoize
    int[][][] dp;
    public int solve(int[][] grid,int i,int j,int cameFrom){
        if(i >= m || j >= n){
            return 0;
        }

        if(i == m-1 && j == n-1){
            return 1;
        }

        if(dp[i][j][cameFrom] != -1){
            return dp[i][j][cameFrom];
        }

        int tot = 0;
        if(grid[i][j] == 0){
            //we can move right or down.
            tot += solve(grid,i,j+1,left);
            tot += solve(grid,i+1,j,up);
            tot %= MOD;
        }

        if(grid[i][j] == 1){
            //check from where it came from
            //mirror only deflects
            if(cameFrom == left){
                tot += solve(grid,i+1,j,up);
            }else{
                tot += solve(grid,i,j+1,left);
            }
        }
        dp[i][j][cameFrom] = tot%MOD;

        return dp[i][j][cameFrom];
    }
    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n][2];
        for (int[][] row : dp) {
            for (int[] cell : row) {
                Arrays.fill(cell, -1);
            }
        }
        return solve(grid,0,0,0);
    }
}

//T.C:- O(2^(m+n))
//T.C:- O(m+n)