import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright (C), 2016-2020 
 * FileName: LocalClassLoader 
 * Author: wei.zheng 
 * Date: 2020/4/1 18:42 
 * Description: �Զ����������
 *  1) ��дfindClass���� 
 *  2) javac����������ص�Person.class�ļ�,��ɾ��Person.javaԴ�ļ�
 */
public class LocalClassLoader extends ClassLoader {
	private String path;

	public LocalClassLoader(String path) {
		this.path = path;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] bytes = loadClassData(name);
			return defineClass(name, bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.findClass(name);
	}

	/**
	 * ��class�ļ��л�ȡ�������ֽ�����
	 * 
	 * @param name
	 * @return byte[]
	 * @throws IOException
	 */
	private byte[] loadClassData(String name) throws IOException {
		String fileName = path + name.replace(".", "/") + ".class";

		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			File file = new File(fileName);
			is = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			int i = 0;
//			int count = 0;
//			while ((i = is.read()) != -1) {
//				System.out.println(++count + " - " + i);
//				baos.write(i);
//			}

			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loadClassData exception:" + e.getMessage());
		} finally {
			try {
				if (is != null) {
					is.close();
				}

				if (baos != null) {
					baos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return baos.toByteArray();
	}
}
