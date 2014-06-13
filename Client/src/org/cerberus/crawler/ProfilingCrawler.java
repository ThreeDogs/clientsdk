// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProfilingCrawler.java

package org.cerberus.crawler;

import org.cerberus.index.CerberusAPI;
import org.cerberus.profile.cpu.CpuDump;
import org.cerberus.profile.memory.MemoryDump;
import org.cerberus.profile.network.NetworkDump;

import edu.umich.PowerTutor.service.UMLoggerService;
import android.content.Intent;
import android.util.Log;

public class ProfilingCrawler extends Thread {

	private static ProfilingCrawler instance;
	private static int inturuptFlag = 0;
	public static Thread thisThread;

    private ProfilingCrawler()
    {
        System.out.println("start");
        thisThread = this;
    }

    public static ProfilingCrawler getInstance()
    {
        if(instance == null)
            instance = new ProfilingCrawler();
        return instance;
    }

    public void run()
    {
        System.out.println("run.");
        while(!thisThread.isInterrupted()) 
        {
            CpuDump.getCpuTrace();
            MemoryDump.getMemoryTrace();
            NetworkDump.getNetworkTrace();
            CerberusAPI.c.startService(new Intent(CerberusAPI.c, UMLoggerService.class));
            
            Log.i("cerberus", (new StringBuilder()).append(System.currentTimeMillis()).append(" profiling...").toString());
            try
            {
                Thread.sleep(1000L);
            }
            catch(InterruptedException e1)
            {
                e1.printStackTrace();
            }
            if(inturuptFlag == 1)
            {
                inturuptFlag = 2;
                break;
            }
        }
    }

    public void stopThread()
    {
        if(thisThread != null) {
            inturuptFlag = 1;
            CerberusAPI.c.stopService(new Intent(CerberusAPI.c, UMLoggerService.class));
        }
    }

    

}
