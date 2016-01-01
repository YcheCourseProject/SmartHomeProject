package models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Registers {

	private int R0;
	private int R1;
	private int R2;
	private int R3;
	private int R4;
	private int R5;
	private int R6;
	private int R7;
	
	
	
	public Registers() throws Exception{
		super();
		Class<Registers> clazz=Registers.class;
		Field[] fields=clazz.getDeclaredFields();
		for(Field field:fields){
			String fiedldName=field.getName();
			Method setMethod=clazz.getMethod("set"+fiedldName, new Class<?>[]{int.class});
			setMethod.invoke(this, new Object[]{(int)-1});		
		}
	}
	public int getR0() {
		return R0;
	}
	public void setR0(int r0) {
		R0 = r0;
	}
	public int getR1() {
		return R1;
	}
	public void setR1(int r1) {
		R1 = r1;
	}
	public int getR2() {
		return R2;
	}
	public void setR2(int r2) {
		R2 = r2;
	}
	public int getR3() {
		return R3;
	}
	public void setR3(int r3) {
		R3 = r3;
	}
	public int getR4() {
		return R4;
	}
	public void setR4(int r4) {
		R4 = r4;
	}
	public int getR5() {
		return R5;
	}
	public void setR5(int r5) {
		R5 = r5;
	}
	public int getR6() {
		return R6;
	}
	public void setR6(int r6) {
		R6 = r6;
	}
	public int getR7() {
		return R7;
	}
	public void setR7(int r7) {
		R7 = r7;
	}
	

}
