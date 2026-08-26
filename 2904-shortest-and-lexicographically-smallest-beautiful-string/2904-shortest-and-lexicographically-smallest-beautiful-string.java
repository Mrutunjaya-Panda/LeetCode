class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        //let's use sliding window.
        //cnt total, my 1's must be atleast equals to k to find the beautiful string
        int n = s.length();
        int tot = 0;
        for(int i=0;i<n;i++){
            tot += s.charAt(i) - '0';
        }

        if(tot < k){
            return "";
        }

        int cnt1 = 0;
        String ans = s;
        int left = 0;
        for(int right = 0;right < n;right++){
            cnt1 += s.charAt(right) - '0';

            while(cnt1 > k || s.charAt(left) == '0'){
                //move left part i.e shrink the window
                cnt1 -= s.charAt(left) - '0';
                left++;
            }

            if(cnt1 == k){
                //check the current valid substring
                String curr = s.substring(left,right+1);
                if(curr.length() < ans.length() 
                    || (curr.length() == ans.length() && curr.compareTo(ans) < 0)
                ){
                    //update ans
                    ans = curr;
                }
            }
            
        }
        return ans;
    }
}

//T.C:- O(n^2), extracting a substring takes O(n) time in the worst case, and this operation can be performed O(n) times. Therefore, the total time complexity is O(n2).
//S.C:- O(n) or O(1)