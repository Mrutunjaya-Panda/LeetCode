import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        if (n < m) return new int[0];

        int[] rightHandMatchLength = new int[n + 1];

        int match = 0;
        int i = n - 1;
        int j = m - 1;
        int k = n - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                match++;
                rightHandMatchLength[k] = match;
                i--;
                j--;
                k--;
            } else {
                rightHandMatchLength[k] = match;
                i--;
                k--;
            }
        }

        while (k >= 0) {
            rightHandMatchLength[k] = match;
            k--;
        }

        rightHandMatchLength[n] = 0;

        i = 0;
        j = 0;

        boolean canChange = false;

        List<Integer> res = new ArrayList<>();

        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {

                res.add(i);
                i++;
                j++;
            }
            else if (!canChange &&
                    rightHandMatchLength[i + 1] >= (m - j - 1)) {

                res.add(i);

                canChange = true;

                i++;
                j++;
            }
            else {
                i++;
            }
        }

        if (j != m || res.size() != m) {
            return new int[0];
        }

        int[] ans = new int[m];

        for (int idx = 0; idx < m; idx++) {
            ans[idx] = res.get(idx);
        }

        return ans;
    }
}

//T.C:- O(n+m)