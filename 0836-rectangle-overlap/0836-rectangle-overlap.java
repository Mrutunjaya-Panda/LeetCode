class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int common_width = Math.min(rec1[2],rec2[2]) - Math.max(rec1[0],rec2[0]);
        int common_height = Math.min(rec1[3],rec2[3]) - Math.max(rec1[1],rec2[1]);
        //if(common_width < 0 && common_height < 0) return false;//since both can make the area 
        //positive despite non-overlapping.
        return common_width >0 && common_height > 0;
    }
}