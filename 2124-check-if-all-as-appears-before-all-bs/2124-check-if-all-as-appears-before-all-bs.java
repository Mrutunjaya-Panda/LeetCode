class Solution {
    public boolean checkString(String s) {
        int n = s.length();
        //StringBuilder str = new StringBuilder(s);
        if(!s.contains("b")) return true;
        //if(!s.contains("a")) return false;
        return (s.lastIndexOf('a') < s.indexOf('b')) ? true : false;
    }
}