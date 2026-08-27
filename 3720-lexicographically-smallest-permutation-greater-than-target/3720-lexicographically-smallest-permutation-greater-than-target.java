class Solution {
    public String lexGreaterPermutation(String s, String target) {
        //first cnt chars present in s.
        int n = s.length();

        int[] cnt = new int[26];
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            int val = c - 'a';
            cnt[val]++;
        }

        //traverse over target.
        StringBuilder res = new StringBuilder();
        int m = target.length();
        for(int i=0;i<m;i++){
            //case 1:- similar elements add orderwise
            char tarChar = target.charAt(i);
            int tar = tarChar - 'a';
            if(cnt[tar] > 0){
                //means we have same char in string s.
                cnt[tar] --;
                //now check for next i+1 chars in target, if forming greater.
                if(canFormGreater(cnt,target,i+1)){
                    //then append current char
                    res.append(tarChar);
                    //check for other possibilities first
                    continue;
                }
                //cannot form, backtrack/restore
                cnt[tar]++;
            }

            //case 2:- fill res current target Idx with just greater char from s.
            for(int j=tar+1;j<26;j++){
                if(cnt[j] > 0){
                    cnt[j] --;
                    res.append((char)('a'+j));
                    //append remaining string from s as first greater elem is ad
                    res.append(getMinString(cnt));
                    return res.toString();
                }
            }
            return "";
        }
        return "";
    }

    // Check if the remaining characters can form a string greater than the suffix.
    private boolean canFormGreater(int[] cnt, String target, int start) {
        String maxStr = getMaxString(cnt);
        String suffix = target.substring(start);
        return maxStr.compareTo(suffix) > 0;
    }

    // Get the maximum lexicographical string (in descending order)
    private String getMaxString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }

    // Get the lexicographically smallest string (in ascending order)
    private String getMinString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));
            }
        }
        return res.toString();
    }
}