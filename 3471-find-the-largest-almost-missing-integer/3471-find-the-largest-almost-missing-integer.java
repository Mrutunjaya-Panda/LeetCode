class Solution {
    //It is simply dividing into 3 cases
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if(k == n){
            int res = nums[0];
            //only one subarray possible so return the largest elem.
            for(int x:nums){
                res = Math.max(res,x);
            }
            return res;
        }

        int cnt[] = new int[51];
        for(int num:nums){
            cnt[num] = cnt[num] + 1;
        }
        if(k == 1){
            //n possible subarrays
            //we need to return the largest one and which appears only in one subarray
            for(int i=50;i>=0;i--){
                if(cnt[i] == 1){
                    return i;
                }
            }
            return -1;
        }

        //now for case 1 < k < n
        //the middle elements will atleast appear twice since it can occur in atleast
        //two sliding windows
        //So possible candidates in only first and the last elem.

        int res = -1;
        if(cnt[nums[0]] == 1){
            res = Math.max(res,nums[0]);
        }

        if(cnt[nums[n-1]] == 1){
            res = Math.max(res,nums[n-1]);
        }

        return res;
    }
}