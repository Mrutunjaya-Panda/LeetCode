class Solution {
    List<List<Integer>> adjL;
    private int[] lastSeen;
    private int[] resultSize;
    public void dfs(int currNode, String s,int[] parent){

        int ch = s.charAt(currNode) - 'a';
        int nearestAnces = lastSeen[ch];

        //before moving deep update last of ch as this currentNode
        lastSeen[ch] = currNode;

        //now check neighbours
        for(int child : adjL.get(currNode)){
            //recurse
            dfs(child,s,parent);
        }

        //backtracking.
        lastSeen[ch] = nearestAnces;//make it who was already present.

        if(currNode != 0){
            int newPar = parent[currNode];
            if(nearestAnces != -1){
                newPar = nearestAnces;
            }

            parent[currNode] = newPar;
            //also update the size of no. of nodes of that newPar node.
            resultSize[newPar] += resultSize[currNode];
        }
    }
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        lastSeen = new int[26];
        Arrays.fill(lastSeen,-1);

        resultSize = new int[n];
        Arrays.fill(resultSize,1);

        //first form the adjacency list for each node, to get all the neighbour nodes of it.
        adjL = new ArrayList<>();
        for(int i=0;i<n;i++){
            adjL.add(new ArrayList<>());
        }
        for(int i=1;i<n;i++){
            int u=parent[i];
            int v = i;
            //do one directional neighbours i.e down side only.
            adjL.get(u).add(v);
        }
        dfs(0,s,parent);
        return resultSize;
    }
}

//Approach (DFS)
//T.C : O(n)
//S.C : O(n)