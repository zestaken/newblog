package ZJChain;

import com.alibaba.fastjson.JSON;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_SG;

import java.util.ArrayList;
import java.util.HashMap;

public class ZJChain {

    //blockChain为静态属性，所有对象都是在对同一个blockchain修改
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 5;
    /**
     * 用于记录所有有效的UTXO，键是String类型的TransactionOutputId
     */
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<>();
    /**
     * 每次交易的最小交易额
     */
    public static float minimumTransaction = 0.1f;
    public static Wallet walletA;
    public static Wallet walletB;
    public static Transaction genesisTransaction;

    /**
     * 检查区块链的有效性
     * @return
     */
    public boolean isChainValid() {
        //todo 验证需修改
        Block curBlock;
        Block prevBlock;
        //用于检验挖矿难度是否达标的字符串
        String target = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String, TransactionOutput> tempUTXOs = new HashMap<>();
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));


        //遍历blockchain，从1开始，保证prevblock的有效性
        for(int i = 1; i < blockChain.size(); i++) {
            curBlock = blockChain.get(i);
            prevBlock = blockChain.get(i - 1);


            try {
                //检查hash值计算有效性
                if(!curBlock.hash.equals(curBlock.calculateHash())) {
                    System.out.println("block的hash值计算错误");
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //检查hash值前后对应关系正确性
            if(!prevBlock.hash.equals(curBlock.prevHash)) {
                System.out.println("当前block与前面block的hash值不对应");
                return false;
            }

            if(!curBlock.hash.substring(0, difficulty).equals(target)) {
                //如果不满足难度标准，也无效
                System.out.println("当前块未满足挖矿难度标准！");
                return false;
            }

            
        }
        return true;
    }

    /**
     * 向区块链中添加块
     * @param newBlock
     */
    public void addBlock(Block newBlock) {
        //先完成挖矿工作才能加入区块链中
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }

    /**
     * 将blockChain转换为json字符串本地存储
     * @return
     */
    public String toJson() {
        String blockChainString = JSON.toJSONString(blockChain);
        return blockChainString;
    }

    /**
     * 设置挖矿难度
     * @param difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
