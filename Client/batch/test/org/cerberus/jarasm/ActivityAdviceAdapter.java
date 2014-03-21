package test.org.cerberus.jarasm;

import java.util.Arrays;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ActivityAdviceAdapter extends AdviceAdapter {

	protected ActivityAdviceAdapter(int api, MethodVisitor mv, int access,
			String name, String desc) {
		super(api, mv, access, name, desc);

	}

	@Override
	protected void onMethodExit(int opcode) {
		System.out.println("onMethodExit - " + opcode);
//		{
//			mv.visitLdcInsn("Log");
//			mv.visitLdcInsn("Test...");
//			mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i",
//					"(Ljava/lang/String;Ljava/lang/String;)I");
//		}
//		{
			
			
//		}
		super.onMethodExit(opcode);
	}

	@Override
	public void visitCode() {
		// TODO Auto-generated method stub
		super.visitCode();
	}

	@Override
	public void visitLabel(Label label) {

		System.out.println("visitLabel : " + label);
		super.visitLabel(label);
	}

	@Override
	public void visitInvokeDynamicInsn(String name, String desc, Handle bsm,
			Object... bsmArgs) {

		System.out.println("visitInvokeDynamicInsn : " + " " + name + " "
				+ desc + " " + bsm + " " + bsmArgs);

		super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
	}

	@Override
	public void visitInsn(int opcode) {
		System.out.println("visitInsn : " + opcode);
		super.visitInsn(opcode);
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		System.out.println("visitVarInsn : " + opcode + " " + var);

		super.visitVarInsn(opcode, var);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name,
			String desc) {
		System.out.println("visitFieldInsn : " + opcode + " " + owner + " "
				+ name + " " + desc);
		super.visitFieldInsn(opcode, owner, name, desc);
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		System.out.println("visitIntInsn : " + opcode + " " + operand);
		super.visitIntInsn(opcode, operand);
	}

	@Override
	public void visitLdcInsn(Object cst) {
		System.out.println("visitLdcInsn : " + cst);
		super.visitLdcInsn(cst);
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		System.out.println("visitMultiANewArrayInsn : " + desc + " " + dims);
		;
		super.visitMultiANewArrayInsn(desc, dims);
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		System.out.println("visitTypeInsn : " + opcode + " " + type);
		super.visitTypeInsn(opcode, type);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		System.out.println("visitMethodInsn : " + opcode + " " + owner + " "
				+ name + " " + desc);
		super.visitMethodInsn(opcode, owner, name, desc);
	}

	@Override
	public void visitJumpInsn(int opcode, Label label) {
		System.out.println("visitJumpInsn : " + opcode + " " + label);
		super.visitJumpInsn(opcode, label);
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		System.out.println("visitLookupSwitchInsn : " + dflt + " "
				+ Arrays.toString(keys) + " " + Arrays.toString(labels));
		super.visitLookupSwitchInsn(dflt, keys, labels);
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt,
			Label... labels) {
		System.out.println("visitTableSwitchInsn : " + min + " " + max + " "
				+ dflt + " " + Arrays.toString(labels));
		super.visitTableSwitchInsn(min, max, dflt, labels);
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler,
			String type) {
		System.out.println("visitTryCatchBlock : " + start + " " + end + " "
				+ handler + " " + type);
		super.visitTryCatchBlock(start, end, handler, type);
	}

	@Override
	protected void onMethodEnter() {
		System.out.println("onMehtodEnter");
		super.onMethodEnter();
	}

}
