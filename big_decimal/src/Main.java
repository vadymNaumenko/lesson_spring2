import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        BigInteger integer = new BigInteger("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println(integer);

        BigDecimal decimal = new BigDecimal("123.444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");
        System.out.println(decimal);

        //объекты являются неизменяемыми (Immutable).
        integer.add(BigInteger.valueOf(33333333));
        System.out.println(integer); //print 11111111111111111111

        // операция сложения +
        BigInteger result = integer.add(BigInteger.valueOf(33333333));
        System.out.println(result); // print 11111133333

        //методы для осуществления арифметических операций:
        // add(), subtract(), multiply(), divide()
        //================================================
        //doubleValue(), intValue(), floatValue(), longValue() и т.д.
        // — используются для преобразования большого числа к примитивному типу Java.

        //max(),min()
        System.out.println(integer.max(BigInteger.valueOf(43252632)));

        //Всего у BigDecimal существует 8 режимов округления.
        BigDecimal round = new BigDecimal("111.5555555555");

        //ROUND_CEILING — округление в большую сторону
        round.setScale(3, RoundingMode.CEILING); // = 111.556

        //ROUND_CEILING — округление в меньшую сторону
        round.setScale(3, RoundingMode.FLOOR); // = 111.555

        //ROUND_DOWN — отбрасывание разряда
        round.setScale(3, RoundingMode.DOWN); // = 111.555

        //ROUND_HALF_UP — округление в большую сторону, если число после запятой >= .5
        BigDecimal.valueOf(0.55).setScale(1,RoundingMode.HALF_UP);// = 0.6
        BigDecimal.valueOf(0.54).setScale(1,RoundingMode.HALF_UP);// = 0.5


         }
}