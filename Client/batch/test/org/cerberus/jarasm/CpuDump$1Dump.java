package test.org.cerberus.jarasm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CpuDump$1Dump implements Opcodes {

	public static byte[] dump(String packageName) throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_5, ACC_SUPER, "org/cerberus/profile/cpu/CpuDump$1", null,
				"java/lang/Object", new String[] { "java/lang/Runnable" });

		cw.visitSource("CpuDump.java", null);

		cw.visitOuterClass("org/cerberus/profile/cpu/CpuDump", "getCpuTrace",
				"()V");

		cw.visitInnerClass("org/cerberus/profile/cpu/CpuDump$1", null, null, 0);

		{
			mv = cw.visitMethod(0, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(16, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
					"()V");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(1, l1);
			mv.visitInsn(RETURN);
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLocalVariable("this",
					"Lorg/cerberus/profile/cpu/CpuDump$1;", null, l0, l2, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "run", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			Label l1 = new Label();
			Label l2 = new Label();
			mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception");
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLineNumber(20, l3);
			mv.visitTypeInsn(NEW, "java/util/HashMap");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>",
					"()V");
			mv.visitVarInsn(ASTORE, 1);
			Label l4 = new Label();
			mv.visitLabel(l4);
			mv.visitLineNumber(24, l4);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Runtime", "getRuntime",
					"()Ljava/lang/Runtime;");
			mv.visitVarInsn(ASTORE, 2);
			Label l5 = new Label();
			mv.visitLabel(l5);
			mv.visitLineNumber(26, l5);
			mv.visitLdcInsn("-0-");
			mv.visitVarInsn(ASTORE, 4);
			Label l6 = new Label();
			mv.visitLabel(l6);
			mv.visitLineNumber(27, l6);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitMethodInsn(INVOKESTATIC, "android/os/Process", "myPid",
					"()I");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(I)V");
			mv.visitLabel(l0);
			mv.visitLineNumber(29, l0);
			mv.visitLdcInsn("top -n 1");
			mv.visitVarInsn(ASTORE, 5);
			Label l7 = new Label();
			mv.visitLabel(l7);
			mv.visitLineNumber(31, l7);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitVarInsn(ALOAD, 5);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Runtime", "exec",
					"(Ljava/lang/String;)Ljava/lang/Process;");
			mv.visitVarInsn(ASTORE, 3);
			Label l8 = new Label();
			mv.visitLabel(l8);
			mv.visitLineNumber(32, l8);
			mv.visitVarInsn(ALOAD, 3);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Process",
					"getInputStream", "()Ljava/io/InputStream;");
			mv.visitVarInsn(ASTORE, 6);
			Label l9 = new Label();
			mv.visitLabel(l9);
			mv.visitLineNumber(33, l9);
			mv.visitTypeInsn(NEW, "java/io/InputStreamReader");
			mv.visitInsn(DUP);
			mv.visitVarInsn(ALOAD, 6);
			mv.visitMethodInsn(INVOKESPECIAL, "java/io/InputStreamReader",
					"<init>", "(Ljava/io/InputStream;)V");
			mv.visitVarInsn(ASTORE, 7);
			Label l10 = new Label();
			mv.visitLabel(l10);
			mv.visitLineNumber(34, l10);
			mv.visitTypeInsn(NEW, "java/io/BufferedReader");
			mv.visitInsn(DUP);
			mv.visitVarInsn(ALOAD, 7);
			mv.visitMethodInsn(INVOKESPECIAL, "java/io/BufferedReader",
					"<init>", "(Ljava/io/Reader;)V");
			mv.visitVarInsn(ASTORE, 8);
			Label l11 = new Label();
			mv.visitLabel(l11);
			mv.visitLineNumber(36, l11);
			Label l12 = new Label();
			mv.visitJumpInsn(GOTO, l12);
			Label l13 = new Label();
			mv.visitLabel(l13);
			mv.visitLineNumber(40, l13);
			mv.visitFrame(Opcodes.F_FULL, 10, new Object[] {
					"org/cerberus/profile/cpu/CpuDump$1", "java/util/Map",
					"java/lang/Runtime", "java/lang/Process",
					"java/lang/String", "java/lang/String",
					"java/io/InputStream", "java/io/InputStreamReader",
					"java/io/BufferedReader", "java/lang/String" }, 0,
					new Object[] {});
			mv.visitVarInsn(ALOAD, 9);
			mv.visitLdcInsn(" ");
			mv.visitLdcInsn("");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "replaceAll",
					"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
			mv.visitLdcInsn("com.example.testandroid");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith",
					"(Ljava/lang/String;)Z");
			Label l14 = new Label();
			mv.visitJumpInsn(IFNE, l14);
			mv.visitVarInsn(ALOAD, 9);
			mv.visitLdcInsn(" ");
			mv.visitLdcInsn("");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "replaceAll",
					"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
			mv.visitLdcInsn(packageName);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith",
					"(Ljava/lang/String;)Z");
			mv.visitJumpInsn(IFEQ, l12);
			mv.visitLabel(l14);
			mv.visitLineNumber(41, l14);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitVarInsn(ALOAD, 9);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/String;)V");
			Label l15 = new Label();
			mv.visitLabel(l15);
			mv.visitLineNumber(43, l15);
			Label l16 = new Label();
			mv.visitJumpInsn(GOTO, l16);
			Label l17 = new Label();
			mv.visitLabel(l17);
			mv.visitLineNumber(44, l17);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 9);
			mv.visitLdcInsn("  ");
			mv.visitLdcInsn(" ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "replaceAll",
					"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
			mv.visitVarInsn(ASTORE, 9);
			mv.visitLabel(l16);
			mv.visitLineNumber(43, l16);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitVarInsn(ALOAD, 9);
			mv.visitLdcInsn("  ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "indexOf",
					"(Ljava/lang/String;)I");
			mv.visitJumpInsn(IFGT, l17);
			Label l18 = new Label();
			mv.visitLabel(l18);
			mv.visitLineNumber(47, l18);
			mv.visitVarInsn(ALOAD, 9);
			mv.visitLdcInsn(" ");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "split",
					"(Ljava/lang/String;)[Ljava/lang/String;");
			mv.visitInsn(ICONST_2);
			mv.visitInsn(AALOAD);
			mv.visitVarInsn(ASTORE, 10);
			Label l19 = new Label();
			mv.visitLabel(l19);
			mv.visitLineNumber(49, l19);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("usage");
			mv.visitVarInsn(ALOAD, 10);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitInsn(POP);
			Label l20 = new Label();
			mv.visitLabel(l20);
			mv.visitLineNumber(50, l20);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn("client_timestamp");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
					"currentTimeMillis", "()J");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf",
					"(J)Ljava/lang/Long;");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put",
					"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
			mv.visitInsn(POP);
			Label l21 = new Label();
			mv.visitLabel(l21);
			mv.visitLineNumber(51, l21);
			Label l22 = new Label();
			mv.visitJumpInsn(GOTO, l22);
			mv.visitLabel(l12);
			mv.visitLineNumber(36, l12);
			mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
			mv.visitVarInsn(ALOAD, 8);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/BufferedReader",
					"readLine", "()Ljava/lang/String;");
			mv.visitInsn(DUP);
			mv.visitVarInsn(ASTORE, 9);
			Label l23 = new Label();
			mv.visitLabel(l23);
			mv.visitJumpInsn(IFNONNULL, l13);
			mv.visitLabel(l1);
			mv.visitLineNumber(59, l1);
			mv.visitJumpInsn(GOTO, l22);
			mv.visitLabel(l2);
			mv.visitFrame(Opcodes.F_FULL, 5, new Object[] {
					"org/cerberus/profile/cpu/CpuDump$1", "java/util/Map",
					"java/lang/Runtime", Opcodes.TOP, "java/lang/String" }, 1,
					new Object[] { "java/lang/Exception" });
			mv.visitVarInsn(ASTORE, 5);
			Label l24 = new Label();
			mv.visitLabel(l24);
			mv.visitLineNumber(60, l24);
			mv.visitVarInsn(ALOAD, 5);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception",
					"fillInStackTrace", "()Ljava/lang/Throwable;");
			mv.visitInsn(POP);
			Label l25 = new Label();
			mv.visitLabel(l25);
			mv.visitLineNumber(61, l25);
			mv.visitLdcInsn("Process Manager");
			mv.visitLdcInsn("Unable to execute top command");
			mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e",
					"(Ljava/lang/String;Ljava/lang/String;)I");
			mv.visitInsn(POP);
			mv.visitLabel(l22);
			mv.visitLineNumber(63, l22);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
					"Ljava/io/PrintStream;");
			mv.visitVarInsn(ALOAD, 4);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/String;)V");
			Label l26 = new Label();
			mv.visitLabel(l26);
			mv.visitLineNumber(65, l26);
			mv.visitMethodInsn(INVOKESTATIC,
					"org/cerberus/profile/cpu/CpuDataList", "getInstance",
					"()Ljava/util/List;");
			mv.visitVarInsn(ALOAD, 1);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add",
					"(Ljava/lang/Object;)Z");
			mv.visitInsn(POP);
			Label l27 = new Label();
			mv.visitLabel(l27);
			mv.visitLineNumber(67, l27);
			mv.visitInsn(RETURN);
			Label l28 = new Label();
			mv.visitLabel(l28);
			mv.visitLocalVariable("this",
					"Lorg/cerberus/profile/cpu/CpuDump$1;", null, l3, l28, 0);
			mv.visitLocalVariable("data", "Ljava/util/Map;", null, l4, l28, 1);
			mv.visitLocalVariable("runtime", "Ljava/lang/Runtime;", null, l5,
					l28, 2);
			mv.visitLocalVariable("process", "Ljava/lang/Process;", null, l8,
					l2, 3);
			mv.visitLocalVariable("res", "Ljava/lang/String;", null, l6, l28, 4);
			mv.visitLocalVariable("cmd", "Ljava/lang/String;", null, l7, l1, 5);
			mv.visitLocalVariable("is", "Ljava/io/InputStream;", null, l9, l1,
					6);
			mv.visitLocalVariable("isr", "Ljava/io/InputStreamReader;", null,
					l10, l1, 7);
			mv.visitLocalVariable("br", "Ljava/io/BufferedReader;", null, l11,
					l1, 8);
			mv.visitLocalVariable("line", "Ljava/lang/String;", null, l13, l12,
					9);
			mv.visitLocalVariable("line", "Ljava/lang/String;", null, l23, l1,
					9);
			mv.visitLocalVariable("usage", "Ljava/lang/String;", null, l19,
					l12, 10);
			mv.visitLocalVariable("e", "Ljava/lang/Exception;", null, l24, l22,
					5);
			mv.visitMaxs(4, 11);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
