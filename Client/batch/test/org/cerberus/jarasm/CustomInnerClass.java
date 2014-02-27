package test.org.cerberus.jarasm;

import org.objectweb.asm.tree.InnerClassNode;

public class CustomInnerClass extends InnerClassNode{

	public CustomInnerClass(String name, String outerName, String innerName,
			int access ) {
		super(name, outerName, innerName, access);
	}

	
	
}
