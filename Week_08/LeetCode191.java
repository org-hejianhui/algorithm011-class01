/**
 * <p>
 * 191.位1的个数
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-08-16 22:40
 * @see LeetCode191
 * @since JDK1.8
 */
public class LeetCode191 {
    /**
     * 1. for loop : 0 ---> 32
     * 2. %2, /2
     * 3. &1, x = x >> 1; (32)
     * 4. while (x > 0) { count++; x = x & (x-1) }
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public int hammingWeight1(int n) {
        String str  = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') count++;
        }
        return count;
    }

    public int hammingWeight2(int n) {
        return Integer.valueOf(n).bitCount(n);
    }

    // 3. &1, x = x >> 1; (32)
    // 循环和位移动 时间O(1) 空间O(1)
    public int hammingWeight3(int n) {
        int count = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    // 4. while(x > 0) {count++; x = x & (x-1)}
    // x = x & (x-1) => 清零最低位的1
    // 时间O(1) 空间O(1)
    public int hammingWeight4(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode191 leetCode191 = new LeetCode191();
        // 3
        System.out.println(leetCode191.hammingWeight1(00000000000000000000000000001011));
        // 1
        System.out.println(leetCode191.hammingWeight1(00000000000000000000000010000000));
        // 31
        //System.out.println(leetCode191.hammingWeight1(11111111111111111111111111111101));
    }
}
