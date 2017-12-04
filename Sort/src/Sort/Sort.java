package Sort;

public class Sort {
	public static boolean less(int x,int y) {
		return x<y;
	}
	public static void exch(int[] a,int x,int y) {
		int t = a[x];
		a[x] = a[y];
		a[y] = t;
	}
	  
	//插入排序
	static void Insertion_Sort(int[] a) {
		for(int i = 0; i < a.length; i++) {
			for(int j = i; j > 0 && less(a[j],a[j-1]); j--) {
				exch(a,j,j-1);
			}
		}
	}
}
