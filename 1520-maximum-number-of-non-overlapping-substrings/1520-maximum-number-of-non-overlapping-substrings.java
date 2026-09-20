class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] startIdx = new int[26];
        Arrays.fill(startIdx,-1);
        int[] endIdx = new int[26];
        Arrays.fill(endIdx,-1);

        boolean[] isValid = new boolean[26];
        Arrays.fill(isValid,true);

        //S1:- Mark start, end Idx for each character of the string
        for(int i=0;i<n;i++){
            if(startIdx[s.charAt(i) - 'a'] == -1){
                //mark start
                startIdx[s.charAt(i) - 'a'] = i;
            }//else{
                //update endIdx till we are getting it.
                endIdx[s.charAt(i) - 'a'] = i;
            //}   
        }

        //S2:- //find correct start and end for each charcter to have valid substring.
        for(int c=0;c<26;c++){
            if(startIdx[c] == -1) continue;

            //first is this char is a validstart to form our substring
            for(int i=startIdx[c];i<=endIdx[c];i++){
                if(startIdx[s.charAt(i) - 'a'] < startIdx[c]){
                    isValid[c] = false;
                    break;//check for substrings with next char c.
                }
                //else
                //see possibility of end extension to form valid substring.
                endIdx[c] = Math.max(endIdx[c],endIdx[s.charAt(i) - 'a']);
            }
        }

        //S3:- Iterate in the string from right to left, inorder to consider
        // substring with minimum length.
        List<String> result = new ArrayList<>();
        int lastTakenStart = Integer.MAX_VALUE;
        for(int i=n-1;i>=0;i--){
            int idx = s.charAt(i)-'a';
            if(!isValid[idx]) continue;
            if(i == startIdx[idx] && endIdx[idx] < lastTakenStart){
                //endIdx[idx] < lastTakenStart :- non-overlappig
                //valid substring with min. length;
                result.add(s.substring(i,endIdx[idx]+1));
                //update
                lastTakenStart = i;
            }
        }
        return result;
    }
}

//T.C:- O(n)