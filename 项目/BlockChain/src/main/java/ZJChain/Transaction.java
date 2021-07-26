package ZJChain;

import utils.StringUtil;

import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {
    /**
     * 交易号
     */
    public String transactionId;
    /**
     * 交易序号，用于记录交易数量
     */
    public static int sequence = 0;
    /**
     * 发送方的地址/public key
     */
    public PublicKey sender;
    /**
     * 接收方的地址/public key
     */
    public PublicKey recipient;
    /**
     * 交易额
     */
    public float value;
    /**
     * 发送方的签名
     */
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }

    /**
     * 计算用于标识交易的transactionId
     * @return
     * @throws Exception
     */
    private String calculateHash() throws Exception {
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                StringUtil.getStringFromKey(recipient) +
                value + sequence);
    }


}
