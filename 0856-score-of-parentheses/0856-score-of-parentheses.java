class Solution {
    public int scoreOfParentheses(String s) {
        int n = s.length();

        ArrayList<Integer> res = new ArrayList<>();
        int sc=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '('){
                //start a fresh
                res.add(sc);
                sc=0;
            }else{
                //')'
                int prev = res.remove(res.size()-1);
                if(s.charAt(i-1) == '('){
                    //inc. score by 1.
                    //sc += 1;
                    //sc += res.remove(res.size()-1);
                    sc = prev + 1;
                    //res.add(sc); //I am jsut keeping 1 len array for overall score.
                }else{
                    //nested
                    sc = prev + 2*sc;
                    //res.add(sc);
                }
            }
        }
        return sc;
    }
}