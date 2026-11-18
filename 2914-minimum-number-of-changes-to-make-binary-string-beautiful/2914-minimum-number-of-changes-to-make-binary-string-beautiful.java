class Solution {
    //as gievn in the question n is even.
    //and we can do any no. of partitions
    //as long as substrings are of even length && chars within it are even
    //APPROACH 2:- we can take min. of 2 length partition.
    public int minChanges(String s) {
        int n = s.length();
        int changes=0;
        for(int i=0;i<n;i+=2){
            if(s.charAt(i) != s.charAt(i+1)){
                changes += 1;
            }
            //elese we can do the partition, no changes required for that 2 length substring.
        }
        return changes;
    }
}