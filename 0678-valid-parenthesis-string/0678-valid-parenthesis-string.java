//Approach-3 (Using two Stacks) - No DP required
//T.C : O(n)
//S.C : O(n)
class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> openSt = new Stack<>();
        Stack<Integer> asterisksSt = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                openSt.push(i);
            } else if (ch == '*') {
                asterisksSt.push(i);
            } else {
                if (!openSt.isEmpty()) {
                    //close can be in openSt itself
                    openSt.pop();
                } else if (!asterisksSt.isEmpty()) {
                    //else from asterisk
                    asterisksSt.pop();
                } else {
                    //cannot be balanced.
                    return false;
                }
            }
        }

        // This post processing will be required for cases like - "*(())(*"
        while (!openSt.isEmpty() && !asterisksSt.isEmpty()) {
            if (openSt.peek() > asterisksSt.peek()) {
                return false;
            }
            openSt.pop();
            asterisksSt.pop();
        }

        return openSt.isEmpty();
    }
}