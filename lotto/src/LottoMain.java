import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class LottoMain {
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_COUNT = 6;
    public static int LOTTO_MAX_NUMBER = 45;
    public static int LOTTO_MIN_NUMBER = 1;
    public static void main(String[] args) {

        System.out.println("금액을 입력하시오");

        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        int count = input / LOTTO_PRICE;

        ArrayList<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            HashSet<Integer> lottoNumber = new HashSet<>();

            while (lottoNumber.size() != LOTTO_COUNT) {
                int randomNum = ThreadLocalRandom.current().nextInt(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER) + LOTTO_MIN_NUMBER;
                lottoNumber.add(randomNum);
            }

            ArrayList<Integer> lotto = new ArrayList<>(lottoNumber);

            lottos.add(new Lotto(lotto));
        }


        for (Lotto lotto : lottos) {
            System.out.printf("lotto : " + lotto.numbers + "\n");
        }


        System.out.println("당첨 번호를 입력해 주세요! (띄어쓰기로 구분해주세요)");

        ArrayList<Integer> winningNumber = new ArrayList<>();
        for (int i = 0; i < LOTTO_COUNT; i++) {
            winningNumber.add(sc.nextInt());
        }
        System.out.println("보너스 볼을 입력해 주세요!");
        winningNumber.add(sc.nextInt());


        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.numbers;
            for (Integer number : numbers) {
                if (winningNumber.contains(number)) {
                    // 갯수 카운트해서 몇개 맞추면 어떻게 할지! -> 당첨 & 수익률!
                }
            }
        }

    }
}
