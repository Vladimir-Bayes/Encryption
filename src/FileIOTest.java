import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * @author ����Vladimir E-mail: gyang.shines@gmail.com
 * @version ����ʱ�䣺2017��12��13�� ����8:40:43 
 * ��˵�� 
 */
public class FileIOTest {

	@SuppressWarnings("resource")
	public static void main(String[] args)  {
		try {
			BufferedReader in = new BufferedReader(new FileReader("D://train//tri5.in"));
			String str;
			while ((str = in.readLine()) != null) {
				System.out.println(str);
			}
//			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

