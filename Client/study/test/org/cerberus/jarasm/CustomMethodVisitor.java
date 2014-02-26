package test.org.cerberus.jarasm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

public class CustomMethodVisitor extends MethodNode {

	public CustomMethodVisitor(int access, String name, String desc,
        String signature, String[] exceptions, MethodVisitor mv) {
		super(access, name, desc, signature, exceptions);
		this.mv = mv;
	}

	public CustomMethodVisitor( int access,
			         String name,
			         String desc,
			         String signature,
			         String[] exceptions) {
		super(  access,
		          name,
		          desc,
		          signature,
		          exceptions);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		
		System.out.println("call..ed");
		
		super.visitMethodInsn( opcode,  owner,  name,  desc);
		
//		Label l1 = new Label();
//		mv.visitLabel(l1);
////		mv.visitLineNumber(13, l1);
//		mv.visitLdcInsn("Log");
//		mv.visitLdcInsn("Test...");
//		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I");
//		mv.visitInsn(Opcodes.POP);
		
		
		
		
	}

	
	
}
