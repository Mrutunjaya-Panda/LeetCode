class Solution {
    //very nice question
    //Approach (Binary search on answer + Sliding Window + Rolling Hash)
    //T.C : O(n*logn)
    //S.C : O(n) for map

    //now check.
    //Since hash and base can be large, an int can overflow.

    private long findPow(long a, int exp){
        long ans=1;
        a=a%MOD;
        while(exp>0){
            if(exp%2 == 1){
                ans = (ans*a)%MOD;
            }
            a = (a*a)%MOD;
            exp = exp/2;
        }
        return ans;
    }

    private static final long MOD = 1_000_000_007L;
    public boolean check(int[] nums,int size,long base){
        int n = nums.length;
        //first precalculate the constant p.
        //long p = (long)Math.pow(base%MOD,size-1);

        //need binary exponentiation
        long p = findPow(base,size-1);
        long hash = 0;
        //create of map. our map<hash,cnt>
        Map<Long,Integer> mp = new HashMap<>();
        //make windows of fixed size i.e size.

        //i.e sliding window type technique in nums.
        int i=0,j=0;
        while(j<n){
            //on the fly, calculate hash.
            hash = (hash*base+nums[j])%MOD;
            if(j-i+1 < size){
                j++;
            }else{
                //first store the prev hash value, of that window.
                mp.put(hash,mp.getOrDefault(hash,0)+1);

                //now remove the i digit first.
                long remove = (nums[i]*p)%MOD;
                //int rem = hash-remove;

                hash = (hash - remove+MOD)%MOD;
                i++;
                j++;
            }
        }

        //traverse in map, and check if anywhere cnt is 1.
        for(var entry:mp.entrySet()){
            if(entry.getValue() == 1){
                return true;
            }
        }
        return false;
    }
    //Apply binary search on answer i.e different sizes.
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        //first preCalculate the base of our hash function to be used in 
        //our map<hash,cnt>
        long base = nums[0];
        for(int num : nums){
            base = Math.max(base,num);
        }
        base = base+1;//our base is maxi. element + 1.

        int l=1;
        int r=n;
        int res = n;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(check(nums,mid,base)){
                //means with mid size subarray we got unique subarray.
                //store in res
                res = mid;
                //& search for less size subarray.
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return res;
    }
}