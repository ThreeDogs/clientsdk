package profiling.org.cerberus.jarasm;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import profiling.org.cerberus.jarasm.actionbar.NaviItemListenerAdviceAdapter;
import profiling.org.cerberus.jarasm.DialogOnClickAdviceAdapter;
import profiling.org.cerberus.jarasm.MotionEventCollectAdviceAdapter;

public class CustomClassNode extends ClassNode implements Opcodes{

	private String[] interfaces;

	private String fileName;

	private List<String> methodList = new ArrayList<String>();
	
	public CustomClassNode(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void visitSource(String file, String debug) {
//		System.out.println("visitSource  " + file + " " + debug);

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

//		System.out.println("visitMethod - " + access + " " + name + " " + desc
//				+ " " + signature + " " + Arrays.toString(exceptions) + " "
//				+ Arrays.toString(this.interfaces) + superName);

//		System.out.println(name + "   " + superName + "   " + fileName + "  " + Arrays.toString(interfaces));
		
		
		if(fileName.indexOf("VingleFragment") > 0) {
//			System.out.println("------------VingleFragment");
		} else {
//			System.out.println("-----------no");
		}
				
		methodList.add(name);
		
		
		
		if (name.startsWith("<") || name.length() <= 2) { // || (0<name.indexOf("$")) 달러 조건 삭제
			return super.visitMethod(access, name, desc, signature, exceptions);
		}

		MethodVisitor mv = super.visitMethod(access, name, desc, signature,
				exceptions);

		for(String interfaceName : interfaces) { 
			if (interfaceName.equals("android/app/DatePickerDialog$OnDateSetListener")){
				if (name.equals("onDateSet")) {
					DatePickerAdviceAdapter datePickerAdviceAdapter = new DatePickerAdviceAdapter(Opcodes.ASM4, mv, access, name, desc, fileName + "dataPicker ...");
					System.out.println(fileName + " " + name + " " + "add datapicker");
					return datePickerAdviceAdapter;
				}
			}
			if (interfaceName.equals("android/app/TimePickerDialog$OnTimeSetListener")){
				if (name.equals("onTimeSet")) {
					TimePickerAdviceAdapter timePickerAdviceAdapter = new TimePickerAdviceAdapter(Opcodes.ASM4, mv, access, name, desc, fileName + "timePicker ...");
					System.out.println(fileName + " " + name + " " + "add timepicker");
					return timePickerAdviceAdapter;
				}
			}
			if (interfaceName.equals("android/widget/AdapterView$OnItemClickListener")){
				if (name.equals("onItemClick")) {
					ListViewOnClickAdviceAdapter listViewOnClickAdviceAdapter = new ListViewOnClickAdviceAdapter(Opcodes.ASM4, mv, access, name, desc, fileName + "listView item click ...");
					System.out.println(fileName + " " + name + " " + "add listviewClick");
					return listViewOnClickAdviceAdapter;
				}
			}
			if (interfaceName.equals("android/widget/AdapterView$OnItemLongClickListener")){
				if (name.equals("onItemLongClick")) {
					ListViewOnClickAdviceAdapter listViewOnClickAdviceAdapter = new ListViewOnClickAdviceAdapter(Opcodes.ASM4, mv, access, name, desc, fileName + "listView item click ...");
					System.out.println(fileName + " " + name + " " + "add listViewLongClick");
					return listViewOnClickAdviceAdapter;
				}
			}
		}
		
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
//					ActionBarMotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new ActionBarMotionEventCollectAdviceAdapter(
//							Opcodes.ASM4, mv, access, name, desc,
//							"Activity onMenuItemSelected");
//					return motionEventCollectAdviceAdapter;
					
				}
			}
		} 
