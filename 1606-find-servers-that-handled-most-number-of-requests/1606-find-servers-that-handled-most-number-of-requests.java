class Solution {
    //Approach (Min Heap)
    //T.C : O(klogk + n*logk + k) ~= O(nlogk)
    //S.C : O(k)
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        //for available/free server, use a sorted set & in java we can use
        //TreeSet.

        TreeSet<Integer> available = new TreeSet<>();

        //initially every server is free.
        for(int i=0;i<k;i++){
            available.add(i); // k * log(k)
        }

        //for busy servers create a minHeap<fininishTime,server> acc. to finishTime first.
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a,b) -> {
                return (a[0] != b[0]) ? Integer.compare(a[0],b[0]):Integer.compare(a[1],b[1]);
            }
        );

        int[] cntReq = new int[k];

        //now traverse in arrival time array.
        for(int i=0;i<arrival.length; i++){
            int time = arrival[i];

            //first put all free servers at the moment into free_server.
            while(!busy.isEmpty() && busy.peek()[0] <= time){
                int server = busy.poll()[1];
                available.add(server);
            }

            //if all servers are busy, drop the current request.
            if(available.isEmpty()){
                continue; //move to next req.
            }

            //get the target server
            int target = i%k;

            //// smallest available server >= target
            Integer targetServer = available.ceiling(target);

            if(targetServer == null){
                //wrap it and find in circular fashion.
                targetServer = available.first();
            }

            available.remove(targetServer);

            busy.offer(new int[]{time+load[i],targetServer});

            cntReq[targetServer]++;
        }

        int maxReq=0;
        for(int i=0;i<k;i++){
            maxReq = Math.max(maxReq,cntReq[i]);
        }

        List<Integer> res = new ArrayList<>();
        for(int i=0;i<k;i++){
            if(cntReq[i] == maxReq){
                res.add(i);
            }
        }
        return res;
    }
}