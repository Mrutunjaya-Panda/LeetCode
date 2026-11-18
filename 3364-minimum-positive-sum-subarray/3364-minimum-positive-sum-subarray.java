//Approach-2 (Improved Brute Force)
//T.C : O(n*(r-l+1))
//S.C : O(n)
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();

        int[] prefixSum = new int[n];

        prefixSum[0] = nums.get(0);
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums.get(i);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {

            for (int len = l; len <= r && i + len <= n; len++) {
                int j = i + len - 1;

                int sum = prefixSum[j] - (i > 0 ? prefixSum[i - 1] : 0);

                if (sum > 0)
                    result = Math.min(result, sum);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}