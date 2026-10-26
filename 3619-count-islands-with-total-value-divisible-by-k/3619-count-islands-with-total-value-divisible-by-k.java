class Solution {
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;
    public long dfs(int[][] grid,int r,int c){

        //first add the current cells sum
        long sum = grid[r][c];
        //mark the current cell as visited by marking it as 0;
        grid[r][c] = 0;

        for(int[] d : dir){
            int new_r = r + d[0];
            int new_c = c + d[1];

            if(new_r >= 0 && new_r < m && new_c >= 0 && new_c < n && grid[new_r][new_c] > 0){
                sum += dfs(grid,new_r,new_c);
            }
        }
        return sum;
    }
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int cnt=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] > 0){
                    //it is land.
                    long val = dfs(grid,i,j);
                    if(val%k == 0){
                        cnt += 1;
                    }
                }
            }
        }
        return cnt;
    }
}
//Approach-1 (Simple DFS)
//T.C : O(m*n)
//S.C : O(1)