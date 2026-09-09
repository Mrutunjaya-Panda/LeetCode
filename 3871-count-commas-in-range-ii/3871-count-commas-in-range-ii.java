class Solution {
    public long countCommas(long n) {
        long cnt = 0;
        long lower = 1000;
        long comma = 1;
        //simplified counting simulation
        while(lower <= n){
            // long upper = lower*1000 - 1;
            // //safety check
            // if(upper > n) upper = n;

            // cnt += comma*(upper - lower + 1);
            // lower = upper+1;
            // comma += 1;
            cnt += (n-lower+1);
            lower = lower*1000;
        }
        return cnt;
    }
}

//T.C : O(log1000(n))
//S.C : O(1)