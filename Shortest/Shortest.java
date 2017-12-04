
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shortest {
	point[] vertex; //记录顶点
	list[] edge;    //记录边
	int e, v;  		//边数，顶点数
	int from, to;   //起始点和终点
	int[] front;		//前一步
	double dis(int a, int b)
	{
		return Math.sqrt(((double)vertex[a].x-vertex[b].x)*(vertex[a].x-vertex[b].x) + ((double)vertex[a].y-vertex[b].y)*(vertex[a].y-vertex[b].y));
	}    //计算a，b点之间的路径
	Comparator<elem> comparator = new Comparator<elem>() {
		public int compare(elem e1, elem e2)
		{   
			if(e1.distance > e2.distance)
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
	}; //判断哪个的距离短
	
	void solve()  //求出最短路径
	{  
		HashSet<Integer> found = new HashSet<Integer>();
		PriorityQueue<elem> D = new PriorityQueue<elem>(comparator);
		double[] tempShort = new double[v];   //记录最短距离
		front = new int[v];   //记录前一个点

		found.add(from);  
		list p = edge[from];
		while(p.next != null)   //
		{
			front[p.next.b] = from;   //第一个点为from
			tempShort[p.next.b] = p.next.distance;  //把from的邻接点的距离作为目前的最短距离
			
			D.add(new elem(p.next.b, p.next.distance)); //把这些点依次加入到优先队列中
			p = p.next;
		}
		
		while(true)
		{
			int t = D.poll().a;   //获取并删除队列头部的元素，如果队列为空，则返回null
			while(found.contains(t)) //判断found里面是否包含t，直到没有t
			{
				t = D.poll().a;
			}
			if(t == to)
			{
				break;
			}
			found.add(t);//把t加入到已发现的点
			
			p = edge[t]; 
			while(p.next != null)
			{
				if(found.contains(p.next.b) == false)
				{
					if(tempShort[p.next.b] == 0 || tempShort[t]+p.next.distance < tempShort[p.next.b])
					{
						tempShort[p.next.b] = tempShort[t]+p.next.distance; //更新最短距离
						front[p.next.b] = t;  //记录前一个点
						D.add(new elem(p.next.b, tempShort[p.next.b]));  //加入优先队列
					}
				}
				p = p.next;  //下一个邻接点
			}
		}  //循环直到找到to为止
		new display();
		int t = to;
		while(t != from)
		{
			System.out.print(t+"<-");
			t = front[t];  
		}  //反向打印出路径
		System.out.println(t);
	}
	void input(String path)  //读入usa.txt里面的文件
	{
		File file = new File(path);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String temp;
			String[] strings = null;
			
			temp = reader.readLine();
			strings = temp.split(" ");
			
			v = Integer.parseInt(strings[0]); //顶点数
			e = Integer.parseInt(strings[1]); //边数
			vertex = new point[v];
			edge = new list[v];
			
			for(int i = 0; i < v; i++)
			{
				vertex[i] = new point();
				edge[i] = new list(0, 0);
				temp = reader.readLine();
				strings = temp.split(" ");
				
				int k = 0;
				while(strings[k].length() == 0)
				{
					k++;
				}
				k++;
				while(strings[k].length() == 0)
				{
					k++;
				}
				vertex[i].x = Integer.parseInt(strings[k++]);
				while(strings[k].length() == 0)
				{
					k++;
				}
				vertex[i].y = Integer.parseInt(strings[k]);
			}
			reader.readLine();
			for(int j = 0; j < e; j++)
			{
				temp = reader.readLine();
				strings = temp.split(" ");
				int k = 0;
				while(strings[k].length() == 0)
				{
					k++;
				}
				int a = Integer.parseInt(strings[k++]);
				while(strings[k].length() == 0)
				{
					k++;
				}
				int b = Integer.parseInt(strings[k]);
				
				list p = edge[a];
				while(p.next != null)
				{
					p = p.next;
				}
				p.next = new list(b, dis(a, b));
				
				p = edge[b];
				while(p.next != null)
				{
					p = p.next;
				}
				p.next = new list(a, dis(a, b));
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   //添加边
	
	public static void main(String[] args)
	{
		Shortest usa = new Shortest();
		usa.input("usa.txt");  //读入usa.txt文件里面的内容
		
		Scanner sc = new Scanner(System.in);
		
		while((usa.from = sc.nextInt()) != -1)
		{
			usa.to = sc.nextInt();
			long startTime = System.currentTimeMillis();
			usa.solve();
			long endTime = System.currentTimeMillis();
			System.out.println("程序运行时间："+(endTime - startTime)+"ms");
		}
		sc.close();
	}
	
	class display   //画图
	{
		JFrame frame = new JFrame("turtle graphics");  //定义视图框
		
		public display() {
			frame.setBounds(200, 100, 700, 500);  //设置frame的坐标
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			MyPanel myPanel = new MyPanel();
			frame.add(myPanel);
			frame.setVisible(true);
		}
		
		class MyPanel extends JPanel
		{
			public void paint(Graphics g)
			{
				g.setColor(Color.green);
				for(int i = 0; i < v; i++)
				{
					list p = edge[i];
					while(p.next != null)
					{
						g.drawLine(vertex[i].x/8, 600-vertex[i].y/8 ,vertex[p.next.b].x/8, 600-vertex[p.next.b].y/8);
						//System.out.println(vertex[i].x + '\t' + vertex[i].y);
						p = p.next;
					}
				}   //画出地图
				g.setColor(Color.red);
				int t = to;
				while(t != from)
				{
					g.drawLine(vertex[t].x/8, 600-vertex[t].y/8 ,vertex[front[t]].x/8,600-vertex[front[t]].y/8);
					t = front[t];
				}   //画出路线
			}
		}
	}
}

class point
{
	int x, y;
} //定义点, 坐标为x，y

class list
{
	int b;
	double distance;
	list next;
	public list(int a, double dis) {
		b = a;
		distance = dis;
		next = null;
	}
}  //定义边的队列

class elem
{
	int a;
	double distance;
	public elem(int a, double dis) {
		this.a = a;
		distance = dis;
	}
}  //定义elem类
