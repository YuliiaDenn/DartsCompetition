import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] team1 = new int[4][3];
        int[][] team2 = new int[4][3];
        int[][] team3 = new int[4][3];


        int[] winers = new int[3];
        System.out.println("First team results:");
        winers[0] = startCompetition(team1);
        System.out.println("\nSecond team results:");
        winers[1] = startCompetition(team2);
        System.out.println("\nThird team results:");
        winers[2] = startCompetition(team3);

        bestScoreAmongTeams(winers);

    }

    private static int startCompetition(int[][] team) {
        for (int i = 0; i < team.length; i++) {
            System.out.println("Player " + (i + 1));
            for (int j = 0; j < team[i].length; j++) {
                team[i][j] = enterPoints();
            }
            System.out.println("Total score of " + (i + 1) + " player = " + totalPoints(team[i]));
        }
        return bestScoreInTheTeam(team);
    }

    private static void bestScoreAmongTeams(int[] winnersScore) {
        int totalScore = 0;
        int team = 0;
        int count = 0;
        for (int i = 0; i < winnersScore.length; i++) {
            if (totalScore < winnersScore[i]) {
                totalScore = winnersScore[i];
                team = i;
            }
        }
        for (int winnerScore : winnersScore) {
            if (totalScore == winnerScore) {
                count++;
            }
        }
        if (count > 1) {
            System.out.println("\nCompetition among winners!");
            int[] teams = new int[count];
            for (int i = 0, j = 0; i < winnersScore.length; i++) {
                if (totalScore == winnersScore[i]) {
                    teams[j] = i + 1;
                    j++;
                }
            }
            ifDrawBetweenTeams(teams, totalScore);
        } else {
            System.out.printf("\nThe best result was shown by player of the team %d with a score of %d", team, totalScore);
        }
    }

    private static void ifDrawBetweenTeams(int[] teams, int totalScore) {
        int[] results = new int[teams.length];
        int[] count = new int[teams.length];
        System.out.println("We have a draw!");
        results = additionalAttempt(results, teams);
        for (int i = 0; i < count.length; i++) {
            results[i] = results[i] + totalScore;
        }
        bestScoreAmongTeamsAfterDraw(results, teams);
    }

    private static void bestScoreAmongTeamsAfterDraw(int[] totalResults, int[] teams) {
        int totalScore = 0;
        int teamNumber = 0;
        int count = 0;
        for (int i = 0; i < totalResults.length; i++) {
            if (totalScore < totalResults[i]) {
                totalScore = totalResults[i];
                teamNumber = teams[i];
            }
        }
        for (int totalResult : totalResults) {
            if (totalScore == totalResult) {
                count++;
            }
        }
        if (count > 1) {
            int[] newTeams = new int[count];
            for (int i = 0, j = 0; i < totalResults.length; i++) {
                if (totalScore == totalResults[i]) {
                    newTeams[j++] = teams[i];
                }
            }
            ifDrawBetweenTeams(newTeams, totalScore);
        } else {
            System.out.printf("\nThe best result was shown by player of the team %d with a score of %d", (teamNumber + 1), totalScore);
        }
    }

    private static int bestScoreInTheTeam(int[][] team) {
        int bestResult = 0;
        int player = 0;
        int count = 0;
        for (int i = 0; i < team.length; i++) {
            if (bestResult < totalPoints(team[i])) {
                bestResult = totalPoints(team[i]);
                player = i;
            }
        }
        for (int[] ints : team) {
            if (bestResult == totalPoints(ints)) {
                count++;
            }
        }
        if (count > 1) {
            int[] candidatesInTheWinner = new int[count];
            for (int i = 0, j = 0; i < team.length; i++) {
                if (bestResult == totalPoints(team[i])) {
                    candidatesInTheWinner[j] = i + 1;
                    j++;
                }
            }
            player = ifDrawBetweenPlayersInTeam(candidatesInTheWinner, 0);
            System.out.println("\nThe winner is player " + player + " with the score of " + bestResult);
            System.out.println("The scores from additional competitions are not taken into account!");
        } else {
            System.out.println("\nThe winner is player " + (player + 1) + " with the score of " + bestResult);
        }
        return bestResult;
    }

    private static int ifDrawBetweenPlayersInTeam(int[] candidatesInTheWinner, int newTotalScore) {
        int winner;
        int[] results = new int[candidatesInTheWinner.length];
        int[] count = new int[candidatesInTheWinner.length];
        System.out.println("We have a draw!");
        results = additionalAttempt(results, candidatesInTheWinner);
        for (int i = 0; i < count.length; i++) {
            results[i] = results[i] + newTotalScore;
        }
        winner = bestScoreInTheTeamAfterDraw(results, candidatesInTheWinner);
        return winner;
    }

    private static int bestScoreInTheTeamAfterDraw(int[] totalResults, int[] candidatesInTheWinner) {
        int totalScore = 0;
        int teamNumber = 0;
        int count = 0;
        for (int i = 0; i < totalResults.length; i++) {
            if (totalScore < totalResults[i]) {
                totalScore = totalResults[i];
                teamNumber = candidatesInTheWinner[i];
            }
        }
        for (int totalResult : totalResults) {
            if (totalScore == totalResult) {
                count++;
            }
        }
        if (count > 1) {
            int[] newTeams = new int[count];
            for (int i = 0, j = 0; i < candidatesInTheWinner.length; i++) {
                if (totalScore == totalResults[i]) {
                    newTeams[j++] = candidatesInTheWinner[i];
                }
            }
            teamNumber = ifDrawBetweenPlayersInTeam(newTeams, totalScore);
        } else {
            System.out.printf("\nThe best result in additional competitions was shown by player %d with a score of %d", teamNumber, totalScore);
        }
        return teamNumber;
    }


//    private static int bestScoreBetweenTwoPlayers(int[] candidatesInTheWinner, int bestResult) {
//        int winner1 = candidatesInTheWinner[0];
//        int winner2 = candidatesInTheWinner[1];
//        int resultWinner1 = bestResult;
//        int resultWinner2 = bestResult;
//        int newBestResult;
//        int[] additionResults = new int[2];
//        System.out.println("Additional winners tournament!");
//        additionResults = additionalAttempt(additionResults);
//        resultWinner1 += additionResults[0];
//        resultWinner2 += additionResults[1];
//        if (resultWinner1 > resultWinner2) {
//            System.out.println("\nThe winner is player " + winner1 + " with the score of " + resultWinner1);
//            newBestResult = resultWinner1;
//        } else if (resultWinner2 > resultWinner1) {
//            System.out.println("\nThe winner is player " + winner2 + " with the score of " + resultWinner2);
//            newBestResult = resultWinner2;
//        } else {
//            System.out.println("Equal points! Does not count! New attempt!");
//            newBestResult = bestScoreBetweenTwoPlayers(candidatesInTheWinner, bestResult);
//        }
//        return newBestResult;
//    }


    private static int[] additionalAttempt(int[] playersPoints, int [] players) {
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