class Solution {
    private static final long MOD = (long)1e9+7;

    // public long ncr(int n,long r){
    //     if (r == 0) return 1;
    //     long numerator = n;
    //     long originalR = r;
    //     while(r > 1){
    //         numerator *= (n-1);
    //         n = n-1;
    //         r--;
    //     }

    //     long denominator = 1;
    //     while(originalR > 0){
    //         denominator *= originalR;
    //         originalR--;
    //     }

    //     return numerator/denominator;
    // }
    public long ncr(int n, long r) {
        long originalR = r;

        long numerator = 1;
        for (int i = 0; i < originalR; i++) {
            numerator *= (n - i);
        }

        long denominator = 1;
        for (int i = 1; i <= originalR; i++) {
            denominator *= i;
        }

        return (numerator / denominator)%MOD;
    }

    //now its time for pascals triangle implementation to find ncr.
    private long[][] PT;
    public long solve(List<Integer> nums){
        int n = nums.size();

        if(n < 3){
            return 1;
        }
        List<Integer> leftA = new ArrayList<>();
        List<Integer> rightA = new ArrayList<>();

        int root = nums.get(0);
        for(int i=1;i<n;i++){
            if(nums.get(i) < root){
                leftA.add(nums.get(i));
            }else{
                rightA.add(nums.get(i));
            }
        }

        //now by recursion we will get answer from left and right.
        long leftAns = solve(leftA);
        long rightAns = solve(rightA);

        //for current node
        // long z = ncr(n-1,leftA.size());

        long z = PT[n-1][leftA.size()];

        //return total ways.
        return (z*((leftAns*rightAns)%MOD))%MOD;
    }
    public int numOfWays(int[] nums) {
        int n = nums.length;

        List<Integer> numsList = new ArrayList<>();
        for(int num : nums){
            numsList.add(num);
        }
        

        //build pascals 2d matrix.
        //T.C for pascal's triangle:- O(n^2)
        PT = new long[n+1][];
        for(int i=0;i<n;i++){
            PT[i] = new long[i+1];
            //initially fill every valid cell of pascals triangle with 1.
            for(int j=0;j<=i;j++){
                PT[i][j] = 1;
            }

            //then update
            for(int j=1;j<i;j++){
                PT[i][j] = (PT[i-1][j] + PT[i-1][j-1])%MOD;
            }
        }
        
        long ans = solve(numsList);
        //System.out.println("solve result = " + ans);
        //return (int)solve(numsList)-1;//because original array would have been counted twice.
        return (int) ((ans - 1 + MOD) % MOD);
    }
}
//T.C:- O(n^2) Recursive BST processing: \(O(n^2)\) worst case, because each recursive call partitions its current list.
//S.C:- O(n^2) + O(n)