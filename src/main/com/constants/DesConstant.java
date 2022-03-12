package com.constants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


/**
 * AES/CBC/NoPadding (128)
 * AES/CBC/PKCS5Padding (128)
 * AES/ECB/NoPadding (128)
 * AES/ECB/PKCS5Padding (128)
 * DES/CBC/NoPadding (56)
 * DES/CBC/PKCS5Padding (56)
 * DES/ECB/NoPadding (56)
 * DES/ECB/PKCS5Padding (56)
 * DESede/CBC/NoPadding (168)
 * DESede/CBC/PKCS5Padding (168)
 * DESede/ECB/NoPadding (168)
 * DESede/ECB/PKCS5Padding (168)
 * RSA/ECB/PKCS1Padding (1024, 2048)
 * RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024, 2048)
 * RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024, 2048)
 *
 * @author admin
 * @desciption
 */
public class DesConstant {
    protected static final String KEY_ALGORITHM = "DES";

    protected static final String KEY_ALGORITHM_3 = "DESede";

    protected static final String ECB_CIPHER_ECB = "DES/ECB/PKCS5Padding";

    protected static final String CBC_CIPHER_CBC = "DES/CBC/PKCS5Padding";

    protected static final String ECB_CIPHER_DE_ECB = "DESede/ECB/PKCS5Padding";

    protected static final String CBC_CIPHER_DE_CBC = "DESede/CBC/PKCS5Padding";

    public static final String CBC_CIPHER_NO_PADDING = "DES/CBC/NoPadding";

    protected static final byte[] I_V = {1, 2, 3, 4, 5, 6, 7, 8};

    /**
     * DES/3DES加密
     *
     * @param isDES3
     * @param isCBC
     * @param key    加密秘钥
     * @param data   加密的数据源
     * @return 加密后的数据
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] des_encrypt(boolean isDES3, boolean isCBC, byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(isDES3 ? KEY_ALGORITHM_3 : KEY_ALGORITHM);
        //将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(isDES3 ? new DESedeKeySpec(key) : new DESKeySpec(key));
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(isDES3 ? (isCBC ? CBC_CIPHER_DE_CBC : ECB_CIPHER_DE_ECB) : (isCBC ? CBC_CIPHER_CBC : ECB_CIPHER_ECB));
        if (isCBC) {
            // ECB模式下，iv不需要
            IvParameterSpec iv = new IvParameterSpec(I_V);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
        } else {
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
        }
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }

    /**
     * @param isDES3
     * @param isCBC
     * @param key    解密秘钥
     * @param data   解密的数据源
     * @return 解密后的数据
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] des_decrypt(boolean isDES3, boolean isCBC, byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(isDES3 ? KEY_ALGORITHM_3 : KEY_ALGORITHM);
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(isDES3 ? new DESedeKeySpec(key) : new DESKeySpec(key));
        // Cipher对象实际完成解密操作，CBC为加密模式，
        Cipher cipher = Cipher.getInstance(isDES3 ? (isCBC ? CBC_CIPHER_DE_CBC : ECB_CIPHER_DE_ECB) : (isCBC ? CBC_CIPHER_CBC : ECB_CIPHER_ECB));
        if (isCBC) {
            // ECB模式下，iv不需要
            IvParameterSpec iv = new IvParameterSpec(I_V);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        } else {
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey);
        }
        // 真正开始解密操作
        return cipher.doFinal(data);
    }

    /**
     * DES加密;
     *
     * @param key
     * @param data
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] des_encrypt(byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return des_encrypt(false, false, key, data);
    }

    /**
     * DES解密;
     *
     * @param key
     * @param data
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] des_decrypt(byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return des_decrypt(false, false, key, data);
    }

    /**
     * 3DES加密;
     *
     * @param key
     * @param data
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] des3_encrypt(byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return des_encrypt(true, false, key, data);
    }

    /**
     * 3DES解密;
     *
     * @param key
     * @param data
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] des3_decrypt(byte[] key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return des_decrypt(true, false, key, data);
    }

}
