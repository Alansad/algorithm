

public class Search{
	int search(String pat,String txt) {
		int M = pat.length();
		int N = txt.length();
		int sum = 0;
		for(int i = 0; i<=N;i++) {
			int j;
			for(j = 0;j < M;j++)
				if(txt.charAt(i+j) != pat.charAt(j))
					break;
				if(j == M) sum++;
		}
		return sum;
	}
}