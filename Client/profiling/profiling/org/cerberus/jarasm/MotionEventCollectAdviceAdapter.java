package profiling.org.cerberus.jarasm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;


public class MotionEventCollectAdviceAdapter extends AdviceAdapter{

	private String log;
	
	protected MotionEventCollectAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc);
		this.log = log;
	}

	
	@Override
	protected void onMethodEnter() {
		System.out.println("onMehtodEnter");
//		mv.visitLdcInsn("Cerberus");
//		mv.visitLdcInsn("Start " + log);
//		mv.visitMethodInsn(INVOKESTATIC,
//				"android/util/Log",
//				"i", "(Ljava/lang/String;Ljava/lang/String;)I");
//		mv.visitEnd();
		
//		mv.visitMethodInsn(INVOKESTATIC,
//				"org/cerberus/profile/memory/MemoryDump",
//				"getMemoryTrace", "()V");
		
		
		//-----------------
		
		Label l0 = new Label();
		Label l1 = new Label();
		Label l2 = new Label();
		mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
		Label l3 = new Label();
		mv.visitLabel(l3);
		mv.visitLineNumber(84, l3);
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("d");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		mv.visitLabel(l0);
		mv.visitLineNumber(86, l0);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
		mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
		mv.visitLdcInsn("Click");
		mv.visitLdcInsn("");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getResources", "()Landroid/content/res/Resources;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getId", "()I");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/res/Resources", "getResourceEntryName", "(I)Ljava/lang/String;");
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
		mv.visitLabel(l1);
		mv.visitLineNumber(87, l1);
		Label l4 = new Label();
		mv.visitJumpInsn(GOTO, l4);
		mv.visitLabel(l2);
		mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
		mv.visitVarInsn(ASTORE, 2);
		Label l5 = new Label();
		mv.visitLabel(l5);
		mv.visitLineNumber(88, l5);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(INSTANCEOF, "android/widget/TextView");
		mv.visitJumpInsn(IFEQ, l4);
		Label l6 = new Label();
		mv.visitLabel(l6);
		mv.visitLineNumber(89, l6);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
		mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
		mv.visitLdcInsn("TextClick");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, "android/widget/TextView");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "getText", "()Ljava/lang/CharSequence;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/CharSequence", "toString", "()Ljava/lang/String;");
		mv.visitLdcInsn("");
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
		mv.visitLabel(l4);
		mv.visitLineNumber(92, l4);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("d");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		
		
		
		super.onMethodEnter();
	}
	
	@Override
	protected void onMethodExit(int opcode) {
		System.out.println("onMethodExit - " + opcode);
		
		if(opcode == ATHROW || opcode == ARETURN || opcode == RETURN || opcode == IRETURN) {
			super.onMethodExit(opcode);
			return;
		}
		
		mv.visitVarInsn(ALOAD, 1);
		mv.visitLdcInsn(new Integer(16908290));
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "findViewById", "(I)Landroid/view/View;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getRootView", "()Landroid/view/View;");
		mv.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getContext", "()Landroid/content/Context;");
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/scenario/RuntimeMotionInjector", "injectEvent", "(Landroid/view/View;Landroid/content/Context;)V");
		
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitLdcInsn("Cerberus");
//		mv.visitLdcInsn("finish " + log);
//		mv.visitMethodInsn(INVOKESTATIC,
//				"android/util/Log",
//				"i", "(Ljava/lang/String;Ljava/lang/String;)I");
		
//		mv.visitEnd();
		
//		mv.visitMethodInsn(INVOKESTATIC,
//				"org/cerberus/profile/memory/MemoryDump",
//				"getMemoryTrace", "()V");
		

		super.onMethodExit(opcode);
	}

}
