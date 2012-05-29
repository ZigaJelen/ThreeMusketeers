package three.musketeers.namespace;

import java.util.Arrays;

import android.R.bool;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.text.style.ScaleXSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.larvalabs.svgandroid.*;

public class ThreeMusketeersBoard extends View {

	int[][] polozaj;
	int velikost, XSelect, YSelect;
	Boolean naVrsti; // true=musketeer
	TextView text2;
	public int[][] getPolozaj() {
		return polozaj;
	}

	public void setPolozaj(int[][] polozaj) {
		this.polozaj = polozaj;
		this.invalidate();
	}

	public ThreeMusketeersBoard(Context context, View txt) {
		super(context);
		// TODO Auto-generated constructor stub
		text2 = (TextView)txt;
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
						}
					}
					if(polozaj[YSelect][XSelect]==2 && naVrsti == true)
					{
						if(polozaj[y][x]==1)
						{
							polozaj[y][x]=2;
							polozaj[YSelect][XSelect]=0;
							NaVrstiJe();
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
				
				//-----------------------------------------------------------
				//				Nepomembno
				//-----------------------------------------------------------
				String polje = "";
				for(int[] x1 : polozaj)
				{
					polje += Arrays.toString(x1) + "\n";
				}
				
				if(!naVrsti) {
					polje += "\n" +"Cardinal Richelieus";
				}
				else {
					polje += "\n" + "The musketeer";
				}
				
				text2.setText(polje);
				//-----------------------------------------------------------
				//-----------------------------------------------------------
				//-----------------------------------------------------------
				
		}
		return super.onTouchEvent(event);
	}
	
	void NaVrstiJe()
	{
		if(naVrsti) {
			naVrsti=false;
		}
		else {
			naVrsti=true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		super.onDraw(canvas);
		canvas.drawColor(Color.BLUE);
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
					canvas.drawRoundRect(new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), 0, 0, color);
				}
				else
				{
					color.setARGB(255, 255, 255, 153);
					canvas.drawRoundRect(new RectF((x-1)*velikost,(y-1)*velikost,x*velikost, y*velikost), 0, 0, color);
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
							figura = SVGParser.getSVGFromResource(getResources(), R.raw.mus).getPicture();
						canvas.drawPicture(figura, new RectF(8+(velikost*(x-1)), 15+(velikost*(y-1)), x*velikost-8, y*velikost-10));
					}
				}
			}
		}
	}
	
	

}
