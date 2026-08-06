class Solution {
    public int product(int val){
        if(val == 0) return 0;
        int prod = 1;
        while(val > 0){
            int dig = val%10;
            prod *= dig;
            val = val/10;
        }
        return prod;
    }
    public int smallestNumber(int n, int t) {
        boolean got = false;
        int temp = n;
        while(!got){
            //i.e while true
            int currElemProd = product(temp);
            if(currElemProd%t == 0){
                //got = true;
                //as we need first min. elem, thatswhy I am returning.
                break;
                //return temp;
            }
            temp = temp+1;
        }
        return temp;
    }
}