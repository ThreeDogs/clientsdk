// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RuntimeMotionInjector.java

package org.cerberus.scenario;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.cerberus.event.collection.MotionCollector;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

public class RuntimeMotionInjector {

	public RuntimeMotionInjector() {
	}

	public static void inJectEventStart(Context context1) {
	}

	public static void injectEvent(View v, Context context)
    {
        System.out.println(v);
        if(v instanceof ViewGroup)
        {
            ViewGroup vGroup = (ViewGroup)v;
            
            System.out.println((new StringBuilder("is ViewGroup (")).append(vGroup.getChildCount()).append(") ").append(v).toString());
            for(int i = 0; i < vGroup.getChildCount(); i++)
            {
                System.out.println((new StringBuilder("call \n")).append(vGroup.getChildAt(i)).append("\n").append(v).toString());
                injectEvent(vGroup.getChildAt(i), context);
            }

            try
            {
                if(v != null)
                {
                	checkDrag(v, context);
                	checkClick(v,context);
                }
            }
            catch(Exception exception) { }
        } else
        {
            try
            {
                if(v != null)
                {
                	checkDrag(v, context);
                   checkClick(v,context);
                }
            }catch (Exception e) {
            	
            }
        }
    }
	
	private static void checkDrag(View v, final Context c) {
		
		Field f = null;
        try
        {
            f = v.getClass().getDeclaredField("mListenerInfo");
            f.setAccessible(true);
        }
        catch(NoSuchFieldException e)
        {
            System.out.println("NoSuchMethod - mListenerInfo");
            try
            {
                f = View.class.getDeclaredField("mListenerInfo");
                f.setAccessible(true);
            }
            catch(NoSuchFieldException e1)
            {
                e1.printStackTrace();
            }
        }
		
		
		
		//----
		try
        {
            Object mListenerInfo = f.get(v);
            if(mListenerInfo != null)
            {
                Field f2 = mListenerInfo.getClass().getDeclaredField("mOnTouchListener");
                f2.setAccessible(true);
                final OnTouchListener thisListener = (OnTouchListener)f2.get(mListenerInfo);
//                System.out.println(Arrays.toString(thisListener.getClass().getDeclaredFields()));
                try
                {
                    if(thisListener.getClass().getDeclaredField("CERBERUS_FLAG_GENERATE") == null)
                        Log.d("cerberus", "this View is Cerberus View");
                }
                catch(Exception e)
                {
                	
                	v.setOnTouchListener(
                			new OnTouchListener(){
                	            private boolean moving = false;//stupid state
                	            private int x;
                	            private int y;
                	            private int x_new;
                	            private int y_new;
                	            
                	            private int CERBERUS_FLAG_GENERATE = 1;
                	            @Override
                	            public boolean onTouch(View v, MotionEvent event) {
                	                switch( event.getAction() ){
                	                case MotionEvent.ACTION_DOWN:
                	                    x = (int)event.getX();
                	                    y = (int)event.getY();
                	                    return false;
                	                case MotionEvent.ACTION_MOVE:
                	                	moving = true;
                	                    if( moving ){
                	                        x_new = (int)event.getX();
                	                        y_new = (int)event.getY();
                	                    }
                	                    return false;
                	                case MotionEvent.ACTION_UP:
                	                    
                	                    if(moving)
                	                    	 try{
                     	                    	MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()), getClass().getName(), "drag", x+","+y+","+x_new+","+y_new, v.getResources().getResourceEntryName(v.getId())));
                     	                    }catch (Exception e) {
         										try{
                     	                    	System.err.println(e.getMessage() + "");
         										}catch (Exception e2) {
         										}
         									}
                	                    moving = false;
                	                    return false;
                	            }
                	            if(thisListener !=null)
                	            	return thisListener.onTouch(v, event);
                	            else 
                	            	return false;
                	        }        	        
                		});
                }
            } else
            {
                System.out.println("onListener is null");
                System.out.println("make onClickListener");
                v.setOnTouchListener(
            			new OnTouchListener(){
            	            private boolean moving = false;//stupid state
            	            private int x;
            	            private int y;
            	            private int x_new;
            	            private int y_new;
            	            
            	            private int CERBERUS_FLAG_GENERATE = 1;
            	            @Override
            	            public boolean onTouch(View v, MotionEvent event) {
            	                switch( event.getAction() ){
            	                case MotionEvent.ACTION_DOWN:
            	                    x = (int)event.getX();
            	                    y = (int)event.getY();
            	                    return false;
            	                case MotionEvent.ACTION_MOVE:
            	                	moving = true;
            	                    if( moving ){
            	                        x_new = (int)event.getX();
            	                        y_new = (int)event.getY();
            	                    }
            	                    return false;
            	                case MotionEvent.ACTION_UP:
            	                    
            	                    if(moving)
            	                    try{
            	                    	MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()), getClass().getName(), "drag", x+","+y+","+x_new+","+y_new, v.getResources().getResourceEntryName(v.getId())));
            	                    }catch (Exception e) {
										try{
            	                    	System.err.println(e.getMessage() + "");
										}catch (Exception e2) {
										}
									}
            	                    moving = false;
            	                    return false;
            	            }
            	            return false;
            	        }        	        
            		});
            }
            
            if(v instanceof EditText) {
            	
            	final EditText et = (EditText) v;
            	
            	et.addTextChangedListener(new TextWatcher() {
        			
        			@Override
        			public void onTextChanged(CharSequence s, int start, int before, int count) {
        				MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()),getClass().getName(),"EditText", s.toString(), c.getResources().getResourceEntryName(et.getId()) ));
        			}
        			
        			@Override
        			public void beforeTextChanged(CharSequence s, int start, int count,
        					int after) {
        			}
        			
        			@Override
        			public void afterTextChanged(Editable s) {
        			}
        		});
            	
            	
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	private static void checkClick(View v,final Context c) {
		
		
		Field f = null;
        try
        {
            f = v.getClass().getDeclaredField("mListenerInfo");
            f.setAccessible(true);
        }
        catch(NoSuchFieldException e)
        {
            System.out.println("NoSuchMethod - mListenerInfo");
            try
            {
                f = View.class.getDeclaredField("mListenerInfo");
                f.setAccessible(true);
            }
            catch(NoSuchFieldException e1)
            {
                e1.printStackTrace();
            }
        }
        try
        {
            Object mListenerInfo = f.get(v);
            if(mListenerInfo != null)
            {
                Field f2 = mListenerInfo.getClass().getDeclaredField("mOnClickListener");
                f2.setAccessible(true);
                final OnClickListener thisListener = (OnClickListener)f2.get(mListenerInfo);
//                System.out.println(Arrays.toString(thisListener.getClass().getDeclaredFields()));
                try
                {
                    if(thisListener.getClass().getDeclaredField("CERBERUS_FLAG_GENERATE") == null)
                        Log.d("cerberus", "this View is Cerberus View");
                }
                catch(Exception e)
                {
                	
                    v.setOnClickListener(new  OnClickListener() {
						
                        public void onClick(View view)
                        {
                            Log.i("cerberus", "TestLogg...........");
                            
                            try{
                                MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()), getClass().getName(), "Click", "", view.getResources().getResourceEntryName(view.getId())));
    	                    }catch (Exception e) {
								try{
    	                    	System.err.println(e.getMessage() + "");
								}catch (Exception e2) {
								}
							}
                            
                            
                            if(thisListener !=null)
                            	thisListener.onClick(view);
//                            RuntimeMotionInjector.injectEvent((FrameLayout) view.getRootView(), c);
                        }

                        private int CERBERUS_FLAG_GENERATE = 1;

					});
                }
            } else
            {
                System.out.println("onListener is null");
                System.out.println("make onClickListener");
                v.setOnClickListener(new  OnClickListener() {
					
                    public void onClick(View view)
                    {
                        Log.i("cerberus", "TestLogg...........");
                        try{
                            MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()), getClass().getName(), "Click", "", view.getResources().getResourceEntryName(view.getId())));
	                    }catch (Exception e) {
							try{
	                    	System.err.println(e.getMessage() + "");
							}catch (Exception e2) {
							}
						}
//                        RuntimeMotionInjector.injectEvent((FrameLayout) view.getRootView(), c);
                    }

                    private int CERBERUS_FLAG_GENERATE = 1;

				});
            }
            
            if(v instanceof EditText) {
            	
            	final EditText et = (EditText) v;
            	
            	et.addTextChangedListener(new TextWatcher() {
        			
        			@Override
        			public void onTextChanged(CharSequence s, int start, int before, int count) {
        				MotionCollector.getInstance().putMotion(new MotionVO(Long.valueOf(System.currentTimeMillis()),getClass().getName(),"EditText", s.toString(), c.getResources().getResourceEntryName(et.getId()) ));
        			}
        			
        			@Override
        			public void beforeTextChanged(CharSequence s, int start, int count,
        					int after) {
        			}
        			
        			@Override
        			public void afterTextChanged(Editable s) {
        			}
        		});
            	
            	
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}
}
