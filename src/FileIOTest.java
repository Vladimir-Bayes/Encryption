import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月13日 下午8:40:43 
 * 类说明 
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

