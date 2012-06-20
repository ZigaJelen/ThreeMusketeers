package three.musketeers.namespace;

import java.lang.reflect.Array;
import java.util.Arrays;

import three.musketeers.activity.Igra;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThreeMusketeersActivity extends Activity {
    /** Called when the activity is first created. */
	public static boolean zaustavi = false;
	private class alfabetaTastk extends AsyncTask<Integer, Void, Void>
	{
		@Override
		protected Void doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			vozlisce t = new vozlisce(board.getPolozaj(), !board.getNaVrst(), board.getMusketeerji());
			PotezaClass el = alg.AlphaBeta(t, board.getNaVrst(), params[0], -AlfaBeta.INF, AlfaBeta.INF);
			if(zaustavi != true)
				poteza = el;
			vrniAlfaBeta();
			return null;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			zaustavi = true;
			super.onCancelled();
		}
		
	}
	
	
	class Odstevaj extends CountDownTimer
	{
		public Odstevaj(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			zaustavi = true;
			globina=0;
			ustavi = false;
			viewcas.setText("0");
			if(poteza== null)
				imamZmagovalca();
			board.setPolozaj(poteza.Poteza.getPolozaj());
			imamZmagovalca();
			board.NaVrstiJe();
			KdoJeNaslednji();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			viewcas.setText(millisUntilFinished/1000+ "");
		}
		
	}
	
	
	private static ThreeMusketeersBoard board;
	private static AlfaBeta alg = new AlfaBeta();
	private static String trenutni;
	private static String igralec1;
	private static String igralec2;
	private static String imeigralca1;
	private static String imeigralca2;
	private static TextView view;
	private static TextView viewcas;
	private static Odstevaj cas1;
	private static Odstevaj cas2;
	private static int globina = 0;
	private static PotezaClass poteza;
	private static ThreeMusketeersActivity contex;
	private static alfabetaTastk task;
	private static boolean ustavi = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        contex = this;
        LinearLayout LayoutGlavni = (LinearLayout)findViewById(R.id.linearLayout1);
        
        board = new ThreeMusketeersBoard(this);
        LayoutGlavni.addView(board);

        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf"); 
		TextView nasl = (TextView) findViewById(R.id.textView1);
		nasl.setTypeface(font);
		 Typeface font2 = Typeface.createFromAsset(getAssets(), "font2.ttf"); 
		nasl = (TextView) findViewById(R.id.TextView01);
		nasl.setTypeface(font2);
		nasl = (TextView) findViewById(R.id.TextView02);
		nasl.setTypeface(font2);
		nasl = (TextView) findViewById(R.id.TextView03);
		nasl.setTypeface(font2);
		nasl = (TextView) findViewById(R.id.TextView04);
		nasl.setTypeface(font2);
		Button btn = (Button) findViewById(R.id.pause);
		btn.setTypeface(font);
		
		Intent intent = getIntent();
		
		trenutni=intent.getStringExtra("muss");
		igralec1 = trenutni;
		igralec2 = intent.getStringExtra("all");
		imeigralca1 = intent.getStringExtra("imem");
		imeigralca2 = intent.getStringExtra("imee");
		view = (TextView) findViewById(R.id.TextView04);
		viewcas = (TextView) findViewById(R.id.TextView01);

	
		
		

//        int[][] polje = {{2,1,0,1,1},{2,1,0,1,1},{1,2,1,2,1},{1,1,1,0,1},{1,2,1,0,1}};
//        board.setPolozaj(polje);   
//        
//        TextView text = (TextView) findViewById(R.id.textView2);
//        TextView text2 = (TextView) findViewById(R.id.textView3);
//        String txt="";
//        
//        for(int[] x : board.getPolozaj())
//        	txt += Arrays.toString(x) + "\n";
//        
//        text.setText(txt);
//        text2.setText("");
       
		
        
        board.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
//				if(board.naVrsti)
//				{
//					vozlisce t = new vozlisce(board.getPolozaj(), !board.getNaVrst(), board.getMusketeerji());
//					PotezaClass temp = alg.AlphaBeta(t, board.getNaVrst(), 5, -AlfaBeta.INF, AlfaBeta.INF);
//					Log.i("TEST", temp.Poteza.getPolozaj().toString());
//					board.setPolozaj(temp.Poteza.getPolozaj());
//					board.NaVrstiJe();
//				}
				return false;
			}
		});
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		int tie = Integer.parseInt(getIntent().getStringExtra("cas1"));
		cas1 = new Odstevaj(tie*1000, 1000);
		tie = Integer.parseInt(getIntent().getStringExtra("cas2"));
		cas2 = new Odstevaj(tie*1000, 1000);
		zacni(trenutni);
	}

	public void pause_onClick(View sender)
    {
    	
    }
    
	public void vrniAlfaBeta()
	{ 
		
		if(ustavi)
		{
			globina++;
			Log.i("TEST", globina+"");
			Integer[] pol = new Integer[1];
			pol[0] = globina;
			task = new alfabetaTastk();
			task.execute(pol);
		}
		
		
	}
	
    public static final void opravljenaPoteza()
    {
    	board.setIgralecJeNaVrsti(false);
    	KdoJeNaslednji();
    
    }
    
    private static void zacni(String in)
    {
    	if(in.compareToIgnoreCase("1") == 0) {
			board.setIgralecJeNaVrsti(true);
    	}
    	else if(in.compareToIgnoreCase("3") == 0) 
    	{
    		if(board.getNaVrst()) {
    			cas1.start();
    		}
    		else {
    			cas2.start();
    		}
    		ustavi = true;
    		zaustavi = false;
    		contex.vrniAlfaBeta();
    	}
    	else if(in.compareToIgnoreCase("2") == 0) 
    	{
    		//pripravleno za MCTS!!!!
				board.setIgralecJeNaVrsti(true);
    	}
    }
    
    private static void KdoJeNaslednji()
    {
    	
    	if(trenutni == igralec1)
    	{
    		trenutni = igralec2;
    		view.setText(imeigralca2);
    	}
    	else if(trenutni == igralec2)
    	{
    		trenutni = igralec1;
    		view.setText(imeigralca1);
    	}
    	zacni(trenutni);
    	imamZmagovalca();
    	
    }
    
    private static void imamZmagovalca()
    {
    	if(vozlisce.preveriZmago(board.getMusketeerji())==1)
    	{
    		contex.setResult(2);
    		contex.finish();
    	}
    	vozlisce tmp = new vozlisce(board.getPolozaj(), board.getNaVrst(), board.getMusketeerji());
    	if(tmp.getNasledniki().size() == 0)
    	{

    		contex.setResult(1);
    		contex.finish();
    	}
    }
    
    private static boolean imaSosede(Point A)
    {
    	if(A.x+1 < board.polozaj.length)
    	{
    		if(board.polozaj[A.x+1][A.y]==1)
    			return false;
    	}
    	if(A.x-1 > -1)
    	{
    		if(board.polozaj[A.x-1][A.y]==1)
    			return false;
    	}
    	if(A.y+1 < board.polozaj.length)
    	{
    		if(board.polozaj[A.x][A.y+1]==1)
    			return false;
    	}
    	if(A.y-1 > -1)
    	{
    		if(board.polozaj[A.x][A.y-1]==1)
    			return false;
    	}
    	return true;
    		
    }
    
//    private static void
    
}