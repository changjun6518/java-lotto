package util;

import java.util.ArrayList;
import java.util.HashSet;

public class Validator {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_FIRST_NUM = 1;
    private static final int LOTTO_LAST_NUM = 45;

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


    public static boolean checkWinningNumber(ArrayList<Integer> winningNumber, int lottoNumber) {
        if (!checkWinningNumberDuplicate(winningNumber, lottoNumber)) {
            System.out.println("번호가 중복되었습니다! 다시 입력해주세요!");
            return false;
        }
        if (!isVailddLottoNumber(lottoNumber)) {
            System.out.println("1~45 숫자를 입력해주세요!");
            return false;
        }

        return true;
    }


    private static boolean isVailddLottoNumber(int lottoNumber) {
        return LOTTO_FIRST_NUM <= lottoNumber && lottoNumber <= LOTTO_LAST_NUM;
    }

    private static boolean checkWinningNumberDuplicate(ArrayList<Integer> winningNumber, int lottoNumber) {
        return !winningNumber.contains(lottoNumber);
    }

    private static boolean checkMoneyPositive(int money) {
        return money >= 0;
    }

    private static boolean checkMoneyOverMinimunPrice(int money) {
        return money >= LOTTO_PRICE;
    }
}
