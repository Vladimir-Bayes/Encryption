import java.awt.AWTException;
import java.awt.Robot;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/** 
* @author ����Vladimir E-mail: gyang.shines@gmail.com
* @version ����ʱ�䣺2017��12��12�� ����9:14:27 
* ��˵�� 
*/
public class DelayTest {

	public static void main(String[] args) {
		System.out.println("11111");
		try   
		{   
			System.out.println("22222");
			Thread.currentThread();
			Thread.sleep(5000);//����
			System.out.println("33333");
		}   
		catch(Exception e){
			
		}
		System.out.println("444444");
		Timer timer=new Timer();//ʵ����Timer��   
		timer.schedule(new TimerTask(){   
		public void run(){   
		System.out.println("555555555");   
		this.cancel();}},5000);//��ٺ���  
		System.out.println("6666666666666666666666666666666");  
		
		 Robot r;
		try {
			r = new   Robot();
			System.out.println( "��ʱǰ:"+new Date().toString()  );   
	         r.delay(   2000   );     
	         System.out.println(   "��ʱ��:"+new Date().toString()   );
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		System.out.println("777777");  
		
		
		
	}
}
