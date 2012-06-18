package three.musketeers.activity;

import three.musketeers.namespace.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		Button but1 = (Button) findViewById(R.id.Button03);
		Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf"); 
		but1.setTypeface(font);
		
		TextView nasl = (TextView) findViewById(R.id.textView1);
		nasl.setTypeface(font);
		
		but1 = (Button) findViewById(R.id.Button02);
		but1.setTypeface(font);
		but1 = (Button) findViewById(R.id.Button01);
		but1.setTypeface(font);
	}
	
	public void igraj_OnClick(View sender)
	{
		Intent pojdi = new Intent(this, Igra.class);
		startActivity(pojdi);
	
	}
	
	public void start_OnClick(View sender)
	{
		
		
	}
	

}
