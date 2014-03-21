package scenario.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class OnCreateAdapter extends AdviceAdapter {

	protected OnCreateAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);
	}

	@Override
	protected void onMethodEnter() {
		// TODO Auto-generated method stub
		super.onMethodEnter();
	}

	@Override
	protected void onMethodExit(int opcode) {
		
		System.out.println("======================" + opcode);
		
		mv.visitTypeInsn(NEW, "org/cerberus/index/CerberusAPI");
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/index/CerberusAPI", "<init>", "(Landroid/content/Context;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/index/CerberusAPI", "start", "()V");
		
//		super.onMethodExit(opcode);
		
	}

	
	

	
	
}
