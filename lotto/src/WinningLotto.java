import java.util.ArrayList;

public class WinningLotto {
    private final Lotto winninglotto;
    private final int bonusBall;

    public WinningLotto(Lotto winninglotto, int bonusBall) {
        this.winninglotto = winninglotto;
        this.bonusBall = bonusBall;
    }

    public Rank calculate(Lotto userLotto) {
        int count = 0;
        boolean bonusCheck = false;
        for (Integer number : userLotto.numbers) {
            if (winninglotto.numbers.contains(number)) {
                count++;
            }
        }
        if (userLotto.numbers.contains(bonusBall)) {
            bonusCheck = true;
        }

        return Rank.valueOf(count, bonusCheck);
    }

}
