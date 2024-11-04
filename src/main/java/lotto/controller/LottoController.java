package lotto.controller;

import java.util.List;
import lotto.domain.*;
import lotto.input.*;
import lotto.service.LottoService;
import lotto.service.ResultCalculator;
import lotto.view.*;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseCount= setPurchaseCount();
        outputView.printLottoCount(purchaseCount);
        Lottos lottos = lottoService.generateLottos(purchaseCount);
        outputView.printLottoNumbers(lottos);
        Lotto winningLotto = setWinLotto();
        int bonusNumber = setBonusNumber(winningLotto);

        ResultCalculator resultCalculator = lottoService.calculateResult(lottos, winningLotto, bonusNumber);
        outputView.printWinningDetails(resultCalculator);
        outputView.printYield(resultCalculator.calculateRate(purchaseCount * 1000));
    }

    private int setPurchaseCount() {
        String purchaseAmount = inputView.getPurchaseAmount();
        return PurchaseAmountProcessor.calculatePurchaseCount(purchaseAmount);
    }

    private Lotto setWinLotto() {
        String winNumbers = inputView.getWinningNumber();
        List<Integer> winningNumbers = WinningNumberProcessor.processWinningNumbers(winNumbers);
        return new Lotto(winningNumbers);
    }

    private int setBonusNumber(Lotto winLotto) {
        System.out.println();
        String bonusNumber = inputView.getBonusNumber();
        return BonusNumberProcessor.validateAndParse(winLotto.getNumbers(), bonusNumber);
    }


}
