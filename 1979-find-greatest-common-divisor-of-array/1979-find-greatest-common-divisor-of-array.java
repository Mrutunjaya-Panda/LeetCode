class Solution {
    public int gcd(int a,int b){
        if(a == 0) return b;
        if(b == 0) return a;

        return gcd(b,a%b);
    }
    public int findGCD(int[] nums) {
        //continue finding GCD on the fly.
        int n = nums.length;
        int mx = Integer.MIN_VALUE;
        int mn = Integer.MAX_VALUE;
        for (int num : nums) {
            mn = Math.min(mn, num);
            mx = Math.max(mx, num);
        }

        return gcd(mx,mn);
    }
}