package test.org.cerberus.scenario;

import java.util.Arrays;

import org.ow2.asmdex.ApplicationVisitor;
import org.ow2.asmdex.ClassVisitor;

public class ApplicationAdapterAnnotateCalls extends ApplicationVisitor {

	
	
	public ApplicationAdapterAnnotateCalls(int api, ApplicationVisitor av) {
		super(api, av);
	}

	@Override
	public ClassVisitor visitClass(int access, String name, String[] signature,
			String superName, String[] interfaces) {
		// TODO Auto-generated method stub
		
		System.out.println("---------ApplicationAdapterAnnotateCalls visitClass Start-------");
		System.out.println("access : " + access);
		System.out.println("name : " + name);
		System.out.println("signature : " + Arrays.toString(signature));
		System.out.println("superName : " + superName);
		System.out.println("interfaces : " + Arrays.toString(interfaces));
		System.out.println("---------ApplicationAdapterAnnotateCalls VisitClass End-------");
//		
		ClassVisitor cv = av.visitClass(access, name, signature, superName, interfaces);
		ClassAdapterAnnotateCalls ca = new ClassAdapterAnnotateCalls(api, cv);
		return ca;
	}

	@Override
	public void visitEnd() {
		// TODO Auto-generated method stub
		System.out.println("ApplicationAdapterAnnotateCalls visitEnd called...");
		super.visitEnd();
	}

	
	
}
