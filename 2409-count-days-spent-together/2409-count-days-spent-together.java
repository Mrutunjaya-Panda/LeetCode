class Solution {
     int mnth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    

    int[] prefix = new int[13];
    // Converts MM-DD into the day number of the year
    int nthDay(String str) {
        int month = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
        int day = (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');

        // Add the days in previous months to get the day number
        return prefix[month-1] + day;
    }
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        //first cumulative the no. of days i.e prefix_sum.
        prefix[0] = 0;
        for(int i=1;i<13;i++){
            prefix[i] = prefix[i-1] + mnth[i];
            //mnth[i] = mnth[i]+mnth[i-1];
        }
        // Find the later arrival date (start of the overlap)
        int start = Math.max(nthDay(arriveAlice), nthDay(arriveBob));

        // Find the earlier departure date (end of the overlap)
        int end = Math.min(nthDay(leaveAlice), nthDay(leaveBob));

        // No overlap if the start date is after the end date
        if (start > end) return 0;

        // +1 because both arrival and departure dates are inclusive
        return end - start + 1;
    }
}