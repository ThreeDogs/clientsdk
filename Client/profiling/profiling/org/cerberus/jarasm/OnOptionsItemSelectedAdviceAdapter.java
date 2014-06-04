package profiling.org.cerberus.jarasm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class OnOptionsItemSelectedAdviceAdapter extends AdviceAdapter{

	private String log;
	
	protected OnOptionsItemSelectedAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc);
		this.log = log;
	}

	
	@Override
	protected void onMethodEnter() {
		
		System.out.println(log);
		
//		Label l0 = new Label();
//		Label l1 = new Label();
//		Label l2 = new Label();
//		mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
//		mv.visitLabel(l0);
////		mv.visitLineNumber(77, l0);
//		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
//		mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
//		mv.visitInsn(DUP);
//		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
//		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
//		mv.visitLdcInsn("Click");
//		mv.visitLdcInsn("");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/app/Activity", "getResources", "()Landroid/content/res/Resources;");
//		mv.visitVarInsn(ALOAD, 1);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
//		mv.visitLdcInsn("getItemId");
//		mv.visitInsn(ICONST_0);
//		mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");
//		mv.visitVarInsn(ALOAD, 1);
//		mv.visitInsn(ICONST_0);
//		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
//		mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/res/Resources", "getResourceEntryName", "(I)Ljava/lang/String;");
//		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
//		mv.visitLabel(l1);
//		Label l3 = new Label();
//		mv.visitJumpInsn(GOTO, l3);
//		mv.visitLabel(l2);
//		mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
//		mv.visitVarInsn(ASTORE, 2);
//		mv.visitLabel(l3);
		
		
		
//		Label l0 = new Label();
//		Label l1 = new Label();
//		Label l2 = new Label();
//		mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
//		mv.visitLabel(l0);
//		mv.visitLineNumber(55, l0);
//		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
//		mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
//		mv.visitInsn(DUP);
//		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
//		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
//		mv.visitLdcInsn("Click");
//		mv.visitLdcInsn("");
//		mv.visitVarInsn(ALOAD, 0);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "com/autoschedule/proto/index/TaskIndexCircleGraph", "getResources", "()Landroid/content/res/Resources;");
//		mv.visitVarInsn(ALOAD, 1);
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
//		mv.visitLdcInsn("getItemId");
//		mv.visitInsn(ICONST_0);
//		mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");
//		mv.visitVarInsn(ALOAD, 1);
//		mv.visitInsn(ICONST_0);
//		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
//		mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/res/Resources", "getResourceEntryName", "(I)Ljava/lang/String;");
//		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
//		mv.visitLabel(l1);
//		Label l3 = new Label();
//		mv.visitJumpInsn(GOTO, l3);
//		mv.visitLabel(l2);
//		mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
//		mv.visitVarInsn(ASTORE, 2);
//		mv.visitLabel(l3);
//		mv.visitLineNumber(56, l3);
//		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//		mv.visitLdcInsn("dd");
//		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		Label l0 = new Label();
		Label l1 = new Label();
		Label l2 = new Label();
		mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
		Label l3 = new Label();
		mv.visitLabel(l3);
		mv.visitLineNumber(128, l3);
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("d");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		mv.visitLabel(l0);
		mv.visitLineNumber(130, l0);
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
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/util/ClassCastUtil", "getResource", "(Ljava/lang/Object;)Landroid/content/res/Resources;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
		mv.visitLdcInsn("getItemId");
		mv.visitInsn(ICONST_0);
		mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitInsn(ICONST_0);
		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/res/Resources", "getResourceEntryName", "(I)Ljava/lang/String;");
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
		mv.visitLabel(l1);
		Label l4 = new Label();
		mv.visitJumpInsn(GOTO, l4);
		mv.visitLabel(l2);
		mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Exception"});
		mv.visitVarInsn(ASTORE, 2);
		mv.visitLabel(l4);
		mv.visitLineNumber(131, l4);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitLdcInsn("d");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		
		
		super.onMethodEnter();
	}

}
