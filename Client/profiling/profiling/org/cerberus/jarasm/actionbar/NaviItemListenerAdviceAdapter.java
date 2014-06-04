package profiling.org.cerberus.jarasm.actionbar;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class NaviItemListenerAdviceAdapter extends AdviceAdapter{
private String log;
	
	public NaviItemListenerAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc);
		this.log = log;
	}

	
	@Override
	protected void onMethodEnter() {
		
		System.out.println(log);
		
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
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/app/Activity", "getResources", "()Landroid/content/res/Resources;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "getId", "()I");
		mv.visitMethodInsn(INVOKEVIRTUAL, "android/content/res/Resources", "getResourceEntryName", "(I)Ljava/lang/String;");
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
		mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
		
		super.onMethodEnter();
	}

}
