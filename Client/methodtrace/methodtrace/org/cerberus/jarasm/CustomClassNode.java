package methodtrace.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class CustomClassNode extends ClassNode {

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		// TODO Auto-generated method stub

		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		
		if(name.indexOf("init") ==-1) {
			return new MethodAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		}
		
		return mv;
	}

	
	
}
