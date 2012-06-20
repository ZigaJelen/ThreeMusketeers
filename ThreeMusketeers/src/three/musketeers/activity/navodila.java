package three.musketeers.activity;

import three.musketeers.namespace.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class navodila extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navodila);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf"); 
		TextView nasl = (TextView) findViewById(R.id.textView1);
		nasl.setTypeface(font);
		
		Typeface font2 = Typeface.createFromAsset(getAssets(), "font2.ttf"); 
		nasl = (TextView) findViewById(R.id.textView2);
		nasl.setTypeface(font2);
		
	}
}
