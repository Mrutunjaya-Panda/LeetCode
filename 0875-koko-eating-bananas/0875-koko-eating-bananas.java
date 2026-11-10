class Solution {
    public boolean check(int[] piles,int h,int k){
        long tot_hours = 0;
        for(int p: piles){
            //finding ceiling hour
            tot_hours += (p+k-1)/k;
        }
        return tot_hours <= h;
    }
    //here binary serach will be applied on answer i.e no. of bananas he can eat per hour.
    public int minEatingSpeed(int[] piles, int h) {
        //piles mein, p[i] -> represents, no. of bananas he can eat in 1 hr i.e speed.
        // we need min. speed. i.e k should be minimum.

        int low = 1;
        int maxi = piles[0];
        for(int ele:piles){
            maxi = Math.max(maxi,ele);
        }
        int high= maxi;

        while(low<high){
            int mid = low+(high-low)/2;
            boolean res = check(piles,h,mid);
            if(res){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }
}

//T.C:-O(log(maxi))