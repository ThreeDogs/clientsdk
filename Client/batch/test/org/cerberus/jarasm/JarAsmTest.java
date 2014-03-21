package test.org.cerberus.jarasm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import scenario.org.cerberus.jarasm.CustomClassNode;


public class JarAsmTest {

	public static void main(String[] args) throws Exception {
		
		System.out.println("--------------- Start Instrumentation Byte Code ---------------");
		
		String rootPath = "/Users/RhoSunghyun/Documents/dev/ttttttttttttttt/temp/newclz";

		if(args.length!=0 && args[0] != null) {
			rootPath = args[0];
		}
		
		if(!(new File(rootPath)).isDirectory() ) {
			return;
		}
		
		scanDirectory(rootPath);
		
		System.out.println("--------------- finish Instrumentation Byte Code ---------------");
	}
	
	private static void scanDirectory(String path) throws Exception {

		File rootDirectory = new File(path);
		
		for(String list : rootDirectory.list() ) {
			
			
			
			File childFile = new File(rootDirectory.getAbsolutePath() + "/" + list);
			
			if(childFile.isDirectory()) {
//				System.out.println(childFile.getAbsolutePath());	
				scanDirectory(childFile.getAbsolutePath());
			} else {
				scanFile(childFile.getAbsolutePath());
			}
			
		}
		
		
	}
	
	private static void scanFile(String path) throws Exception {
		
//		System.out.println("	" + path);
		//&& 0>path.indexOf("$")
		if(path.endsWith(".class") && 0<path.indexOf("Activity")) {
			// is class file
			File classFile = new File(path);
			
			FileInputStream fis = new FileInputStream(classFile);
			
			int api = Opcodes.V1_6;
			
			ClassReader cr = new ClassReader(fis);
			ClassWriter cw = new ClassWriter(cr,api);
			ClassNode cn = new CustomClassNode();
			
			
			cr.accept(cn, ClassReader.SKIP_FRAMES);
			cn.accept(cw);
			
			byte[] b = cw.toByteArray();
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(b);
			fos.close();
		}
		
	}
	
}
