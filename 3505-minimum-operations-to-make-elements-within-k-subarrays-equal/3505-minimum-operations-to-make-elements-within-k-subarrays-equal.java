class Solution {
    //Approach (Sliding Window)
    //T.C : O(n log x + n * k)
    //S.C : O(n*k)
    int n,x,k;

    long[] minOpsSubarray;
    // minOpsSubarray[i] = min operations required to make all elements of subarray[i...i+x-1] equal
    static final long INF = (long) 1e16; // 10^16

    long[][] memo;
    public long solve(int cnt, int i){
        //Base Case.
        if(cnt == k){
            return 0;//minimum k subarrays done.
        }

        //the no. of subarrays required.
        int subarraysNeeded = k - cnt;
        int elementsNeeded = (x*subarraysNeeded);
        int lastIdx = i+elementsNeeded-1;
        if (lastIdx >= n) {
            return INF;
        }

        if(memo[cnt][i] != -1){
            return memo[cnt][i];
        }
        // Took ith index as start of a subarray
        long res = minOpsSubarray[i] + solve(cnt+1,i+x);

        // Skip index i
        res = Math.min(res, solve(cnt, i + 1));

        return memo[cnt][i] = res;
    }

    // ---- multiset helpers (TreeMap<value, frequency>) ----
    private void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int freq = map.get(val);
        if (freq == 1) map.remove(val);
        else map.put(val, freq - 1);
    }

    public long minOperations(int[] nums, int x, int k) {
        this.n = nums.length;
        this.x = x;
        this.k = k;

        minOpsSubarray = new long[n];
        memo = new long[k + 1][n];
        for (long[] row : memo) Arrays.fill(row, -1);

        //TreeMap is like ordered map/sorted map.
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;          // TreeMap.size() counts distinct keys, so track sizes manually
        long leftSum = 0, rightSum = 0;

        int i = 0;
        int j = 0;

        //first preprocessing, to get the equal subarray formation ops of size x.
        while(j<n){
            if(leftSize <= rightSize){
                //add in the left subarray with val = nums[j]
                add(left,nums[j]);
                leftSize++;
                leftSum += nums[j];
            }else{
                add(right, nums[j]);
                rightSize++;
                rightSum += nums[j];
            }

            // Balancing:- i.e if positioning of elems are still not sorted/ordered.
            if (leftSize > 0 && rightSize > 0) {
                int maxLeft  = left.lastKey();
                int minRight = right.firstKey();

                if (maxLeft > minRight) {
                    remove(left, maxLeft);
                    remove(right, minRight);

                    add(left, minRight);
                    add(right, maxLeft);

                    leftSum  += minRight - maxLeft;
                    rightSum += maxLeft - minRight;
                }
            }

            // If we have found the subarray of size x
            if(j-i+1 == x){
                int median = left.lastKey();

                // minOpsSubarray[i] = (left.size()*median - leftSum) + (rightSum - right.size()*median);//will fail give error.
                minOpsSubarray[i] = (leftSize*median - leftSum) + (rightSum - rightSize*median);

                if(left.containsKey(nums[i])){
                    remove(left,nums[i]);
                    leftSize--;
                    leftSum = leftSum - nums[i];
                }else {
                    remove(right, nums[i]);
                    rightSize--;
                    rightSum -= nums[i];
                }
                i++;
            }
            j++;
        }
        return solve(0, 0); // recursion
    }
}