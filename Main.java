import java.io.*;
import java.util.*;

public class Main {
        static final int MONTHS = 12;
        static final int DAYS = 28;
        static final int COMMS = 5;
        static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
        static String[] months = {"January","February","March","April","May","June",
                "July","August","September","October","November","December"};
        private static  int [][][] profits = new int [MONTHS][DAYS][COMMS];

        // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
        public static void loadData() {
        }

        // ======== 10 REQUIRED METHODS (Students fill these) ========

        public static String mostProfitableCommodityInMonth(int month) {
            if (month < 0 || month >= MONTHS) return "INVALID_MONTH";

            int bestComm = 0;
            int bestProfit = Integer.MIN_VALUE;

            for (int i = 0; i < COMMS; i++) {
                int sum = 0;
                for (int j = 0; j < DAYS; j++) {
                    sum += profits[month][j][i];
                }
                if (sum > bestProfit) {
                    bestProfit = sum;
                    bestComm = i;
                }
            }
            return commodities[bestComm] + " " + bestProfit;
        }

        public static int totalProfitOnDay(int month, int day) {
            int sum = 0;
            if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) return -99999;
            for (int i = 0; i < COMMS; i++) {
                sum += profits[month][day - 1][i];
            }
            return sum;
        }

        public static int commodityProfitInRange(String commodity, int from, int to) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++) {
                if (commodities[i].equals(commodity)){
                    cIndex = i;
                }
            }
            if (cIndex == -1 || from < 1 || to > DAYS || from > to) return -99999;

            int sum = 0;
            for (int j = 0; j < MONTHS; j++) {
                for (int k = from - 1; k <= to - 1; k++) {
                    sum += profits[j][k][cIndex];
                }
            }
            return sum;
        }

        public static int bestDayOfMonth(int month) {
            if (month < 0 || month >= MONTHS) return -1;
            int bestDay = 1;
            int bestProfit = Integer.MIN_VALUE;

            for (int i = 0; i < DAYS; i++) {
                int sum = 0;
                for (int j = 0; j < COMMS; j++) {
                    sum += profits[month][i][j];
                    if (sum > bestProfit) {
                        bestProfit = sum;
                        bestDay = i + 1;
                    }
                }
            }
            return bestDay;
        }

        public static String bestMonthForCommodity(String comm) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++){
                if (commodities[i].equals(comm)){
                    cIndex = i;
                }
            }
            if (cIndex == -1) return "INVALID_COMMODITY";

            int bestMonth = 0;
            int bestProfit = Integer.MIN_VALUE;

            for (int i = 0; i < MONTHS; i++) {
                int sum = 0;
                for (int j = 0; j < DAYS; j++) {
                    sum += profits[i][j][cIndex];
                    if (sum > bestProfit) {
                        bestProfit = sum;
                        bestMonth = i;
                    }
                }
            }
            return months[bestMonth];
        }

        public static int consecutiveLossDays(String comm) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++){
                if (commodities[i].equals(comm)) {
                    cIndex = i;
                }
            } if (cIndex == -1) return -1;

            int maxStreak = 0;
            int current = 0;

            for (int i = 0; i < MONTHS; i++) {
                for (int j = 0; j < DAYS; j++) {
                    if (profits[i][j][cIndex] < 0) {
                        current++;
                        if (current > maxStreak) maxStreak = current;
                    } else {
                        current = 0;
                    }
                }
            }
            return maxStreak;
        }

        public static int daysAboveThreshold(String comm, int threshold) {
            int cIndex = -1;
            for (int i = 0; i < COMMS; i++) {
                if (commodities[i].equals(comm)) {
                    cIndex = i;
                }
            } if (cIndex == -1) return -1;

            int count = 0;
            for (int i = 0; i < MONTHS; i++) {
                for (int j = 0; j < DAYS; j++) {
                    if (profits[i][j][cIndex] > threshold) {
                        count++;
                    }
                }
            }
            return count;
        }

        public static int biggestDailySwing(int month) {
            if (month < 0 || month >= MONTHS) return -99999;

            int maxSwing = 0;
            for (int i = 0; i < DAYS - 1; i++) {
                int today = 0;
                int next = 0;
                for (int j = 0; j < COMMS; j++) {
                    today += profits[month][i][j];
                    next += profits[month][i + 1][j];
                }
                int diff = Math.abs(today - next);
                if (diff > maxSwing){
                    maxSwing = diff;
                }
            }
            return maxSwing;
        }

        public static String compareTwoCommodities(String c1, String c2) {
            return "DUMMY is better by 1234";
        }

        public static String bestWeekOfMonth(int month) {
            if (month < 0 || month >= MONTHS) return "INVALID_MONTH";

            int bestWeek = 1;
            int bestProfit = Integer.MIN_VALUE;

            for (int i = 0; i < 4; i++) {
                int sum = 0;
                for (int j = i * 7; j < i * 7 + 7; j++) {
                    for (int k = 0; k < COMMS; k++) {
                        sum += profits[month][j][k];
                        if (sum > bestProfit) {
                            bestProfit = sum;
                            bestWeek = i + 1;
                        }
                    }
                }
            }
            return "Week " + bestWeek;
        }

        public static void main(String[] args) {
            loadData();
            System.out.println("Data loaded – ready for queries");
        }
    }
