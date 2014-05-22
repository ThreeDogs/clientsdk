package scenario.org.cerberus.jarasm;

import java.util.List;

import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class ListItemOnClickChangeAdviceAdapter extends AdviceAdapter{
	private String log;
	private String methodName;
	
	protected ListItemOnClickChangeAdviceAdapter(int api, MethodVisitor mv,
			int access, String name, String desc, String log, String methodName) {
		super(api, mv, access, name, desc);
		this.log = log;
		this.methodName = methodName;
	}

	
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {

		
		System.out.println("visitMethodInsn" + opcode + " " + owner + " " + name + " " + desc);
		
		
		if(name.equals("setOnClickListener")) {
			System.out.println("add ListItemOnClickListener");
			
			if(methodName.equals("getView")) {
				
				mv.visitVarInsn(ILOAD, 1);
				mv.visitVarInsn(ALOAD, 3);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/evnetlistener/ListItemOnClickFactory", "newInstance", "(Landroid/view/View$OnClickListener;ILandroid/view/ViewGroup;)Lorg/cerberus/evnetlistener/ListItemOnClickListener;");
				
			} else {
				
				mv.visitVarInsn(ILOAD, 1);
//			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
				mv.visitVarInsn(ALOAD, 3);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/evnetlistener/ListItemOnClickFactory", "newInstance", "(Landroid/view/View$OnClickListener;Ljava/lang/Object;Ljava/lang/Object;)Lorg/cerberus/evnetlistener/ListItemOnClickListener;");
			}
			
		}
		
		super.visitMethodInsn( opcode,  owner,  name,  desc);
	}


//	@Override
//	public void visitTypeInsn(int arg0, String arg1) {
//
//		
//		
//		System.out.println("visitTypeInsn " + arg0 + " " + arg1);
//		
//		if(arg1.startsWith("java")) {
//			super.visitTypeInsn(arg0, arg1);
//			return;
//		}
//		
//		try {
//			
//			List<String> interfaces = ClassInfoFinder.getInstance(arg1).interfaces;
//			System.out.println("interface = " + interfaces);
//			for(String strInterface : interfaces) {
//				if(strInterface.toLowerCase().indexOf("click")>0) {
//					System.out.println("=======+=");
//					mv.visitTypeInsn(NEW, "com/autoschedule/proto/index/TaskIndexAdapter$CheckOnClick");
//					mv.visitInsn(DUP);
////					super.visitTypeInsn(arg0, arg1);
////					return;
//					break;
//				}
//			}
//			
////			if(ClassInfoFinder.getInstance(arg1).superName.toLowerCase().indexOf("click") >0 ) {
////				mv.visitTypeInsn(NEW, "com/autoschedule/proto/index/TaskIndexAdapter$CheckOnClick");
////				mv.visitInsn(DUP);
////				super.visitTypeInsn(arg0, arg1);
////				return;
////			}
//		} catch (Exception e) {
//			if(e.getMessage().toLowerCase().indexOf("filenotfound")>0) {
//				System.out.println("notFound file");
//			} else {
//				e.printStackTrace();
//			}
//			
//			
//		}
//		
//		super.visitTypeInsn(arg0, arg1);
//	}
	
	
	
}
