import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] team1 = new int[4][3];
        int[][] team2 = new int[4][3];
        int[][] team3 = new int[4][3];

        int[] winners = new int[3];
        System.out.println("First team results:");
        winners[0] = startCompetition(team1);
        System.out.println("\nSecond team results:");
        winners[1] = startCompetition(team2);
        System.out.println("\nThird team results:");
        winners[2] = startCompetition(team3);
        winner(winners);
    }

    private static void winner(int[] totalResults) {
        System.out.println("\nCompetition among winners!");
        System.out.println();
        int[] teams = new int[totalResults.length];
        for (int i = 0; i < teams.length; i++) {
            teams[i] = i + 1;
        }
        int[] winner = bestResult(totalResults, teams);
        System.out.printf("\nThe best result was shown by player of the team %d with a score of %d", winner[0], winner[1]);
    }

    private static int startCompetition(int[][] team) {
        int[] bestResultWithAdditionPoints;
        int bestResult = 0;
        int[] candidatesInTheWinner = new int[team.length];
        int[] totalScores = new int[team.length];
        for (int i = 0; i < team.length; i++) {
            System.out.println("Player " + (i + 1));
            for (int j = 0; j < team[i].length; j++) {
                team[i][j] = enterPoints();
            }
            totalScores[i] = totalPoints(team[i]);
            candidatesInTheWinner[i] = i + 1;
            System.out.println("Total score of " + (i + 1) + " player = " + totalPoints(team[i]));
            if (bestResult < totalPoints(team[i])) {
                bestResult = totalPoints(team[i]);
            }
        }
        bestResultWithAdditionPoints = bestResult(totalScores, candidatesInTheWinner);
        int additionPoints = (bestResultWithAdditionPoints[1] - bestResult);
        if (additionPoints != 0) {
            System.out.printf("\nThe best result in additional competitions was shown by player %d with a score of %d", bestResultWithAdditionPoints[0], additionPoints);
        }
        System.out.println("\nThe winner is player " + bestResultWithAdditionPoints[0] + " with the score of " + bestResult);
        System.out.println("The scores from additional competitions are not taken into account!");
        return bestResult;
    }

    private static int[] ifDraw(int[] candidatesInTheWinner, int bestResult) {
        int[] winner;
        int[] results = new int[candidatesInTheWinner.length];
        int[] count = new int[candidatesInTheWinner.length];
        System.out.println("We have a draw!");
        results = additionalAttempt(results, candidatesInTheWinner);
        for (int i = 0; i < count.length; i++) {
            results[i] = results[i] + bestResult;
        }
        winner = bestResult(results, candidatesInTheWinner);
        return winner;
    }

    private static int[] bestResult(int[] totalResults, int[] candidatesInTheWinner) {
        int bestResult = 0;
        int[] winner = new int[2];
        int count = 0;
        for (int i = 0; i < totalResults.length; i++) {
            if (bestResult < totalResults[i]) {
                bestResult = totalResults[i];
                winner[0] = candidatesInTheWinner[i];
                winner[1] = totalResults[i];
            }
        }
        for (int totalResult : totalResults) {
            if (bestResult == totalResult) {
                count++;
            }
        }
        if (count > 1) {
            int[] newCandidatesInTheWinner = new int[count];
            for (int i = 0, j = 0; i < candidatesInTheWinner.length; i++) {
                if (bestResult == totalResults[i]) {
                    newCandidatesInTheWinner[j++] = candidatesInTheWinner[i];
                }
            }
            winner = ifDraw(newCandidatesInTheWinner, bestResult);
        }
        return winner;
    }

    private static int[] additionalAttempt(int[] playersPoints, int[] players) {
        int[] result = new int[playersPoints.length];
        for (int i = 0; i < playersPoints.length; i++) {
            System.out.println("Player " + players[i] + ":");
            result[i] += playersPoints[i] + enterPoints();
        }
        return result;
    }

    private static int totalPoints(int[] attempts) {
        int maxResult = 0;
        for (int attempt : attempts) {
            maxResult += attempt;
        }
        return maxResult;
    }

    private static int enterPoints() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number of points from 0 to 60: ");
        int points = scan.nextInt();
        if (points < 0 || points > 60) {
            System.out.println("Incorrect value!");
            System.out.print("Enter a number of points from 0 to 60: ");
            points = scan.nextInt();
        }
        return points;
    }
}