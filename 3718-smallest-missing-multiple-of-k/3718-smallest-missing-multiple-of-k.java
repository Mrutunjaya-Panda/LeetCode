class Solution {
    public int missingMultiple(int[] nums, int k) {
        
        int present[] = new int[102];

        for(int num : nums){
            if(num %k == 0){
                present[num/k] = 1;
            }
        }

        for(int i=1; ;i++){
            if(present[i] == 0){
                //return that smallest not present
                return i*k;
            }
        }
    }
}

//T.C:- O(n)
//S.C:- O(1)