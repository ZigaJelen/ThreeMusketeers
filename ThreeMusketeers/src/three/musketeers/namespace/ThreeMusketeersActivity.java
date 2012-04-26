package three.musketeers.namespace;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

public class ThreeMusketeersActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout LayoutGlavni = (LinearLayout)findViewById(R.id.linearLayout1);
       
        ThreeMusketeersBoard board = new ThreeMusketeersBoard(this, findViewById(R.id.textView1));
        LayoutGlavni.addView(board);
        
        
//        int[][] polje = {{2,1,-1,1,1},{2,1,-1,1,1},{1,2,1,2,1},{1,1,1,-1,1},{1,2,1,-1,1}};
//        board.setPolozaj(polje);
    }
}