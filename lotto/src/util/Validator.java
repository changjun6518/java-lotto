package util;

public class Validator {
    private static final int LOTTO_PRICE = 1000;
    public static boolean checkMoneyVaild(int money) {
        if (!checkMoneyPositive(money)) {
            System.out.println("금액이 음수입니다 양의 값을 입력해 주세요!");
            return false;
        }
        if (!checkMoneyOverMinimunPrice(money)) {
            System.out.println("더 많은 금액을 입력하세요! 로또 한장에 " + LOTTO_PRICE + " 입니다!");
            return false;
        }
        return true;
    }


    private static boolean checkMoneyPositive(int money) {
        return money >= 0;
    }

    private static boolean checkMoneyOverMinimunPrice(int money) {
        return money >= LOTTO_PRICE;
    }
}
