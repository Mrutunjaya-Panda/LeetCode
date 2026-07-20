class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        //flatten the whole array into single 1D array.
        int m = grid.length;
        int n = grid[0].length;

        int total = m*n;
        k = k%total;
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=0;i<m;i++){
            List<Integer> curr = new ArrayList<>();
            for(int j=0;j<n;j++){
                curr.add(0);//initialize each row with 0's
            }
            ans.add(curr);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int oldIdx = i*n + j;//idx in an flattened array.
                int newIdx = (oldIdx + k)%total;//modulo so that it doesn't go out of bound.

                //now convert this newIdx to Idx in 2D grid.
                int newRow = newIdx/n;
                int newCol = newIdx%n;

                ans.get(newRow).set(newCol,grid[i][j]);
            }
        }
        return ans;
    }
}