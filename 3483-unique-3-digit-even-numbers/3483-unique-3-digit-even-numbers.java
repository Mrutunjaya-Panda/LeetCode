class Solution {
    //T.C : O(n^3 + SlogS), S = total 3 digits even numbers
    //S.C : O(S)
    //Brute force approach
    public int totalNumbers(int[] digits) {
        int n = digits.length;
        //take a set to store unduplicate results.
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(i == j || i == k || j==k) continue;
                    //else, form the 3 digit no.

                    int num = (digits[i]*100+digits[j]*10+digits[k]);
                    if(num >= 100 && num %2==0){
                        set.add(num);
                    }
                }
            }
        }

        //now sort the result
        //List<Integer> list = new ArrayList<>(set);
        //Collections.sort(list);
        return set.size();
    }
}