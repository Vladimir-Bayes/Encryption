import java.awt.AWTException;
import java.awt.Robot;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月12日 下午9:14:27 
* 类说明 
*/
public class DelayTest {

	public static void main(String[] args) {
		System.out.println("11111");
		try   
		{   
			System.out.println("22222");
			Thread.currentThread();
			Thread.sleep(5000);//毫秒
			System.out.println("33333");
		}   
		catch(Exception e){
			
		}
		System.out.println("444444");
		Timer timer=new Timer();//实例化Timer类   
		timer.schedule(new TimerTask(){   
		public void run(){   
		System.out.println("555555555");   
		this.cancel();}},5000);//五百毫秒  
		System.out.println("6666666666666666666666666666666");  
		
		 Robot r;
		try {
			r = new   Robot();
			System.out.println( "延时前:"+new Date().toString()  );   
	         r.delay(   2000   );     
	         System.out.println(   "延时后:"+new Date().toString()   );
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		System.out.println("777777");  
		
		
		
	}
}
