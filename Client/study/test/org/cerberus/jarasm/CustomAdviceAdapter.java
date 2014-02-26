package test.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class CustomAdviceAdapter extends AdviceAdapter {

	protected CustomAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);

	
		}

	@Override
	protected void onMethodExit(int opcode) {
//		mv.visitVarInsn(ALOAD, 0);
		mv.visitLdcInsn("Log");
		mv.visitLdcInsn("Test...");
		mv.visitMethodInsn(INVOKESTATIC,
				"android/util/Log",
				"i", "(Ljava/lang/String;Ljava/lang/String;)I");
		
		mv.visitEnd();
				
	}

	
}
