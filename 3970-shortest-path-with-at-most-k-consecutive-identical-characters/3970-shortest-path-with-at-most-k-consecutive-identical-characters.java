import java.util.*;
class Solution {
    //This is a Very nice question uses Dijkstra's algorithm in a modified way..
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        
        //build adjList
        List<List<int[]>> adjL = new ArrayList<>();

        for(int i=0;i<n;i++){
            adjL.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            //undirected graph
            adjL.get(u).add(new int[]{v,wt});
        }

        //here dist. array would be n*k 2D array.
        // dist[node][count] = min cost to reach node
        // with 'count' consecutive identical characters
        int[][] dist = new int[n][k+1];
        

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        //initial source node dist would be 0.
        dist[0][1] = 0;

        // Heap holds {dist, node, count}, ordered by distance
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a,int[] b) -> Integer.compare(a[0],b[0]));

        pq.offer(new int[]{0,0,1});

        while(!pq.isEmpty()){
            int[] top = pq.poll();
            int d = top[0];
            int node = top[1];
            int cnt = top[2];

            if(d > dist[node][cnt]){
                continue;
            }

            //we reached at our required node.
            if(node == n-1){
                return d;
            }

            //now check the neighbours of this node.
            for(int[] vec : adjL.get(node)){
                int adjNode = vec[0];
                int wt = vec[1];

                //check whether two nodes chars are adjacent or not
                int newCnt = (labels.charAt(adjNode) == labels.charAt(node)) ? cnt+1:1;

                if(newCnt > k){
                    //this path is not possible to consider although it may happen that 
                    //it is giving minimum dist.
                    continue;
                }

                int newDist = d + wt;
                if(newDist < dist[adjNode][newCnt]){
                    dist[adjNode][newCnt] = newDist;
                    pq.offer(new int[]{newDist,adjNode,newCnt});
                }
            }
        }
        return -1;
    }
}

//Appoach Using Dijkstra's
//Let V = n, E = edges.length, k = max count length, usually Dijkstra is O(E * log(V)), in this problem, E = E*K and V = n*K
//T.C : O(E*K * log(n*K))
//S.C : O(V+E) = O(n*K + E*k)