//		else if (name.equals("onMenuItemSelected")) {
//			MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
//					Opcodes.ASM4, mv, access, name, desc,
//					"Activity onMenuItemSelected");
//			return motionEventCollectAdviceAdapter;
//		} 
		if (name.equals("onOptionsItemSelected") ) {
			OnOptionsItemSelectedAdviceAdapter onOptionItemSelectedAdviceAdapter = new OnOptionsItemSelectedAdviceAdapter(
					Opcodes.ASM4, mv, access, name, desc,
					fileName + "    onOptionsItemSelected");
			System.out.println(fileName + " " + name + " " + "add onOptionItemSelectedAdviceAdapter");
			return onOptionItemSelectedAdviceAdapter;
		}
		if (name.equals("naviItemListener")) {
			NaviItemListenerAdviceAdapter naviItemListenerAdviceAdapter = new NaviItemListenerAdviceAdapter(
					Opcodes.ASM4, mv, access, name, desc,
					fileName + "    naviItemListenerAdviceAdapter");
			System.out.println(fileName + " " + name + " " + "add naviItemListener");
			return naviItemListenerAdviceAdapter;
		}
		
		if (superName.toLowerCase().indexOf("adapter")>0 ) {
			ListItemOnClickChangeAdviceAdapter listItemOnClickChangeAdviceAdapter = new ListItemOnClickChangeAdviceAdapter(
					Opcodes.ASM4, mv, access, name, desc,
					fileName + "    list in onclick change adapter",name);
			System.out.println(fileName + " " + name + " " + "change getView in onclickListener");
			
			
			return listItemOnClickChangeAdviceAdapter;
		}
		
		if( 0<desc.indexOf("Landroid/view/View" ) && name.equals("onItemClick") && interfaces != null) {
			for(int i = 0 ; i < this.interfaces.length; i++) {
				if(interfaces[i].equals("android/widget/AdapterView$OnItemClickListener")) {
					MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(Opcodes.ASM4, mv, access, name, desc ,"OnItemClickListner onItemClick");
					System.out.println(fileName + " " + name + " " + "add motionEventCollectAdviceAdapter");
					return motionEventCollectAdviceAdapter;		
				} 
			}
			
		}
		
		if (name.equals("onCreate")) {

			// sqlite 예외 조건
			if (!superName.equals("android/database/sqlite/SQLiteOpenHelper")) {
//				System.out.println("onCreate----  " + fileName);
//				System.out.println("visitMethod - " + access + " " + name + " "
//						+ desc + " " + signature + " "
//						+ Arrays.toString(exceptions) + " "
//						+ Arrays.toString(this.interfaces) + superName);
				
				if(superName.indexOf("Dialog") > 0 ) {
//					DialogOnCreateAdapter dialogOnCreateAdapter = new DialogOnCreateAdapter(
//							Opcodes.ASM4, mv, access, name, desc);
//					return dialogOnCreateAdapter;
					return mv;
				} else {
				if(superName.indexOf("Activity") > 0) {
					OnCreateAdapter onCreateAdapter = new OnCreateAdapter(
							Opcodes.ASM4, mv, access, name, desc);
					System.out.println(fileName + " " + name + " " + "add OnCreateAdapter");
					return onCreateAdapter;
				}
					return mv;
				}
			}
		}
