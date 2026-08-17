class Solution {
    int[] prefSum;
    //solve from Alice's perspective
    public int solve(int[] stoneValue,int l,int r){
        if(l == r){
            //single stone remaining, no further division is possible.
            return 0;//no partitions possible
        }
        //there are n-2 partitions possible as we have to divide them into two non-empty 
        //arrays.
        int score = Integer.MIN_VALUE;
        for(int mid=l;mid<r;mid++){
            //for the current partition
            // if(l==0){

            // }
            int leftSum = prefSum[mid] - (l==0 ? 0 : prefSum[l-1]); //sum from l to mid.
            int rightSum = prefSum[r] - prefSum[mid]; //sum from mid+1 to r.

            if(leftSum < rightSum){
                //bob will remove the rightSum and alice will take leftSum
                //alice score
                score = Math.max(score, leftSum + solve(stoneValue,l,mid));
            }else if(leftSum > rightSum){
                score = Math.max(score, rightSum + solve(stoneValue,mid+1,r));
            }else{
                //we have to check both options and pick the maximum one.
                //remove rightSum
                int option1 = leftSum + solve(stoneValue,l,mid);
                //remove leftSum
                int option2 = rightSum + solve(stoneValue,mid+1,r);

                score = Math.max(score, Math.max(option1,option2));
            }
        }

        //return the maximum possible score for alice
        return score;
    }

    //memoized
    int[][] dp;
    public int solveM(int[] stoneValue,int l,int r){
        if(l == r){
            //single stone remaining, no further division is possible.
            return 0;//no partitions possible
        }

        if(dp[l][r] != -1) return dp[l][r];
        //there are n-2 partitions possible as we have to divide them into two non-empty 
        //arrays.
        int score = Integer.MIN_VALUE;
        for(int mid=l;mid<r;mid++){
            //for the current partition
            // if(l==0){

            // }
            int leftSum = prefSum[mid] - (l==0 ? 0 : prefSum[l-1]); //sum from l to mid.
            int rightSum = prefSum[r] - prefSum[mid]; //sum from mid+1 to r.

            if(leftSum < rightSum){
                //bob will remove the rightSum and alice will take leftSum
                //alice score
                score = Math.max(score, leftSum + solveM(stoneValue,l,mid));
                dp[l][r] = score;
            }else if(leftSum > rightSum){
                score = Math.max(score, rightSum + solveM(stoneValue,mid+1,r));
                dp[l][r] = score;
            }else{
                //we have to check both options and pick the maximum one.
                //remove rightSum
                int option1 = leftSum + solveM(stoneValue,l,mid);
                //remove leftSum
                int option2 = rightSum + solveM(stoneValue,mid+1,r);

                score = Math.max(score, Math.max(option1,option2));
                dp[l][r] = score;
            }
        }

        //return the maximum possible score for alice
        return score;
    }
    
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int l =0,r=n-1;

        dp = new int[n][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }

        prefSum = new int[n];
        //prefSum[-1] = 0;//edge case when l-1 becomes -1.
        int sum=0;
        for(int i=0;i<n;i++){
            sum = sum+stoneValue[i];
            prefSum[i] = sum;
        }
        return solveM(stoneValue,l,r);
    }
}

//T.C:- (3^n)
//for memoized T.C:- O(n^3)