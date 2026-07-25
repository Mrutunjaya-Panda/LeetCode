class Solution {
    public int maxProduct(int n) {
        int first = 0, second = 0;
        while(n>0){
            //extract digit
            int d = n%10;
            if(d>first){
                second = first;
                first = d;
            }else if(d>second){
                second = d;
            }
            n /= 10;
        }

        return first*second;
    }
}

// Time complexity: O(logn).//it is log base 10.
// Space complexity: O(1).