package ZJChain;

import ZJChain.Block;
import ZJChain.ZJChain;
import org.junit.jupiter.api.Test;

public class ZJChainTest {
    @Test
    public void test1() {
        //初始化区块链
        ZJChain zjChain = new ZJChain();
        //向区块链中添加10个块
        for(int i = 0; i < 10; i++) {
            //创建新块
            Block block;
            if(zjChain.blockChain.size() == 0) {
                block = new Block("ZJChain.Block: " + i, "0");
                block.mineBlock(5);
            } else {
                block = new Block("ZJChain.Block: " + i, zjChain.blockChain.get(
                        zjChain.blockChain.size() - 1).hash);
                block.mineBlock(5);
            }
            zjChain.addBlock(block);
        }

        for(Block block : zjChain.blockChain) {
            System.out.println("hash: " + block.hash + " prevHash: " + block.prevHash);
        }
        System.out.println("isvalid：" + zjChain.isChainValid());
    }
}
