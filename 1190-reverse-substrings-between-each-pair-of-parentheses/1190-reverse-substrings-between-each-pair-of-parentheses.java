class Solution {
    //Wormhole Teleportation technique
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer>openParenthIdx = new Stack<>();
        int[] door = new int[n];

        //mapping
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '('){
                openParenthIdx.push(i);
            }else if(s.charAt(i) == ')'){
                // 
                int open = openParenthIdx.pop();
                door[open] = i;
                door[i] = open;
            }
        }

        String result = "";
        int flag=1;

        for(int i=0;i<n;i+=flag){
            if(s.charAt(i) == '(' || s.charAt(i) == ')'){
                //move to close bracket and change the direction
                i = door[i];
                flag = -flag;
            }else{
                result += s.charAt(i);
            }
        }
        return result;
    }
}

//T.C:- O(2*n)
//S.C:- O(n)