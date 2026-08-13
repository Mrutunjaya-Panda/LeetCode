class Solution {
    class Node{
        int pre;
        int suf;
        int maxLen;//longest repeating char in that string
        char leftChar;
        char rightChar;

        //constructor
        Node(){}

        Node(int pre, int suf, int maxLen, char leftChar, char rightChar){
            this.pre = pre;
            this.suf = suf;
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.maxLen = maxLen;
        }
    }

    //for segment Tree
    Node segTree[];
    int n;

    private Node merge(Node left,Node right,int leftLen,int rightLen){
        Node res = new Node();

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        //prefixMaxLen
        res.pre = left.pre;
        //edge case
        //prefix
        if(left.pre == leftLen && left.rightChar == right.leftChar){
            res.pre = left.pre + right.pre;
        }

        //suffix
        res.suf = right.suf;
        if(right.suf == rightLen && left.rightChar == right.leftChar){
            res.suf = left.suf + right.suf;
        }

        // Maximum repeating substring
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        //edge
        if(left.rightChar == right.leftChar){
            res.maxLen = Math.max(res.maxLen,left.suf+right.pre);
        }
        return res;
    }

    private void build(int idx,int l,int r,String s){
        if(l == r){
            char ch = s.charAt(l);
            segTree[idx] = new Node(1,1,1,ch,ch);
            return;
        }

        int mid = l+(r-l)/2;
        //recursion
        //leftChild as root
        build(2*idx+1,l,mid,s);
        //rightChild as rootNode
        build(2*idx+2,mid+1,r,s);

        //NodeLeft,Noderight,leftLen,rightLen
        int leftLen = mid-l+1;
        int rightLen = r-mid;//mid not included
        segTree[idx] = merge(segTree[2*idx+1],segTree[2*idx+2],leftLen,rightLen);
    }

    private void update(int idx,int l,int r,int pos,char ch){
        if(l == r){
            //char ch = s.charAt(l);
            //update with new char
            segTree[idx] = new Node(1,1,1,ch,ch);
            return;
        }

        int mid = l+(r-l)/2;
        if(pos <= mid){
            //update in left subtree
            update(2*idx+1,l,mid,pos,ch);
        }else{
            update(2*idx+2,mid+1,r,pos,ch);
        }

        int leftLen = mid-l+1;
        int rightLen = r-mid;//mid not included
        //merge left and right node to form the root node
        segTree[idx] = merge(segTree[2*idx+1],segTree[2*idx+2],leftLen,rightLen);
    }
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        segTree = new Node[4*n];
        //build the segment tree first with root node idx = 0 in segMent as it is an array.
        //which is a tree internally.
        //continuosly dividing tree to reach single char the we will merge
        build(0,0,n-1,s);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for(int i=0;i<q;i++){
            //for each query
            int pos = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            //update with new char and check for new one char maxLen
            update(0,0,n-1,pos,ch);
            ans[i] = segTree[0].maxLen; //root node will have the whole stree and its attributes
        }
        return ans;
    }
}

//Approach (Segment Tree)
//T.C : O(n + klog(n)), build: O(n) and each update: O(log n), done k times → O(k·log n)
//S.C : O(4*n) ~ O(n)