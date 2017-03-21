import org.jikesrvm.scheduler.*;
import org.jikesrvm.classloader.*;
import org.jikesrvm.runtime.*;
import org.vmmagic.unboxed.Address;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
class Multithread extends Thread
{

	static
	{
		System.loadLibrary("lbr");
	}

	private native void lbrRecord();

	private native void getTid();



	public void run()
	{


		System.out.print("Hello! Im ");


		new Multithread().getTid();
//		new Multithread().lbrRecord();

	String[] args = new String[1];
	args[0] = "Benchmark";
 if (args.length != 1) {
      System.err.println("Usage: java Invoker ");
      System.exit(1);
    }
    Class[] argTypes = new Class[1];
    argTypes[0] = String[].class;
    try {
      Method mainMethod = Class.forName(args[0]).getDeclaredMethod("main",argTypes);
      System.out.println("args[0] : "+args[0]);
      Object[] argListForInvokedMain = new Object[1];
      argListForInvokedMain[0] = new String[0];
      // Place whatever args you
      // want to pass into other
      // class's main here.

	Thread fuck = currentThread();
	
//	System.out.println(fuck.getName());


	RVMThread my_thread ;

	my_thread =  RVMThread.getCurrentThread();
	fuck = my_thread.getJavaLangThread();
//	fuck.dumpStack();
//		System.out.println("Stack Length : " + my_thread.framePointer);


	my_thread.dumpStack();
 for (int i = 0; i < my_thread.numThreads; i++) {
      RVMThread t = my_thread.threads[i];
//	t.dumpStack();

	}

//	StackBrowser browser = new StackBrowser();
//	RVMClass my_class  = browser.getCurrentClass();

	
//	Address pointer = Magic.getReturnAddressUnchecked(fp);
//	System.out.println("ADDR = " + browser.currentFramePointer);

	


      mainMethod.invoke(null, argListForInvokedMain);
      }
      catch (ClassNotFoundException ex) {
        System.err.println("Class "+args[0]+"      not found in classpath.");
      }
      catch (NoSuchMethodException ex) {
        System.err.println("Class "+args[0]+"   does not define public static         void main(String[])");
      }
      catch (InvocationTargetException ex) {
        System.err.println("Exception while    executing "+args[0]+":"+ex.getTargetException());
      }
      catch (IllegalAccessException ex) {
        System.err.println("main(String[]) in       class "+args[0]+" is not public");
      }




		try
		{
			//sleep(2000);


//			Process p = Runtime.getRuntime().exec("ls");

		
//			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
			String line = null;
/*
			while((line = br.readLine()) != null)
			{

			        System.out.println(line);

			}

*/
		}
		catch(Exception e)
		{

			System.out.println(e);

		}



//	System.out.println("I wanna see the timing");


	}


	public static void main(String[] args)

	{

		Multithread mt1 = new Multithread();
	RVMThread my_thread ;

	my_thread =  RVMThread.getCurrentThread();

	my_thread.dumpStack();




		mt1.start();
		
//	new Multithread().lbrRecord();


//	RVMThread my_thread ;

	my_thread =  RVMThread.getCurrentThread();

	my_thread.dumpStack();


		System.out.println("Termination!!!!");
	}

}
