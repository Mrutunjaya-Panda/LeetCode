class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        //low is always 0, the best case, since we can alsways make 2 elems equal.
        int mini = Integer.MAX_VALUE;

        //case 1: change max elem and min elem to thier adjacents. // ek bada + ek chhota hatao
        mini = Math.min(mini, nums[n-2]-nums[1]);

        //case 2: change last 2 max elems to the elem at the mid.
        mini = Math.min(mini, nums[n-3]-nums[0]);

        //case 3: change first 2 elems to the mid.
        mini = Math.min(mini, nums[n-1]-nums[2]);

        return mini;
    }
}
//T.C:- O(nlogn)