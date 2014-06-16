package profiling.org.cerberus.jarasm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class OnCreateAdapter extends AdviceAdapter {
	Label l0 = new Label();
	protected OnCreateAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);
	}

	@Override
	protected void onMethodEnter() {
		// TODO Auto-generated method stub
		
		mv.visitLabel(l0);
		mv.visitLineNumber(31, l0);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitVarInsn(LSTORE, 10001);
		

		
//		Label l1 = new Label();
//		mv.visitLabel(l1);
//		mv.visitLineNumber(212, l1);
//		mv.visitTypeInsn(NEW, "org/cerberus/profile/drawtime/FrameDrawTimeDump");
//		mv.visitInsn(DUP);
//		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/profile/drawtime/FrameDrawTimeDump", "<init>", "()V");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/app/Activity", "getWindow", "()Landroid/view/Window;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/Window", "getDecorView", "()Landroid/view/View;");
//		mv.visitLdcInsn(new Integer(16908290));
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "findViewById", "(I)Landroid/view/View;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getRootView", "()Landroid/view/View;");
//		mv.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/profile/drawtime/FrameDrawTimeDump", "frameDrawTime", "(Landroid/view/View;)V");
		
		
		super.onMethodEnter();
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
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/Context", "getApplicationContext", "()Landroid/content/Context;");
		mv.visitLdcInsn(JarAsmTest.apiKey);
		mv.visitInsn(ICONST_0);
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/index/CerberusAPI", "<init>", "(Landroid/content/Context;Ljava/lang/String;Z)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/index/CerberusAPI", "start", "()V");
		
		
		
		Label l13 = new Label();
		mv.visitLabel(l13);
		mv.visitLineNumber(162, l13);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitVarInsn(LSTORE, 6);
		Label l14 = new Label();
		mv.visitLabel(l14);
		mv.visitLineNumber(163, l14);
		mv.visitTypeInsn(NEW, "java/util/HashMap");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V");
		mv.visitVarInsn(ASTORE, 8);
		Label l15 = new Label();
		mv.visitLabel(l15);
		mv.visitLineNumber(164, l15);
		mv.visitVarInsn(ALOAD, 8);
		mv.visitLdcInsn("view_type");
		mv.visitLdcInsn("activity");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l16 = new Label();
		mv.visitLabel(l16);
		mv.visitLineNumber(165, l16);
		mv.visitVarInsn(ALOAD, 8);
		mv.visitLdcInsn("load_start_timestamp");
		mv.visitVarInsn(LLOAD, 10001);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l17 = new Label();
		mv.visitLabel(l17);
		mv.visitLineNumber(166, l17);
		mv.visitVarInsn(ALOAD, 8);
		mv.visitLdcInsn("load_finish_timestamp");
		mv.visitVarInsn(LLOAD, 6);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l18 = new Label();
		mv.visitLabel(l18);
		mv.visitLineNumber(167, l18);
		mv.visitVarInsn(ALOAD, 8);
		mv.visitLdcInsn("view_id");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l19 = new Label();
		mv.visitLabel(l19);
		mv.visitLineNumber(168, l19);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/profile/drawtime/FrameDrawTimeDumpList", "getInstance", "()Ljava/util/List;");
		mv.visitVarInsn(ALOAD, 8);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);
		
		
		Label l22 = new Label();
		mv.visitLabel(l22);
		mv.visitLocalVariable("startActivityTime$", "J", null, l0, l22, 10001);
		mv.visitLocalVariable("finishActivityTime$", "J", null, l14, l22, 6);
		mv.visitLocalVariable("map", "Ljava/util/Map;", null, l15, l22, 8);
		
		
		super.onMethodExit(opcode);
		
	}

	
	

	
	
}
