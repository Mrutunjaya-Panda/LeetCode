class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        //find index from where down starts, starting from right side..
        int idx = -1;
        for(int i=n-2;i>=0;i--){
            if(nums[i] < nums[i+1]){
                //first index found, where we can swipe with jsut next greater.
                idx = i;
                break;
            }
        }

        if(idx == -1){
            //just reverse, as it is already in decreasing order.
            reverse(nums,0,n-1);
            return;
        }

        //swipe with just next greater elem.
        for(int i=n-1;i>=idx;i--){
            if(nums[i] > nums[idx]){
                //swap
                int temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        //reverse/sort the suffix.
        //reverse(nums,idx+1,n-1);
        Arrays.sort(nums,idx+1,nums.length);
    }

    public void reverse(int[] nums,int start,int last){
        //reversing in-place.
        while (start < last) {
            int temp = nums[start];
            nums[start] = nums[last];
            nums[last] = temp;
            start++;
            last--;
        }
    }
}
//T.C:- O(n)