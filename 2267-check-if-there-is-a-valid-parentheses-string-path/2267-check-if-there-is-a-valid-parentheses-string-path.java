class Solution {
    //memoize
    int[][][] dp;
    public boolean solve(int i,int j,int openCnt,char[][] grid,int n,int m){
        if(i >= n || j >= m){
            //invalid or outofbound path
            return false;
        }

        if(grid[i][j] == '('){
            openCnt++;
        }else{
            openCnt--;
        }

        if(openCnt < 0){
            //impossible to form a valid parenthesis path.
            return false;
        }

        if(i == n-1 && j == m-1){
            //reached to destination cell, but check if it is a valid= parenthes path or not=.
            return (openCnt == 0) ? true:false;
        }

        if(dp[i][j][openCnt] != -1) return (dp[i][j][openCnt] == 1)?true : false;

        //paths
        boolean right = solve(i,j+1,openCnt,grid,n,m);
        boolean down = solve(i+1,j,openCnt,grid,n,m);

        if(right || down){
            dp[i][j][openCnt] = 1;
            return true;
        }

        //couldn't find any valid path.
        dp[i][j][openCnt] = 0;
        return false;
    }
    public boolean hasValidPath(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[0][0] == ')') return false;

        //also if parenthesis length is odd the also never possible to balance
        if((m+n-1) %2 != 0) return false;

        dp = new int[101][101][201];//max openCnt can be m+n-1;
        for(int[][] Twod : dp){
            for(int[] oned: Twod){
                Arrays.fill(oned,-1);
            }
        }
        return solve(0,0,0,grid,n,m);
    }
}

//T.C:- O(n*m)