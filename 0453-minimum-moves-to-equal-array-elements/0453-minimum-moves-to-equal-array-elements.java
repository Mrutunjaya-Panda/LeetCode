class Solution {
    public int minMoves(int[] nums) {
        //focus on maxEle. each time and bring it to the minElem, in that way we will 
        //focus on only 1 element.
        int n = nums.length;
        int minEle = nums[0];
        for(int i=1;i<n;i++){
            minEle = Math.min(minEle,nums[i]);
        }

        //now calculate the gap for each elem. w.r.t minEle
        int ops=0;
        for(int i=0;i<n;i++){
            int gap = nums[i] - minEle;
            ops += gap;
        }

        return ops;
    }
}

//T.C:- O(n);