package test.org.cerberus.scenario;

import org.omg.CosNaming.IstringHelper;
import org.ow2.asmdex.MethodVisitor;
import org.ow2.asmdex.Opcodes;

public class MethodAdapterAnnotateCalls extends MethodVisitor {

	public MethodAdapterAnnotateCalls(int api, MethodVisitor mv) {
		super(api, mv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, int[] arguments) {
		// TODO Auto-generated method stub
//		super.visitMethodInsn(opcode, owner, name, desc, arguments);
		
//		boolean isStatic;
//		String singnature;
//		switch(opcode) {
//		case Opcodes.INSN_INVOKE_STATIC:
//		case Opcodes.INSN_INVOKE_STATIC_RANGE:
//			isStatic=true;
//			break;
//		default:
//			isStatic = false;	
//		}
//		
		
		mv.visitMethodInsn(opcode, owner, name, desc, arguments);
		
	}

	
	
}
