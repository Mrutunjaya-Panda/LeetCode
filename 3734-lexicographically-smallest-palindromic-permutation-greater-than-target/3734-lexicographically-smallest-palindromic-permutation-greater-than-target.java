class Solution {
    char midChar = '$';
    String result = "";
    int half = 0;

    public boolean solve(StringBuilder curr,int[] halfCnt, String target,int i,boolean greater){
        if(i == half){
            //build leftHalf
            // if(greater){
            //     res = curr.toString();
            //     return true;
            // }
            //here curr is basically forming leftHalf of the palindrome.
            String leftHalf = curr.toString();
            //because in StringBuilder reverse(), it reverses inPlace.
            String rightHalf = new StringBuilder(leftHalf).reverse().toString();
            String candidate = leftHalf;
            if(midChar != '$'){
                candidate += midChar;
            }
            candidate += rightHalf;
            if(candidate.compareTo(target) > 0){
                //strictly greater & palindromic simultaneously
                result = candidate;
                return true;
            }
            return false;
        }

        //check/try every possibility for each index
        for(char ch='a';ch <= 'z';ch++){
            if(halfCnt[ch - 'a'] == 0){
                continue;
            }

            if(!greater && ch < target.charAt(i)){
                continue;
            }

            //do
            curr.append(ch);
            halfCnt[ch - 'a']--;

            //explore
            boolean isGreater = greater || (ch > target.charAt(i));
            if(solve(curr,halfCnt,target,i+1,isGreater)){
                return true;
            }

            //undo
            curr.deleteCharAt(curr.length() - 1);
            halfCnt[ch - 'a']++;
        }
        return false;
    }
    public String lexPalindromicPermutation(String s, String target) {
        
        int n = s.length();
        int cnt[] = new int[26];

        for(int i=0;i<n;i++){
            cnt[s.charAt(i) - 'a']++;
        }
        //cnt no. of chars with odd frequency
        int oddCnt = 0;
        
        for(int i=0;i<26;i++){
            if(cnt[i]%2 == 1){
                oddCnt++;
                midChar = (char)(i+'a');
            }
        }

        if(oddCnt > 1){
            //palindrome is not possible just return with empty string.
            return "";
        }

        //now a palindrome string is possible with permutations.
        // Left-half counts + middle char (only when n is odd).

        int[] halfCnt = new int[26];
        for(int i=0;i<26;i++){
            halfCnt[i] = cnt[i]/2;
        }

        half = n/2;//till this we need index of target inorder to form palindrome.
        StringBuilder curr = new StringBuilder();
        solve(curr,halfCnt,target,0,false);
        return result;
    }
}