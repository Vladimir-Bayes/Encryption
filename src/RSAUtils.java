import java.io.ByteArrayOutputStream;  
import java.security.Key;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.SecureRandom;  
import java.security.Security;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.UUID;  
  
import javax.crypto.Cipher;  
  
import org.bouncycastle.jce.provider.BouncyCastleProvider;  
import org.bouncycastle.util.encoders.Base64;


/** 
* @author ����Vladimir E-mail: gyang.shines@gmail.com
* @version ����ʱ�䣺2017��12��11�� ����8:16:04 
* ��˵�� 
*/

/** 
 * RSA��ȫ������� 
 */  

public class RSAUtils {
	/** 
     * �ǶԳƼ�����Կ�㷨 
     */  
    public static final String KEY_ALGORITHM_RSA = "RSA";  
  
    /** 
     * ��Կ 
     */  
    private static final String RSA_PUBLIC_KEY = "RSAPublicKey";  
  
    /** 
     * ˽Կ 
     */  
    private static final String RSA_PRIVATE_KEY = "RSAPrivateKey";  
      
    /** 
     * RSA��Կ����  
     * Ĭ��1024λ�� 
     * ��Կ���ȱ�����64�ı�����  
     * ��Χ��512��65536λ֮�䡣 
     */  
    private static final int KEY_SIZE = 1024;  
      
    static{  
        Security.insertProviderAt(new BouncyCastleProvider(), 1);  
    }  
    /** 
     * ˽Կ���� 
     *  
     * @param data 
     *            ���������� 
     * @param key 
     *            ˽Կ 
     * @return byte[] �������� 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key)  
            throws Exception {  
  
        // ȡ��˽Կ  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);  
  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
  
        // ����˽Կ  
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // �����ݽ���  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
  
        int blockSize = cipher.getBlockSize();  
        if(blockSize>0){  
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);  
            int j = 0;  
            while (data.length - j * blockSize > 0) {  
                bout.write(cipher.doFinal(data, j * blockSize, blockSize));  
                j++;  
            }  
            return bout.toByteArray();  
        }  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * ��Կ���� 
     *  
     * @param data 
     *            ���������� 
     * @param key 
     *            ��Կ 
     * @return byte[] �������� 
     * @throws Exception 
     */  
    public static byte[] decryptByPublicKey(byte[] data, byte[] key)  
            throws Exception {  
  
        // ȡ�ù�Կ  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);  
  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
  
        // ���ɹ�Կ  
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);  
  
        // �����ݽ���  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * ��Կ���� 
     *  
     * @param data 
     *            ���������� 
     * @param key 
     *            ��Կ 
     * @return byte[] �������� 
     * @throws Exception 
     */  
    public static byte[] encryptByPublicKey(byte[] data, byte[] key)  
            throws Exception {  
  
        // ȡ�ù�Կ  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);  
  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
          
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);  
  
        // �����ݼ���  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
          
