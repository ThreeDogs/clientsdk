package methodtrace.org.cerberus.jarasm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class MethodAdviceAdapter extends AdviceAdapter {

	Label l1 ;
	Label l0 ;
	
	protected MethodAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc) {
		super(api, mv, access, name, desc);
//		System.out.println(api + " " + mv + " " + access + " " + name + " " + desc);
	}

	@Override
	protected void onMethodEnter() {
		
		l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(91, l0);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/profile/method/MethodTraceDumpList", "nextValue", "()Ljava/lang/Integer;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I");
		mv.visitVarInsn(ISTORE, 10002);
		l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLineNumber(92, l1);
		mv.visitTypeInsn(NEW, "java/util/HashMap");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V");
		mv.visitVarInsn(ASTORE, 10003);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitLineNumber(93, l2);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("tree_key");
		mv.visitVarInsn(ILOAD, 10002);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l3 = new Label();
		mv.visitLabel(l3);
		mv.visitLineNumber(94, l3);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("class_name");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;");
		mv.visitInsn(ICONST_2);
		mv.visitInsn(AALOAD);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackTraceElement", "getClassName", "()Ljava/lang/String;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l4 = new Label();
		mv.visitLabel(l4);
		mv.visitLineNumber(95, l4);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("method_name");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;");
		mv.visitInsn(ICONST_2);
		mv.visitInsn(AALOAD);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackTraceElement", "getMethodName", "()Ljava/lang/String;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l5 = new Label();
		mv.visitLabel(l5);
		mv.visitLineNumber(96, l5);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("start_timestamp");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l6 = new Label();
		mv.visitLabel(l6);
		mv.visitLineNumber(97, l6);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("tId");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getId", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l7 = new Label();
		mv.visitLabel(l7);
		mv.visitLineNumber(98, l7);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("tLength");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;");
		mv.visitInsn(ARRAYLENGTH);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l8 = new Label();
		mv.visitLabel(l8);
		mv.visitLineNumber(99, l8);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("parent");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;");
		mv.visitInsn(ICONST_3);
		mv.visitInsn(AALOAD);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackTraceElement", "getMethodName", "()Ljava/lang/String;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);	
		
		
		super.onMethodEnter();
	}

	@Override
	protected void onMethodExit(int opcode) {

		Label l9 = new Label();
		mv.visitLabel(l9);
		mv.visitLineNumber(101, l9);
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitLdcInsn("end_timestamp");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(POP);
		Label l10 = new Label();
		mv.visitLabel(l10);
		mv.visitLineNumber(102, l10);
		mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/profile/method/MethodTraceDumpList", "getInstance", "()Ljava/util/List;");
		mv.visitVarInsn(ALOAD, 10003);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);
		
		
		Label l12 = new Label();
		mv.visitLabel(l12);
		mv.visitLocalVariable("sequence$", "I", null, l0, l12, 10002);
		mv.visitLocalVariable("methodMap$", "Ljava/util/Map;", null, l1, l12, 10003);
		
		super.onMethodExit(opcode);
	}
	
	
}
