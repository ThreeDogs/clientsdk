package scenario.org.cerberus.jarasm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

public class CustomMethodVisitor extends MethodNode {

	public CustomMethodVisitor(int access, String name, String desc,
        String signature, String[] exceptions, MethodVisitor mv) {
		super(access, name, desc, signature, exceptions);
		System.out.println("..call");
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
		
		System.out.println("visitMethodInsn  " + opcode + " " +  access + " " + name +  access + " " + signature
				 +  access + " " + access + " " );
	
		super.visitMethodInsn( opcode,  owner,  name,  desc);
		
	}

	@Override
	public void visitCode() {
		super.visitCode();
	}

	@Override
	public void visitLabel(Label label) {
		super.visitLabel(label);
		System.out.println("label   "  + label);
	}

	
	
}
