import org.jikesrvm.compilers.common.assembler.ia32.*;
import org.jikesrvm.util.*;
import org.jikesrvm.compilers.common.*;
import org.vmmagic.unboxed.*;
import org.jikesrvm.VM;
 import org.jikesrvm.scheduler.*;
import org.jikesrvm.classloader.*;
import org.jikesrvm.runtime.*;
import java.io.*;

class Benchmark{
protected static int global;
 
public static void main(String[] args) throws IOException{
    long t1 = System.nanoTime();
 
    int value = 0;
//  Thread fuck = currentThread();

  //      System.out.println(fuck.getName());



	RVMThread my_thread ;

	my_thread =  RVMThread.getCurrentThread();

	my_thread.dumpStack();
	System.out.println("Benchmark Stack length : "+ my_thread.getStackLength());

	byte[] bytecode = my_thread.getStack();
	FileOutputStream output = new FileOutputStream("./paper.txt");	    
	output.write(bytecode);
	output.close();

//	VM.sysWriteln(Magic.objectAsAddress(my_thread.getStack())	);
//	VM.sysWriteln( Magic.objectAsAddress(my_thread.getStack() ).plus( my_thread.getStackLength()));


	Address fp = Magic.getFramePointer();
	Address ip = Magic.getReturnAddress(fp); 
	VM.sysWriteln(fp);
//	fp = Magic.getCallerFramePointer(fp);

//	ip = Magic.getReturnAddress(fp); 
//	VM.sysWriteln("FP : " fp + "IP : " + ip);

	int compiledMethodId = Magic.getCompiledMethodID(fp);
	CompiledMethod compiledMethod = CompiledMethods.getCompiledMethod(compiledMethodId);
	VM.sysWrite("Next Inst : ");
	VM.sysWrite(Magic.getReturnAddressLocation(fp));
	VM.sysWrite(" , Return Location : ");
	VM.sysWriteln(Magic.getReturnAddressLocation(fp));


//	System.out.println("ID : " + compiledMethodId);
	CodeArray Instruction = compiledMethod.getEntryCodeArray();
//	VM.sysWriteln("Instruction offset : " + compiledMethod.getInstructionOffset(ip));
//	Offset instructionOffset = compiledMethod.getInstructionOffset(ip);
	VM.sysWriteln("ID : "+ compiledMethodId + " Length : " + Instruction.length());
	VM.sysWriteln("num of Instruction : " + compiledMethod.numberOfInstructions());
	String name = compiledMethod.getCompilerName();
	VM.sysWriteln("Compiler : " + name);	


	fp = Magic.getCallerFramePointer(fp);
	CompiledMethod comMethod2 = CompiledMethods.getCompiledMethod(Magic.getCompiledMethodID(fp));

	CodeArray Inst2 = compiledMethod.codeArrayForOffset( comMethod2.getInstructionOffset(ip )  );
	FileOutputStream gay = new FileOutputStream("./gay.txt");
	/*stack trace*/

	compiledMethod.printStackTrace(comMethod2.getInstructionOffset(ip), PrintContainer.get(System.out));	


	for ( int i=0 ; i< Inst2.length() ; i++)
	gay.write(Inst2.get(i));

	gay.close();



	FileOutputStream paper = new FileOutputStream("./paper.txt");

	for ( int i=0 ; i< Instruction.length() ; i++)
	paper.write(Instruction.get(i));

	paper.close();

	VM.sysWriteln("Instruction Addr : "+ Magic.objectAsAddress(compiledMethod.getEntryCodeArray())	);
	System.out.println("Instruction length : " + Instruction.length());
	RVMMethod method = compiledMethod.getMethod();
	System.out.println("Name : " +method.getName() );
	

	if (compiledMethod.containsReturnAddress(ip)){ 
       Offset instructionOffset = compiledMethod.getInstructionOffset(ip);
	VM.sysWriteln("Offset : "+instructionOffset);

	System.out.println("Successful!!");
		}


	VM.sysWriteln(ip);




	/*Stack Browser Testing*/


	StackBrowser browser = new StackBrowser();
	browser.init();
	VM.sysWriteln("\n\nCurrent bytecode index : " + browser.getBytecodeIndex() );
	RVMMethod sbm = browser.getMethod();
	VM.sysWriteln("current name : "+ sbm.getName() );



	/*calculate function test*/

/*	CompiledMethod cal = CompiledMethods.getCompiledMethod(compiledMethodId+1);
	RVMMethod metcal = cal.getMethod();
	System.out.println("Name : " +metcal.getName() );
*/



	int tot = 1;



	for (int j= 0 ; j <  100; j++)
	    for (int i = 0; i < 100 * 1000 * 1000; i++) {
		   	if(i%3 ==0)	 value = calculate(value,tot);
			if(i%3 ==1)      value = calculate(value,tot)-value;
			if(i%3 ==2)      value = calculate(value,tot)+value;
			if(tot == 1) tot = 0;
	    }
 
    long t2 = System.nanoTime();
//    System.out.println("Execution time: " + ((t2 - t1) * 1e-6) + " milliseconds");
}
 
protected static int calculate(int arg, int tot) {
    //L1: assert (arg >= 0) : "should be positive";
    //L2: if (arg < 0) throw new IllegalArgumentException("arg = " + arg + " < 0");
 /*	if (tot == 1)
	{
		RVMThread my_thread ;
		my_thread =  RVMThread.getCurrentThread();
		my_thread.dumpStack();
	}*/
    global = arg * 6;
    global += 3;
    global /= 2;
    return arg + 2;
	}
}
