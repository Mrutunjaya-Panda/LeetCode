class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int d=0;
        int[] result = new int[n];
        //divide alternatively in G0 and g1 like even odd logic.
        //i.e we are halfly dividing bracket pairs, to get the maximum(minimum)..
        for(int i=0;i<n;i++){
            if(seq.charAt(i) == '('){
                //inc. the depth.
                d++;
                result[i] = (d%2 == 0) ? 0 : 1;
            }else{
                //closing bracket
                //before dec., first check this ")" bracket is opened by whom, check its group and put ')' in that group.
                result[i] = (d%2 == 0) ? 0 : 1;
                d--;
            }
        }
        return result;
    }
}
//T.C:- O(n);