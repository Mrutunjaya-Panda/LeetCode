class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        
        //put knowledge in a hashmap.
        Map<String,String> mp = new HashMap<>();

        for(int i=0;i<knowledge.size();i++){
            List<String> curr = knowledge.get(i);
            //for(int j=0;j<curr.size();j++){

            mp.put(curr.get(0),curr.get(1));
            //}
        }

        //now traverse in s.
        int n = s.length();
        boolean addKey = false;
        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(c == '('){
                addKey = true;
            }else if(c == ')'){
                if(mp.containsKey(key.toString())){
                    result.append(mp.get(key.toString()));
                }else{
                    result.append("?");
                }
                addKey = false;
                key = new StringBuilder();
            }else if(addKey){
                key.append(c);
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }
}

//T.C:- O(n+m), m is for building hash table and for looking up.