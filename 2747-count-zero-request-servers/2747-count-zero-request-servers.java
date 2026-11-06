class Solution {
    //Nice question, nice use of sliding window.
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        int q = queries.length;
        //1. sort queries and keep indices intact.
        int[][] timeIndex = new int[q][2];

        for(int i=0;i<q;i++){
            timeIndex[i][0] = queries[i];
            timeIndex[i][1] = i;
        }

        // Sort timeIndex by time
        Arrays.sort(timeIndex, (a,b) -> a[0] - b[0]);

        //2. Now sort the logs according to time too.
        // Sort logs by time (index 1)
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        //3. keep a map, to keep the track of no. of servers, and its cnt in the present window.
        Map<Integer,Integer> mp = new HashMap<>();

        //4. now sliding window over logs.
        int[] result = new int[q];

        int i = 0, j = 0;
        //for each query
        for(int[] pair : timeIndex){
            int curtime = pair[0];
            int ind = pair[1];
            
            int start = curtime - x;
            int end = curtime;

            //right expand: time <= end
            while(j<m && logs[j][1] <= end){
                int serverId = logs[j][0];
                //mp.putIfAbsent(serverId,mp.getOrDefault(serverId,0)+1);
                mp.put(serverId,mp.getOrDefault(serverId,0)+1);
                j++;
            }

            //left shrink window: time < start, skip them
            while(i<m && logs[i][1] < start){
                //first remove from map.
                int serverId = logs[i][0];
                mp.put(serverId, mp.get(serverId) - 1);
                if(mp.get(serverId) == 0){
                    //remove it from the map
                    mp.remove(serverId);
                }
                i++;
            }
            // mp.size() unique servers got requests
            result[ind] = n - mp.size();
        }
        return result;
    }
}

//T.C:- O(qlogq+mlogm)