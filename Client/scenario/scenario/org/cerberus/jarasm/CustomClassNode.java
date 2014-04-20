package scenario.org.cerberus.jarasm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class CustomClassNode extends ClassNode {

	private String[] interfaces;

	private String fileName;

	private List<String> methodList = new ArrayList<String>();
	
	public CustomClassNode(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void visitSource(String file, String debug) {
		System.out.println("visitSource  " + file + " " + debug);

		super.visitSource(file, debug);
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		// System.out.println("visitOuterClass  " + owner + " " + name + " " +
		// desc);
		super.visitOuterClass(owner, name, desc);
	}

	@Override
	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		// System.out.println("visitInnerClass  " + name + " " + outerName + " "
		// + innerName + " " + access);
		super.visitInnerClass(name, outerName, innerName, access);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {

		System.out.println("visitMethod - " + access + " " + name + " " + desc
				+ " " + signature + " " + Arrays.toString(exceptions) + " "
				+ Arrays.toString(this.interfaces) + superName);

		methodList.add(name);
		
		if (name.startsWith("<")) { // || (0<name.indexOf("$")) 달러 조건 삭제
			return super.visitMethod(access, name, desc, signature, exceptions);
		}

		MethodVisitor mv = super.visitMethod(access, name, desc, signature,
				exceptions);

		if (name.equals("onMenuItemSelected") && interfaces != null) {

			for (int i = 0; i < this.interfaces.length; i++) {
				if (0 < interfaces[i].indexOf("OnMenuItemSelectedListener")) {
//					 MotionEventCollectAdviceAdapter
//					 motionEventCollectAdviceAdapter = new
//					 MotionEventCollectAdviceAdapter(Opcodes.ASM4, mv, access,
//					 name, desc,
//					 "OnActionModeFinishedListener onMenuItemSelected");
//					 return motionEventCollectAdviceAdapter;

					
					//ActionBar
					ActionBarMotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new ActionBarMotionEventCollectAdviceAdapter(
							Opcodes.ASM4, mv, access, name, desc,
							"Activity onMenuItemSelected");
					return motionEventCollectAdviceAdapter;
					
				}
			}
		} else if (name.equals("onMenuItemSelected")) {
			MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
					Opcodes.ASM4, mv, access, name, desc,
					"Activity onMenuItemSelected");
			return motionEventCollectAdviceAdapter;
		}

		if( 0<desc.indexOf("Landroid/view/View" ) && name.equals("onItemClick") && interfaces != null) {
			for(int i = 0 ; i < this.interfaces.length; i++) {
				if(interfaces[i].equals("android/widget/AdapterView$OnItemClickListener")) {
					MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(Opcodes.ASM4, mv, access, name, desc ,"OnItemClickListner onItemClick");
					return motionEventCollectAdviceAdapter;		
				} 
			}
			
		}
		
		if (name.equals("onCreate")) {

			// sqlite 예외 조건
			if (!superName.equals("android/database/sqlite/SQLiteOpenHelper")) {
				System.out.println("onCreate----  " + fileName);
				System.out.println("visitMethod - " + access + " " + name + " "
						+ desc + " " + signature + " "
						+ Arrays.toString(exceptions) + " "
						+ Arrays.toString(this.interfaces) + superName);
				OnCreateAdapter onCreateAdapter = new OnCreateAdapter(
						Opcodes.ASM4, mv, access, name, desc);
				return onCreateAdapter;

			}
		}
		if (name.equals("onCreateOptionsMenu")) {
			
			// sqlite 예외 조건
				System.out.println("onCreateOptionsMenu----  " + fileName);
				System.out.println("visitMethod - " + access + " " + name + " "
						+ desc + " " + signature + " "
						+ Arrays.toString(exceptions) + " "
						+ Arrays.toString(this.interfaces) + superName);
				MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
						Opcodes.ASM4, mv, access, name, desc,
						"onCreateOptionsMenu onClick");
				return motionEventCollectAdviceAdapter;
				
		}
		if (name.equals("onClick")) {
			System.out.println("onClick----  " + fileName);
			System.out.println("visitMethod - " + access + " " + name + " "
					+ desc + " " + signature + " "
					+ Arrays.toString(exceptions) + " "
					+ Arrays.toString(this.interfaces) + superName);
			// MotionEventCollectAdviceAdapter eventCollectAdviceAdapter =
			// new MotionEventCollectAdviceAdapter(Opcodes.ASM4, mv, access,
			// name, desc, "");
			// return eventCollectAdviceAdapter;

			if (0 < desc.indexOf("Landroid/view/View")
					&& name.equals("onClick") && interfaces != null) {
				for (int i = 0; i < this.interfaces.length; i++) {
					if (interfaces[i]
							.equals("android/view/View$OnClickListener")) {
						System.out.println("onClick----  " + fileName);
						System.out.println("visitMethod - " + access + " "
								+ name + " " + desc + " " + signature + " "
								+ Arrays.toString(exceptions) + " "
								+ Arrays.toString(this.interfaces) + superName);
						MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
								Opcodes.ASM4, mv, access, name, desc,
								"OnClickListener onClick");
						return motionEventCollectAdviceAdapter;
					}
				}
			}

		}
		// else {
		System.out.println(name);
		return mv;
		// }

	}

	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {

		for (int interfaceIndex = 0; interfaceIndex < interfaces.length; interfaceIndex++) {
		}

		super.visit(version, access, name, signature, superName, interfaces);
		this.interfaces = interfaces;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		// System.out.println("visitField  " + version + " " + access + " " +
		// name + access + " " + signature
		// + access + " " + superName + access + " " + value);

		return super.visitField(access, name, desc, signature, value);
	}

	@Override
	public void visitEnd() {

		boolean isOnCreateOptionsMenu = false;

		if(superName.toLowerCase().indexOf("activity") > 0 || !(superName.toLowerCase().indexOf("sherlock")>0)) {
		
			for(Object methodName : methods) {
				if(methodName.toString().equals("onCreateOptionsMenu")) {
					isOnCreateOptionsMenu = true;
					break;
				}
			}
	
			if(!isOnCreateOptionsMenu) {
				//Create isOnCreateOptionMenu
				
				
				
				MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC, "onBackPressed", "()V", null, null);
				mv.visitCode();
				Label l0 = new Label();
				mv.visitLabel(l0);
				mv.visitLineNumber(58, l0);
				mv.visitVarInsn(Opcodes.ALOAD, 0);
				mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "android/app/Activity", "onBackPressed", "()V");
				Label l1 = new Label();
				mv.visitLabel(l1);
				mv.visitLineNumber(59, l1);
				mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
				mv.visitLdcInsn("==============");
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
				Label l2 = new Label();
				mv.visitLabel(l2);
				mv.visitLineNumber(60, l2);
				mv.visitInsn(Opcodes.RETURN);
				Label l3 = new Label();
				mv.visitLabel(l3);
				mv.visitLocalVariable("this", "Lcom/example/testandroid/MainActivity;", null, l0, l3, 0);
				mv.visitMaxs(2, 1);
				mv.visitEnd();
	
			}
		}		
		super.visitEnd();
	}

}
