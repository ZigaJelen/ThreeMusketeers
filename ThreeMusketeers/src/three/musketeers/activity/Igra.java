package three.musketeers.activity;

import three.musketeers.namespace.R;
import three.musketeers.namespace.ThreeMusketeersActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Igra extends Activity {

	public final static int CODE = 2253;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.igra);

		Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf"); 
		
		TextView nasl = (TextView) findViewById(R.id.textView1);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.textView2);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.TextView01);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.TextView02);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.TextView03);
		nasl.setTypeface(font);
		nasl = (TextView) findViewById(R.id.textView3);
		nasl.setTypeface(font);
		
		Typeface font2 = Typeface.createFromAsset(getAssets(), "font2.ttf"); 
		EditText ed = (EditText) findViewById(R.id.EditText01);
		ed.setTypeface(font2);
		ed = (EditText) findViewById(R.id.EditText02);
		ed.setTypeface(font2);
		ed = (EditText) findViewById(R.id.EditText03);
		ed.setTypeface(font2);
		ed = (EditText) findViewById(R.id.editText1);
		ed.setTypeface(font2);
		
		Button btn = (Button) findViewById(R.id.nazaj);
		btn.setTypeface(font);
		btn = (Button) findViewById(R.id.start);
		btn.setTypeface(font);

	}
	
	public void button_Click(View sender)
	{
		Button btn = (Button) sender;
		int tmp = Integer.parseInt(btn.getText().toString());
		tmp++;
		btn.setText(tmp+"");
		if(tmp==4)
		{
			btn.setText("1");
			tmp=1;
		}
		
		if(btn.equals(findViewById(R.id.Button03)))
		{
			if(tmp != 1) 
			{
				LinearLayout el = (LinearLayout) findViewById(R.id.igrmussket);
				el.setVisibility(View.VISIBLE);
				EditText ed = (EditText) findViewById(R.id.EditText02);
				ed.setText("Comp 1");
			}
			else
			{
				LinearLayout el = (LinearLayout) findViewById(R.id.igrmussket);
				el.setVisibility(View.GONE);
				EditText ed = (EditText) findViewById(R.id.EditText02);
				ed.setText("Igrale 1");
			}
		}
		else if(btn.equals(findViewById(R.id.Button01)))
		{
			if(tmp != 1) 
			{
				LinearLayout el = (LinearLayout) findViewById(R.id.nasprotnik);
				el.setVisibility(View.VISIBLE);
				EditText ed = (EditText) findViewById(R.id.editText1);
				ed.setText("Comp 2");
			}
			else
			{
				LinearLayout el = (LinearLayout) findViewById(R.id.nasprotnik);
				el.setVisibility(View.GONE);
				EditText ed = (EditText) findViewById(R.id.editText1);
				ed.setText("Igrale 2");
			}
		}
		btn.setBackgroundDrawable(zamenjavaSlikice(tmp));
	}
	
	private Drawable zamenjavaSlikice(int in)
	{
		if(in == 2)
		{
			return getResources().getDrawable(R.drawable.mcts);
		}
		else if(in == 1)
		{
			return getResources().getDrawable(R.drawable.musketeers2);
		}
		
		return getResources().getDrawable(R.drawable.alfabeta);
	}
	
	public void nazaj_OnClick(View sender)
	{
		
		finish();
	}

	public void start_OnClick(View sender)
	{
		Intent pojdi = new Intent(this, ThreeMusketeersActivity.class);
		
		Button btn = (Button) findViewById(R.id.Button03);
		pojdi.putExtra("muss", btn.getText());
		btn = (Button) findViewById(R.id.Button01);
		pojdi.putExtra("all", btn.getText());
		
		EditText ed = (EditText) findViewById(R.id.EditText02);
		pojdi.putExtra("imem", ed.getText().toString());
		ed = (EditText) findViewById(R.id.editText1);
		pojdi.putExtra("imee", ed.getText().toString());
		
		ed = (EditText) findViewById(R.id.EditText01);
		pojdi.putExtra("cas1", ed.getText().toString());
		ed = (EditText) findViewById(R.id.EditText03);
		pojdi.putExtra("cas2", ed.getText().toString());
		
		startActivityForResult(pojdi, CODE);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == CODE && resultCode >0 && resultCode<3)
		{
			Intent pojdi = new Intent(this, Konec.class);
			
			if(resultCode==1) 
			{
				EditText ed = (EditText) findViewById(R.id.EditText02);
				pojdi.putExtra("zmg", ed.getText().toString());
			}
			else
			{
				EditText ed = (EditText) findViewById(R.id.editText1);
				pojdi.putExtra("zmg", ed.getText().toString());
			}
			startActivity(pojdi);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