//		if (name.toLowerCase().equals("onstart")) {
//			
//			// sqlite 예외 조건
//			if (!superName.equals("android/database/sqlite/SQLiteOpenHelper")) {
////				System.out.println("onCreate----  " + fileName);
////				System.out.println("visitMethod - " + access + " " + name + " "
////						+ desc + " " + signature + " "
////						+ Arrays.toString(exceptions) + " "
////						+ Arrays.toString(this.interfaces) + superName);
//				
//				if(superName.indexOf("Dialog") > 0 ) {
////					DialogOnCreateAdapter dialogOnCreateAdapter = new DialogOnCreateAdapter(
////							Opcodes.ASM4, mv, access, name, desc);
////					return dialogOnCreateAdapter;
//					return mv;
//				} else {
//					if(superName.toLowerCase().indexOf("fragment") > 0 || superName.toLowerCase().indexOf("sherlockfragment") > 0) {
//						
//						if(superName.toLowerCase().indexOf("sherlock") > 0) {
//							ActionbarFragmentOnStartAdviceAdapter actionbarFragmentOnStartAdviceAdapter = new ActionbarFragmentOnStartAdviceAdapter(
//									Opcodes.ASM4, mv, access, name, desc, "actionbar sherlock onStart");
//							System.out.println(fileName + " " + name + " " + "add Actionbar OnCreateAdapter");
//							return actionbarFragmentOnStartAdviceAdapter;
//						}
//						
//						OnCreateAdapter onCreateAdapter = new OnCreateAdapter(
//								Opcodes.ASM4, mv, access, name, desc);
//						System.out.println(fileName + " " + name + " " + "add OnCreateAdapter");
//						return onCreateAdapter;
//					} 
//					return mv;
//				}
//			}
//		}
//		if (name.equals("onCreateOptionsMenu")) {
//			
//			// sqlite 예외 조건
//				System.out.println("onCreateOptionsMenu----  " + fileName);
//				System.out.println("visitMethod - " + access + " " + name + " "
//						+ desc + " " + signature + " "
//						+ Arrays.toString(exceptions) + " "
//						+ Arrays.toString(this.interfaces) + superName);
//				MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
//						Opcodes.ASM4, mv, access, name, desc,
//						"onCreateOptionsMenu onClick");
//				return motionEventCollectAdviceAdapter;
//				
//		}
		if (name.equals("onClick")) {
//			System.out.println("onClick----  " + fileName);
//			System.out.println("visitMethod - " + access + " " + name + " "
//					+ desc + " " + signature + " "
//					+ Arrays.toString(exceptions) + " "
//					+ Arrays.toString(this.interfaces) + superName);
			// MotionEventCollectAdviceAdapter eventCollectAdviceAdapter =
			// new MotionEventCollectAdviceAdapter(Opcodes.ASM4, mv, access,
			// name, desc, "");
			// return eventCollectAdviceAdapter;

			if (0 < desc.indexOf("Landroid/view/View")
					&& name.equals("onClick") && interfaces != null) {
				for (int i = 0; i < this.interfaces.length; i++) {
					if (interfaces[i]
							.equals("android/view/View$OnClickListener")) {
//						System.out.println("onClick----  " + fileName);
//						System.out.println("visitMethod - " + access + " "
//								+ name + " " + desc + " " + signature + " "
//								+ Arrays.toString(exceptions) + " "
//								+ Arrays.toString(this.interfaces) + superName);
						MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
								Opcodes.ASM4, mv, access, name, desc,
								"OnClickListener onClick");
						System.out.println(fileName + " " + name + " " + "add OnClickListener");
						return motionEventCollectAdviceAdapter;
					} else if(interfaces[i].indexOf("android/content/DialogInterface") >= 0) {
						DialogOnClickAdviceAdapter dialogOnClickAdviceAdapter = new DialogOnClickAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
						return dialogOnClickAdviceAdapter;
						
					}
				}
			}
			
			for (int i = 0; i < this.interfaces.length; i++) {
				if (interfaces[i]
						.equals("android/view/View$OnClickListener")) {
//					System.out.println("onClick----  " + fileName);
//					System.out.println("visitMethod - " + access + " "
//							+ name + " " + desc + " " + signature + " "
//							+ Arrays.toString(exceptions) + " "
//							+ Arrays.toString(this.interfaces) + superName);
					MotionEventCollectAdviceAdapter motionEventCollectAdviceAdapter = new MotionEventCollectAdviceAdapter(
							Opcodes.ASM4, mv, access, name, desc,
							"OnClickListener onClick");
					System.out.println(fileName + " " + name + " " + "add OnClickListener");
					return motionEventCollectAdviceAdapter;
//					android.content.DialogInterface.OnClickListener
				} else if(interfaces[i].indexOf("DialogInterface") >= 0) {
					DialogOnClickAdviceAdapter dialogOnClickAdviceAdapter = new DialogOnClickAdviceAdapter(Opcodes.ASM4, mv, access, name, desc);
					System.out.println(fileName + " " + name + " " + "add DialogOnClick");
					return dialogOnClickAdviceAdapter;
					
				}
			}

		}
		// else {
