package com.mobanker.jfinal;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecretCommunication
{
    private static final BlockingQueue<Map<String, String>> CHANNEL = new LinkedBlockingQueue<Map<String, String>>(1);

    public static void main(String[] args) throws Exception
    {
        // 1. 生成RSA算法的公私密钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        kpg.initialize(512, random);
        KeyPair keyPair = kpg.generateKeyPair();

        Thread client = new Thread(new CommClient(keyPair));
        Thread server = new Thread(new CommServer(keyPair.getPublic()));
        client.start();
        server.start();
    }

    static class CommClient implements Runnable
    {
        private KeyPair rsaKeyPair;

        public CommClient(KeyPair keyPair)
        {
            this.rsaKeyPair = keyPair;
        }

        @Override
        public void run()
        {
            try
            {
                Map<String, String> receive = CHANNEL.take();
                String msgBytes = receive.get("msg");
                String keyBytes = receive.get("key");
                BASE64Decoder _64decoder = new BASE64Decoder();
                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) rsaKeyPair.getPrivate();
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
                // 5. 客户端拿到使用公钥加密后的目标密钥后，再使用自己的私钥(与服务端的RSA公钥是配对的)进行解密
                byte[] deKeyBytes = cipher.doFinal(_64decoder.decodeBuffer(keyBytes));
                // 6. 将解密后的目标密钥内容进行重构成目标密钥对象
                SecretKey sessionKey = new SecretKeySpec(deKeyBytes, "AES");
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, sessionKey);
                // 7. 使用重构后的目标密钥进行解密消息密文
                String msg = new String(cipher.doFinal(_64decoder.decodeBuffer(msgBytes)), "UTF-8");
                System.out.println("Client-Receive:" + msg);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    static class CommServer implements Runnable
    {
        private PublicKey    rsaPublicKey;

        private KeyGenerator keyGenerator;

        {
            try
            {
                keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256); // AES仅支持128,192,256 bit 的算法
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public CommServer(PublicKey rsaPublicKey)
        {
            this.rsaPublicKey = rsaPublicKey;
        }

        @Override
        public void run()
        {
            try
            {
                String msg = "Legend of AK47 王者之风";
                // 1.通常建议用AES算法生成目标密钥(生成的key不支持RSA算法)
                // String keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^";
                // Key sessionKey = new SecretKeySpec(keyStr.getBytes("UTF-8"), "AES");
                SecretKey sessionKey = keyGenerator.generateKey();
                Cipher cipher = Cipher.getInstance("AES", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, sessionKey);
                /*
                 * 2. 使用AES算法的目标密钥将目标消息进行加密 (AES/DES加密速度快,适合大量数据,DES容易破解,一般用3重DES,后来又出现了更快更安全的AES)
                 * RSA是公钥加密,速度慢,只能处理少量数据,优点是公钥即使在不安全的网络上公开,
                 * 也能保证安全常见情况是双方用RSA协商出一个密钥后通过AES/3DES给数据加密)
                 */
                byte[] msgBytes = cipher.doFinal(msg.getBytes("UTF-8"));
                cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
                // 3. 使用RSA算法的客户段公钥对目标密钥进行加密
                byte[] keyBytes = cipher.doFinal(sessionKey.getEncoded());
                Map<String, String> info = new HashMap<String, String>();
                BASE64Encoder _64encoder = new BASE64Encoder();
                String keyStr = _64encoder.encode(keyBytes);
                String msgStr = _64encoder.encode(msgBytes);
                System.out.println("key64: " + keyStr);
                System.out.println("msg64: " + msgStr);
                info.put("key", keyStr);
                info.put("msg", msgStr);
                // 4. 将加密后的目标密钥和加密后的目标消息一同发送给客户端
                CHANNEL.offer(info);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void en_de_crypt() throws Exception
    {
        String s = "毛红朝";
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey key = generator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bs = cipher.doFinal(s.getBytes("UTF-8"));
        System.out.println("encrypt:" + Arrays.toString(bs));
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("encrypt-str:" + encoder.encode(bs));
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] bs2 = cipher.doFinal(bs);
        System.out.println("decrypt:" + new String(bs2, "UTF-8"));
    }

    public static void verifyWithPubKey() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException,
            NoSuchProviderException, UnsupportedEncodingException
    {
        // Security.addProvider(provider)
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey puk = keyPair.getPublic();
        PrivateKey pik = keyPair.getPrivate();

        String data = "Hello, Java.";
        Signature signature = Signature.getInstance("SHA1withRSA");

        // private key sign
        signature.initSign(pik);
        System.out.println(Arrays.toString(data.getBytes("UTF-8")));
        signature.update(data.getBytes("UTF-8"));
        byte[] signinfo = signature.sign();
        System.out.println(Arrays.toString(signinfo));

        // public key resolve sign
        signature.initVerify(puk);
        boolean ok = signature.verify(signinfo);
        System.out.println("publicKey-verify:" + ok);

        signature.update(data.getBytes("UTF-8"));
        ok = signature.verify(signinfo);
        System.out.println("verify:" + ok);
    }

}
