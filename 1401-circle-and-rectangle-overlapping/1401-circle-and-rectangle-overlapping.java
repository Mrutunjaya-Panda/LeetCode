class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        //find nearest point in rectangle from the center of the circle.
        int xi;
        int yi;
        if(x1>xCenter){
            //nearest is x1
            xi = x1;
        }else if(x2 < xCenter){
            xi = x2;
        }else{
            //for x1 < xC && x2 > xC
            xi = xCenter;
        }

        if(y1>yCenter){
            //nearest is x1
            yi = y1;
        }else if(y2 < yCenter){
            yi = y2;
        }else{
            //for x1 < xC && x2 > xC
            yi = yCenter;
        }

        //find distance of nearest pt. in rectangle from the center ofthe circle.
        int d = (int)Math.sqrt(Math.abs(xCenter - xi)*Math.abs(xCenter - xi) + 
        Math.abs(yCenter - yi)*Math.abs(yCenter - yi));

        return d <= radius;
    }
}