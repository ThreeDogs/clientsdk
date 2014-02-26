package test.org.cerberus.scenario;

import java.util.Arrays;

import org.ow2.asmdex.ClassVisitor;
import org.ow2.asmdex.FieldVisitor;
import org.ow2.asmdex.MethodVisitor;

public class ClassAdapterAnnotateCalls extends ClassVisitor {

	public ClassAdapterAnnotateCalls(int api, ClassVisitor cv) {
		super(api, cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String[] signature, String[] exceptions) {

//		System.out.println("---------ClassAdapterAnnotateCalls visitClass Start-------");
//		System.out.println("access : " + access);
//		System.out.println("name : " + name);
//		System.out.println("desc : " + desc);
//		System.out.println("signature : " + Arrays.toString(signature));
//		System.out.println("exceptions : " + Arrays.toString(exceptions));
//		System.out.println("---------ClassAdapterAnnotateCalls VisitClass End-------");
		
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
		MethodAdapterAnnotateCalls ma = new MethodAdapterAnnotateCalls(api, mv);
		
		return ma;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String[] signature, Object value) {
		
		FieldVisitor fv = cv.visitField(access, name, desc, signature, value);
		FieldAdapterAnnotateCalls fa = new FieldAdapterAnnotateCalls(api, fv);
		
		return fa;
	}

	
	
}
