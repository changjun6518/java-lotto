import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LottoMain {
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_COUNT = 6;
    public static int LOTTO_MAX_NUMBER = 45;
    public static int LOTTO_MIN_NUMBER = 1;
    public static ArrayList<Lotto> lottos = new ArrayList<>();
    public static ArrayList<Integer> winningNumber = new ArrayList<>();


    public static int moneyCharge(Scanner sc) {
        System.out.println("금액을 입력하시오");
        int money = sc.nextInt();
        int count = money / LOTTO_PRICE;
        return count;
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
        System.out.println("당첨 번호를 입력해 주세요! (띄어쓰기로 구분해주세요)");
        for (int i = 0; i < LOTTO_COUNT; i++) {
            winningNumber.add(sc.nextInt());
        }
        System.out.println("보너스 볼을 입력해 주세요!");
        int bonusNum = sc.nextInt();
    }

    public static boolean inputBonousNumber(int bonusNum, Lotto lotto) {
        if (lotto.numbers.contains(bonusNum)) {
            return true;
        } else return false;
    }

    public static void checkWinning() {
        for (Lotto lotto : lottos) {
            int winningCount = 0;
            List<Integer> numbers = lotto.numbers;
            for (Integer number : numbers) {
                if (winningNumber.contains(number)) {
                    winningCount++;
                }
            }

            printWinningMessage(winningCount);
        }
    }

    public static void printWinningMessage(int winningCount) {
        switch (winningCount) {
            case 3:
                Rank rank = Rank.valueOf(6, false);
                rank.printResult(3L);

                System.out.println("3개 맞추셨습니다! 상금 5000원");
                break;
            case 4:
                System.out.println("4개 맞추셨습니다! 상금 5만원");
                break;
            case 5:
                System.out.println("5개 맞추셨습니다! 상금 500만원");
                break;
            case 6:
                System.out.println("6개 맞추셨습니다! 상금 5억!!!!");
                break;
            default:
                System.out.println("꽝! 아쉽지만 다음 기회에");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = moneyCharge(sc);

        lottoGenerateByMoney(count);

        showLottoNumbers();

        inputWinningNumber(sc);

        checkWinning();
    }

}
