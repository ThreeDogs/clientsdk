package scenario.org.cerberus.jarasm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import scenario.org.cerberus.jarasm.CustomClassNode;


public class JarAsmTest {

	public static String apiKey;
	
	public static void main(String[] args) throws Exception {
//		if(args!=null) {
//			return;
//		}
		System.out.println("--------------- Start Instrumentation Byte Code ---------------");
		
		String rootPath = "/Users/RhoSunghyun/Documents/dev/ttttttttttttttt/temp/newclz";
		String packageName = "";
		
		if(args.length!=0 && args[0] != null) {
			rootPath = args[0];
		}
		
		if(args.length!=0 && args[1] != null) {
			apiKey = args[1];
		}

		if(args.length>=3 && args[2] != null) {
			packageName = args[2];
			while( packageName.indexOf(".") > 0 ) {
				packageName = packageName.replace(".", "/");
			}
			
		}
		
		if(!(new File(rootPath + "/" + packageName)).isDirectory() ) {
			System.out.println(rootPath + "/" + packageName + " is not directory");
			return;
		}
		
		scanDirectory((new File(rootPath + "/" + packageName)).getParentFile().getAbsolutePath());
		
		System.out.println("--------------- finish Instrumentation Byte Code ---------------");
		System.out.println("root - " + (new File(rootPath + "/" + packageName)).getAbsolutePath() );
	}
	
	private static void scanDirectory(String path) throws Exception {

		File rootDirectory = new File(path);
		
		boolean isSingleName = false;
		String faildString = "";
		for(String list : rootDirectory.list() ) {
			
			File childFile = new File(rootDirectory.getAbsolutePath() + "/" + list);
			
			if(childFile.isDirectory()) {
			} else {
				if(childFile.getName().replaceAll(".class", "").length() < 3 && !childFile.getName().replace(".class", "").equals("R") ){
					isSingleName = true;
					faildString = childFile.getName().replaceAll(".class", "");
					break;
				}
			}
			
		}	
		
		if(isSingleName) {
			System.out.println("warning progard..." + " " + path + " " + faildString);
			return;
		}
		
		for(String list : rootDirectory.list() ) {
			
			
			
			File childFile = new File(rootDirectory.getAbsolutePath() + "/" + list);
			
			if(childFile.isDirectory()) {
//				System.out.println(childFile.getAbsolutePath());	
				scanDirectory(childFile.getAbsolutePath());
			} else {
				if(childFile.getName().replaceAll(".class", "").length() > 3 )
					scanFile(childFile.getAbsolutePath());
			}
			
		}
		
		
	}
	
	private static void scanFile(String path) throws Exception {
		try{
//		System.out.println("	" + path);
		//&& 0>path.indexOf("$")
//		if(path.endsWith(".class") && 0<path.indexOf("Activity")) {
			// is class file
			if(path.indexOf("android") > 0 && path.indexOf("support") > 0 && path.indexOf("v4") > 0) {
				return;
			}
			if(path.indexOf("android") > 0 && path.indexOf("support") > 0 && path.indexOf("v7") > 0) {
				return;
			}
			if(path.indexOf("com") > 0 && path.indexOf("actionbarsherlock") > 0 ) {
				return;
			}
			
			File classFile = new File(path);
			
			FileInputStream fis = new FileInputStream(classFile);
			
			int api = Opcodes.V1_6;
			try{
			ClassReader cr = new ClassReader(fis);
			ClassWriter cw = new ClassWriter(cr,api);
			ClassNode cn = new CustomClassNode(classFile.getName());
			
			
			cr.accept(cn, ClassReader.SKIP_FRAMES);
			cn.accept(cw);
			
			byte[] b = cw.toByteArray();
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(b);
			fos.close();
			}catch(Exception e) {
				
			}
			
//		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
