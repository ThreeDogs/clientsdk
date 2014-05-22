package test.org.cerberus.jarasm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class ClassInfoFinder  extends ClassNode implements Opcodes {
	
	public static ClassNode getInstance(String fileName) throws Exception {
		File classFile = new File(JarAsmTest.RealPath + "/" + fileName + ".class");
		
		FileInputStream fis = new FileInputStream(classFile);
		int api = Opcodes.V1_6;
		ClassReader cr = new ClassReader(fis);
		ClassWriter cw = new ClassWriter(cr,api);
		ClassNode cn = new CustomClassNode(classFile.getName());
		
		
		cr.accept(cn, ClassReader.SKIP_FRAMES);
		cn.accept(cw);
//		com/autoschedule/proto/index/TaskIndexAdapter$2
		return cn;
	}
	
	public String getSuperClass() {
		
		return superName;
	}
	
	

}
