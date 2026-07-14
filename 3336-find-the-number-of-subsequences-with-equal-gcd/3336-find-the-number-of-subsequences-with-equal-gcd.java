class Solution {

    private static final int MOD = 1_000_000_007;
    private int[][][] dp;

    public int subsequencePairCount(int[] nums) {

        int n = nums.length;

        dp = new int[n][201][201];

        for (int i = 0; i < n; i++) {
            for (int g1 = 0; g1 <= 200; g1++) {
                Arrays.fill(dp[i][g1], -1);
            }
        }

        return solve(nums, 0, 0, 0);
    }

    private int solve(int[] nums, int idx, int g1, int g2) {

        if (idx == nums.length) {
            return (g1 != 0 && g2 != 0 && g1 == g2) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1) {
            return dp[idx][g1][g2];
        }

        long ans = 0;

        // skip
        ans += solve(nums, idx + 1, g1, g2);

        // put in first subsequence
        ans += solve(
                nums,
                idx + 1,
                gcd(g1, nums[idx]),
                g2
        );

        // put in second subsequence
        ans += solve(
                nums,
                idx + 1,
                g1,
                gcd(g2, nums[idx])
        );

        ans %= MOD;

        return dp[idx][g1][g2] = (int) ans;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}