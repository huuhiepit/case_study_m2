package game.view.impl;

public interface IBaseView {
    void launcher();

    int getNumberMinMax(String str, int min, int max);

    char convertNumberToCharCorrect(int number);
    int convertCharToNumberCorrect(char c);
}
