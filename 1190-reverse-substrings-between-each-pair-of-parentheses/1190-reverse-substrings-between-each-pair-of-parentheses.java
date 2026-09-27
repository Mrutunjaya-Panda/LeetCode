class Solution {
    public String reverseParentheses(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> openParenthIdx = new Stack<>();

        //if(s.charAt())
        int n = s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '('){
                openParenthIdx.push(result.length());
            }else if(s.charAt(i) == ')'){
                int start = openParenthIdx.pop();
                reverse(result,start,result.length()-1);
            }else{
                //append
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
    public StringBuilder reverse(StringBuilder result,int s,int e){
        String portion = result.substring(s,e+1);
        String revPortion = new StringBuilder(portion).reverse().toString();
        return result.replace(s,e+1,revPortion);
    }
}

//T.C:- O(n^2)
//S.C:- O(n)