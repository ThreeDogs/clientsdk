package profiling.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import scenario.org.cerberus.jarasm.JarAsmTest;

public class OnStartAdviceAdapter extends AdviceAdapter {

private String log;
	
	protected OnStartAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc);
		this.log = log;
	}
	
	@Override
	protected void onMethodExit(int opcode) {
		
		System.out.println("======================" + opcode);
		
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/app/Activity", "getWindow", "()Landroid/view/Window;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/Window", "getDecorView", "()Landroid/view/View;");
		mv.visitLdcInsn(new Integer(16908290));
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "findViewById", "(I)Landroid/view/View;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getRootView", "()Landroid/view/View;");
		mv.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/scenario/RuntimeMotionInjector", "injectEvent", "(Landroid/view/View;Landroid/content/Context;)V");
		
		
		mv.visitTypeInsn(NEW, "org/cerberus/index/CerberusAPI");
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitLdcInsn(JarAsmTest.apiKey);
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/index/CerberusAPI", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/index/CerberusAPI", "start", "()V");
		
//		super.onMethodExit(opcode);
		
	}
	
	
}
