class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        //we will  use greedy approach here.
        int n = s.length();
        int cnt1 = 0;
        for(char c : s.toCharArray()){
            if(c == '1') cnt1++; //counting the no. of 1's present.
        }

        //find out the zero blocks
        List<Integer>zeroBlocks = new ArrayList<>();
        int i=0;
        while(i<n){
            int start = i;
            while(i<n && s.charAt(i) == s.charAt(start)){
                //i moves, start remains steady for a particular block to compare.
                i++;
            }
            //a block is found, but it is not known yet if it is a 0 or 1 block.
            if(s.charAt(start) == '0'){
                zeroBlocks.add(i-start);//adding size of the block.
            }
        }

        int m = zeroBlocks.size();
        int bestCnt = 0;
        if(m < 2){
            return cnt1;
        }
        for(int j=0;j<m-1;j++){
            //counting the no. of zeroBlocks
            bestCnt = Math.max(bestCnt, zeroBlocks.get(j) + zeroBlocks.get(j+1));
        }

        return cnt1 + bestCnt;
    }
}

//T.C:- 0(n)