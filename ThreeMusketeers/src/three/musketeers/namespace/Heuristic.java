package three.musketeers.namespace;

import java.util.ArrayList;

import android.graphics.Point;
import android.util.Log;

public class Heuristic {
	
	private Point[] musketer;
	private int[][] _polozaj;
	
	public Heuristic() {
	
	}
	
	public Heuristic(int[][] polozaj) {
		_polozaj = polozaj;
		musketer = getMussketers();
		
	}

	private Point[] getMussketers()
	{
		Point[] musk = new Point[3];
		int st = 0;
		for(int x=0;x<_polozaj.length;x++)
		{
			for(int y=0;y<_polozaj[x].length;y++)
			{
				if(_polozaj[x][y]==2)
				{
					musk[st] = new Point();
					musk[st].x=x;
					musk[st].y=y;
					st++;
					if(st==3)
						return musk;
				}
			}
		}
		return null;
	}
	
	private boolean jePotMozna(Point a, Point b, boolean smer) // true - v smeri x-osi
	{
		if(smer)
		{
			if(a.x < b.x)
			{
				for(int x=a.x+1;x<=b.x;x++)
				{
					if(_polozaj[x][a.y]!=1)
						return false;
				}
			}
			else
			{
				for(int x=a.x-1;x>=b.x;x--)
				{
					if(_polozaj[x][a.y]!=1)
						return false;
				}
			}
		}
		else
		{
			if(a.y < b.y)
			{
				for(int x=a.y+1;x<=b.y;x++)
				{
					if(_polozaj[a.x][x]!=1)
						return false;
				}
			}
			else
			{
				for(int x=a.y-1;x>=b.y;x--)
				{
					if(_polozaj[a.x][x]!=1)
						return false;
				}
			}
		}
		return true;
	}
	
	private int abs(int a, int b)
	{
		if(a>b)
			return a-b;
		return b-a;
		
	}
	
//	public int getValue()
//	{
//		int min = 0;
//		int moznosti = 0;
//		if(jePotMozna(musketer[0], musketer[1], true) && jePotMozna(musketer[0], musketer[2], true))
//		{
//			min=abs(musketer[0].x, musketer[1].x)+abs(musketer[0].x, musketer[2].x);
//			moznosti++;
//		}
//		
//		if(jePotMozna(musketer[1], musketer[0], true) && jePotMozna(musketer[1], musketer[2], true))
//		{
//			int tmp = abs(musketer[1].x, musketer[0].x)+abs(musketer[1].x, musketer[2].x);
//			if(tmp < min)
//				min=tmp;
//			moznosti++;
//		}
//		
//		if(jePotMozna(musketer[2], musketer[0], true) && jePotMozna(musketer[2], musketer[1], true))
//		{
//			int tmp = abs(musketer[2].x, musketer[0].x)+abs(musketer[2].x, musketer[1].x);
//			if(tmp < min)
//				min=tmp;
//			moznosti++;
//		}
//		
//		//za Y
//		if(jePotMozna(musketer[2], musketer[0], false) && jePotMozna(musketer[2], musketer[1], false))
//		{
//			int tmp = abs(musketer[2].y, musketer[0].y)+abs(musketer[2].y, musketer[1].y);
//			if(tmp < min)
//				min=tmp;
//			moznosti++;
//		}
//		
//		if(jePotMozna(musketer[2], musketer[0], false) && jePotMozna(musketer[2], musketer[1], false))
//		{
//			int tmp = abs(musketer[2].y, musketer[0].y)+abs(musketer[2].y, musketer[1].y);
//			if(tmp < min)
//				min=tmp;
//			moznosti++;
//		}
//		
//		if(jePotMozna(musketer[2], musketer[0], false) && jePotMozna(musketer[2], musketer[1], false))
//		{
//			int tmp = abs(musketer[2].y, musketer[0].y)+abs(musketer[2].y, musketer[1].y);
//			if(tmp < min)
//				min=tmp;
//			moznosti++;
//		}
//		
//		Log.i("TEST", ""+(min+moznosti));
//		return 0;
//	}

	
	public int getValue(Point[] musk)
	{
		Point AB = new Point();
		Point BC = new Point();
		Point CA = new Point();
		
		AB.x = Math.abs(musk[0].x - musk[1].x);
		if(AB.x == 0)
			AB.x-=2;
		AB.y = Math.abs(musk[0].y - musk[1].y);
		if(AB.y == 0)
			AB.y-=2;
		
		BC.x = Math.abs(musk[1].x - musk[2].x);
		if(BC.x == 0)
			BC.x-=2;
		BC.y = Math.abs(musk[1].y - musk[2].y);
		if(BC.y == 0)
			BC.y-=2;
		
		CA.x = Math.abs(musk[0].x - musk[2].x);
		if(CA.x == 0)
			CA.x-=2;
		CA.y = Math.abs(musk[0].y - musk[2].y);
		if(CA.y == 0)
			CA.y-=2;
		
		ArrayList<Integer> st = new ArrayList<Integer>();
		st.add(AB.x+ CA.x);
		st.add(AB.x+ BC.x);
		st.add(BC.x+ CA.x);
		st.add(AB.y+ CA.y);
		st.add(AB.y+ BC.y);
		st.add(BC.y+ CA.y);

		Log.i("TEST", st.toString());
		
		Integer tmp = st.get(0);
		for(int x=1;x<st.size();x++)
		{
			tmp = Math.min(tmp, st.get(x));
		}
		
		return tmp;
	}
	
}
