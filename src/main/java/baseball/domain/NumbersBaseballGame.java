package baseball.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NumbersBaseballGame {
    private final int numberOfDigitsInAnswer;
    private ComputerPlayer computerPlayer;

    public NumbersBaseballGame(int numberOfDigitsInAnswer) {
        this.numberOfDigitsInAnswer = numberOfDigitsInAnswer;
        this.computerPlayer = new ComputerPlayer();
    }

    public void run() {
        while (true) {
            playGame();
            if (!isContinueGame()) {
                System.out.println("애플리케이션을 종료합니다.");
                break;
            }
        }
    }

    private boolean isContinueGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String inputContinueFlag = Console.readLine();
        // inputValidator.checkInputContinueFlag(inputContinueFlag);
        int continueFlag = convertFromInputContinueFlagToContinueFlag(inputContinueFlag);
        return continueFlag == 1;
    }

    private void playGame() {
        System.out.println("숫자 야구 게임을 시작합니다.");
        computerPlayer.makeAnswer(numberOfDigitsInAnswer);
        while (true) {
            System.out.print("숫자를 입력해주세요 : ");
            String inputAnswer = Console.readLine();
            // inputValidator.checkInputAnswer(inputRestartFlag);
            Numbers userAnswer = convertFromInputAnswerToNumbers(inputAnswer);
            computerPlayer.judgeUserAnswer(userAnswer);
            if (computerPlayer.isThreeStrike()) {
                computerPlayer.sayThreeStrike();
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            } else if (computerPlayer.isNothing()) {
                computerPlayer.sayNothing();
            } else {
                computerPlayer.sayBallAndStrike();
            }
        }
    }

    private Numbers convertFromInputAnswerToNumbers(String inputValue) {
        return new Numbers(numberOfDigitsInAnswer,
                Arrays.stream(inputValue.split("")).map(Integer::parseInt).collect(Collectors.toList()));
    }

    private int convertFromInputContinueFlagToContinueFlag(String inputValue) {
        return Integer.parseInt(inputValue);
    }
}