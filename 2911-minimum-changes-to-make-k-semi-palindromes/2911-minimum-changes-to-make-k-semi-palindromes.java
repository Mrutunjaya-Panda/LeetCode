class Solution {
    int n;
    String str;
    public int getCost(int s,int e){
        //find the len, of this current substring.
        int len = e-s+1;
        int minChange = Integer.MAX_VALUE;

        for(int d=1;d<len;d++){
            if(len%d != 0){
                continue;
            }

            //form d no. of groups.
            int cost = 0;
            for(int g=0;g<d;g++){
                //for the current group find its left and right ptr.
                int l = s+g;
                int lettersPerGroup = len/d;
                int r = l+(lettersPerGroup-1)*d;

                //now palindrome check and changeCnt.
                while(l < r){
                    if (str.charAt(l) != str.charAt(r))
                        cost++;

                    l += d;
                    r -= d;
                }
            }
            minChange = Math.min(minChange,cost);
        }
        return minChange;
    }

    //memoize
    int[][] memo;
    public int solve(int s,int k){
        //base case
        if(k==0){
            if(s == n) return 0;//valid partition, cannot be partitioned further
            //else 
            return Integer.MAX_VALUE;//invalid partitioning.
        }

        if(memo[s][k] != -1) return memo[s][k];

        int result = Integer.MAX_VALUE;
        for(int end = s+1;end<n;end++){
            //[start,end] -> 1 piece
            int remCost = solve(end+1,k-1);
            if(remCost == Integer.MAX_VALUE){
                continue;
            }
            int cost = getCost(s,end) + remCost;

            result = Math.min(result,cost);
        }

        return memo[s][k] = result;
    }
    public int minimumChanges(String s, int k) {
        this.n = s.length();
        this.str = s;
        memo = new int[n+1][k+1];
        for(int[] d:memo){
            Arrays.fill(d,-1);
        }
        return solve(0,k);
    }
}