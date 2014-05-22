package org.cerberus.index;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.cerberus.event.collection.MotionCollector;
import org.cerberus.scenario.NetworkMotionStream;
import org.cerberus.service.AlwaysTopButtonService;

import android.content.Context;
import android.content.Intent;
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
    public static String apiKey;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    public static int index = 0x1000;
	public boolean isRecording;
	
	public CerberusAPI(Context c, String apiKey) {
		this.c = c;
		this.apiKey = apiKey;
		isRecording = true;
	}
	public CerberusAPI(Context c, String apiKey, boolean isRecording) {
		this.c = c;
		this.apiKey = apiKey;
		this.isRecording = isRecording;
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
		
		if (isRecording) {
			c.startService(new Intent( c , AlwaysTopButtonService.class));
		}
	}
	
//	public void a(){
//		ProfilingCrawler.getInstance().start();
//	}
}
