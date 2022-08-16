import java.util.Arrays;
import java.util.Scanner;

public class GradeStudent {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        begin();
        double[] midTermScore = midTerm(sc);
        double[] finalTermScore = finalTerm(sc);
        double[] homeworkScore = homework(sc, (int) midTermScore[1], (int) finalTermScore[1]);
        report(midTermScore[0], finalTermScore[0], homeworkScore[0]);
    }

    //=============Hàm bắt đầu: mô tả ngắn gọn chương trình=============
    public static void begin() {
        System.out.println("PROGRAM DESCRIPTION:");
        System.out.println("This program reads exam/homework scores");
        System.out.println("and reports your overall course grade.");
        System.out.println();
    }

    //==============Hàm tính điểm thi giữa kì========================
    public static double[] midTerm(Scanner sc) {
        System.out.println("Midterm: ");
        System.out.print("Weight (0-100)? ");
        int weight = sc.nextInt();
        System.out.print("Scores earned? ");
        int score = sc.nextInt();
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int isShift = sc.nextInt();
        int shiftAmount = 0;

        //--Nếu chọn isShift = 1 thì nhập shift amount, chọn khác 1 thì bỏ qua bước này
        if(isShift == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = sc.nextInt();
        }

        //--------Hiệu chỉnh điểm số về tối đa 100--------------
        int totalPoints = (score + shiftAmount > 100 ? 100 : score + shiftAmount);

        //-------in ra điểm tổng và điểm trọng số phần midTerm---------------
        System.out.println("Total points = " + totalPoints + " / 100");
        // làm tròn sau dấu phẩy 1 chữ số
        double weightedScore = ((double) Math.round((((double) totalPoints) * weight / 100)*10))/10;
        System.out.println("Weighted score = " + weightedScore + " / " + weight);
        System.out.println();

        //----trả về 1 mảng số thực gồm điểm trọng số và trọng số ----
        return new double[]{weightedScore,weight};
    }

    //==============Hàm tính điểm thi cuối kì========================
    public static double[] finalTerm(Scanner sc) {
        System.out.println("Final: ");
        System.out.print("Weight (0-100)? ");
        int weight = sc.nextInt();
        System.out.print("Scores earned? ");
        int score = sc.nextInt();
        System.out.print("Were scores shifted (1=yes, 2=no)? ");
        int isShift = sc.nextInt();
        int shiftAmount = 0;

        //--Nếu chọn 1 thì nhập shift amount, chọn khác 1 thì bỏ qua bước này
        if(isShift == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = sc.nextInt();
        }

        //--------Hiệu chỉnh điểm số về tối đa 100--------------
        int totalPoints = (score + shiftAmount > 100 ? 100 : score + shiftAmount);

        //-------in ra điểm tổng và điểm trọng số---------------
        System.out.println("Total points = " + totalPoints + " / 100");
        // làm tròn sau dấu phẩy 1 chữ số
        double weightedScore = ((double) Math.round((((double) totalPoints) * weight / 100)*10))/10;
        System.out.println("Weighted score = " + weightedScore + " / " + weight);
        System.out.println();

        //----trả về 1 mảng số thực gồm điểm trọng số và trọng số phần finalTerm----
        return new double[]{weightedScore,weight};
    }

    //==========Hàm tính điểm bài tập về nhà và điểm chuyên cần=========
    public static double[] homework(Scanner sc, int weight1, int weight2) {
        System.out.println("Homework: ");

        //tính trọng số điểm homework dựa trên trọng số 2 phần trước
        // để đảm bảo tổng trọng số tất cả các phần bằng 100
        int weight = 100 - weight1 - weight2;
        System.out.println("Weight (0-100)? " + weight);
        System.out.print("Number of assignments? ");
        int numOfAsm = sc.nextInt();
        int scoreSum = 0;
        int maxSum = 0;

        //--lặp theo số lượng bài tập và tính ra tổng điểm bải tập và tổng điểm lớn nhất đạt được
        for(int i = 1; i <= numOfAsm; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            int score = sc.nextInt();
            int max = sc.nextInt();
            scoreSum+=score;
            maxSum+=max;
        }

        //--Hiệu chỉnh tổng điểm bài tập về tối đã 150-----------
        scoreSum = scoreSum>150 ? 150 : scoreSum;
        maxSum = maxSum>150 ? 150 : maxSum;

        //--nhập số buổi được điểm danh và tính ra điểm chuyên cần (tối đa 30)---
        System.out.print("How many sections did you attend? ");
        int numOfAttend = sc.nextInt();
        int sectionScore = numOfAttend*5 > 30 ? 30 : numOfAttend*5;
        System.out.println("Section points = " + sectionScore + " / 30");

        //---Tính toán tổng điểm và tổng điểm max phần homework và in ra kết quả
        int totalPoints = scoreSum + sectionScore;
        int totalMax = maxSum + 30;
        System.out.println("Total points = " + totalPoints + " / " + totalMax);
        double weightedScore = ((double) Math.round((((double) totalPoints) * weight / totalMax)*10))/10;
        System.out.println("Weighted score = " + weightedScore + " / " + weight);
        System.out.println();

        //----trả về 1 mảng số thực gồm điểm trọng số và trọng số ----
        return new double[]{weightedScore,weight};
    }

    public static void report(double midSc, double finalSc, double homeworkSc) {
        double total = midSc + finalSc + homeworkSc;
        System.out.println("Overall percentage = " + total);
        double GPA = 0;
        if (total >= 85) {GPA = 3.0;}
        else if (total >= 75) {GPA = 2.0;}
        else if (total >= 60) {GPA = 1.0;}
        else {GPA = 0.0;}
        System.out.println("Your grade will be at least: " + GPA);
        if (GPA == 3.0) {
            System.out.println("Excellent! Your grade is very high.");
        }
        else if (GPA == 2.0) {
            System.out.println("Good! Your grade is quite high.");
        }
        else if (GPA == 1.0) {
            System.out.println("oh no! Your grade is quite low, you need more effort.");
        }
        else {
            System.out.println("Sorry! Your grade is very bad, you have to learn this lession again.");
        }
    }
}