class Solution {
    //recursive solution
    //see from player 1 perspective i.e you.

    //POV:- when its your turn do choose best,
    //when its your opponents turn:- expect the worst since both are playing optimally.

    //T.C:- O(2^n) & Space O(n/2)
    public int helper(int[] nums, int si, int ei){
        if(si > ei) return 0;
        if(si == ei) return nums[si];//p1 can win by choosing that element.
        //choices
        //for next iteration for p1, choose the minimum because maximum would have been
        //already choosen by p2 and we will be left with minimum to choose .
        int take_si = nums[si] + Math.min(helper(nums,si+2,ei),helper(nums,si+1,ei-1));
        int take_ei = nums[ei] + Math.min(helper(nums,si+1,ei-1),helper(nums,si,ei-2));
        //now choose the best for p1 between two
        
        return Math.max(take_si,take_ei);

    }
    public boolean predictTheWinner(int[] nums) {
        //solve for player1 i.e start with it.
        int n = nums.length;
        //if(n ==0) return true;
        int tot = 0;
        for(int num : nums){
           tot += num;
        }
        int bestP1 = helper(nums,0,n-1);
        int p2 = tot - bestP1;
        if(bestP1 < p2){
            return false;//p1 cannot win
        }
        return true;
    }
}