class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftKnownSum = 0;
        int rightKnownSum = 0;

        int leftQnCnt = 0;
        int rightQnCnt = 0;

        //if total question cnts is odd, then alice will always win.

        for(int i=0;i<n;i++){
            if(num.charAt(i) == '?'){
                if(i < n/2){
                    //on left half
                    leftQnCnt++;
                }else{
                    //on right half
                    rightQnCnt++;
                }
            }else{
                //It is a number
                if(i < n/2){
                    //on left half
                    leftKnownSum += num.charAt(i) - '0';
                }else{
                    //on right half
                    rightKnownSum += num.charAt(i) - '0';
                }
            }
        }

        //if total question cnts is odd, then alice will always win.
        int totQ = leftQnCnt+rightQnCnt;
        if(totQ%2 == 1){
            return true;
        }

        int leftSum = 2*leftKnownSum + 9*leftQnCnt;
        int rightSum = 2*rightKnownSum + 9*rightQnCnt;

        if(leftSum == rightSum){
            //bob wins
            return false;
        }

        return true;

    }
}