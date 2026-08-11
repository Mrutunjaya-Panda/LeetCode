class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        Set<Integer> s = new HashSet<>(n);

        for(int num:nums){
            s.add(num);
        }

        //int total = 0;
        int total = nums[0];
        for(int i=1;i<n;i++){
            if(nums[i] == nums[i-1]+1){
                //then sequential
                total += nums[i];
            }else{
                break;
            }
        }

        while(s.contains(total)){
            total += 1;
        }

        return total;
    }
}