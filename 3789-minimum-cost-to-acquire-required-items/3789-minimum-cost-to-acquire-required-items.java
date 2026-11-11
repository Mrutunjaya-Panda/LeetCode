class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long tot=0;

        //greedily we will solve this, although we can solve using recursion.

        if(cost1+cost2 <= costBoth){
            //take independently.
            tot = (long)need1*cost1+(long)need2*cost2;
            return tot;
        }

        // else{
            //take costBoth min. of need1 & need2.
            long cntBoth = Math.min(need1,need2);
            tot += cntBoth*(long)costBoth;
            need1 -= (int)cntBoth;
            need2 -= (int)cntBoth;
            //for next remainining.
            if(need1 > 0){
                tot += (long)need1*(long)Math.min(costBoth,cost1);
            }else{
                tot += (long)need2*(long)Math.min(costBoth,cost2);
            }
        // }
        return tot;
    }
}