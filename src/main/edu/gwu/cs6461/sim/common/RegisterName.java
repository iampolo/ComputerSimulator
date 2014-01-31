package edu.gwu.cs6461.sim.common;

public enum RegisterName {
	R0("R0"),
	R1("R1"),
	R2("R2"),
	R3("R3"),
	X1("X1"),
	X2("X2"),
	X3("X3"),
	MAR("MAR"),
	MBR("MBR"),
	MSR("MSR"),
	MFR("MFR"),
	CC("CC"),
	IR("IR"),
	PC("PC");
	
	private final String name;
	private RegisterName(String name){
		this.name= name;
	}
	
	public String getVal(){
		return name;
	}
}
