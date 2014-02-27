package test.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class MotionEventCollectAdviceAdapter extends AdviceAdapter{

	protected MotionEventCollectAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc) {
		super(api, mv, access, name, desc);
	}

	
	@Override
	protected void onMethodEnter() {
		System.out.println("onMehtodEnter");
		
		mv.visitLdcInsn("Cerberus");
		mv.visitLdcInsn("doOnClickListener");
		mv.visitMethodInsn(INVOKESTATIC,
				"android/util/Log",
				"i", "(Ljava/lang/String;Ljava/lang/String;)I");
//		mv.visitEnd();
		
		super.onMethodEnter();
	}
	
	@Override
	protected void onMethodExit(int opcode) {
		System.out.println("onMethodExit - " + opcode);
//		mv.visitVarInsn(ALOAD, 0);
		mv.visitLdcInsn("Cerberus");
		mv.visitLdcInsn("finish ... OnClickListener");
		mv.visitMethodInsn(INVOKESTATIC,
				"android/util/Log",
				"i", "(Ljava/lang/String;Ljava/lang/String;)I");
		
//		mv.visitEnd();
		super.onMethodExit(opcode);
	}

}
