package ZJChain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    public PublicKey publicKey;
    public PrivateKey privateKey;

    public Wallet() {
        generateKeyPair();
    }

    /**
     * 生成公私钥
     */
    public void generateKeyPair() {
        try {
            //指定算法ECDSA生成密钥对
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            //初始化并生成密钥对
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            //获取公私钥
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
