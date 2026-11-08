class Solution {
    // Approach - 2 (Better Approach)
    // T.C : O(m*n)
    // S.C : O(m+n)
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //do preprocessing first to know the cnt of servers in each row and 
        //each col.
        int[] indexRowCnt = new int[m];
        int[] indexColCnt = new int[n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int r=i;
                int c=j;
                if(grid[i][j] == 1){
                    indexRowCnt[r] += 1;
                    indexColCnt[c] += 1;
                }
            }
        }

        int resServers=0;
        //now do traverse..
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int r=i;
                int c=j;
                if(grid[i][j] == 1 && (indexRowCnt[r] > 1 || indexColCnt[c] > 1)){
                    resServers++;
                }
            }
        }
        return resServers;
    }
}