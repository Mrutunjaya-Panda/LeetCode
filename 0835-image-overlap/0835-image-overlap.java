class Solution {
    public int countOne(int[][] a, int[][] b,int ro,int co){
        int n = a.length;
        int cnt =0;
        //iterate over a matrix and find valid b's -> then find cnt 1's
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int b_rowIdx = i + ro;
                int b_colIdx = j + co;
                if(b_rowIdx < 0 || b_rowIdx >= n || b_colIdx < 0 || b_colIdx >= n){
                    //means the current cell of a matrix is not overlapping with b matrix.
                    continue;
                }
                //else overlapping, check if both cells have 1 i.e the overlapping cells.
                if(a[i][j] == 1 && b[b_rowIdx][b_colIdx] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxi = 0;
        //for each row_offset
        for(int row_offset=-n+1;row_offset<n;row_offset++){
            //we have that many col_offset.
            for(int col_offset=-n+1;col_offset<n;col_offset++){
                maxi = Math.max(countOne(img1,img2,row_offset,col_offset),maxi);
            }
        }
        return maxi;//return the overlap with maximum 1 overlap count.
    }
}

//T.C:- O(n^4)
//S.C:- O(1)