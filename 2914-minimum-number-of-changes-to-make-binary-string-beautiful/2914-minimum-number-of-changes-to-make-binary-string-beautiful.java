class Solution {
    public int minChanges(String s) {
        int n = s.length();
        char chPrev = s.charAt(0);
        int cnt=0;
        int changes = 0;
        //approach partition it on the fly, each of even length where ever possible.
        for(int i=0;i<n;i++){
            
            if(s.charAt(i) == chPrev){
                cnt++;
                continue;
            }

            //curr i.e s[i] is not equal to chPrev
            if(cnt%2 == 0){
                //partition kardo.
                //kiyunki previous substring length even hai
                cnt = 1;//start new Substring.
            }else{
                //1 change is needed to make the substring even length
                changes += 1;

                //and reset cnt 
                cnt = 0;
            }
            chPrev = s.charAt(i);//update to new char.
        }
        return changes;
    }
}