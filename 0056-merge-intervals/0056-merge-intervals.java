import java.util.*;
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        int lowL = intervals[0][0];
        int rightH = intervals[0][1];
        
        int buses = 1;
        ArrayList<int[]> list = new ArrayList<>();
        //list.add(new int[]{lowL,rightH});
        for(int i=1;i<n;i++){
            int curL = intervals[i][0];
            int curH = intervals[i][1];
            if(curL <= rightH){
                //overlapping
                //extend end
                rightH = Math.max(curH,rightH);
                //don't add, as the interval might extend.
                // list.add(new int[]{lowL,rightH});
            }else{
                buses++;
                //first add the interval we've finished:
                list.add(new int[]{lowL,rightH});
                // Start new interval
                lowL = curL;
                rightH = curH;
            }

        }

        // After loop, add the last interval
        list.add(new int[]{lowL,rightH});
        //if no. of intervals asked.
        //return buses;
        //convert
        int m = list.size();
        // int[][] res = list.toArray(new int[0]);
        //col will be same i. 2
        int[][] res = new int[m][2];
        for(int i=0;i<m;i++){
            res[i] = list.get(i);
        }
        return res;
    }
}

// Time Complexity  : O(n log n)
// Space Complexity : O(n)