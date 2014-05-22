package test.org.cerberus.jarasm.actionbar;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ActionbarFragmentOnStartAdviceAdapter extends AdviceAdapter {
private String log;
	
	public ActionbarFragmentOnStartAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc + " " +  log);
		this.log = log;
	}

	


	@Override
	protected void onMethodEnter() {
		// TODO Auto-generated method stub
		super.onMethodEnter();
	}

	@Override
	protected void onMethodExit(int arg0) {
		
		System.out.println("==== " + arg0 + " " + log);
		
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/FragmentActivity", "getWindow", "()Landroid/view/Window;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/Window", "getDecorView", "()Landroid/view/View;");
		mv.visitLdcInsn(new Integer(16908290));
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "findViewById", "(I)Landroid/view/View;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getRootView", "()Landroid/view/View;");
		mv.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/FragmentActivity", "getApplicationContext", "()Landroid/content/Context;");
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/scenario/RuntimeMotionInjector", "injectEvent", "(Landroid/view/View;Landroid/content/Context;)V");
//
//		mv.visitTypeInsn(NEW, "org/cerberus/index/CerberusAPI");
//		mv.visitInsn(DUP);
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
//		mv.visitLdcInsn("");
//		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/index/CerberusAPI", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/index/CerberusAPI", "start", "()V");
	
//		mv.visitCode();
//		Label l0 = new Label();
//		mv.visitLabel(l0);
//		mv.visitLineNumber(62, l0);
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/FragmentActivity", "getWindow", "()Landroid/view/Window;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/Window", "getDecorView", "()Landroid/view/View;");
//		mv.visitLdcInsn(new Integer(16908290));
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "findViewById", "(I)Landroid/view/View;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getRootView", "()Landroid/view/View;");
//		mv.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/FragmentActivity", "getApplicationContext", "()Landroid/content/Context;");
//		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/scenario/RuntimeMotionInjector", "injectEvent", "(Landroid/view/View;Landroid/content/Context;)V");
//		Label l1 = new Label();
//		mv.visitLabel(l1);
//		mv.visitLineNumber(65, l1);
//		mv.visitTypeInsn(NEW, "org/cerberus/index/CerberusAPI");
//		mv.visitInsn(DUP);
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/support/v4/app/Fragment", "getActivity", "()Landroid/support/v4/app/FragmentActivity;");
//		mv.visitLdcInsn("");
//		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/index/CerberusAPI", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/index/CerberusAPI", "start", "()V");
//		
//		super.onMethodExit(arg0);		
		
	}


}