package test;

public class Main000 {

    public static void main(String[] args) {

        int nums = 0;
        for(int i = 2; i <= 2021; i++) {
            int temp = 2021 % i;
            while(temp != 0 && temp != 1) {
                temp = 2021 % temp;
            }
            if(temp == 0) {
                nums++;
            }
        }
        System.out.println(nums);
    }
}
