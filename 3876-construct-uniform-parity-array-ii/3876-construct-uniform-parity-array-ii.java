class Solution {
    public boolean uniformArray(int[] nums1) {
        //find the min. element, then will configure
        int minEl = nums1[0];
        for(int num:nums1){
            minEl = Math.min(minEl,num);
        }

        //remember for parity to get changed we need to subtract currelem. from odd elem.
        //and according to question smaller than curr elem,
        //because nums1[i] - nums1[j] >= 1.
        if(minEl % 2 == 1){
            //means odd is the min. element,
            //then there is the 100% possibility of converting all even Elems into odd,
            //so return true;
            return true;
        }

        //now it means the minEl is even => we cannot convert every elem. into even
        //except if every elem. is even.
        for(int num : nums1){
            if(num % 2 == 1){
                return false;//not possible
            }
        }
        return true;// all elems are even.
    }
}