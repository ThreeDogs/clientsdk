package org.cerberus.index;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CerberusAPI {

	private static final int STATUS_START = 1;
	private static final int STATUS_NONE = 0;
	
	private static int STATUS = STATUS_NONE;
	private Context c;
	
	public CerberusAPI(Context c) {
		this.c = c;
	}
	
	public void start() {
		
		if(STATUS == STATUS_START) {
			return;
		}
		
		Button widgetBtn = new Button(c);
		widgetBtn.setHeight(50);
		widgetBtn.setWidth(50);
		widgetBtn.setTag("CerberusWidgetBtn");
		widgetBtn.setClickable(true);
		widgetBtn.setFocusable(true);
		widgetBtn.setText("Record...");
		
		widgetBtn.setOnClickListener(new OnClickListener() {
			private final int STATUS_RUNNING = 1;
			private final int STATUS_FINISH = 0;
			
			private int status = STATUS_FINISH;
			
			@Override
			public void onClick(View arg0) {

				Toast.makeText(c, "Start scenario recording...", Toast.LENGTH_LONG).show();
				
			}
		});
		
		widgetBtn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent motionEvent) {

				System.out.println(v + " " + motionEvent);
				
//				Toast.makeText(c, "Start scenario recording...", Toast.LENGTH_LONG).show();
				
				Toast toast = Toast.makeText(c.getApplicationContext(),	"Start scenario recording..." , Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				
				return false;
			}
		});
		
		STATUS = STATUS_START;
		
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT
				);
		
		params.gravity = Gravity.LEFT | Gravity.BOTTOM;
		
		WindowManager wm = (WindowManager) c.getSystemService(c.WINDOW_SERVICE);
		wm.addView(widgetBtn, params);
		
	}
	
}
