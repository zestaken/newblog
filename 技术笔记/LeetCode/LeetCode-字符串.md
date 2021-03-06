---
title: Leetcode-字符串
date: 2021-06-11 19:49:16
tags: [LeetCode, 数据结构]
categories: 技术笔记
---
# 总结 

## String类的charAt()与toCharArray()实现原理

* 例题：[ip-地址无效化-1108](#1-ip-地址无效化-1108)
* 看一下charAt()的源码：
```java
    public char charAt(int index) {
        if ((index < 0) || (index >= value.length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }
```
* 这就是直接在数组中取值的方法。那么value是什么东西呢？
```java
    /** The value is used for character storage. */
    private final char value[];

    //再看看构造方法
    public String() {
        this.value = "".value;
    }

    public String(String original) {
        this.value = original.value;
        this.hash = original.hash;
    }

    //这个体现得最好，直接就是字符数组之间的copy
    public String(char value[]) {
        this.value = Arrays.copyOf(value, value.length);
    }
```
* `value`的声明为一个字符数组，而且构造方法中通过`original.value`的方法赋值，也是将这个String对象中的value字符数组，赋给新的这个String对象。所以说白了，String类，就是一个字符数组给它加上一大堆方法封装而成的。（倒和C语言本质差不多）。
* String类的核心是字符数组，所以能轻易把它转换为字符数组也不奇怪了，toCharArray的实质就是把封装在String中的字符数组value取出来用：
```java
    public char[] toCharArray() {
        // Cannot use Arrays.copyOf because of class initialization order issues
        char result[] = new char[value.length];
        System.arraycopy(value, 0, result, 0, value.length);
        return result;
    }
```

## StringBuilder的append()方法的参数

* 例题：[ip-地址无效化-1108](#1-ip-地址无效化-1108)
* append方法既能传字符，也能直接传字符串。事实上，不仅是字符和字符串，基本上其它类型如int，long等都能传，因为它们都能转为字符啊。看一下append是如何实现的：
```java
    public AbstractStringBuilder append(char c) {
        ensureCapacityInternal(count + 1);
        //直接在字符数组最后一位后加一个字符
        value[count++] = c;
        return this;
    }

    public AbstractStringBuilder append(int i) {
        if (i == Integer.MIN_VALUE) {
            append("-2147483648");
            return this;
        }
        int appendedLength = (i < 0) ? Integer.stringSize(-i) + 1
                                     : Integer.stringSize(i);
        int spaceNeeded = count + appendedLength;
        ensureCapacityInternal(spaceNeeded);
        //将整型转化合适长度的字符存入value字符数组中
        Integer.getChars(i, spaceNeeded, value);
        count = spaceNeeded;
        return this;
    }

    public AbstractStringBuilder append(String str) {
        if (str == null)
            return appendNull();
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }
```
* 结合String的本质是字符数组这一点来看，这一切都十分简单：只要最后能转为char，什么都可以往字符数组里塞。

## StringBuilder的insert()方法使用

* 例题： [1-ip-地址无效化-1108](#1-ip-地址无效化-1108)
* 首先看一下insert方法插入字符的源码，了解它的主要实现逻辑：
```java
    public AbstractStringBuilder insert(int offset, char c) {
        ensureCapacityInternal(count + 1);
        //将当前位置字符及其后面的字符依次向后挪一位
        System.arraycopy(value, offset, value, offset + 1, count - offset);
        //当前位置赋值为指定字符
        value[offset] = c;
        //字符串总长度加一
        count += 1;
        return this;
    }
```
* 根据insert方法的实现逻辑，insert方法是将当前位置及其以后的字符依次向后移一位，然后当前位置变为指定字符。所以插入一个字符后，原来字符的下标就变为了`offsetn + 1`（原来字符及其以后的字符的下标都加一）,原来字符之前的字符相对位置不变。
* 此外，类似append方法，insert方法也是可以直接插入字符串的（不仅字符串，还有许多其它的类型，如字符数组，浮点数（float），int，boolean等，只是最终效果都是添加成字符串）：
```java
//举两个例子
    /**
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int dstOffset, CharSequence s,
                                int start, int end)
    {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    /**
     * @throws StringIndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public StringBuilder insert(int offset, Object obj) {
            super.insert(offset, obj);
            return this;
    }
```

## String与StringBuilder

* 要说String和StringBuilder有什么不同，最直观的使用体验就是，String是不能变的，初始化时是啥就是一直是啥，想要改String的内容，就只能重新初始化一个String在其中加上想要改的内容。而StringBuider呢，从它特有的append和insert方法就可以看出，这兄弟的内容和长短可以随便改。
* String和StringBuilder的本质都是字符数组，但是其中又有不同：
  * String：
```java
   /** The value is used for character storage. */
    private final char value[];
```
  * StringBuilder:继承自AbstractStringBuilder，我们大致看一下AbstractStringBuilder就可以了解情况了。
```java

    /**
     * The value is used for character storage.
     */
    char[] value;

    //再看一个在append和insert方法中都出现了的方法：用于实现动态扩容
    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
        if (minimumCapacity - value.length > 0) {
            value = Arrays.copyOf(value,
                    newCapacity(minimumCapacity));
        }
    }
```
* 这下其实就很明显了，String核心是一个`final char[]`，而StringBuilder就是一个普通的`char[]`。final修饰的是初始化之后就不能改变的变量。而StringBuilder用着普通的字符数组，还为它配备了像ensureCapacityInternal这种动态扩容的方法以insert和append这种改变字符数组内容的方法，所以它可以随意变化值或长度。
* 不得不说，这就是，铁打的字符数组，流水的方法。。。

## foreach循环的原理

* 例题：[1-ip-地址无效化-1108](#1-ip-地址无效化-1108)
* foreach循环可以用来遍历数组或者集合。虽然遍历这两者的用法相同，但是实现原理却不一样。
* 首先看看遍历数组时是什么情况,将我们遍历字符数组的代码编译后反编译一下：
```java
//自己编写的源码
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

//编译后反编译得到的源码
   public String deFangIpaddr5(String address) {
      StringBuilder newAddress = new StringBuilder();
      char[] var3 = address.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char temp = var3[var5];
         if (temp == '.') {
            newAddress.append("[.]");
         } else {
            newAddress.append(temp);
         }
      }

      return newAddress.toString();
   }
```
  * 二者对比一下，就可以看出，foreach循环做的事就是将我们以前遍历取值的的标准for循环封装了一下：
```java
for(int var5 = 0; var5 < var4; ++var5) {
    char temp = var3[var5];
    ...
}

//等价于

for(char temp : address.toCharArray()) {
    ...
}
```
  * 这其中也要注意一个小细节，用于接收值的变量是每一次循环都要重新声明的，所以foreach循环中的临时变量只能现场声明，不能用之前声明好的。
* 遍历集合的情况，偷个懒，等遇到了再说。。。

---

# 1. IP 地址无效化 1108 

* 题目：
```
给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."

示例 1：

输入：address = "1.1.1.1"
输出："1[.]1[.]1[.]1"
示例 2：

输入：address = "255.100.50.0"
输出："255[.]100[.]50[.]0"
 

提示：
给出的 address 是一个有效的 IPv4 地址
```

## java解法

* 法一：
  * 遍历整个字符串，同时新建一个空的StringBuilder，遇到`.`的时候，加上`[]`使用`append()`添加进入StringBuilder中去。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611201714.png)
```java
//解题代码
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

//JUnit测试用例
    @Test
    public void testDeFangIpaddr1() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr1("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法二
  * 调用String的`replace()`方法，直接将`.`替换为`[.]`
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611202515.png)
```java
//解题代码
    public String deFangIpaddr2(String address) {
        return address.replace(".", "[.]");
    }

//测试用例
    @Test
    public void testDeFangIpaddr2() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr2("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法三：
  * 创建StringBuilder对象，并把原字符串赋给它，调用其`insert`方法，在`.`的前后插入`[]`。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611204856.png)
```java
//解题代码
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
//测试用例
    @Test
    public void testDeFangIpaddr3() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr3("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法四：
  * 同一一样，但是append方法可以直接添加字符串。。。
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611205634.png)
```java
//解题代码
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
//测试用例
    @Test
    public void testDeFangIpaddr4() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr4("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```
* 法五：
  * 将String转为字符数组，利用foreach循环遍历
  * 评测结果：
![](https://gitee.com/zhangjie0524/picgo/raw/master/20210611211051.png)
```java
//解题代码
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
//测试用例
    @Test
    public void testDeFangIpaddr5() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr5("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
```

# 2. 有效的字母异位词 242 

* [题目](https://leetcode-cn.com/problems/valid-anagram/)
---
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
示例 1:
```
输入: s = "anagram", t = "nagaram"
输出: true
```
示例 2:
```
输入: s = "rat", t = "car"
输出: false
```
提示:
```
1 <= s.length, t.length <= 5 * 104
s 和 t 仅包含小写字母
```

## Java解法

* 法一：字母异位代表着相同字母的出现次数一样。用一个26位的数组存储字母出现的次数（下标对应字母），遍历一次第一个字符串，存储各个字母的次数，然后再遍历第二个字符串，减去各个字母的次数，最后的数组值全为0，则说明字母异位。
  * 结果：![BuLLAf](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/BuLLAf.png)
  * 代码：
```java
public class ValidAnagram242 {

    public boolean isAnagram(String s, String t) {
        //存储判断结果
        boolean res = true;
        //用长度为26的整数数组表示每个字母出现的次数（下标表示字母，值代表出现次数）
        int[] abc = new int[26];
        //将字符串转换为字符数组遍历，将字母转化为数字，存储出现次数
        for(char i : s.toCharArray()) {
            abc[i - 'a']++;
        }
        //遍历第二个字符数组，出现一个字母则对应位置次数减一
        for(char i : t.toCharArray()) {
            abc[i - 'a']--;
        }

        //遍历数组，如果有对应位置字母出现次数不同，则对应位置的值不为0，返回false
        for(int i = 0; i < 26; i++) {
            if(abc[i] != 0) {
                return res = false;
            }
        }
        //所有元素都相等
        return res;
    }
}
```

# 3. 同构字符串 205

* [题目](https://leetcode-cn.com/problems/isomorphic-strings/)
---
给定两个字符串 s 和 t，判断它们是否是同构的。
如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
```
示例 1:

输入：s = "egg", t = "add"
输出：true
示例 2：

输入：s = "foo", t = "bar"
输出：false
示例 3：

输入：s = "paper", t = "title"
输出：true
```
提示：
    可以假设 s 和 t 长度相同。

## Java解法

* 法一：关键在于确定字母之间的映射关系，然后检测这种关系是否矛盾。字母之间的映射关系是通过字母出现的位置来决定的，字母第一次出现时就与同样位置的另一个字符相映射，如果之后这个字母再次出现，则对应的位置应还是哪个字符，也就是说，那个字符第一次出现的位置应相同。（本题偷懒使用两个哈希表实现，这样还不如直接用一个哈希表中将字母映射关系存好，直接对比。其实用26位的字符数组用以上思路应该也能做出来）
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210907223838.png)
  * 代码：
```java
public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        //将字符串转化为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        //创建两个哈希集合，存储字符串中字符值及其第一次出现的位置
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        //遍历两个字符数组，同时记录位置，如果对应位置的字符的第一次出现的位置不同，则不是同构字符串
        for(int i = 0; i < s1.length; i++) {
            //用两个变量记录对应位置字母第一次出现的位置
            int pos1 = i;
            int pos2 = i;
            //如果当前位置的字母是第一次出现，则存入哈希表，如果不是，则取出第一次出现位置
            if(!map1.containsKey(s1[i])) {
                map1.put(s1[i], i);
            } else {
               pos1 = map1.get(s1[i]);
            }

            if(!map2.containsKey(s2[i])) {
                map2.put(s2[i], i);
            } else {
                pos2 = map2.get(s2[i]);
            }
            //如果两个字母第一次出现的位置不同，则不是同构字符串
            if(pos1 != pos2) {
                return false;
            }
        }
        return true;
    }
}
```

# 4. 回文子串 647

* [题目](https://leetcode-cn.com/problems/palindromic-substrings/)
---
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
回文字符串 是正着读和倒过来读一样的字符串。
子字符串 是字符串中的由连续字符组成的一个序列。
具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
```
示例 1：

输入：s = "abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入：s = "aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
```
提示：

1 <= s.length <= 1000
s 由小写英文字母组成

## Java解法

* 法一：回文子串的本质就是前后对称，对称的关键就是对称轴。所以我们以每一个元素确认当前对称轴，然后找寻对称轴前后的元素是否相同，来判断是否是回文子串。需要注意的是，奇数个元素和偶数个元素的对称的对称轴情况不同，需要区别处理。（应该可以通过除法，取余等操作将两个遍历循环合并）
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210908095417.png)
  * 代码：
```java
public class PalindromicSubstrings647 {

    public int countSubstrings(String s) {

        //将字符串转化为字符数组
        char[] s1 = s.toCharArray();
        int length = s1.length;
        int count = 0;

        //遍历字符数组，用当前元素确认中轴，找寻前后元素，看是否对称
        for(int i = 0; i < length; i++) {
            //要区分回文子串是偶数个还是奇数个
            //偶数个的中轴在当前元素和下一元素之间（或者说中轴是两个元素）
            //奇数个的中轴就在当前元素上
            int l = i;
            int r1 = i;
            int r2 = i + 1;
            //查找奇数个数的回文子串
            while(l >= 0 && r1 < length && s1[l] == s1[r1]) {
                count++;
                l--;
                r1++;
            }
            //复原l的值
            l = i;
            //查找偶数个的回文子串
            while(l >= 0 && r2 < length && s1[l] == s1[r2]) {
                count++;
                l--;
                r2++;
            }
        }

        return count;
    }
}
```

# 5. 计数二进制子串 696

* [题目](https://leetcode-cn.com/problems/count-binary-substrings/)
---
给定一个字符串 s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是连续的。
重复出现的子串要计算它们出现的次数。
```
示例 1 :

输入: "00110011"
输出: 6
解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

请注意，一些重复出现的子串要计算它们出现的次数。

另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
示例 2 :

输入: "10101"
输出: 4
解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
```
提示：
s.length 在1到50,000之间。
s 只包含“0”或“1”字符。

## Java解法

* 法一：利用如果满足条件，必须前后不同字符连续出现的次数相同，所以关键在于计算同一字符连续出现的次数，如果之前字符连续出现的次数不小于当前字符连续出现的次数，那么必然可以构成一组。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210909224120.png)
  * 代码：
```java
public class CountBinarySubstrings696 {

    public int countBinarySubstrings2(String s) {
        //统计结果
        int count = 0;
        //将字符串转为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        //记录当前字符和之前不同字符的连续出现次数
        int pre = 0, cur = 0;

        //遍历字符数组，记录当前字符连续出现次数，以及之前相对当前字符不同的字符的连续出现次数
        //temp标记当前字符是0还是1
        char temp = '0';
        for(char c : s1) {

            if(c == temp) {
                //如果字符没变，则计数加一
                cur++;
            } else {
                //如果字符变化了，则将之前记录的字符连续次数，赋给统计之前次数的变量，重新开始计数
                pre = cur;
                cur = 1;
                //修改当前字符种类
                temp = c;
            }
            //当之前连续出现的字符的次数的不小于当前字符连续出现次数时，可以构成对称，计数一次
            if(pre >= cur) {
                count++;
            }
        }

        return count;
    }
}
```

# 6. 基本计算器 II 227

* [题目](https://leetcode-cn.com/problems/basic-calculator-ii/)
---
给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
整数除法仅保留整数部分。
```
示例 1：

输入：s = "3+2*2"
输出：7
示例 2：

输入：s = " 3/2 "
输出：1
示例 3：

输入：s = " 3+5 / 2 "
输出：5
```
提示：
```
1 <= s.length <= 3 * 105
s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
s 表示一个 有效表达式
表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
题目数据保证答案是一个 32-bit 整数
```

## Java解法

* 法一：用两个队列分别存储数值和运算符。遍历字符串时，从队列尾部插入，尾部读取，把队列当作栈使用，如果遇到比存入队列中的的运算符优先级高的运算符，则将该运算符前后的数值获取，计算出结果再存入数值队列中。遍历字符串结束后，需要从左到右计算，从队列头部读取，从队列头部插入，因为高优先级运算符已经转换为运算结果，所以运算时都是同级的运算符，只需从左到右运算即可。从左到右运算时，将每一次运算的结果插入队列头部，作为下一次运算的左运算数。
  * 结果：![dzELwI](https://gitee.com/zhangjie0524/picgo/raw/master/uPic/dzELwI.png)
  * 代码：
```java
public class BasicCalculatorII227 {

    //用于临时存储连续的数字以便计算数值
    ArrayList<Integer> tempNums = new ArrayList<>();

    /**
     * 主计算函数，遍历字符串，按序获取对应的数值以及运算符
     * @param s
     * @return 表达式计算结果
     */
    public int calculate(String s) {
        //将字符串转换为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        //用两个双端队列，按序存储数值和运算符
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();
        //存储运算结果
        int res = 0;

        //遍历整个字符数组
        for(int i = 0; i < s1.length;) {
            //获取当前位置的值
            char c = s1[i];

            //如果是数字，则获取接下来连续出现的数字，存储到动态数组中，并修改下标
            if(isNum(c)) {
                i = getNums(s1, i);
            }
            //如果是空格
            if(isSpace(c)) {
                //空格是连续数字的分隔符，计算数值，存入数值队列中
                if(!tempNums.isEmpty()) {
                    nums.addLast(getNum(tempNums));
                }
                //下标前进到下一位，进入下一次循环
                i++;
                continue;
            } else if(isOp(c)) {
                //如果是运算符
                if(!tempNums.isEmpty()) {
                    //运算符也是数字的分隔符，计算数值，存入数值队列
                    nums.addLast(getNum(tempNums));
                }
                //如果运算符队列为空，则直接存入运算符，进入下一次循环
                if(ops.isEmpty()) {
                    ops.addLast(c);
                    i++;
                    continue;
                }
                //运算符队列不为空，获取队列尾运算符
               char temp = ops.getLast();
                //如果队列尾运算符是+或-
               if(temp == '+' || temp == '-' ) {
                   //* / 的优先级比 + -高，先将乘除运算结束，将结果存入数值队列中
                   if (c == '*' ) {
                       //获取数值队列末尾元素
                       int num1 = nums.pollLast();
                       //获取当前运算符之后的连续数字并得出其值
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       //运算，结果存入数值队列尾
                       int num3;
                       num3 = num2 * num1;
                       nums.addLast(num3);
                   } else if (c == '/') {
                       int num1 = nums.pollLast();
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       int num3;
                       num3 = num1 / num2;
                       nums.addLast(num3);
                   } else {
                       //如果当前运算符是+ -，则直接存入运算符队列
                       i++;
                       ops.addLast(c);
                       continue;
                   }
               } else {
                   //没有比 * /优先级高的运算，直接存入运算符队列
                   i++;
                   ops.addLast(c);
                   continue;
               }

            }
        }
        //因为以空格和运算符作为连续数字的分隔符，所以末尾的数字有可能没有存入数值队列
        if(!tempNums.isEmpty()) {
            nums.addLast(getNum(tempNums));
        }
        //如果运算符队列为空，则说明数值队列只有一个数，无需运算，直接返回
        if(ops.isEmpty()) {
            return nums.pollFirst();
        }
        //从队列头部取出数值和运算符进行运算
        //运算规则是从左到右，如果还是用栈的思路，就是从右到左计算
        //因为高优先级的运算符都已经计算为结果了，所以都是同一优先级的运算
        while(!nums.isEmpty() && !ops.isEmpty()) {
            int num1 = nums.pollFirst();
            int num2 = nums.pollFirst();
            char op = ops.pollFirst();
            if(op == '+') {
                res = num1 + num2;
            } else if (op == '-'){
                res = num1 - num2;
            } else if (op == '*') {
                res = num1 * num2;
            } else if(op == '/') {
                res = num1 / num2;
            }
            //每一次运算的结果是下一次运算的前一位运算数，重新存入数值队列头部
            nums.addFirst(res);
        }

        return res;
    }

    /**
     * 判断字符是否是数字
     * @param c
     * @return
     */
    public boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否是运算符
     * @param c
     * @return
     */
    public boolean isOp(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否是空格
     * @param c
     * @return
     */
    public boolean isSpace(char c) {
        if(c == ' ') {
            return true;
        }
        return false;
    }

    /**
     * 将连续的数字转换为值
     * @param list
     * @return
     */
    public int getNum(ArrayList<Integer> list) {
        int length = list.size();
        int i = 0;
        int res = 0;

        //遍历数字，将每位数乘上其10的幂，加在最后的结果上
        for(int num : list) {
            res += num * Math.pow(10, length - i - 1);
            i++;
        }
        //清空集合
        list.clear();

        return res;
    }

    /**
     * 获取连续数字，并依次存入集合中
     * @param s
     * @param start 开始位置
     * @return 返回下一个非数字字符位置
     */
    public int getNums(char[] s, int start) {
        int i;
        int j;
        //如果开始是连续的空格，则跳过，知道有数字出现为止
        for(i = start; i < s.length; i++) {
            char c = s[i];
            if(!isSpace(c)) {
                break;
            }
        }
        //从跳过空格后的位置开始读取数字
        for(j = i; j < s.length; j++) {
            char c = s[j];
            if(isNum(c)) {
                tempNums.add(c - '0');
            } else {
                break;
            }
        }
        return j;
    }
}
```
  

# 7. 实现 strStr() 28

* [题目](https://leetcode-cn.com/problems/implement-strstr/)
---
实现 strStr() 函数。
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
说明：
当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
```
示例 1：

输入：haystack = "hello", needle = "ll"
输出：2
示例 2：

输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：

输入：haystack = "", needle = ""
输出：0

提示：

0 <= haystack.length, needle.length <= 5 * 104
haystack 和 needle 仅由小写英文字符组成
```

## Java解法

* 法一：双指针遍历两个字符串，直到找到匹配的子串或者余下的长度比子串长度还小时停止。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20210911131211.png)
  * 代码：
```java
public class ImplementStrStr28 {

    public int strStr(String haystack, String needle) {
        //将字符串转化为字符数组，方便遍历
        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();
        int l1 = s1.length;
        int l2 = s2.length;
        //如果子串为空字符串，返回0
        if(s2.length == 0) {
            return 0;
        }
        //结果默认为-1
        int res = -1;

        //遍历字符串，直到所剩长度比子串长度小
        for(int i = 0; i + l2 <= l1; i++) {
            //如果当前字符与子串第一个字符相同，则继续比较之后的字符
            if(s1[i] == s2[0]) {
                int j = i + 1;
                int t = 1;
                //当子串只有一位字符时，可以直接返回
                if(t == l2) {
                    return res = i;
                }
                //双指针，遍历两个字符串，逐个比较字符是否相同，直到子串遍历完
                while (t < l2) {
                    if(s1[j] != s2[t]) {
                        break;
                    }
                    t++;
                    j++;
                }
                //如果子串被遍历完了，说明完全匹配，返回开始下标即可
                if(t == l2) {
                    return res = i;
                }
            }
        }
        //到达这里，说明前面没有找到匹配子串，直接返回默认值-1
        return res;
    }
}
```

* 法二：KMP(字符串模式匹配算法)[KMP解析](https://www.zestaken.top/post/%E4%B8%93%E4%B8%9A%E5%9F%BA%E7%A1%80%2F%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%2F%E7%AC%AC%E5%9B%9B%E7%AB%A0%20%E4%B8%B2):其中心思想是利用子串的对称性来在比较的时候跳过一些对称的部分，以起到加速匹配的效果。
  * 结果：![](https://zjpicture.oss-cn-beijing.aliyuncs.com/img/20211114114926.png)
  * 代码：
```java
    public int strStr(String haystack, String needle) {
        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();
        int l1 = s1.length;
        int l2 = s2.length;
        int result = -1; //结果默认为-1
        //子串为空串直接返回0
        if(l2 == 0) {
            result = 0;
            return  result;
        }

        //求子串每一个元素的next数组（即当前元素之前对称的元素对数）
        int i = 0, k = -1;
        int[] next = new int[l2];
        next[0] = -1; //第一个元素设为-1，因为它之前没有元素
        while(i < l2 - 1) { //最后一个元素在倒数第二个元素遍历时已经记录
            if(k == -1 || s2[i] == s2[k]) {
                i++; //若之前有元素相等，则记在下一个元素的next值上
                k++; //k值从-1开始，与i对应元素依此比过来，如果相等则k与i同时移位，如果不等k回溯到它的next值处再比较，i不变
                //next值的求取实质是子串前半部分与后半部分比较，得元素对应相等个数；可以看成是两个串的匹配，又可以用next回溯的思想，有点递归的感觉
                next[i] = k; //k的值代表着对称相等的元素对数
            } else {
                k = next[k];
            }
        }

        int j = 0, m = 0;
        while(j + l2 - m <= l1 && m < l2) {
            if(m == -1 || s2[m] == s1[j]) { //m等于负一说明子串从头开始寻找，主串后移一位
                j++;
                m++;
            } else {
                m = next[m];
            }

            if(m  == l2) {
                result = j - m; //当前位置是子串最后一个元素对应的位置，减去子串长度即为开始匹配的位置
            }

        }

        return result;
    }
```






