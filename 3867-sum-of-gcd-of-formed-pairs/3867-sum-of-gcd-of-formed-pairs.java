class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;

        int[] mx = new int[n];
        int prefixMax = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            prefixMax = Math.max(nums[i],prefixMax);
            mx[i] = prefixMax;
        }

        //for prefixGcd array.
        int[] prefixGcd = new int[n];
        for(int i=0;i<n;i++){
            prefixGcd[i] = gcd(nums[i],mx[i]);
        }

        //sort
        Arrays.sort(prefixGcd);

        //now pairing and finding gcd_sum_of_pairs
        long ans = 0;
        int left = 0, right = n-1;
        while(left < right){
            ans += gcd(prefixGcd[left],prefixGcd[right]);
            left++;
            right--;
        }
        return ans;
    }

    public int gcd(int a,int b){
        if(a == 0) return b;
        if(b == 0) return a;

        return gcd(b,a%b);
    }
}

// Complexity Analysis
// Let n be the length of the array nums, and let U be the maximum value in nums.

// Time complexity: O(nlogn+nlogU).

// Sorting prefixGcd takes O(nlogn) time. Computing each greatest common divisor takes O(logU) time, and this operation is performed O(n) times.

// Space complexity: O(n).

// Used to store the arrays mx and prefixGcd.