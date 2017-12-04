package Sort;

public class test {
	public static void main(String args[]) {
		int log = 10000;
		int[] arr = new int[log];
		long sum = 0;
		long average = 0;
		long sum2 = 0;
		long average2 = 0;
        for (int i = 0; i < log; i++) {
                arr[i] = i;
        }
        for (int i = 0; i < log; i++) {
                int random = (int) (log * Math.random());
                int temp = arr[i];
               arr[i] = arr[random];
               arr[random] = temp;
        }
        int[] a = new int[log];
        for(int j=0;j<arr.length;j++) {
        		a[j] = arr[j];
        }
        Sort s1 = new Sort();
        for(int i = 0;i < 10;i++) {
        		long startTime = System.currentTimeMillis();
        		for(int j=0;j<arr.length;j++) {
            		a[j] = arr[j];
            }
        		s1.Insertion_Sort(a);
     
        		long endTime = System.currentTimeMillis();
            	sum += (endTime - startTime);
            	sum2 += (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            	System.out.println("第"+(i+1)+"个"+"程序运行时间"+(endTime - startTime)+"ms"+"空间占用:"+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
            
        }
        average = sum/10;
        sum=0;
        average2 = sum2/10;
        sum2=0;
        System.out.println("插入排序程序运行平均时间"+average+"ms"+"平均内存"+average2); 
        
        Mersort s2 = new Mersort();
        for(int i = 0;i < 10;i++) {
        		long startTime = System.currentTimeMillis();
        		for(int j=0;j<arr.length;j++) {
            		a[j] = arr[j];
            }
    			s2.TopDown_Mergesort(a,0,a.length-1);
    			long endTime = System.currentTimeMillis();
            	sum += (endTime - startTime);
            	sum2 += (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            	System.out.println("第"+(i+1)+"个"+"程序运行时间"+(endTime - startTime)+"ms"+"空间占用:"+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        }
        average = sum/10;
        sum=0;
        average2 = sum2/10;
        sum2=0;
        System.out.println("自顶向下归并排序程序运行平均时间"+average+"ms"+"平均内存"+average2); 
        
        Mersort s3 = new Mersort();
        for(int i = 0;i < 10;i++) {
        		long startTime = System.currentTimeMillis();
        		for(int j=0;j<arr.length;j++) {
            		a[j] = arr[j];
            }
			s3.sortBU(a);
			long endTime = System.currentTimeMillis();
			sum += (endTime - startTime);
			sum2 += (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
			System.out.println("第"+(i+1)+"个"+"程序运行时间"+(endTime - startTime)+"ms"+"空间占用:"+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        }
        average = sum/10;
        sum=0;
        average2 = sum2/10;
        sum2=0;
        System.out.println("自底向上归并排序程序运行平均时间"+average+"ms"+"平均内存"+average2); 
        
        QuickSort s4 = new QuickSort();
        for(int i = 0;i < 10;i++) {
        		long startTime = System.currentTimeMillis();
        		for(int j=0;j<arr.length;j++) {
            		a[j] = arr[j];
            }
    			s4.sort(a,0,a.length-1);
    			long endTime = System.currentTimeMillis();
            	sum += (endTime - startTime);
            	sum2 += (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            	System.out.println("第"+(i+1)+"个"+"程序运行时间"+(endTime - startTime)+"ms"+"空间占用:"+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        }
        average = sum/10;
        sum=0;
        average2 = sum2/10;
        sum2=0;
        System.out.println("快速排序程序运行平均时间"+average+"ms"+"平均内存"+average2); 
        
        QuickSortThree s5 = new QuickSortThree();
        for(int i = 0;i < 10;i++) {
        		long startTime = System.currentTimeMillis();
        		for(int j=0;j<arr.length;j++) {
            		a[j] = arr[j];
            }
			s5.sort(a,0,a.length-1);
			long endTime = System.currentTimeMillis();
        		sum += (endTime - startTime);
        		sum2 += (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        		System.out.println("第"+(i+1)+"个"+"程序运行时间"+(endTime - startTime)+"ms"+"空间占用:"+(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        }
        average = sum/10;
        sum=0;
        average2 = sum2/10;
        sum2=0;
        System.out.println("三路快速排序程序运行平均时间"+average+"ms"+"平均内存"+average2); 
	}
}
