package upload.code.cerberus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;


public class JavaCompileInfo {

	public static String index ;
	
	public static void main(String[] args) throws Exception {
		
		String folderPath = args[0];
		String index = args[1];
		JavaCompileInfo.index = index;
		String savePath = args[2];
		
		File folder = new File(folderPath);
		
		String filePath = null;
		
		for ( String list : folder.list() ) {
			
			if( list.endsWith(".class") ){
				filePath = folderPath + "/" + list;
				break;
			}
			
		}
		
		File classFile = new File(filePath);
		FileInputStream fis = new FileInputStream(classFile);
		
		int api = Opcodes.V1_5;
		try {
			
			ClassReader cr = new ClassReader(fis);
			ClassWriter cw = new ClassWriter(cr, api);
			ClassNode cn = new FrClassNode(api, index);
			
			cr.accept(cn, ClassReader.SKIP_FRAMES);
			cn.accept(cw);
			
			
			byte[] b = cw.toByteArray();
			FileOutputStream fos = new FileOutputStream(savePath + "/" + "CerberusRunner_" + index + ".class" );
			System.out.println(savePath + "/" + "CerberusRunner_" + index + ".class");
			fos.write(b);
			fos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
