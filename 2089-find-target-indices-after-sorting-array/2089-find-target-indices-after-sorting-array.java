class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int[] A = nums.clone();
        Arrays.sort(A);
        for(int i=0;i<n;i++){
            if(A[i] == target){
                res.add(i);
            }
        }
        return res;
    }
}