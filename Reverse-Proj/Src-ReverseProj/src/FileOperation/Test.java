package FileOperation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;

import models.CodeLine;
import models.Registers;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Registers registers=new Registers();
		Class<Registers> clazz=Registers.class;
		Field[] fields=clazz.getDeclaredFields();
		for(Field field:fields){
			String fiedldName=field.getName();
			Method setMethod=clazz.getMethod("get"+fiedldName, new Class<?>[]{});
			Integer integer=(Integer) setMethod.invoke(registers, new Object[]{});		
			System.out.println(fiedldName+":"+integer);
		}
		System.out.println(Integer.parseInt("16",16));
		CodeLine codeline=new CodeLine("LDR     R3, [R3,#0x5C]");
		System.out.println(codeline.getInstruction());
		System.out.println(codeline.getDestReg());
		System.out.println(codeline.getInstantNumber());
	
		System.out.println(codeline.getRegArray()[0]);
		Executors.newCachedThreadPool();
	}
	

}
