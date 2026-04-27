package abacus.matrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Matrix {
	private static final Logger logger=LogManager.getLogger(Matrix.class);		
	private int[][] abacus;
	
	private int rowSum(int row, int[][] matrix){
		int sum=0;
		for(int j=0; j < matrix[row].length; j++){
			sum+=matrix[row][j];
		}
		return sum;
	}
	
	public double getSum(){
		int rows=abacus.length;
		double sum=0;
		for(int i=0; i < rows; i++){
			for(int j=0; j < abacus[i].length; j++){
				sum+=(abacus[i][j]*Math.pow(10, (rows-(i+1))*1.0));				
			}
		}
		return sum;
	}
	
	// private void update() {
	// calculate the negative result, ...
	public double getNegativeSum(double sum){
	// double sum=getSum();
	double nsum=0;
	try {
		// choose the closest "big friend" 10^p to the current result
		double p=0;
		p = (sum == 0) ? 1 : (int)Math.log10(Math.abs(sum)) + 1; // safer code
		double base=Math.pow(10, p);
		
		// and calculate: current sum - "big friend"
		nsum=(sum==0)?0:(sum - base);
		
	}catch(ArithmeticException e) {
		// log(0) should never ever happen, since it is already guarded in
		// the code lines above (i. e. nsum=(sum==0)?:0:(sum-base)).
		// Yet if something like that does happen, then ...
		logger.error(e.getMessage());
	}
	return nsum;
	}
	
	
	public void add(int row, int amount){
		int limit=abacus[row].length;// max=10
		int j=(limit-1);
		int count=0;
		int currentSum=rowSum(row, abacus);
		if(currentSum < limit) {
			while((j>=0)&&(count < amount)){
				if(abacus[row][j]==0) {
					abacus[row][j]=1;
					count++;
				}
				j--;
			}
		}else{
			logger.info("addition attempt but no bead is available anymore");
		}
	}
	
	public void subtract(int row, int amount){
		int limit=abacus[row].length;
		int j=0;
		int count=0;
		int currentSum=rowSum(row, abacus);
		if(currentSum > 0){
			while((j<limit)&&(count < amount)){
				if(abacus[row][j]==1) {
					abacus[row][j]=0;
					count++;
				}
				j++;
			}
			}else{// disables this button when no bead is any longer
				// available
			logger.info("subtraction atttempt but no bead is available anymore");
		}
	}
	
	public String toString(){
		String str="";
		for(int i=0; i < abacus.length; i++){
			for(int j=0; j < abacus[i].length; j++){
				str+=abacus[i][j]+" ";
			}
			str+="\n ";
		}
		return str;
	}
	
	public Matrix(int rows, int cols) {
		// TODO Auto-generated constructor stub
		abacus=new int[rows][cols];
		for(int i=0; i < rows; i++){
			for(int j=0; j < cols; j++){
				abacus[i][j]=0;
			}
		}
	}
	
	public int[][] getAbacus(){
		return abacus;
	}
}
