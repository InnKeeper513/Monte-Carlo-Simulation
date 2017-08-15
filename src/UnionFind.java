public class UnionFind {

    private int[] sz;
    private int[] id;
    private int width;
    private int height;

    public UnionFind(int N, int width, int height){
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
        this.width = width;
        this.height = height;
    }

    private int root(int i){
        while(i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public boolean checkSize(int y, int x){
        if(y == 0)
            return true;

        int p = y * width + x;
        for(int i = 0; i < width; i++){
            if(root(p) == root(i))
                return true;
        }
        return false;
    }

    public void initialize(){
        for(int i = 0; i < width * height; i++){
            id[i] = i;
            sz[i] = 0;
        }
    }
    public void union(int y1, int x1, int y2, int x2){
        int p = y1 * width + x1;
        int q = y2 * width + x2;

        if(!connected(p,q)) {
            int i = root(p);
            int j = root(q);
            if (i == j)
                return;
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
        }
    }
}
