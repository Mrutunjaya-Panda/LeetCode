class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        //first form the adjacency list
        //the elements present within the array are ArrayList list
        List<Integer> list[] = new ArrayList[n];
        for(int i=0;i<n;i++){
            //empty arrayList
            list[i] = new ArrayList<>();
        }

        //Indegree Array
        int indegree[] = new int[n];
        for(int[] inv : invocations){
            int first = inv[0];
            int second = inv[1];
            list[first].add(second);
            indegree[second]++;
        }

        //mark suspicious nodes and update Indegree of nodes
        //By traversal BFS
        boolean sus[] = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        sus[k] = true;//k is suspicious
        while(!q.isEmpty()){
            int elem = q.poll();
            if(sus[elem]){
                //means the current elem is sus.
                //so every elem associated with it should be marked sus.
                for(int num : list[elem]){
                    indegree[num]--;
                    if(!sus[num]){
                        //then mark it true as it is directly or indirectly associated with suspicious
                        sus[num] = true;
                        q.offer(num);
                    }
                }
            }
        }

        boolean canRemoveAll = true;
        List<Integer> remainingGroup = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(sus[i] && indegree[i] != 0){
                //means an edge is connected from unaffected group to suspicious/affected
                //group.
                canRemoveAll = false;
                break;
            }else if(!sus[i]){
                remainingGroup.add(i);
            }
        }

        if(!canRemoveAll){
            //we cannot remove any node
            List<Integer> all = new ArrayList<>();
            for(int i=0;i<n;i++){
                all.add(i);
            }
            return all;
        }

        return remainingGroup;//i.e not suspicious group
    }
}

// Let n be the number of nodes, and let m be the number of edges (that is, the length of invocations).

// Time complexity: O(n+m).

// Initializing the auxiliary data structures takes O(n) time. During the search, each node is visited at most once and each edge is processed at most once, resulting in O(n+m) time. Constructing the output requires another O(n) time. Therefore, the overall time complexity is O(n+m).

// Space complexity: O(n+m).

// The adjacency list requires O(n+m) space, while the remaining auxiliary data structures require O(n) space. Therefore, the overall space complexity is O(n+m).