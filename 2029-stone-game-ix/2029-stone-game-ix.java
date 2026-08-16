class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0=0,cnt1=0,cnt2=0;

        for(int x:stones){
            int remType = x%3;
            if(remType == 0){
                cnt0++;
            }else if(remType == 1){
                cnt1++;
            }else{
                cnt2++;
            }
        }

        //It is a kind of question which requires a lot of dry run tests to reach out
        //to a conclusion.
        //check if odd no. of 0's are present or even no. of zeroes
        if(cnt0%2 == 0){
            //even
            //for alice to win
            return (cnt1>=1 && cnt2 >=1);
        }
        //else cnt0 = odd
        //for alice to win
        return (cnt1-cnt2 > 2 || cnt2-cnt1 > 2);
    }
}