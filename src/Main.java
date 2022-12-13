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
            int[] teams = new int[count];
            for (int i = 0, j = 0; i < winnersScore.length; i++) {
                if (totalScore == winnersScore[i]) {
                    teams[j++] = i;
                }
            }
            ifDrawBetweenTeams(teams, totalScore);

        } else {
            System.out.printf("\nThe best result was shown by player of the team %d with a score of %d", (team + 1), totalScore);
        }
    }

    private static void ifDrawBetweenTeams(int[] teams, int totalScore) {
        int[] results = new int[teams.length];
        System.out.println("We have a draw!");
        results = additionalAttempt(results);
        for (int team : teams) {
            results[team] = results[team] + totalScore;
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
                    newTeams[j++] = i;
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

        for (int[] playerPoints : team) {
            if (bestResult < totalPoints(playerPoints)) {
                bestResult = totalPoints(playerPoints);
            }
        }
        for (int i = 0; i < team.length; i++) {
            if (bestResult == totalPoints(team[i])) {
                player = i;
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
            bestResult = ifDrawBetweenPlayersTeam(candidatesInTheWinner, bestResult, count, team);
        } else {
            System.out.println("\nThe winner is player " + (player + 1) + " with the score of " + bestResult);
        }

        return bestResult;
    }

    private static int bestScoreBetweenTwoPlayers(int[] candidatesInTheWinner, int bestResult) {
        int winner1 = candidatesInTheWinner[0];
        int winner2 = candidatesInTheWinner[1];
        int resultWinner1 = bestResult;
        int resultWinner2 = bestResult;
        int newBestResult;
        int[] additionResults = new int[2];
        System.out.println("Additional winners tournament!");
        additionResults = additionalAttempt(additionResults);
        resultWinner1 += additionResults[0];
        resultWinner2 += additionResults[1];
        if (resultWinner1 > resultWinner2) {
            System.out.println("\nThe winner is player " + winner1 + " with the score of " + resultWinner1);
            newBestResult = resultWinner1;
        } else if (resultWinner2 > resultWinner1) {
            System.out.println("\nThe winner is player " + winner2 + " with the score of " + resultWinner2);
            newBestResult = resultWinner2;
        } else {
            System.out.println("Equal points! Does not count! New attempt!");
            newBestResult = bestScoreBetweenTwoPlayers(candidatesInTheWinner, bestResult);
        }
        return newBestResult;
    }

    private static int ifDrawBetweenPlayersTeam(int[] playersScore, int bestResult, int count, int[][] players) {
        int newBestResult;
        System.out.println("We have a draw!");
        if (count == 2) {
            newBestResult = bestScoreBetweenTwoPlayers(playersScore, bestResult);
        } else {
            newBestResult = startCompetition(players);
        }
        return newBestResult;
    }

    private static int[] additionalAttempt(int[] players) {
        int[] result = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            result[i] += players[i] + enterPoints();
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