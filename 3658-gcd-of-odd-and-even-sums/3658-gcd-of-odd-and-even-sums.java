class Solution {
    public int gcd(int a, int b){
        if(a == 0) return b;
        if(b == 0) return a;

        return gcd(b,a%b);
    }
    public int gcdOfOddEvenSums(int n) {
        int cnt1 = 0, cnt2 = 0;
        int sumOdd = 0, sumEven = 0;
        int strOdd = 1, strEven = 2;
        while(cnt1 != n){
            sumOdd += strOdd;
            cnt1++;
            strOdd += 2;
        }

        while(cnt2 != n){
            sumEven += strEven;
            cnt2++;
            strEven += 2;
        }

        return gcd(sumOdd, sumEven);
    }
}