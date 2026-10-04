class Solution {
    //Approach 4
    //T.C:- O(n)
    //S.C:- O(1)
    public boolean checkValidString(String s) {
        int n=s.length();

        //left to right traversal
        //assume * = (
        int openCnt = 0;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '*'){
                openCnt++;
            }else{
                openCnt--;
            }

            //if at any point, close > open or opencnt < 0
            if(openCnt < 0){
                return false;
            }
        }

        //right to left traversal
        //assume * = )

        int closeCnt = 0;
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i) == ')' || s.charAt(i) == '*'){
                closeCnt++;
            }else{
                closeCnt--;
            }

            //if at any point, close > open or opencnt < 0
            if(closeCnt < 0){
                return false;
            }
        }

        //else both are +ve
        return true;
    }
}