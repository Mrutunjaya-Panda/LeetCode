class Solution {
    public int minimumPushes(String word) {
        // int n = word.length();
        // //use greedy approach
        // //8 distinct can be accomodated with each key for minimum
        // int ans = 0;
        // for(int i=0;i<n;i++){
        //     ans += i/8 + 1; //after each of length 8, pushes will increase.
        // }

        // return ans;
        //but above approach will not work because letters are not always distinct
        //let's use greedy + sorting based on frequency in descending order.
        int n = word.length();
        //use greedy approach
        //8 distinct can be accomodated with each key for minimum
        int ans = 0;
        int[] freq = new int[26];
        for(char c: word.toCharArray()){
            freq[c-'a'] = freq[c-'a'] + 1;
        }
        //sort frequencies in descending order
        Arrays.sort(freq);
        int[] sortedFrequency = new int[26];
        for(int i=0;i<26;i++){
            sortedFrequency[i] = freq[25 - i];//to store in descending order
        }
        for(int i=0;i<26;i++){
            //to reduce the push ops. take that elem. which occurs many times
            // at max 26 distinct chars can be present
            ans += (sortedFrequency[i])*(i/8 + 1); //after each of length 8, pushes will increase.
        }

        return ans;
    }
}
//T.C:- O(nlogn)