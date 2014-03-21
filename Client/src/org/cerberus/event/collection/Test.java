package org.cerberus.event.collection;
import org.cerberus.index.CerberusAPI;

import android.app.Activity;


public class Test extends Activity{

	public void main(String[] args) {
		
		System.out.println();
		
		new CerberusAPI(this).start();
		
	}
}
