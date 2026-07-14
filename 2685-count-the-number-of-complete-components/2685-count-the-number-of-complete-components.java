//using BFS
class Solution {
    List<Integer> graph[];
    boolean visited[];

    int vertices;
    int degreeSum;
    public int countCompleteComponents(int n, int[][] edges) {
        
        //build adjacency list for traversal
        graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            //initialize with empty array list for each node.
            graph[i] = new ArrayList<>();
        }

        for(int[] e: edges){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);//since undirected graph
        }

        visited = new boolean[n];
        int completeComponents = 0;

        for(int i=0;i<n;i++){
            
            if(!visited[i]){
                //for new component
                vertices = 0;
                degreeSum = 0;
                //dfs(i);//in dfs if same component node it will already be marked visted
                bfs(i);
                long actualEdges = degreeSum/2;
                long requiredEdges = (long)vertices*(vertices-1)/2;
                if(actualEdges == requiredEdges){
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    // public void dfs(int node){
    //     visited[node] = true;
    //     vertices++;
    //     degreeSum += graph[node].size();

    //     for(int nei : graph[node]){
    //         if(!visited[nei]){
    //             dfs(nei);
    //         }
    //     }
    // }

    public void bfs(int node){
        visited[node] = true;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            int curr = q.poll();
            vertices++;
            degreeSum += graph[curr].size();
            for(int nei : graph[curr]){
                if(!visited[nei]){
                    visited[nei] = true;
                    q.offer(nei);
                }
            }
        }
    }
}