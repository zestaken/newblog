package string;

public class DefangIPaddr {

    //StringBuilder的append
    public String deFangIpaddr1 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char temp = address.charAt(i);
            if(temp == '.') {
                newAddress.append('[');
                newAddress.append('.');
                newAddress.append(']');
            } else {
                newAddress.append(temp);
            }
        }

        return newAddress.toString();
    }

    //String的replace
    public String deFangIpaddr2 (String address) {
        return address.replace(".", "[.]");
    }

    //StringBuilder的insert
    public String deFangIpaddr3 (String address) {
        StringBuilder newAddress = new StringBuilder(address);
        for(int i = 0; i < newAddress.length(); i++) {
            if(newAddress.charAt(i) == '.') {
                //insert是当前计数位变为指定字符，原来的字符到下一位
                newAddress.insert(i + 1, ']'); //先插后面的括号，这样.的位置还未改变，方便添加前面的括号
                newAddress.insert(i, '[');
                i += 3;
                continue;
            }
        }
        return newAddress.toString();
    }

    //StringBuilder的append可以直接添加字符串。。。
    public String deFangIpaddr4 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char temp = address.charAt(i);
            if(temp == '.') {
//                newAddress.append('[');
//                newAddress.append('.');
//                newAddress.append(']');
                newAddress.append("[.]");
            } else {
                newAddress.append(temp);
            }
        }

        return newAddress.toString();
    }

    //将String转为字符数组，利用foreach循环遍历
    public String deFangIpaddr5 (String address) {
        StringBuilder newAddress = new StringBuilder();
        for(char temp : address.toCharArray()) {
            if(temp == '.') {
                newAddress.append("[.]");
            } else {
                newAddress.append(temp);
            }
        }
        return newAddress.toString();
    }
}
