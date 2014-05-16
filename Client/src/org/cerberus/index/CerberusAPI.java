package org.cerberus.index;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.cerberus.event.collection.MotionCollector;
import org.cerberus.scenario.NetworkMotionStream;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class CerberusAPI {

    private static final int STATUS_START = 1;
    private static final int STATUS_NONE = 0;
    private static int STATUS = 0;
    public static final int STATUS_RUNNING = 1;
    public static final int STATUS_FINISH = 0;
    public static Integer status_ = Integer.valueOf(0);
    private Context c;
    private String apiKey;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

	
	
	public CerberusAPI(Context c, String apiKey) {
		this.c = c;
		this.apiKey = apiKey;
	}
	
	public void start() {
		
		System.out.println("------------------------");
		
		mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            public void uncaughtException(Thread t, Throwable e)
            {
                Log.e("cerberus", (new StringBuilder("error \n")).append(getStackTrace(e)).toString());
                mUncaughtExceptionHandler.uncaughtException(t, e);
            }

            private String getStackTrace(Throwable e)
            {
                Writer result = new StringWriter();
                PrintWriter printWriter = new PrintWriter(result);
                for(Throwable cause = e; cause != null; cause = cause.getCause())
                    cause.printStackTrace(printWriter);

                String stackTraceAsString = result.toString();
                printWriter.close();
                return stackTraceAsString;
            }

        });
		
		
		if(STATUS == STATUS_START) {
			System.out.println("STATUE == STATUS_START");
			return;
		}
		System.out.println("------------------------");
		Button widgetBtn = new Button(c);
		widgetBtn.setHeight(50);
		widgetBtn.setId(0x1000);
		widgetBtn.setWidth(50);
		widgetBtn.setTag("CerberusWidgetBtn");
		widgetBtn.setClickable(true);
		widgetBtn.setFocusable(true);
		widgetBtn.setText("Record...");
		
//		widgetBtn.setOnClickListener(new OnClickListener() {
//			private final int STATUS_RUNNING = 1;
//			private final int STATUS_FINISH = 0;
//			
//			private int status = STATUS_FINISH;
//			
//			@Override
//			public void onClick(View arg0) {
//
//				System.out.println("status = " + status);
//				
//				if(status == STATUS_RUNNING) {
//					status = STATUS_FINISH;
//					
//					//send Network
//					((NetworkMotionStream)MotionCollector.getInstance().getStream()).sendNetworkData();
//					System.out.println("--"   );
//				} else {
//					status = STATUS_RUNNING;
////					Toast.makeText(c, "Start scenario recording...", Toast.LENGTH_LONG).show();
//				}
//				
//			}
//		});
		
		widgetBtn.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == 1)
                    if(CerberusAPI.status_.intValue() == 1)
                    {
                        CerberusAPI.status_ = Integer.valueOf(0);
                        ((NetworkMotionStream)MotionCollector.getInstance().getStream()).sendNetworkData();
                        System.out.println("--");
                    } else
                    {
                        CerberusAPI.status_ = Integer.valueOf(1);
                        ((NetworkMotionStream)MotionCollector.getInstance().getStream()).getScenarioId(apiKey);
                        Toast toast = Toast.makeText(c.getApplicationContext(), "Start scenario recording...", 1);
                        toast.setGravity(17, 0, 0);
                        toast.show();
                    }
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
	
//	public void a(){
//		ProfilingCrawler.getInstance().start();
//	}
}
