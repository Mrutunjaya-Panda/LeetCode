class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        //create a root array with length nums
        //here root will contain component, so that if same component
        //for a node, they will have same value and hence edge exists.
        boolean[] ans = new boolean[queries.length];
        int[] root = new int[n];//n = no. of nodes
        root[0] = 0;
        for(int i=1;i<n;i++){
            //if true, same component -> edge exists
            root[i] = (nums[i] - nums[i-1] <= maxDiff) ? root[i-1] : i;
        }

        for(int i=0;i<queries.length;i++){
            //only true if same component
            ans[i] = (root[queries[i][0]] == root[queries[i][1]]);
        }
        return ans;
    }
}