//		System.out.println(name);
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
	public void visitEnd() {

		System.out.println(fileName + " end.. start");
		
		boolean isOnCreateOptionsMenu = false;
		boolean isOnKeyDown = false;
		boolean isActivity = false;
		
		if(superName.toLowerCase().indexOf("activity") > 0) {
			isActivity = true;
		}
		
		if(superName.toLowerCase().indexOf("activity") > 0 || !(superName.toLowerCase().indexOf("sherlock")>0)) {
		
			for(Object methodName : methods) {
				if(methodName.toString().equals("onCreateOptionsMenu")) {
					isOnCreateOptionsMenu = true;
				}
				if(methodName.toString().equals("onKeyDown")) {
					isOnKeyDown = true;
				}
			}
	
			if(!isOnCreateOptionsMenu && isActivity) {
				//Create isOnCreateOptionMenu
				
				
				
//				MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC, "onBackPressed", "()V", null, null);
//				mv.visitCode();
//				Label l0 = new Label();
//				mv.visitLabel(l0);
//				mv.visitLineNumber(58, l0);
//				mv.visitVarInsn(Opcodes.ALOAD, 0);
//				mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "android/app/Activity", "onBackPressed", "()V");
//				Label l1 = new Label();
//				mv.visitLabel(l1);
//				mv.visitLineNumber(59, l1);
//				mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//				mv.visitLdcInsn("==============");
//				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
//				Label l2 = new Label();
//				mv.visitLabel(l2);
//				mv.visitLineNumber(60, l2);
//				mv.visitInsn(Opcodes.RETURN);
//				Label l3 = new Label();
//				mv.visitLabel(l3);
//				mv.visitLocalVariable("this", "Lcom/example/testandroid/MainActivity;", null, l0, l3, 0);
//				mv.visitMaxs(2, 1);
//				mv.visitEnd();
	
			}
			if(!isOnKeyDown && isActivity) {
				
				System.out.println(fileName + " " + name + " " + "add onKeyDown");
				
				//Create isOnKeyDown
				MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC, "onKeyDown", "(ILandroid/view/KeyEvent;)Z", null, null);
				mv.visitCode();
				Label l0 = new Label();
				mv.visitLabel(l0);
				mv.visitLineNumber(91, l0);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitMethodInsn(INVOKEVIRTUAL, "android/view/KeyEvent", "getAction", "()I");
				Label l1 = new Label();
				mv.visitJumpInsn(IFNE, l1);
				Label l2 = new Label();
				mv.visitLabel(l2);
				mv.visitLineNumber(92, l2);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitInsn(ICONST_4);
				Label l3 = new Label();
				mv.visitJumpInsn(IF_ICMPNE, l3);
				Label l4 = new Label();
				mv.visitLabel(l4);
				mv.visitLineNumber(93, l4);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
				mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
				mv.visitInsn(DUP);
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitLdcInsn("BackButton");
				mv.visitLdcInsn("");
				mv.visitLdcInsn("");
				mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
				mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
				Label l5 = new Label();
				mv.visitLabel(l5);
				mv.visitLineNumber(94, l5);
				mv.visitJumpInsn(GOTO, l1);
				mv.visitLabel(l3);
				mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitIntInsn(BIPUSH, 82);
				Label l6 = new Label();
				mv.visitJumpInsn(IF_ICMPNE, l6);
				Label l7 = new Label();
				mv.visitLabel(l7);
				mv.visitLineNumber(95, l7);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
				mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
				mv.visitInsn(DUP);
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitLdcInsn("MenuButton");
				mv.visitLdcInsn("");
				mv.visitLdcInsn("");
				mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
				mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
				Label l8 = new Label();
				mv.visitLabel(l8);
				mv.visitLineNumber(96, l8);
				mv.visitJumpInsn(GOTO, l1);
				mv.visitLabel(l6);
				mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitIntInsn(BIPUSH, 24);
				Label l9 = new Label();
				mv.visitJumpInsn(IF_ICMPNE, l9);
				Label l10 = new Label();
				mv.visitLabel(l10);
				mv.visitLineNumber(97, l10);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
				mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
				mv.visitInsn(DUP);
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitLdcInsn("VolumeUp");
				mv.visitLdcInsn("");
				mv.visitLdcInsn("");
				mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
				mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
				Label l11 = new Label();
				mv.visitLabel(l11);
				mv.visitLineNumber(98, l11);
				mv.visitJumpInsn(GOTO, l1);
				mv.visitLabel(l9);
				mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitIntInsn(BIPUSH, 25);
				mv.visitJumpInsn(IF_ICMPNE, l1);
				Label l12 = new Label();
				mv.visitLabel(l12);
				mv.visitLineNumber(99, l12);
				mv.visitMethodInsn(INVOKESTATIC, "org/cerberus/event/collection/MotionCollector", "getInstance", "()Lorg/cerberus/scenario/MotionCollectionManager;");
				mv.visitTypeInsn(NEW, "org/cerberus/scenario/MotionVO");
				mv.visitInsn(DUP);
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
				mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;");
				mv.visitLdcInsn("VolumeDown");
				mv.visitLdcInsn("");
				mv.visitLdcInsn("");
				mv.visitMethodInsn(INVOKESPECIAL, "org/cerberus/scenario/MotionVO", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
				mv.visitMethodInsn(INVOKEVIRTUAL, "org/cerberus/scenario/MotionCollectionManager", "putMotion", "(Lorg/cerberus/scenario/MotionVO;)V");
				mv.visitLabel(l1);
				mv.visitLineNumber(103, l1);
				mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitVarInsn(ILOAD, 1);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitMethodInsn(INVOKESPECIAL, "android/app/Activity", "onKeyDown", "(ILandroid/view/KeyEvent;)Z");
				mv.visitInsn(IRETURN);
//				Label l13 = new Label();
//				mv.visitLabel(l13);
//				mv.visitLocalVariable("this", "Lcom/example/testandroid/MainActivity;", null, l0, l13, 0);
//				mv.visitLocalVariable("keyCode", "I", null, l0, l13, 1);
//				mv.visitLocalVariable("event", "Landroid/view/KeyEvent;", null, l0, l13, 2);
//				mv.visitMaxs(8, 3);
				mv.visitEnd();
				
			}
		}		
		System.out.println(fileName + " end.. start");
//		super.visitEnd();
	}

}
