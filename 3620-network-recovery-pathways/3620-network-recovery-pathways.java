class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            //empty arrayList for each node
            g.add(new ArrayList<>());
        }

        int l = Integer.MAX_VALUE;
        int r = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            //if not online then it is not a valid path.
            if (!online[u] || !online[v]) {
                continue;
            }
            g.get(u).add(new int[] { v, w });//neighbour, weight
            l = Math.min(l, w);//minimum weight/cost of all edges in the graph
            r = Math.max(r, w);//maximum weight/cost of all edges in the graph
        }

        if (!check(g, l, k, n)) {
            return -1;
        }

        int ans = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (check(g, mid, k, n)) {
                //first we are looking for atleast minimum, then we are maximizing
                //think like: Max(Min(p1,p2,p3,..))
                //i.e largest possible minimum edge.
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        //return r;
        return ans;
    }

    private boolean check(List<List<int[]>> g, int mid, long k, int n) {
        //check if score = this mid possible or not.
        //if in any path, any edge cost < mid, skip that path completly
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) ->
            Long.compare(a[0], b[0]) // building min. PQ acc. to distance
        );

        dis[0] = 0;//since source is 0, & initially store reaching 0 to 0 -> 0.
        pq.offer(new long[] { 0, 0 }); //{distance/cost,node}

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long d = top[0]; // distance from source(0) to u node.
            int u = (int) top[1];

            if (d > k) {
                return false;
            }
            if (u == n - 1) {
                //traversed and reached the destination -> valid path with score = mid
                //rest allow binary search to Maximimize.
                return true;
            }
            if (d > dis[u]) {
                //we stored min. distance for that node, don't change unless you receive more minimum.
                continue;
            }

            //now check for associated nodes of node u from adjacency list..
            for (int[] edge : g.get(u)) {
                int v = edge[0];//neighbour
                int w = edge[1];
                if (w < mid) {
                    //weight should be atleast score = mid.
                    //don't add to PQ.
                    continue;
                }
                if (dis[v] > dis[u] + w) {
                    //we got minimum for dis[v] i.e to reach v node from source(0 node) -> update dis[v]
                    //& further explore this path.
                    //dis[u] -> 0(source node) se u node tak pahunchne par kitna distance laga.
                    dis[v] = dis[u] + w;
                    pq.offer(new long[] { dis[v], v });
                }
            }
        }
        return false;
    }
}

// That is why we binary search on

// mid

// Suppose

// mid = 6

// means

// Can I find a path whose every edge is at least 6?

// If yes

// Try bigger.

// Otherwise

// Try smaller.