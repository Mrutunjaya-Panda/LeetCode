class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer,Set<Integer>> map = new HashMap<>();//row,{seats resweved in each row}

        for(int[] row : reservedSeats){
            int r = row[0];
            int seat = row[1];

            //map.computeIfAbsent(row,k -> new HashSet()).add(seat);
            //alternatively
            map.putIfAbsent(r,new HashSet());
            map.get(r).add(seat);
        }

        //find empty rows, we can fill empty rows with atMax 2 groups
        //i.e group A and group C.

        int empty_rows = n - map.size();
        int result = (empty_rows)*2;

         for(var entry : map.entrySet()){
            //for the current booked row get the bookedSeats
            Set<Integer> bookedSeats = entry.getValue();

            //for groupA to be valid or get allocated seats
            boolean groupA = !bookedSeats.contains(2) && !bookedSeats.contains(3) && !bookedSeats.contains(4) && !bookedSeats.contains(5);

            boolean groupB = !bookedSeats.contains(4) && !bookedSeats.contains(5) && !bookedSeats.contains(6) && !bookedSeats.contains(7);

            boolean groupC = !bookedSeats.contains(6) && !bookedSeats.contains(7) && !bookedSeats.contains(8) && !bookedSeats.contains(9);

            //for the current row
            if(groupA && groupC){
                result += 2;
            }else if(groupA || groupB || groupC){
                result += 1;
            }
         }

         return result;
    }
}

//T.C : O(N), N = reservedSeats.length
//S.C : O(N), for storing reserved seats in map (in form of HashSet)