class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int originalIdx = 0;
        int revIdx = 0;
        int sum=0;
        for(int i=1;i<=n;i++){
            originalIdx = (s.charAt(i-1) - 'a');
           // reqIdx = 26 - originalIdx+1;
            revIdx = 26 - (originalIdx);

            sum += revIdx*i;
        }
        return sum;
    }
}