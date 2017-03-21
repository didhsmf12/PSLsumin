public class Invoked {

static {
      System.loadLibrary("hello"); // Load native library at runtime
                                   // hello.dll (Windows) or libhello.so (Unixes)
   }

private native void getTid();
public static void main(String[] args) {
System.out.print("hello there!!! From Invoked : ");
new Invoked().getTid();
 }
}
