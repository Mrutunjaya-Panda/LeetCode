class Solution {
    // Essentially, after sorting the array:

    // We calculate the product of the three largest positive numbers, and
    // The product of the two smallest negative numbers and the largest positive number, respectively.
    // The maximum value between the two is the answer.
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int prodNegPos = nums[0]*nums[1]*nums[n-1];
        int prodPos = nums[n-3]*nums[n-2]*nums[n-1];
        return Math.max(prodNegPos, prodPos);
    }
}
// Time Complexity: O(nlogn)
// Space Complexity: O(1)−O(logn)