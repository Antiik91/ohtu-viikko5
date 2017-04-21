package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1") {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        int round = 4;
        String score = "";
        if (player1Score == player2Score) {
            score = scoreIsTie();
        } else if (player1Score >= round || player2Score >= round) {
            score = checkAdvantageOrWin();
        } else {
            score = scoreIsSomethingElse(score);
        }
        return score;
    }

    private String scoreIsSomethingElse(String score) {
        int situation;
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                situation = player1Score;
            } else {
                score += "-";
                situation = player2Score;
            }
            score = handleAdvantage(situation, score);
        }
        return score;
    }

    private String handleAdvantage(int situation, String score) {
        switch (situation) {
            case 0:
                score += "Love";
                break;
            case 1:
                score += "Fifteen";
                break;
            case 2:
                score += "Thirty";
                break;
            case 3:
                score += "Forty";
                break;
        }
        return score;
    }

    private String checkAdvantageOrWin() {
        String score;
        int advantagePoints = player1Score - player2Score;
        if (advantagePoints == 1) {
            score = "Advantage player1";
        } else if (advantagePoints == -1) {
            score = "Advantage player2";
        } else if (advantagePoints >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
        return score;
    }

    private String scoreIsTie() {
        String score;
        switch (player1Score) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;

        }
        return score;
    }
}
