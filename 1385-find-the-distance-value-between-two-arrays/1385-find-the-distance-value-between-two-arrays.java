class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int m = arr1.length;
        int n = arr2.length;

        //1. sort array2.
        Arrays.sort(arr2);

        int cnt=0;
        //2. find lower_bound using Binary search for each elem of arr1..
        for(int i=0;i<m;i++){
            //get the index of lower_bound in arr2.
            int l = lowerBound(arr2, arr1[i]);

            //first 2 are edge cases.
            if(l > n-1){
                //means all elems in a2 are smaller than elems in a1[i].
                //so check the last elem. diff.
                if(Math.abs(arr2[n-1] - arr1[i]) > d){
                    cnt++;
                }
            }else if(l == 0){
                //all elems in a2 are greater or equal than a1[i].
                if (Math.abs(arr2[0] - arr1[i]) > d) cnt++;
            }else{
                if(arr2[l] - arr1[i] > d && arr1[i] - arr2[l-1] > d) cnt++; 
            }

        }
        return cnt;
    }

    // first index with arr[idx] >= target (n if none)
    private int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}

//Approach-2 (Binary Search - lower_bound)
//T.C : O(mlogn)
//S.C : O(1)