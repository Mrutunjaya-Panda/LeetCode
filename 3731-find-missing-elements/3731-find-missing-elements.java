class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int n = nums.length;
        Set<Integer>set = new HashSet<>();
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            set.add(nums[i]);
            largest = Math.max(largest,nums[i]);
            smallest = Math.min(smallest,nums[i]);
        }

        List<Integer> res = new ArrayList<>();
        for(int r=smallest;r<=largest;r++){
            if(!set.contains(r)){
                res.add(r);
            }
        }

        return res;
    }
}

//Let n be the length of the array nums, and let D be the difference between the maximum and minimum elements in nums.

//Time complexity: O(D+n).
//S.C:- O(n)