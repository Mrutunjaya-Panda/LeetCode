//Approach-2 (Follow up optimal in O(1))
//T.C : O(1) for both methods
//S.C : O(n) for the stream
class ProductOfNumbers {
    private List<Integer> cumulativeStream;
    private int n;
    public ProductOfNumbers() {
        cumulativeStream = new ArrayList<>();
        n=0;//Initially the size of the stream is 0.
    }
    
    public void add(int num) {
        //while adding cumuLative product to the stream chech if 0 encountered.
        if(num == 0){
            //make empty stream
            cumulativeStream.clear();
            n = 0;
        }else{
            if(cumulativeStream.isEmpty()){
                cumulativeStream.add(num);
            }else{
                cumulativeStream.add(cumulativeStream.get(n-1)*num);
            }
            n++;
        }
        
    }
    
    public int getProduct(int k) {
        if(k > n){
            //we encountered zero
            return 0;
        }

        if(k == n){
            return cumulativeStream.get(n-1);
        }

        //else
        return cumulativeStream.get(n-1)/cumulativeStream.get(n-k-1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */