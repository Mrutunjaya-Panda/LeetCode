class Solution {
    public String smallestPalindrome(String s) {
        //finding mid and sorting first part till partition
        int n = s.length();
        int mid = n/2;
        char[] a = s.toCharArray();//breaking into array of char elements.

        //sort first half
        Arrays.sort(a,0,mid);

        //iterate and assign to make it lexicographically smallest palindrom.
        for(int i=0;i<mid;i++){
            //it handles for both odd and even length because you are making
            //changes on the char array itself.
            a[n-i-1] = a[i];
        }
        return new String(a);
    }
}
//T.c:- O(nlogn)
//S.C:- O(n)

