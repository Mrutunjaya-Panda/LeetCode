class Solution {
    //Approach (2 Min Heaps)
    //T.C : O((n+m)*logn)
    //S.C : O(n)
    public int[] assignTasks(int[] servers, int[] tasks) {
         int n = servers.length;
         int m = tasks.length;

         // Define two min heaps, one for free and one for busy.
         // int[] -> {wt,idx} idx here is of servers
         PriorityQueue<int[]> free = new PriorityQueue<>(
            (a,b) -> a[0] != b[0] ? Integer.compare(a[0],b[0]) : Integer.compare(a[1],b[1])
         );

        // Long[] -> busy servers: {finTime, wt, idx}, smallest (finTime, wt, idx) first
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a,b) -> {
                if(a[0] != b[0]) return Long.compare(a[0], b[0]);
                if(a[1] != b[1]) return Long.compare(a[1], b[1]);

                return Long.compare(a[2], b[2]);
            }
        );

        //Initially every server is free add in it.
        for(int i=0;i<n;i++){
            free.add(new int[]{servers[i],i});
        }

        //now loop through tasks..
        int[] result = new int[m];

        for(int j=0;j<m;j++){
            // Busy Servers ---> Free Servers
            //i.e at time j, move all those servers who got free at j sec or freed into freeServers.
            while(!busy.isEmpty() && busy.peek()[0] <= j){
                long[] cur = busy.poll();
                int wt = (int)cur[1];
                int idx = (int)cur[2];
                free.add(new int[]{wt,idx});
            }

            //if all are busy and no server is free.
            if(free.isEmpty()){
                // nobody free: wait for the earliest finishing server
                long[] cur = busy.poll();
                long old_f = cur[0];
                //since this will be immediately picked up,
                //thatswhy it will not further go to free server anymore
                //push in busy with new finishTime.
                long new_f = old_f + tasks[j];
                result[j] = (int)cur[2];
                long[] new_c = new long[]{new_f,cur[1],cur[2]};
                busy.add(new_c);
            }else{
                // Free Servers ---> Busy Servers, starts right now at time j
                int[] cur = free.poll();
                int idx = cur[1];
                result[j] = idx;
                long f = (long)j+tasks[j];
                busy.add(new long[]{f,cur[0],cur[1]});
            }

        }
        return result;
    }
}

