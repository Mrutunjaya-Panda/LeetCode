class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int result=0;
        int open=0,close=0;
        //we need two traversals first:-
        // Left to right

        for(int i=0;i<n;i++){
            if(s.charAt(i) == '('){
                open++;
            }else{
                //")"
                close++;
            }

            //now check openCnt and closeCnt
            if(open == close){
                //means we have gotten some valid parentheses case
                result = Math.max(result,open+close);
            }else if(close > open){
                //it can never be valid  by taking this, so reset and move further to find other valid cases
                open=0;
                close=0;
            }
            //else simply continue the loop
            //since open > close, so in future we might get valid.
        }

        //again reset before right to left traversal.
        open=0;
        close=0;
        //now right to left traversal.
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i) == '('){
                open++;
            }else{
                //")"
                close++;
            }

            //now check openCnt and closeCnt
            if(open == close){
                //means we have gotten some valid parentheses case
                result = Math.max(result,open+close);
            }else if(open > close){
                //it can never be valid  by taking this, so reset and move further to find other valid cases
                open=0;
                close=0;
            }
            //else simply continue the loop
            //since open < close, so in future we might get valid by open equalizing close.
        }
        return result;
    }
}