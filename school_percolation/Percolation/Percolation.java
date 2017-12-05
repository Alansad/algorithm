public class Percolation {
    private int n;
    private WeightedQuickUnionUF uf;
    private boolean[] grid;
    private int top;
    private int bottom;
    public Percolation(int N) // 创建N*N的blocked网格，还有N*N+2个节点用来进行union，find，connected这些操作
    {
        if (N <= 0) throw new IllegalArgumentException();
        n = N;
        grid = new boolean[N*N];  //这里使用一维数组存储的网格，对应创建的节点
        uf = new WeightedQuickUnionUF(n*n+2);
        top = n*n;  //定义一个top和一个bottom格子用来判断连通
        bottom = n*n + 1;
    }
   
    public void open(int i, int j) 
    //如果第i行第j列没有开的话把它打开
    {
        if ((i < 1) || (j < 1) || (i > n) || (j > n)) 
            throw new IndexOutOfBoundsException();
        int x = (i-1)* n + (j-1);//换算成数组中的位置
        grid[x] = true;   //打开时把上下左右的格点也要打开
        if ((i > 1) && (grid[x - n])) uf.union(x, x - n);
        if ((j > 1) && (grid[x - 1])) uf.union(x, x - 1);
        if ((i < n) && (grid[x + n])) uf.union(x, x + n);
        if ((j < n) && (grid[x + 1])) uf.union(x, x + 1);
        
        //如果顶部的那一行的格子打开着那么就把它和top联通
        if (i == 1) uf.union(top, x);
        
        // 
        if (i == n) uf.union(bottom, x);
    }

    public boolean isOpen(int i, int j) //判断格子是否打开着
    {
        if ((i < 1) || (j < 1) || (i > n) || (j > n)) 
            throw new IndexOutOfBoundsException();
        int x = (i-1)* n + (j-1);
        return grid[x];
    }

    public boolean isFull(int i, int j) // 判断格点是否为full，即是否和top连通并且打开
    {
        if ((i < 1) || (j < 1) || (i > n) || (j > n)) 
            throw new IndexOutOfBoundsException();
        int x = (i-1)* n + (j-1);
        return grid[x] && uf.connected(x, top);
        
    }

    public boolean percolates()              // 判断网格系统是否渗透，即判断top和bottom是否连通
    {
        return uf.connected(top, bottom); 
    }
}
