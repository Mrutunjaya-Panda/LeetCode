class Solution {
    //Approach (Cases)
    //T.C : O(n)
    //S.C : O(1)
    public int minOperations(String s) {
        int n = s.length();

        //1. first check if the string is already sorted or not.
        boolean sorted = true;
        char[] arr = s.toCharArray();
        for(int i=1;i<n;i++){
            if(arr[i-1] > arr[i]){
                sorted = false;
                break;
            }
        }

        if(sorted == true){
            return 0;
        }
        
        if(n == 2){
            //impossible to sort with a substring logic.
            return -1;
        }
        
        
        //2. find max and min char.
        int min_char = arr[0];
        int max_char = arr[0];
        for(int i=1;i<n;i++){
            min_char = Math.min(min_char,arr[i]);
            max_char = Math.max(max_char,arr[i]);
        }

        //3. check first and last char.
        if(arr[0] == min_char || arr[n-1] == max_char){
            //only 1 ops is required
            return 1;
        }

        //4. check mid_max and mid_min with min_char and max_char.
        int min_mid = arr[1];
        int max_mid = arr[1];
        for(int i=2;i<n-1;i++){
            min_mid = Math.min(min_mid,arr[i]);
            max_mid = Math.max(max_mid,arr[i]);
        }

        if(min_mid == min_char || max_mid == max_char){
            return 2;
        }
        return 3;
    }
}