        int blockSize = cipher.getBlockSize();  
        if(blockSize>0){  
            int outputSize = cipher.getOutputSize(data.length);  
            int leavedSize = data.length % blockSize;  
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1  
                    : data.length / blockSize;  
            byte[] raw = new byte[outputSize * blocksSize];  
            int i = 0,remainSize=0;  
            while ((remainSize = data.length - i * blockSize) > 0) {  
                int inputLen = remainSize > blockSize?blockSize:remainSize;  
                cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);  
                i++;  
            }  
            return raw;  
        }  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * ˽Կ���� 
     *  
     * @param data 
     *            ���������� 
     * @param key 
     *            ˽Կ 
     * @return byte[] �������� 
     * @throws Exception 
     */  
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key)  
            throws Exception {  
  
        // ȡ��˽Կ  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);  
  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
  
        // ����˽Կ  
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
  
        // �����ݼ���  
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
  
        int blockSize = cipher.getBlockSize();  
        if(blockSize>0){  
            int outputSize = cipher.getOutputSize(data.length);  
            int leavedSize = data.length % blockSize;  
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1  
                    : data.length / blockSize;  
            byte[] raw = new byte[outputSize * blocksSize];  
            int i = 0,remainSize=0;  
            while ((remainSize = data.length - i * blockSize) > 0) {  
                int inputLen = remainSize > blockSize?blockSize:remainSize;  
                cipher.doFinal(data, i * blockSize, inputLen, raw, i * outputSize);  
                i++;  
            }  
            return raw;  
        }  
        return cipher.doFinal(data);  
    }  
  
    /** 
     * ȡ��˽Կ 
     *  
     * @param keyMap 
     *            ��ԿMap 
     * @return key ˽Կ 
     * @throws Exception 
     */  
    public static Key getPrivateKey(Map<String, Key> keyMap)  
            throws Exception {  
        return keyMap.get(RSA_PRIVATE_KEY);  
    }  
  
    /** 
     * ȡ��˽Կ 
     *  
     * @param keyMap 
     *            ��ԿMap 
     * @return byte[] ˽Կ 
     * @throws Exception 
     */  
    public static byte[] getPrivateKeyByte(Map<String, Key> keyMap)  
            throws Exception {  
        return keyMap.get(RSA_PRIVATE_KEY).getEncoded();  
    }  
      
    /** 
     * ȡ�ù�Կ 
     *  
     * @param keyMap 
     *            ��ԿMap 
     * @return key ��Կ 
     * @throws Exception 
     */  
    public static Key getPublicKey(Map<String, Key> keyMap)  
            throws Exception {  
        return keyMap.get(RSA_PUBLIC_KEY);  
    }  
      
    /** 
     * ȡ�ù�Կ 
     *  
     * @param keyMap 
     *            ��ԿMap 
     * @return byte[] ��Կ 
     * @throws Exception 
     */  
    public static byte[] getPublicKeyByte(Map<String, Key> keyMap)  
            throws Exception {  
        return keyMap.get(RSA_PUBLIC_KEY).getEncoded();  
    }  
      
    /** 
     * ��ʼ����Կ 
     * @param byte[] seed ���� 
     * @return Map ��ԿMap 
     * @throws Exception 
     */  
    public static Map<String,Key> initKey(byte[] seed)throws Exception{  
        // ʵ������Կ��������  
        KeyPairGenerator keyPairGen = KeyPairGenerator  
                .getInstance(KEY_ALGORITHM_RSA);  
  
        // ��ʼ����Կ��������  
        keyPairGen.initialize(KEY_SIZE, new SecureRandom(seed) );  
  
        // ������Կ��  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
  
        // ��Կ  
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  
  
        // ˽Կ  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
  
        // ��װ��Կ  
        Map<String, Key> keyMap = new HashMap<String, Key>(2);  
  
        keyMap.put(RSA_PUBLIC_KEY, publicKey);  
        keyMap.put(RSA_PRIVATE_KEY, privateKey);  
  
        return keyMap;  
    }  
      
    /** 
     * ��ʼ����Կ 
     * @param seed ���� 
     * @return Map ��ԿMap 
     * @throws Exception 
     */  
    public static Map<String,Key> initKey(String seed)throws Exception{  
        return initKey(seed.getBytes());  
    }  
  
    /** 
     * ��ʼ����Կ 
     *  
     * @return Map ��ԿMap 
     * @throws Exception 
     */  
    public static Map<String, Key> initKey() throws Exception {  
        return initKey(UUID.randomUUID().toString().getBytes());  
    }  
      
    public static PublicKey getPublicRSAKey(String key) throws Exception {  
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64.decode(key));  
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
        return kf.generatePublic(x509);  
    }  
  
    public static PrivateKey getPrivateRSAKey(String key) throws Exception {  
        PKCS8EncodedKeySpec pkgs8 = new PKCS8EncodedKeySpec(Base64.decode(key));  
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM_RSA);  
        return kf.generatePrivate(pkgs8);  
    }  
}
