package Sort;

public class QuickSortThree{
	
	public static void exch(int[] a,int x,int y) {
		int t = a[x];
		a[x] = a[y];
		a[y] = t;
	}
	
	public static void sort(int []array,int lo,int hi){
        if(hi <= lo) {
        		return;
        }
		int lt = lo;  //维护lt指针，使得a[lo...lt-1]都小于value 
		int i = lo+1;  //维护i指针，使得a[lt...i-1]都等于value， a[i...gt]中的元素还未确定  
		int gt = hi;   //维护gt指针，使得a[gt+1...hi]都大于value
        int key=array[lo];
        while(i<gt){
           if(array[i] < key) {  // a[i] < value时，交换a[lt]和a[i]，然后lt和i指针右移
        	   	exch(array,lt++,i++);
           }else if(array[i] > key) {  //从右边
        	   	exch(array,i,gt--);
           }else {
        	   	 i++;   //
           }
            
        }
        sort(array,lo,lt-1);
        sort(array,gt+1,hi);
    }
}
