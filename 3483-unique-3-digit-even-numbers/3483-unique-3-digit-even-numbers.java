class Solution {
    //Approach 2, go on forming 3-digits no. through the constarints provided
    //T.C:- O(1)
    //S.C:- O(1)
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for(int d:digits){
            freq[d] = freq[d]+1;
        }

        List<Integer> result = new ArrayList<>();
        //now start forming
        //for 100th place
        for(int i=1;i<=9;i++){
            if(freq[i] == 0) continue;
            freq[i]--;
            for(int j=0;j<=9;j++){
                if(freq[j] == 0) continue;
                freq[j]--;
                for(int k=0;k<=8;k+=2){
                    if(freq[k] == 0) continue;
                    freq[k]--;
                    int num = i*100+j*10+k;
                    result.add(num);
                    //for this no. formation the digits usage is done, so inc. freq.
                    freq[k]++;
                }
                freq[j]++;
            }
            freq[i]++;
        }

        //The result is already sorted, & has no duplicated entries.
        return result.size();
    }
}