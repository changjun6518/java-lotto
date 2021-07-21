import domain.Lotto;
import domain.Rank;
import domain.WinningLotto;
import util.Validator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LottoMain {
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_COUNT = 6;
    public static int LOTTO_MAX_NUMBER = 45;
    public static int LOTTO_MIN_NUMBER = 1;
    public static ArrayList<Lotto> lottos = new ArrayList<>();
    public static WinningLotto winningLotto;
    public static int userMoney;
    public static int totalWinningMoney = 0;




    public static int moneyCharge(Scanner sc) {
        boolean check = true;
        while (check) {
            System.out.println("금액을 입력하시오");
            userMoney = sc.nextInt();
            check = !Validator.checkMoneyVaild(userMoney);
        }
        return userMoney / LOTTO_PRICE;
    }

    public static ArrayList<Integer> lottoNumberGenerate() {
        HashSet<Integer> lottoNumber = new HashSet<>();

        while (lottoNumber.size() != LOTTO_COUNT) {
            int randomNum = ThreadLocalRandom.current().nextInt(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER) + LOTTO_MIN_NUMBER;
            lottoNumber.add(randomNum);
        }

        ArrayList<Integer> lotto = new ArrayList<>(lottoNumber);
        Collections.sort(lotto);
        return lotto;
    }

    public static void lottoGenerateByMoney(int count) {
        for (int i = 0; i < count; i++) {
            ArrayList<Integer> lotto = lottoNumberGenerate();
            lottos.add(new Lotto(lotto));
        }
    }

    public static void showLottoNumbers() {
        for (Lotto lotto : lottos) {
            System.out.printf("lotto : " + lotto.numbers + "\n");
        }
    }

    public static void inputWinningNumber(Scanner sc) {
        ArrayList<Integer> winningNumber = new ArrayList<>();

        System.out.println("당첨 번호를 입력해 주세요! (띄어쓰기로 구분해주세요)");
        for (int i = 0; i < LOTTO_COUNT; i++) {
            winningNumber.add(sc.nextInt());
        }
        System.out.println("보너스 볼을 입력해 주세요!");
        int bonusNum = sc.nextInt();
        winningLotto = new WinningLotto(new Lotto(winningNumber), bonusNum);
    }


    public static void checkWinning() {
        for (Lotto lotto : lottos) {
            Rank calculatedRank = winningLotto.calculate(lotto);
            totalWinningMoney += calculatedRank.getWinningPrice();
            printWinningMessage(calculatedRank);
        }
    }

    public static void printWinningMessage(Rank calculatedRank) {
        calculatedRank.printResult();
    }

    public static void printYield() {
        System.out.println("총 수익률은 " + totalWinningMoney / userMoney * 100 + " % 입니다");
    }

    public static void startGame() {
        Scanner sc = new Scanner(System.in);

        int count = moneyCharge(sc);

        lottoGenerateByMoney(count);

        showLottoNumbers();

        inputWinningNumber(sc);

        checkWinning();

        printYield();
    }

}
