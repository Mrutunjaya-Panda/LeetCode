class Solution {
    int n;
    int m;
    public boolean dfs(int[][] grid, int r, int c){
        if(r<0 || r>=n || c<0 || c>=m){
            //island is not possible
            return false;
        }

        if(grid[r][c] == 1){
            return true; //closed here
        }
        //mark the current cell as visited by marking it 1.
        grid[r][c] = 1;

        //explore all directions
        boolean left = dfs(grid,r,c-1);
        boolean right = dfs(grid, r, c + 1);
        boolean up = dfs(grid, r - 1, c);
        boolean down  = dfs(grid, r + 1, c);
        return left && right && up && down;
    }
    public int closedIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == 0){
                    cnt += (dfs(grid,i,j) == true ? 1:0);
                }
            }
        }
        return cnt;
    }
}