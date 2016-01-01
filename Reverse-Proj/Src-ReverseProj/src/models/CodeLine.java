package models;

public class CodeLine {

	private String instruction;
	private String destReg;
	private String operationSentence;
	private String [] regArray;
	private int instantNumber;
	
	
	public CodeLine(String line) {
		super();
		this.instruction=calIntruction(line);
		this.destReg=calDestReg(line);
		this.operationSentence=calOperationSentence(line);
		generateOtherInfo();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 生成Rn列表还有立即数
	 */
	private void generateOtherInfo(){
	   String tmpStr=operationSentence.replace("[", "").replace("]", "");
	   String [] strs=tmpStr.split(",");
	   int regArraySize=strs.length;
	   if(tmpStr.contains("#")){
		   regArraySize--;
		   String instantNumber=strs[strs.length-1].trim().replaceAll("#", "");
		   if(instantNumber.startsWith("0x")){
			   this.instantNumber=Integer.parseInt(instantNumber.replaceAll("0x", ""),16);
		   }
		   else
			   this.instantNumber=Integer.parseInt(instantNumber);
	   }
	   else
	   {
		   this.instantNumber=0;
	   }
	   if(regArraySize==0){
		   this.regArray=null;
		   return;
	   }
	   this.regArray=new String[regArraySize];
	   for(int i=0;i<regArraySize;i++){
		   this.regArray[i]=strs[i].trim();
	   }
	}
	
	private  String calDestReg(String line){
		String string=line.split("\\s")[1];
		string=string.replaceAll(",", "");
		return string;
	}
	
	private   String calIntruction(String line){
		String string=line.split("\\s")[0];
		return string;
	}
	
	private   String calOperationSentence(String line){
		String string=line.replaceFirst("[A-Z]*\\s+[A-Z]*[0-9]*,", "");
		return string;
	}
	
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getDestReg() {
		return destReg;
	}
	public void setDestReg(String destReg) {
		this.destReg = destReg;
	}
	public String getOperationSentence() {
		return operationSentence;
	}
	public void setOperationSentence(String operationSentence) {
		this.operationSentence = operationSentence;
	}
	public String[] getRegArray() {
		return regArray;
	}
	public void setRegArray(String[] regArray) {
		this.regArray = regArray;
	}
	public int getInstantNumber() {
		return instantNumber;
	}
	public void setInstantNumber(int instantNumber) {
		this.instantNumber = instantNumber;
	}
	
	
	
}
