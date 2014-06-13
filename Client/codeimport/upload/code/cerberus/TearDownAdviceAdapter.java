package upload.code.cerberus;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class TearDownAdviceAdapter extends AdviceAdapter {

	protected TearDownAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);
	}

	@Override
	protected void onMethodExit(int opcode) {
		// TODO Auto-generated method stub
		super.onMethodExit(opcode);
		
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/test/CerberusTestRunner", "tearDown", "()V");
	}
	
	

}
