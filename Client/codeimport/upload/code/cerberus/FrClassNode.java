package upload.code.cerberus;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class FrClassNode extends ClassNode {

	private String index;
	
	
	public FrClassNode(int api, String index) {
//		super(api);
		this.index = index;
	}


	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		System.out.println(name);
		super.visit(version, access, "org/cerberus/test/CerberusRunner_" + index, signature, "org/cerberus/test/CerberusActivityInstrumentation", interfaces);
	}


	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		
		
		if(name.equals("setUp")) {
			return new SetupAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		} else if (name.equals("tearDown")) {
			return new TearDownAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		} else if(name.equals("<clinit>")){
			return new ClientAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		} else if (name.equals("<init>")) {
			return new InitAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		} else if (name.equals("testRun")) {
			return new TestRunAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
		} else if (name.equals("findView")){
			return null;
		}
		
		return mv;
	}


	
	

	


	
}
