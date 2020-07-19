package 柠檬水找零;

/**
 * <p>
 * 柠檬水找零
 * </p>
 *
 * @author: hejianhui
 * @create: 2020-07-19 21:23
 * @see Solution
 * @since JDK1.8
 */
public class Solution {
    public boolean lemonadeChange(int[] bills) {
        if(bills == null || bills.length == 0) return false;
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if(bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                five--;
                ten++;
            } else if (ten > 0) {
                ten--;
                five--;
            } else {
                five -= 3;
            }

            if(five < 0) return false;
        }
        return true;
    }
}
