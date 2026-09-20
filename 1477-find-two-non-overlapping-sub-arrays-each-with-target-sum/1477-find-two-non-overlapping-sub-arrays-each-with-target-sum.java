class Solution {
    //SLIDING WINDOW + NON-OVERLAPPING.
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int i=0,j=0;

        int currSum=0;
        // int currLen=0;
        int[] minBestLenTillIdx = new int[n];
        Arrays.fill(minBestLenTillIdx, Integer.MAX_VALUE);
        int bestLen = Integer.MAX_VALUE;//best min. len till now

        int res = Integer.MAX_VALUE;
        while(j<n){
            currSum += arr[j];
            // currLen = j-i+1;

            while(i<j && currSum > target){
                currSum -= arr[i];
                i++;
            }

            if(currSum == target){
                int currLen = j-i+1;
                //since we need two non-overlapping subarrays with each subarray sum = target.
                //each subarray with minLen which is tracked by minBestLenTillIdx[] array.
                if(i>0 && minBestLenTillIdx[i-1] != Integer.MAX_VALUE){
                    res = Math.min(res, currLen+minBestLenTillIdx[i-1]);
                }

                //on the fly find best Min. len of the subarray whose sum = target
                bestLen = Math.min(bestLen,currLen);
            }

            //extend window
            minBestLenTillIdx[j] = bestLen;
            j++;
        }
        return (res == Integer.MAX_VALUE) ? -1:res;
    }
}

//T.C:- O(2.n)
//S.C:- O(n)