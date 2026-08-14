class Solution {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int i=0,j=0;
        Map<Character,Integer> freq = new HashMap<>();
        int maxLen = 2;
        //sliding window concept
        while(j<n){
            char ch = s.charAt(j);
            int cnt = freq.getOrDefault(ch,0)+1;
            freq.put(ch,cnt);
            //if exceeds 2.
            while(i<j && freq.get(s.charAt(j)) > 2){
                //dec. the freq. from left and move right
                freq.put(s.charAt(i),freq.get(s.charAt(i))-1);
                i++;
            }
            int curLen = j-i+1;
            maxLen = Math.max(maxLen,curLen);
            j++;
        }
        return maxLen;
    }
}