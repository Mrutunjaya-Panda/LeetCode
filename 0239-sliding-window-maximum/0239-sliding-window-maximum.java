class Solution {
    //(worst case :O(n*log(n)) when elements are in ascending order)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(b[0],a[0])
        ); // int[], {value,index}.
        int ptr = 0;
        for(int i=0;i<n;i++){
            //first remove the the elems which shouldn't be present in the current window.
            // Remove maximum elements which are outside the window
            //Lazy deletion
            while(!pq.isEmpty() && pq.peek()[1] <= i-k){
                pq.poll();
            }

            // Add current element: {value, index}
            pq.offer(new int[]{nums[i],i});

            // Store maximum when window size reaches k
            if(i >= k-1){
                // Remove expired elements if they are at the top
                while (!pq.isEmpty() && pq.peek()[1] <= i-k) {
                    pq.poll();
                }
                res[ptr++] = pq.peek()[0];
            }
        }
        return res;
    }
}