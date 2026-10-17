class Solution {
    public int sumV(int v){
        if(v>=1 && v <= 9){
            return v;
        }
        int sum =0;
        while(v > 0){
            int dig = v % 10;
            sum += dig;
            v = v/10;
        }
        return sum;
    }
    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[] digitSum = new int[n];
        Integer[] indices = new Integer[n];
        //don't change nums[] and digitSum[], just sort indices.

        for(int i=0;i<n;i++){
            digitSum[i] = sumV(nums[i]);
            indices[i] = i;
        } 

        //now sort acc. to digitSum.
        Arrays.sort(indices, 
            (a,b) -> {
                if(digitSum[a] != digitSum[b]){
                    return Integer.compare(digitSum[a], digitSum[b]);
                }
                return Integer.compare(nums[a], nums[b]);  
            }
        );

        //now create a hashmap to store indices[i], idx of indices[i] in nums.

        Map<Integer,Integer> mp = new HashMap<>();
        for(int i : indices){
            mp.put(nums[i],i);
        }
        
        int[] target = new int[n];

        for (int i = 0; i < n; i++) {
            target[i] = nums[indices[i]];
        }
        //traverse in nums.
        int swap = 0;
        for(int i=0;i<n;i++){
            // int numsIdx = i;
            // int expectedIdx = indices[i];
            // int expectedAtI = nums[indices[i]];
            //because we are swapping nums, nums now may not hold correct positions.
            int expectedAtI = target[i];
            int currAtI = nums[i];
            if(currAtI == expectedAtI){
                continue;
            }

            int currIdx = mp.get(expectedAtI);
            //swap
            // int temp = nums[i];
            // nums[i] = nums[curIdx];
            // nums[curIdx] = temp;

            // //update map
            // mp.put(expectedAtI, i);
            // // mp.put(currAtI, indices[i]);
            // mp.put(currAtI, curIdx);

            // Swap
            nums[i] = expectedAtI;
            nums[currIdx] = currAtI;

            // Update current positions
            mp.put(expectedAtI, i);
            mp.put(currAtI, currIdx);

            //cnt swap.
            swap++;
        }
        return swap;
    }
}

//T.C:- O(nlogn + n)
//S.C:- O(n)