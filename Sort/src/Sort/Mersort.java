package Sort;

public class Mersort {
	
	private static int[] aux;
	
	public static void merge(int a[],int low,int mid,int high) {
		int i=low;  
        int j=mid+1;  
        for(int k=low;k<=high;k++){  
            aux[k]=a[k];    
        }  
        for(int k=low;k<=high;k++){  
            if(i>mid){  
                a[k] = aux[j++];    //左半边用尽，取右半边元素
            }else if(j>high){  
                a[k] = aux[i++];    //右半边用尽，左半边元素
            }else if(aux[i]<aux[j]){  
                a[k]=aux[i++];     //右半边当前元素小于左半边当前元素，取右半边元素
            }else{  
                a[k]=aux[j++];    //左半边当前元素小于右半边当前元素，取左半边元素
            }  
        }  
    }    
	//自顶向下归并排序
	static void TopDown_Mergesort(int[] a,int lo,int hi){
		aux = new int[a.length];
		if(hi <= lo) {
			return;
		}
		int mid = lo +(hi - lo) / 2;
		TopDown_Mergesort(a,lo,mid); //将左边排序
		TopDown_Mergesort(a,mid+1,hi); //将右边排序
		merge(a,lo,mid,hi); //归并结果
	}
	//自底向上归并排序
	public static void sortBU(int[] a){
		aux = new int[a.length];
        int N = a.length;
        for(int sz = 1; sz < N; sz=2*sz){
            for(int lo = 0; lo < N - sz; lo += 2*sz){
                merge(a,lo,lo+sz-1,Math.min(lo+2*sz-1, N-1));
            }
        }
    }
}
