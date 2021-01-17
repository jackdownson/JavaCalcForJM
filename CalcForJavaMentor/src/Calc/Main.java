package Calc;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    static RomanNumeral Numeral = new RomanNumeral();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 2 numbers (roman or arabic) to calculate. Please use space between numbers and math symbol.\n" +
                "Program works with 1-10 arabic and roman nums. \n" +
                "If you will input roman + arabic program will throw exception.\n" +
                "Accepteg math symbols: +, -, *, / \n" +
                "Enter: ");
        String prot = scanner.nextLine();
        System.out.println("You have entered: " + prot);

        String prot1 = "10/10";

        String strArr[] =  getStrToArr(prot);                                  // получение формата str{x, +, y}
        String numsString[] = getNums(prot);                                   // получение чисел в Str[]


        if (checkStrToRoman(numsString) == true) {                             // работаем с римскими числами
            String[] res = convToArabic(strArr);
            Integer resInt = calcTwoNums(res);
            System.out.println("Result: "+ RomanNumeral.toRoman(resInt));
        }else {                                                             // работаем с арабскими числами
            checkStrArabic(numsString);
            System.out.println(calcTwoNums(strArr));
        }
        //System.out.println(numsString[0] + numsString[1]);

    }

    static boolean checkStrArabic(String[] a) {

        boolean flag;
        Integer[] arr = {0, 0};
        try {


            for (int i = 0; i < a.length; i++) {
                arr[i] = Integer.parseInt(a[i]);
            }
        }
        catch(NumberFormatException e) {
            System.err.println("You've entered arabic and  roman");
            return false;
        }

        return true;
    }
    static boolean checkStrToRoman(String[] a){ //конвертируется ли в римские

        boolean flag = false;
        boolean[] stat = {false, false};
        String elementsRoman[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for(int i = 0; i<a.length; i++){
            //check eq in elementsRoman
            for(int j=0;j<elementsRoman.length; j++){
                if (Objects.equals(a[i], elementsRoman[j])) {
                    stat[i] = true;

                }
            }
        }
        if(stat[0] && stat[1] == true ){
            flag = true;

        }
        return flag;
    }

    static String[] convToArabic(String[] num) { // прогоняем список и по индексу конвертируем римское в арабское значение
        String[] res = {"11" ,num[1], "11"};
        String elementsRoman[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i<res.length; i=i+2){
            for (int j = 0; j < elementsRoman.length; j++) {
                if (num[i].equals(elementsRoman[j])) {
                    int b = j + 1;
                    String str = Integer.toString(b);
                    res[i] = str;
                    //System.out.println(res[i]);
                }
            }
        }
        return res;
    }
    static Integer calcTwoNums(String[] num) throws NumberFormatException{


        int firstElement = Integer.parseInt(num[0]);
        int secondElement = Integer.parseInt(num[2]);
        if (firstElement > 10 || secondElement > 10 || firstElement < 0 || secondElement <0) { // function checkCapability
            throw new NumberFormatException("Throw exception. Number is not equal to conditions.");
        }
        Integer result;
        if(num[1].equals("+")){
            result = firstElement + secondElement;

            return result;
        }else if (num[1].equals("-")) {

            result = firstElement - secondElement;
            return result;
        }else if (num[1].equals("*")){

            result = firstElement * secondElement;
            return result;
        }else if (num[1].equals("/")){

            result = firstElement / secondElement;
            return result;
        }

        return 1111111;
    }

    static String[] getNums(String prot) { // получение 2 чисел (еще не преобразованных) в массиве
        String elements[] = prot.split(" "); //создадим массив и разобьем строку на элементы

        String nums[] = {elements[0], elements[2]};
        return nums;
    }
    static String[] getStrToArr(String prot) { // создание массива str с символом
        String elements[] = prot.split(" ");
        try {
            String strToArr[] = {elements[0], elements[1], elements[2]};
            return strToArr;

        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("You should use spaces between numbers and math symbol");
        }
        return elements;
    }

}
