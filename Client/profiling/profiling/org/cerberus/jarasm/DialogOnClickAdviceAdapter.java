package profiling.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class DialogOnClickAdviceAdapter extends AdviceAdapter {

	protected DialogOnClickAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMethodEnter() {
		super.onMethodEnter();
		
		
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
		mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
		mv.visitLdcInsn("AlertDialogClick");
		mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
		mv.visitInsn(DUP);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;");
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
		mv.visitLdcInsn("");
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
		
	}
	
	
	
}
