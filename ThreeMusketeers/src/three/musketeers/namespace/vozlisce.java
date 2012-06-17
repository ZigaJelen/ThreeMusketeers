//vozlišèe za MCTS algoritem

package three.musketeers.namespace;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.graphics.Point;
import android.util.Log;
// 1 1 1 1 2
// 1 1 1 1 1
// 1 1 2 1 1
// 1 1 1 1 1
// 2 1 1 1 1
public class vozlisce{
	
		private ArrayList<Point> musketerji;
	
		private ArrayList<vozlisce> nasledniki;
		private double  ocena; 		//estimated simulation value
		private double st_obiskov;
		
		private boolean igralec; 	//kdo je na vrsti 1 = mušketir, 0 = kardinal  
		private int [][] polozaj; 
		private int X1;
		private int Y1;
		private int X2;
		private int Y2;		
		private String pol;
		private int[] mozne_poteze;// max število potez, ko izveš možne poteze, generiraš naslednike
		
		
		public int[][] getPolozaj() {
			return polozaj;
		}
		
		public int getX1()
		{
			return this.X1;
		}
		public int getY1()
		{
			return this.Y1;
		}
		public int getX2()
		{
			return this.X2;
		}
		public int getY2()
		{
			return this.Y2;
		}
		public Boolean getIgralec()
		{
			return this.igralec;
		}

		public vozlisce(boolean igr,int x1, int y1, int x2, int y2, int[][] polozaj_matrike, ArrayList<Point> musk)
		{	
			musketerji = new ArrayList<Point>();
			nasledniki = new ArrayList<vozlisce>();
			st_obiskov = 1;
			ocena = 0;
			igralec = igr;
			ocena = 0;
			st_obiskov = 1;
			X1 = x1;
			Y1 = y1;
			X2 = x2;
			Y2 = y2;
			
			polozaj = new int[polozaj_matrike.length][];
			for(int i = 0; i < polozaj_matrike.length; i++)
				polozaj[i] = polozaj_matrike[i].clone();
			if(polozaj_matrike[x1][y1]==2)
			{
				for(Point el : musk)
				{
					if(el.x == x1 && el.y==y1)
						musketerji.add(new Point(x2, y2));
					else
						musketerji.add(new Point(el));
				}
				polozaj[x1][y1] = 0;
				polozaj[x2][y2] = 2;
			}
			else if(polozaj_matrike[x1][y1]==1)
			{
				for(Point el : musk)
					musketerji.add(new Point(el));
				polozaj[x1][y1] = 0;
				polozaj[x2][y2] = 1;
			}
		}
		
		public vozlisce(int[][] polozaj_matrike, Boolean igr, ArrayList<Point> musk)
		{
			musketerji = musk;
			igralec = igr;
			nasledniki = new ArrayList<vozlisce>();
			ocena = 0;
			st_obiskov = 1;			
			int st_potez = 0;
			
			
			if(igr) //Stetje potez za belega, èe je igr 1
			{
				for(int i=0; i<5 ;i++)
				{
					for(int j=0; j<5; j++)
					{
						if(polozaj_matrike[i][j]==1)
						{						
							if(i+1!=5) //èe ne gre ven iz desnega roba
							{
								if(polozaj_matrike[i+1][j]==0) //desno
								{	 
									nasledniki.add(new vozlisce(true,i,j,i+1,j, polozaj_matrike, musketerji));
									st_potez++;
								}
							}
						
							if(i-1!=-1) //èe ne gre ven iz levega roba
							{
								if(polozaj_matrike[i-1][j]==0) //levo
								{ 
									nasledniki.add(new vozlisce(true,i,j,i-1,j, polozaj_matrike, musketerji));
									st_potez++;
								}
							}
						
							if(j+1!=5) //èe ne gre ven iz zgornjega roba
							{
								if(polozaj_matrike[i][j+1]==0) //gor
								{	
									nasledniki.add(new vozlisce(true,i,j,i,j+1, polozaj_matrike, musketerji));
									st_potez++;
								}
							}						
						
							if(j-1!=-1) //èe ne gre ven iz spodnjega roba
							{
								if(polozaj_matrike[i][j-1]==0) //dol
								{	
									nasledniki.add(new vozlisce(true,i,j,i,j-1, polozaj_matrike, musketerji));
									st_potez++;
								}
							}
						}
					}
				}
			}
			else
			{
				for(int i=0; i<5 ;i++)
					{
						for(int j=0; j<5; j++)
						{
							if(polozaj_matrike[i][j]==2)
							{	
								
								if(i+1!=5) //èe ne gre ven iz desnega roba
								{
									if(polozaj_matrike[i+1][j]==1) //desno
									{	 
										nasledniki.add(new vozlisce(false,i,j,i+1,j, polozaj_matrike, musketerji));
										st_potez++;
									}
								}
							
								if(i-1!=-1) //èe ne gre ven iz levega roba
								{
									if(polozaj_matrike[i-1][j]==1) //levo
									{ 
										nasledniki.add(new vozlisce(false,i,j,i-1,j, polozaj_matrike, musketerji));
										st_potez++;
									}
								}
							
								if(j+1!=5) //èe ne gre ven iz zgornjega roba
								{
									if(polozaj_matrike[i][j+1]==1) //gor
									{	
										nasledniki.add(new vozlisce(false,i,j,i,j+1, polozaj_matrike, musketerji));
										st_potez++;
									}
								}						
							
								if(j-1!=-1) //èe ne gre ven iz spodnjega roba
								{
									if(polozaj_matrike[i][j-1]==1) //dol
									{	
										nasledniki.add(new vozlisce(false,i,j,i,j-1, polozaj_matrike, musketerji));
										st_potez++;
									}
								}
							}
						}
					}		
			}
			Log.i("TEST", musketerji.toString());
		}
		
		public void dodajNaslednika(vozlisce voz)
		{
			nasledniki.add(voz);
		}
		
		public String getMozne()
		{
			String izp="";
			for(int i=0;i<nasledniki.size(); i++)
			{
				izp+=" iz:" + Integer.toString(nasledniki.get(i).getX1());
				izp+=" ";
				izp+=Integer.toString(nasledniki.get(i).getY1());				
				izp+=" v: " + Integer.toString(nasledniki.get(i).getX2());
				izp+=" ";
				izp+=Integer.toString(nasledniki.get(i).getY2());
				izp+="\n";
			}
			return izp;
		}
		
		public double getOcena(){return this.getOcena(); }
		public double getSt_obiskov(){return this.getSt_obiskov();}
		public ArrayList<vozlisce> getNasledniki() {
			return nasledniki;
		}
		public ArrayList<Point> getMusketerji() {
			return musketerji;
		}
	}

