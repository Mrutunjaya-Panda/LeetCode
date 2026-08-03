class Solution {
    //let us represent Alice A = 1, and Bob b = 0.
    int n;
    //T.C:- O(2ⁿ), S.C:- O(n)
    public int solveAlice(int[] piles, int person, int idx,int m){
        //base case
        if(idx >= n) return 0; // empty or exhausted

        int currStoneVal = 0;
        int result = (person == 1) ? -1 : Integer.MAX_VALUE;
        //exploring possingle cases for taking piles for the particular person.
        for(int x=1;x<= Math.min(2*m,n-idx);x++){
            currStoneVal += piles[idx+x-1];
            if(person == 1){
                //game strategey, choose best for yourself
                result = Math.max(result,currStoneVal + solveAlice(piles,0,idx+x,Math.max(m,x)));
                //next starting index for that person = idx+x.
            }else{
                //Bob choosing means take no stone and expect min. from bobs end since
                //we are solving for Alice and both plays optimally.
                result = Math.min(result,solveAlice(piles,1,idx+x,Math.max(m,x)));
            }
        }
        return result;
    }

    //memoized
    //T.C:- no. of states*work done per state i.e O(2*n^2)*O(n) i.e O(n^3)
    //S.C:- O(n^2) + O(n)//recursion stack space
    static int[][][] dp;
    public int solveAliceM(int[] piles, int person, int idx,int m){
        //base case
        if(idx >= n) return 0; // empty or exhausted

        if(dp[person][idx][m] != -1) return dp[person][idx][m];
        int currStoneVal = 0;
        int result = (person == 1) ? -1 : Integer.MAX_VALUE;
        //exploring possingle cases for taking piles for the particular person.
        for(int x=1;x<= Math.min(2*m,n-idx);x++){
            currStoneVal += piles[idx+x-1];
            if(person == 1){
                //game strategey, choose best for yourself
                result = Math.max(result,currStoneVal + solveAliceM(piles,0,idx+x,Math.max(m,x)));
                //next starting index for that person = idx+x.
            }else{
                //Bob choosing means take no stone and expect min. from bobs end since
                //we are solving for Alice and both plays optimally.
                result = Math.min(result,solveAliceM(piles,1,idx+x,Math.max(m,x)));
            }
        }
        //store and return
        return dp[person][idx][m] = result;
    }

    public int stoneGameII(int[] piles) {
        int m = 1;
        n = piles.length;
        dp = new int[2][n + 1][n + 1];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j <= n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        //initially alice is starting on 0-index.
        int bestAlice = solveAliceM(piles,1,0,m);
        return bestAlice;
    }
}