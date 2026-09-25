class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        //finding all subarrays whose prod%k == x, x is remainder, count remainder x
        //subarrays.
        long[] result = new long[k];
        long[] prevCnt = new long[k];
        for(int i=0;i<n;i++){
            //index i par end hone waale all subarrays
            long[] currCnt = new long[k];
            int currRem = nums[i]%k;
            currCnt[currRem] += 1;

            //now from i's i.e prevCnt find currCnt
            for(int oldRem=0;oldRem<=k-1;oldRem++){
                int newRem = (int)((long)oldRem*nums[i]%k)%k;
                currCnt[newRem] += prevCnt[oldRem];
            }

            prevCnt = currCnt;

            for(int x=0;x<=k-1;x++){
                result[x] += prevCnt[x];
            }
        }
        return result;
    }
}

//T.c:- O(n*k)