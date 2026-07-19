class Solution {
    public String smallestSubsequence(String s) {
        //first find freq. of all chars.
        int n = s.length();
        int[] freq = new int[26];
        boolean[] visited = new boolean[26];

        for(char i : s.toCharArray()){
            freq[i - 'a']++;
        }

        Stack<Character> st = new Stack<>();
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            int idx = ch - 'a';

            //dec. freq.
            freq[idx]--;

            if(visited[idx]) continue;

            while(!st.isEmpty() && st.peek() > ch && freq[st.peek() - 'a'] > 0){
                //then pop to keep lexiographical order.
                visited[st.pop() - 'a'] = false;
            }

            st.push(ch);
            visited[idx] = true;
        }

        //now
        String result = "";
        for(char ch : st){
            //stack is iterated from bottom to top, as it is internally stored as a arrayList
            result += ch;
        }
        return result;
    }
}

//T.c:- 0(n)