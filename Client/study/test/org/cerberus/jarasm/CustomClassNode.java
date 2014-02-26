package test.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class CustomClassNode extends ClassNode {

	
	
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

		
		if(name.startsWith("<") || (0<name.indexOf("$"))) {
			return super.visitMethod(access, name, desc, signature, exceptions);
		}
		System.out.println("		" + name );
//		
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		if(name.equals("onCreate")) {
			CustomAdviceAdapter caa = new CustomAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
			return caa;
		} else {
			return mv;
		}
		
		
		
//		
//		CustomMethodVisitor  customMV = new CustomMethodVisitor( access,  name, desc, signature, exceptions,mv);
//		customMV.visitMethodInsn( access,  name,  desc,  signature);
//		return customMV;
	}

	
	
}
