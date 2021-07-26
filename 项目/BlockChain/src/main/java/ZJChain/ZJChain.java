package ZJChain;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

public class ZJChain {

    //blockChain为静态属性，所有对象都是在对同一个blockchain修改
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 5;

    /**
     * 检查区块链的有效性
     * @return
     */
    public boolean isChainValid() {
        Block curBlock;
        Block prevBlock;

        //遍历blockchain，从1开始，保证prevblock的有效性
        for(int i = 1; i < blockChain.size(); i++) {
            curBlock = blockChain.get(i);
            prevBlock = blockChain.get(i - 1);
            String target = new String(new char[difficulty]).replace('\0', '0');


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
                System.out.println("当前块未满足难度标准！");
                return false;
            }
        }
        return true;
    }

    /**
     * 向区块链中添加块
     * @param block
     */
    public void addBlock(Block block) {
        blockChain.add(block);
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
