package three.musketeers.namespace;

import java.util.ArrayList;
import java.util.Arrays;

import three.musketeers.activity.Igra;

import android.R.bool;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.style.ScaleXSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.larvalabs.svgandroid.*;

public class ThreeMusketeersBoard extends View {

	int[][] polozaj;
	int velikost, XSelect, YSelect;
	Boolean naVrsti; // true=musketeer
	static Boolean igralecJeNaVrsti = false;
	
	private ArrayList<Point> musketeerji;
	
	public ArrayList<Point> getMusketeerji() {
		return musketeerji;
	}

	public int[][] getPolozaj() {
		return polozaj;
	}

	public void setPolozaj(int[][] polozaj) {
		this.polozaj = polozaj;
		musketeerji = new ArrayList<Point>();
		for (int i = 0; i < polozaj.length; i++) {
			for (int j = 0; j < polozaj[i].length; j++) {
				if(polozaj[i][j]==2)
					musketeerji.add(new Point(i, j));
			}
		}
		ThreeMusketeersActivity.imamZmagovalca();
		NaVrstiJe();
		this.invalidate();
	}

	public ThreeMusketeersBoard(Context context) {
		super(context);
		musketeerji = new ArrayList<Point>();
		XSelect=-1;
		YSelect=-1;
		naVrsti = true;
		
		polozaj = new int[5][];
		
		for(int x=0;x<polozaj.length;x++)
		{
			polozaj[x] = new int[5];
			for(int y=0;y<polozaj[x].length;y++)
			{
				if((x==0 && y==4)||(x==2 && y==2)||(x==4 && y==0)) {
					polozaj[x][y]=2;
					musketeerji.add(new Point(x, y));
				}
				else {
					polozaj[x][y]=1;
				}
			}
		}
		
	}

	public Boolean getNaVrst() {
		return naVrsti;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(!igralecJeNaVrsti)
			return super.onTouchEvent(event);
		
		int x = (int)(event.getX()/velikost);
		int y = (int)(event.getY()/velikost);
		if(x<5&&y<5)
		{
				if((x==XSelect&&(y+1==YSelect || y-1==YSelect))||(y==YSelect&&(x-1==XSelect || x+1==XSelect)))
				{
					System.out.println("SOSED");
					
					if(polozaj[YSelect][XSelect]==1 && naVrsti==false)
					{
						if(polozaj[y][x]<=0)
						{
							polozaj[y][x]=1;
							polozaj[YSelect][XSelect]=0;
							NaVrstiJe();
							ThreeMusketeersActivity.opravljenaPoteza();
						}
					}
					if(polozaj[YSelect][XSelect]==2 && naVrsti == true)
					{
						if(polozaj[y][x]==1)
						{
							spremeniMussket(new Point(YSelect, XSelect),new Point(y, x));
							polozaj[y][x]=2;
							polozaj[YSelect][XSelect]=0;
							NaVrstiJe();
							ThreeMusketeersActivity.opravljenaPoteza();
						}
					}
					XSelect = -1;
					YSelect = -1;
				}
				else if(polozaj[y][x]>0)
				{
					if(XSelect == x && YSelect == y)
					{
						XSelect = -1;
						YSelect = -1;
					}
					else
					{
						XSelect = x;
						YSelect = y;
					}
				}
			
				this.invalidate();
		}
		return super.onTouchEvent(event);
	}
	
	private void spremeniMussket(Point pred,Point el)
	{
		if(musketeerji.get(0).equals(pred))
		{
			musketeerji.get(0).x = el.x;
			musketeerji.get(0).y = el.y;
		}
		if(musketeerji.get(1).equals(pred))
		{
			musketeerji.get(1).x = el.x;
			musketeerji.get(1).y = el.y;
		}
		if(musketeerji.get(2).equals(pred))
		{
			musketeerji.get(2).x = el.x;
			musketeerji.get(2).y = el.y;
		}
	}
	
	public void NaVrstiJe()
	{
		if(naVrsti) {
			naVrsti=false;
		}
		else {
			naVrsti=true;
		}
	}

	private static boolean preveri = true;

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		Paint color = new Paint();
		velikost = this.getWidth()/5;
		System.out.println(this.getWidth());
		color.setARGB(255, 0, 0, 0);
		color.setStrokeWidth(5);
		//canvas.drawRect0, 0, 200, 200, color color);
		
		int k=1;
		for(int y=1;y<=5;y++)
		{
			for(int x=1;x<=5;x++)
			{
				//Izris površine
				if(k%2==0)
				{
					color.setStrokeWidth(5);
					color.setARGB(255, 0, 0, 0);
					
					color.setARGB(255, 53, 94, 0);
//					canvas.drawRoundRect(new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), 0, 0, color);
					Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.kvad); 
					canvas.drawBitmap(bmp,null, new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), color);
				}
				else
				{
					color.setARGB(255, 255, 255, 153);
//					canvas.drawRoundRect(new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), 0, 0, color);
					Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.kvadp); 
					canvas.drawBitmap(bmp,null, new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), color);
				}
				k++;
				//Izbrani kvadratek
				if(x-1==XSelect && y-1 == YSelect)
				{
					color.setARGB(120, 139, 30, 0);
					canvas.drawRoundRect(new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), 0, 0, color);				
				}
				
				//Izris Figur!
				if(polozaj !=null)
				{
					if(polozaj[y-1][x-1]>0)
					{
						Picture figura=null;
						if(polozaj[y-1][x-1]==1)
							figura = SVGParser.getSVGFromResource(getResources(), R.raw.crmen).getPicture();
						if(polozaj[y-1][x-1]==2)
						{
							figura = SVGParser.getSVGFromResource(getResources(), R.raw.mus).getPicture();
						}
						canvas.drawPicture(figura, new RectF(8+(velikost*(x-1)), 15+(velikost*(y-1)), x*velikost-8, y*velikost-10));
					}
				}
			}
		}
	}

	public static void setIgralecJeNaVrsti(Boolean igralecJeNaVrsti) {
		ThreeMusketeersBoard.igralecJeNaVrsti = igralecJeNaVrsti;
	}
}
