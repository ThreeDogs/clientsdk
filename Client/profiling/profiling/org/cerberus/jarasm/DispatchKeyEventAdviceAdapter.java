package profiling.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class DispatchKeyEventAdviceAdapter extends AdviceAdapter {

	private String log;

	protected DispatchKeyEventAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log) {
		super(api, mv, access, name, desc);
		this.log = log;
	}

	@Override
	protected void onMethodEnter() {
		System.out.println("onMehtodEnter");

		mv.visitLdcInsn("Cerberus");
		mv.visitLdcInsn("Start " + log);
		mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I");
		// mv.visitEnd();
		mv.visitMethodInsn(INVOKESTATIC,
				"org/cerberus/profile/memory/MemoryDump", "getMemoryTrace",
				"()V");

		super.onMethodEnter();
	}

	@Override
	protected void onMethodExit(int opcode) {

		if (opcode == ATHROW) {
			super.onMethodExit(opcode);
			return;
		}

		// mv.visitVarInsn(ALOAD, 0);
		mv.visitLdcInsn("Cerberus");
		mv.visitLdcInsn("finish " + log);
		mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i",
				"(Ljava/lang/String;Ljava/lang/String;)I");

		// mv.visitEnd();
		mv.visitMethodInsn(INVOKESTATIC,
				"org/cerberus/profile/memory/MemoryDump", "getMemoryTrace",
				"()V");

		super.onMethodExit(opcode);
	}

}
