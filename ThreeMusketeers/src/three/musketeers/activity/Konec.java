package three.musketeers.activity;

import three.musketeers.namespace.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Konec extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.konec);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf"); 
		
		TextView nasl = (TextView) findViewById(R.id.textView2);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.textView1);
		nasl.setTypeface(font);
		Typeface font2 = Typeface.createFromAsset(getAssets(), "font2.ttf"); 
		nasl = (TextView) findViewById(R.id.TextView01);
		nasl.setTypeface(font2);
		
		nasl.setText(getIntent().getStringExtra("zmg"));

		Button btn = (Button) findViewById(R.id.nazaj);
		btn.setTypeface(font);
		
	}
	
	public void nazaj_OnClick(View sender)
	{
		finish();
	}

	

}
