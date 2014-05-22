package test.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ActionBarMotionEventCollectAdviceAdapter extends AdviceAdapter {

	private String log ;
	
	protected ActionBarMotionEventCollectAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc, String log) {
		super(api, mv, access, name, desc);
		
		this.log = log;
	}

}
