import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.sound.sampled.LineListener;

public class MainJava {

	public static void main(String[] args) {
		try {
			
			MainJava mainJava = new MainJava();
			System.out.println("MainJava classLoader: "+ mainJava.getClass().getClassLoader());
			System.out.println("java.class.path： "+System.getProperty("java.class.path"));
			System.out.println("MainJava classLoader parent: "+ mainJava.getClass().getClassLoader().getParent());
			System.out.println("java.ext.dirs： "+System.getProperty("java.ext.dirs"));
			
			String path = "C:/Users/ubt/eclipse-workspace/Test/src/";
			LocalClassLoader localClassLoader = new LocalClassLoader(path);
			Class<?> cls = localClassLoader.loadClass("Person");
			System.out.println("classLoader: " + cls.getClassLoader());

			// 遍历所有的字段
			Field[] declaredFields = cls.newInstance().getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				System.out.println("Declared field: " + field.getName());
			}

			
			// 打印name字段值
			Field field = cls.newInstance().getClass().getDeclaredField("name");
			field.setAccessible(true);
			Object value = field.get(cls.newInstance());
			System.out.println("print name value:" + value);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
