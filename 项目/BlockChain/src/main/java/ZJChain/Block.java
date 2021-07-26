package ZJChain;

import utils.StringUtil;

import java.util.Date;

public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timestamp;
    private int nonce; //用于挖矿的变量

    public Block(String data, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timestamp = new Date().getTime();
        //初始化哈希值必须在其它属性都已初始化之后
        try {
            this.hash = calculateHash();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算当前块的哈希值
     * @return
     * @throws Exception
     */
    public String calculateHash() throws Exception {
        String calculatedHash = StringUtil.applySha256(prevHash+data+timestamp+nonce);
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {
        //生成目标字符串：此处是包含指定数量（difficulty）个连续的0的字符串
        String target = new String(new char[difficulty]).replace('\0', '0');
        //检查当前块的hash值中从0到difficulty部分是否与target字符串相同，如果不相同，则修改nonce，重新计算hash
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            try {
                hash = calculateHash();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("nonce：" + nonce);
    }
}
