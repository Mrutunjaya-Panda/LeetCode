//recursive solution
class Solution {
    String result = "";
    //greater to check if we have got our next smallest greater.
    //i for traversal in target
    public boolean solve(StringBuilder curr, int[] cnt, String target, int i, boolean greater){
        if(i == target.length()){
            if(greater){
                result = curr.toString();
                return true;
            }
            return false;
        }

        //now start forming the string
        //for each place, choose the correct char.
        //we are starting from 'a' because we need smallest lexicographic.
        for(char ch = 'a';ch <= 'z';ch++){
            if(cnt[ch - 'a'] == 0){
                //means not present in s, just continue
                continue;
            }

            if(!greater && ch < target.charAt(i)){
                //since curr tar idx > than curr char.
                continue;
            }

            //do
            curr.append(ch);
            cnt[ch - 'a']--;

            //recursive call for next indices
            //explore
            boolean isGreater = greater || (ch > target.charAt(i));
            if(solve(curr,cnt,target,i+1,isGreater)){
                return true;
            }

            //undo, the path taken didn't conclude
            curr.deleteCharAt(curr.length() - 1);
            cnt[ch - 'a']++;
        }

        return false;
    }
    public String lexGreaterPermutation(String s, String target) {
        
        int cnt[] = new int[26];
        int n = s.length();
        for(int i=0;i<n;i++){
            cnt[s.charAt(i) - 'a']++;
        }

        //recursive call
        StringBuilder curr = new StringBuilder();//to form the current str
        solve(curr,cnt,target,0,false);
        return result;
    }
}