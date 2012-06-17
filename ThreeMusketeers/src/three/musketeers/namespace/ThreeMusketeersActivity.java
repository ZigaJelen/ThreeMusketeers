package three.musketeers.namespace;

import java.lang.reflect.Array;
import java.util.Arrays;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThreeMusketeersActivity extends Activity {
    /** Called when the activity is first created. */
	private ThreeMusketeersBoard board;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout LayoutGlavni = (LinearLayout)findViewById(R.id.linearLayout1);
        
        board = new ThreeMusketeersBoard(this, findViewById(R.id.textView2),findViewById(R.id.textView3));
        LayoutGlavni.addView(board);

//        int[][] polje = {{2,1,0,1,1},{2,1,0,1,1},{1,2,1,2,1},{1,1,1,0,1},{1,2,1,0,1}};
//        board.setPolozaj(polje);   
        
        TextView text = (TextView) findViewById(R.id.textView2);
        TextView text2 = (TextView) findViewById(R.id.textView3);
        String txt="";
        
        for(int[] x : board.getPolozaj())
        	txt += Arrays.toString(x) + "\n";
        
        text.setText(txt);
        text2.setText("");
        final AlfaBeta alg = new AlfaBeta();
       
        
        board.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(board.naVrsti)
				{
					vozlisce t = new vozlisce(board.getPolozaj(), !board.getNaVrst(), board.getMusketeerji());
					PotezaClass temp = alg.AlphaBeta(t, board.getNaVrst(), 5, -AlfaBeta.INF, AlfaBeta.INF);
					Log.i("TEST", temp.Poteza.getPolozaj().toString());
					board.setPolozaj(temp.Poteza.getPolozaj());
					board.NaVrstiJe();
				}
				return false;
			}
		});
    }
}