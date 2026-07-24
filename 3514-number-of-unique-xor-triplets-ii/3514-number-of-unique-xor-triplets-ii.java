class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        //store all pair xors first
        //Set<Integer> s1 = new HashSet<>();
        boolean[] pairXor = new boolean[2048]; //since nums[i] <= 1500, so nearest greater pow(2) = 2^11 i.e 2048, since in xor maximum xor is 2048 i.e 11 bit maximum number.
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                //s1.add(nums[i]^nums[j]);
                pairXor[nums[i]^nums[j]] = true;
            }
        }

        //Find triplet XORs with the help of pairXors and nums array.
        //Set<Integer> s2 = new HashSet<>();
        boolean[] tripletXor = new boolean[2048];
        // for(int pairXor : s1){
        //     for(int num : nums){
        //       s2.add(pairXor^num);
        //     }
        // }
        for(int xor = 0;xor<2048;xor++){
            if(!pairXor[xor]) continue;
            for(int num : nums){
                tripletXor[xor^num] = true;
            }
        }

        //now cnt in tripletXor, the no. of unique xor
        int cnt = 0;
        for(boolean b : tripletXor){
            if(b) cnt++;
        }
        return cnt;
    }
}

// Total Time Complexity
// O(n^2)+O(2048n)+O(2048)

// Since 2048 is constant:
// O(n^2)

//S.C:- 0(2048) + 0(2048) i.e Auxilliary space = 0(1) i.e constant.