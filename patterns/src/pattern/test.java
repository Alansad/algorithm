package pattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class test {
	public static String txt2String(File file){
	        StringBuilder result = new StringBuilder();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	            String s = null;
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                result.append(System.lineSeparator()+s);
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return result.toString();
	    }
	
	public static void main(String args[]) {
		File file = new File("/Users/guojiantao/Desktop/pattern/src/pattern/usa.txt");
        String str = txt2String(file);
        String pat = "At this the whole pack rose up into the air";
        long startTime = System.currentTimeMillis();
        KMP kmp = new KMP(pat);
        System.out.println(kmp.Search(str));
        long endTime = System.currentTimeMillis();
		System.out.println("KMP算法程序运行时间："+(endTime - startTime)+"ms");
		long startTime1 = System.currentTimeMillis();
		BoyerMoore BM = new BoyerMoore();
		System.out.println(BM.Search(str,pat));
        long endTime1 = System.currentTimeMillis();
		System.out.println("BoyerMoore算法程序运行时间："+(endTime1 - startTime1)+"ms");
		Search sear = new Search();
		long startTime2 = System.currentTimeMillis();
		System.out.println(sear.search(pat, str));
		long endTime2 = System.currentTimeMillis();
		System.out.println("暴力法算法程序运行时间："+(endTime2 - startTime2)+"ms");
        
	}
}
