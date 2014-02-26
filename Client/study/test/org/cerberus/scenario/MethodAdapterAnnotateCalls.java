package test.org.cerberus.scenario;

import java.util.Arrays;

import org.omg.CosNaming.IstringHelper;
import org.ow2.asmdex.MethodVisitor;
import org.ow2.asmdex.Opcodes;

public class MethodAdapterAnnotateCalls extends MethodVisitor {

	public MethodAdapterAnnotateCalls(int api, MethodVisitor mv) {
		super(api, mv);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, int[] arguments) {
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


//		System.out.println("opcode - " + opcode);
//		System.out.println("owner - " + owner);
//		System.out.println("name - " + name);
//		System.out.println("desc - " + desc);
//		System.out.println("arguments - " + Arrays.toString(arguments));
		
		
		
		mv.visitMethodInsn(opcode, owner, name, desc, arguments);
		
	}

	
	
}
