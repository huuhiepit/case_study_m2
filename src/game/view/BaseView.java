package game.view;

import game.service.impl.*;
import game.view.impl.IBaseView;

import java.util.Scanner;

public abstract class BaseView implements IBaseView {
    protected IPlayerService playerService;
    protected IQuestionService questionService;
    protected IQuestionSubjectService questionSubjectService;
    protected ISetExamService setExamService;
    protected ISetExamPlayerService setExamPlayerService;
    protected ISetExamLevelSubjectService setExamLevelSubjectService;

    protected Scanner scanner = new Scanner(System.in);
    public abstract void launcher();
    public int getNumberMinMax(String str, int min, int max) throws IndexOutOfBoundsException {
        System.out.print(str);
        int num;
        try {
            num = Integer.parseInt(scanner.nextLine());
            if (num < min || num > max) {
                System.out.println("Chỉ được nhập số từ khoảng " + min + " đến " + max);
                return getNumberMinMax(str, min, max);
            }
            return num;
        } catch (Exception e) {
            System.out.println("Lỗi không nhập đúng định dạng số.");
            return getNumberMinMax(str, min, max);
        }

    }
    public int getNumberMinMax(String str, String err, int min, int max) throws IndexOutOfBoundsException {
        System.out.print(str);
        int num;
        try {
            num = Integer.parseInt(scanner.nextLine());
            if (num < min || num > max) {
                System.out.println(err);
                return getNumberMinMax(str, min, max);
            }
            return num;
        } catch (Exception e) {
            System.out.println("Lỗi không nhập đúng định dạng số.");
            return getNumberMinMax(str, min, max);
        }
    }
    @Override
    public char convertNumberToCharCorrect(int number) {
        if(number >= 0 && number <= 3) {
            return (char) ('A' + number);
        }
        throw new IllegalArgumentException("Số không hợp lệ");
    }

    @Override
    public int convertCharToNumberCorrect(char c) {
        if (c >= 'A' && c <= 'D') {
            return (int) (c - 'A');
        }
        throw new IllegalArgumentException("Kí tự không hợp lệ");
    }
}
