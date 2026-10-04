class Solution {
    int n;
    //memoize
    int[][] dp;
    public boolean solve(int idx, int open, String s){
        //base case
        if(idx == n){
            return open==0;
        }
        boolean isValid = false;

        if(dp[idx][open] != -1) return (dp[idx][open] == 1) ? true : false;
        if(s.charAt(idx) == '('){
            isValid |= solve(idx+1,open+1,s);
        }else if(s.charAt(idx) == '*'){
            //then we have 3 options.
            //1. * -> '('
            isValid |= solve(idx+1,open+1,s);
            
            //2. * -> ''
            isValid |= solve(idx+1,open,s);
            //3. * -> ')'
            //safety check
            //because if -ve it cannot be valid further at any cost i.e impossible.
            if(open > 0){
                isValid |= solve(idx+1,open-1,s);
            }
        }else{
            // ')'
            if(open > 0){
                isValid |= solve(idx+1,open-1,s);
            }
        }

        if(isValid){
            dp[idx][open] = 1;
        }else{
            dp[idx][open] = 0;
        }
        
        return isValid;
    }
    public boolean checkValidString(String s) {
        this.n = s.length();
        dp = new int[101][101];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }
        return solve(0,0,s);
    }
}

//T.C:- O(n)