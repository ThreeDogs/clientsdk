package upload.code.cerberus;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class SetupAdviceAdapter extends AdviceAdapter{

	protected SetupAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);
	}

	@Override
	protected void onMethodExit(int opcode) {
		// TODO Auto-generated method stub
		super.onMethodExit(opcode);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/test/CerberusTestRunner", "setUp", "()V");
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name,
			String desc) {

		if(name.equals("solo") && owner.equals("org/cerberus/test/CerberusTestRunner")) {
			owner = "org/cerberus/test/CerberusRunner_" + JavaCompileInfo.index;
		}
		
		super.visitFieldInsn(opcode, owner, name, desc);
	}
	
	
	
}
