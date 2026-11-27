class Solution {
    //Approach-1 (Recursion Memo)
    //T.C : O(n^2 + n*budget)
    //S.C : O(n + n*budget)
    int cheapestPrice;
    int[][] dp;
    public int solve(int n,int[][] items, int budget,int[] copies){
        if(budget <= 0){
            // we can't take any path from here just return 
            return 0;
        }
        if(n == 0){
            //means we explored each item but still budget left, we can take chepest elem, multiple times.
            return budget/cheapestPrice;
        }

        if(dp[n-1][budget] != -1) return dp[n-1][budget];

        //recurse.
        int take = 0;
        int price = items[n - 1][1];
        if(price <= budget){
            //then only we can take that item.
            take = 1+copies[n-1]+solve(n-1,items,budget-price,copies);
        }

        //skip
        int skip = solve(n-1,items,budget,copies);

        return dp[n-1][budget] = Math.max(take,skip);
    }
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        cheapestPrice = Integer.MAX_VALUE;
        // for(var item : items){
        //     cheapestPrice = Math.min(cheapestPrice,item[1]);
        // }

        int[] copies = new int[n]; //copies[i] = free copies if I buy ith item
        for (int i = 0; i < n; i++) {
            cheapestPrice = Math.min(cheapestPrice,items[i][1]);

            int copy = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (items[j][0] % items[i][0] == 0)
                    copy++;
            }

            copies[i] = copy;
        }

        dp = new int[n][budget+1];

        for(int[] row:dp){
            Arrays.fill(row,-1);
        }

        return solve(n,items,budget,copies);
    }
}