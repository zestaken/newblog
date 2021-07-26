---
title: Java实现区块链
date: 2021-07-26 08:02:19
tags: [Java, 区块链]
categories: 项目
---

# 区块链概念

* 区块链（BlockChain）起源于比特币，2008年11月1日，一位自称中本聪(Satoshi Nakamoto)的人发表了《比特币:一种点对点的电子现金系统》一文，阐述了基于P2P网络技术、加密技术、时间戳技术、区块链技术等的电子现金系统的构架理念，这标志着比特币的诞生。
* 在比特币形成过程中，区块（ZJChain.Block）是一个一个的存储单元，记录了一定时间内各个区块节点全部的交流信息。各个区块之间通过随机散列(也称哈希算法)实现链接，后一个区块包含前一个区块的哈希值，随着信息交流的扩大，一个区块与一个区块相继接续，形成的结果就叫区块链（BlockChain）。
* 看着这些概念头脑中也难以形成一个具体的印象，不如实现一个区块链的demo来看一看。
* [参考教程](https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa)

# 1. 实现Block结构

* 区块链（BlockChain）顾名思义，是将一个个区块（ZJChain.Block）链接起来形成。所以我们实现区块链的第一步是实现Block结构。
* 区块链的链不是传统的通过指针等技术实现，而是通过哈希值来链接。所以一个Block中需要包含自身的哈希值，前一个Block的哈希值，还有自身的数据。而当前块的哈希值是通过前一个块的哈希值、当前块的创建的时间以及当前块的数据三者根据加密算法计算得出的。所以Block中还要包含时间戳变量表示块创建时间。
* Block类实现如下：
```java
public class ZJChain.Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timestamp;

    public ZJChain.Block(String data, String prevHash) {
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
        String calculatedHash = StringUtil.applySha256(prevHash+data+timestamp);
        return calculatedHash;
    }
}
```
* 应用SHA256算法来计算哈希值：
```java
    /**
     * 应用SHA256算法接收输入字符串计算并返回哈希字符串
     * @param input
     * @return
     * @throws Exception
     */
    public static String applySha256(String input) throws Exception {
            //返回实现指定摘要算法的 MessageDigest 对象。此处是SHA-256算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); //getInstance有异常
            //根据输入的bytes数组完成哈希计算。
            byte[] hash = digest.digest(input.getBytes("UTF-8"));//getBytes有异常
            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i < hash.length; i++) {
                //将生成的哈希字节数组每一字节（8bit）转换16进制数字符串
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    //当生成的16进制数只有一位时，在末尾添0，丢弃生成的16进制数（因为8位应是两位的16进制数，除非前面全为0）
                    hexString.append("0");
                }
                //将每一个字节的转换结果连接
                hexString.append(hex);
            }
            return hexString.toString();
    }
```
  * SHA256:
    * SHA256的中文全称叫做“安全哈希算法”。所谓的“哈希”是Hash的音译，而Hash就是进行Hash函数的意思。通常来说，Hash函数的运算有一个共同特点。就是不论原始数据有多少位，只要通过Hash运算后，得到结果的长度都是固定的。
    * Hash函数的类型有很多种，包括SHA224、SHA256、SHA384、SHA512、SHA512/224、SHA512/256等。但是比特币仅选用了SHA256。这个256代表的意思是，数据经过函数运算后得到的结果必须是一个256位的2进制数字。
    * 每次Hash计算后得到的结果有三个要求：第一、输入Hash函数之前的数据和通过Hash函数处理过后得到的编号必须一一对应。第二、每一个编号的长度都是固定的。第三、我们无法通过编号倒推出数据的内容。

# 2. 实现区块链（BlockChain）结构

* 前面构造来区块（ZJChain.Block），现在把他们连接起来存储就形成了区块链。我们采用ArrayList结构来组织这些Block。
```java
public class ZJChain.ZJChain {

    //blockChain为静态属性，所有对象都是在对同一个blockchain修改
    public static ArrayList<Block> blockChain = new ArrayList<Block>();

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
}
```
  * 因为ArrayList是内存中的数据结构，需要长期保存的话需要转换为JSON字符串写入文件中保存,通过alibaba的fastjson包实现。 
