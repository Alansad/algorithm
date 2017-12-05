import java.util.InputMismatchException;

public class PercolationStats 
{
    private double[] pthreshold;
    private int n, t;

    public PercolationStats(int N, int T)    
    // 在一个N*N的网格进行T次计算实验
    {
        if ((N <= 0) || (T <= 0)) throw new IllegalArgumentException(); //抛出IllegalArgumentException例外
        n = N;
        t = T;

        pthreshold = new double[t];  //用来存储每一次的结果

        for (int e = 0; e < T; e++) {
            Percolation p = new Percolation(N);
            int opened = 0;		//记录打开的网格点
            while (!p.percolates()) {   //如果不渗透就继续打开点
                int row, col;
                do {
                    row = 1 + StdRandom.uniform(N);  //不断随机打开网格点
                    col = 1 + StdRandom.uniform(N);
                } while (p.isOpen(row, col));
                p.open(row, col);
                opened++;
            }
            pthreshold[e] = (opened*1.0)/(n*n);  //计算每一次的渗滤阈值并且存到pthreshold中
        }
    }

    public double mean()                     
    // 计算平均值
    {
        return StdStats.mean(pthreshold);
    }

    public double stddev()                   
    // 计算样本的标准偏差
    {
        return StdStats.stddev(pthreshold);
    }

    public double confidenceLo()             
    // 置信区间的下界
    {
        return mean() - 1.96*stddev()/Math.sqrt(t);
    }
    public double confidenceHi()             
    // 置信区间的上界
    {
        return mean() + 1.96*stddev()/Math.sqrt(t);
    }
    
    public static void main(String[] args)   
    // 输入N和T,然后进行实验
    {
        int N, T;
       
            N = StdIn.readInt();
            T = StdIn.readInt();    
        long startTime = System.currentTimeMillis();
        PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " 
                + ps.confidenceLo() + ", " + ps.confidenceHi());
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间"+(endTime - startTime)+"ms");
        
    }
}