class Solution {
    //optimal approach
    int MOD = (int)1e9+7;
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;

        int[] pre = new int[n];
        pre[0] = arr[0];

        for(int i=1;i<n;i++){
            pre[i] = pre[i-1] + arr[i];
        }

        int oddCnt=0;
        int evenCnt=1;// for prefixSum = 0;

        int cnt=0;
        for(int i=0;i<n;i++){
            if(pre[i] % 2 == 0){
                //Structure:- odd +even
                cnt = (cnt + oddCnt)%MOD;
                evenCnt += 1;
            }else{
                //Structure:- even + odd
                cnt = (cnt + evenCnt)%MOD;
                oddCnt += 1;
            }
        }
        return cnt;
    }
}

//T.C:- O(n)
//S.C:- O(n)