* 构建区块链的测试：
```java
   @Test
    public void test1() {
        //初始化区块链
        ZJChain.ZJChain zjChain = new ZJChain.ZJChain();
        //向区块链中添加10个块
        for(int i = 0; i < 10; i++) {
            //创建新块
            ZJChain.Block block;
            if(zjChain.blockChain.size() == 0) {
                block = new ZJChain.Block("ZJChain.Block: " + i, "0");
            } else {
                block = new ZJChain.Block("ZJChain.Block: " + i, zjChain.blockChain.get(
                        zjChain.blockChain.size() - 1).hash);
            }
            zjChain.addBlock(block);
        }

        for(ZJChain.Block block : zjChain.blockChain) {
            System.out.println("hash: " + block.hash + " prevHash: " + block.prevHash);
        }
    }
```

# 3. 准备挖矿！！！

* 提起比特币，区块链，便离不开挖矿这个话题。那什么是挖矿？比特币挖矿就是找到一个随机数（Nonce）参与哈希运算Hash（ZJChain.Block Header），使得最后得到的哈希值符合难度要求（在很多种组合中试出满足要求的组合, 有一点运气成分），用公式表示就是Hash（ZJChain.Block Header）<= target。具体的说就是使生成的哈希值的开头至少有指定数目个0。实现如下：
```java
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
```
* 简单来看挖矿难度的高低就是生成区块头的哈希值有多少0。difficulty每增加1，运算量都是呈几何增加，十分恐怖。
* 当难度为4:![SAWTcE](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/SAWTcE.png)
* 当难度为5:![lVcfrW](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/lVcfrW.png)
* 当难度为6:![4IZwd2](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/4IZwd2.png)（好家伙，直接跑了8分多钟。。。）
* 对比可以看出，难度增加1，运算量（nonce可以表示运算的次数）直接增加一个量级，怪不得作为工作量衡量的标准（proof-of-work)。
* 这里还可以看出，挖矿之所以很耗算力，并不是因为这个哈希计算本身有多么复杂，而是它是需要重复这个哈希计算很多次（我这随便提一点难度，都到千万级了。。。）直到满足要求。这也是为什么GPU挖矿效率比CPU高的原因：其实不是GPU运算速度比CPU快，而是GPU运算的数据是单一的，是经过CPU运算往后分离出来的单一数据。CPU运算的所需求的东西许多，而且不是单一的某种数据。CPU可以运行更复杂的指令。如果是做一个简单的数学计算，一个最大16核的CPU最多只能同时跑16个线程，而一个普通的GPU就可以同时跑3000多个线程，所以做简单数学，GPU就比CPU要快几个数量级，而生成区块要做的哈希计算偏偏就是一个很简单的数学题。
* 另外，在检查区块链的有效性(isChainValid)的时候，还需要增加检查hash值是否满足难度要求这一点：
```java
    if(!curBlock.hash.substring(0, difficulty).equals(target)) {
        //如果不满足难度标准，也无效
        System.out.println("当前块未满足难度标准！");
        return false;
    }
```

# 4. 创建钱包

* 比特币是一种点对点的电子现金系统，没有实物形态，可以存储在比特币钱包里。日常生活中，钱包是用来放钱的，但比特币钱包里却没有比特币，而只是确立比特币所有权的工具：比特币被记录在比特币网络的区块链（前面实现的BlockChain）中，比特币的所有权是通过数字密钥、比特币地址和数字签名（接下来要实现的）来确立的。
* 数字密钥并不存储在网络中，而是由用户生成并存储在一个文件或简单的数据库中，称为钱包。比特币钱包里存储着你的比特币信息，包括比特币地址（类似于你的银行卡账号）和数字秘钥。
* 数字秘钥是用公钥加密创建一个密钥对，用于控制比特币的获取。密钥对包括一个私钥，和由其衍生出的唯一的公钥。公钥用于接收比特币，而私钥用于生成比特币支付时的交易签名（类似于你银行卡的密码）。支付比特币时，比特币的当前所有者需要在交易中提交其公钥和签名（每次交易的签名都不同，但均从同一个私钥生成）。比特币网络中的所有人都可以通过所提交的公钥和签名进行验证，并确认该交易是否有效，即确认支付者在该时刻对所交易的比特币拥有所有权。比特币私钥就用来保护你的钱包，如果私钥丢失，你将永远失去这笔比特币。
* Wallet实现：
```java
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
```
* ECDSA:椭圆曲线数字签名算法（Elliptic Curve Digital Signature Algorithm，缩写ECDSA）是一种被广泛应用于数字签名的加密算法。

# 5. 实现交易（Transaction)

* 既然是一种货币，那么最重要的功能就是用来交易，下面实现。
* 