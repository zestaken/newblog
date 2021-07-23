import java.util.Date;

public class Block {

    public String hash;
    public String prevHash;
    private String data;
    private long timestamp;

    public Block(String data, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timestamp = new Date().getTime();
    }
}
