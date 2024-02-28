package edu.school21.numbers;

public class NumberWorker {
    public boolean isPrime(int number) {
        if(number<=1){
            throw new IllegalNumberException("This number is less then 2");
        }
        else if(number==2){
            return true;
        }
        else {
            for(int i = 2; i * i <= number; i++){
                if (number % i != 0) continue;
                return false;
            }
            return true;
        }
    }

    public int digitsSum(int number) {
        long long_number = (long) number;
        int summa = 0;
        if(long_number < 0) long_number *= -1;

        while (long_number > 0){
            summa += long_number % 10;
            long_number /= 10;
        }
        return summa;
    }
}
