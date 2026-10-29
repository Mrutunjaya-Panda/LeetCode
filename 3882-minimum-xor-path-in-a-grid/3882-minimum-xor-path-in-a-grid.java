class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //we will use BitSet DS.
        //BitSet is internally an array of fixed size. 
        BitSet[][] dp = new BitSet[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j] = new BitSet(1024);
            }
        }

        //initial cell (0,0) set it with grid[0][0] bit.
        dp[0][0].set(grid[0][0]);

        //now traverse
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) continue;

                BitSet up = new BitSet(1024);
                BitSet left = new BitSet(1024);

                if(i > 0){
                    up = (BitSet)dp[i-1][j].clone();
                }
                if(j > 0){
                    left = (BitSet)dp[i][j-1].clone();
                }

                //now take OR of both to avoid redundant calculations.
                // BitSet combined = new BitSet(1024);
                // for(int x=0;x<1024;x++){
                //     if(up.get(x) || left.get(x)){
                //         combined.set(x);
                //     }
                // }
                //another way.
                BitSet combined = (BitSet) up.clone();
                combined.or(left);

                //now xoring each combined set cell with grid[i][j] to find BitSet of grid[i][j].
                for(int xorV=0;xorV<1024;xorV++){
                    if(combined.get(xorV)){
                        //take xor.
                        int resXorV = xorV ^ grid[i][j];
                        dp[i][j].set(resXorV);
                    }
                }
            }
        }
        //now again traverse with (m-1,n-1)'s bitmask and return the lowest xor val i.e set to 1.
        for(int xorV=0;xorV<1024;xorV++){
            if(dp[m-1][n-1].get(xorV)){
                return xorV;
            }
        }
        return -1;
    }
}

//Approach (Bits Xor Property and Grid DP format)
//T.C : O(m*n)
//S.C : O(m*n)