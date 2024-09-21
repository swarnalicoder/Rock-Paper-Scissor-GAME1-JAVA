import java.util.Random;
//RockPaperScissor: The backend logic that manages the game rules and scores.
public class RockPaperScissor {
    public static final String[]choices = {"Rock", "Paper", "Scissor"};

    // Game state variables
    private String computerChoice;
    private int computrScore, playerScore;
    private final Random random = new Random();

    //Gatters
    //These methods allow other parts of the program to get information about the game state:
    public String getComputerChoice() {
        return computerChoice;
    }

    public int getComputrScore() {
        return computrScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    //Play game
    public String play(String playerChoice) {
        // Rondomly select choice for the compter
        computerChoice = choices[random.nextInt(choices.length)];
        //random.nextInt(choices.length):
        //
        //choices.length gives the number of items in the choices array, which is 3 in this case.
        //random.nextInt(3) generates a random integer between 0 (inclusive) and 3 (exclusive).
        // This means it can return 0, 1, or 2.

        //If both having same choice return Draw
        if (playerChoice.equals(computerChoice)) {
            return "Draw";
        }

        boolean playerWins = (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissor") && computerChoice.equals("paper")) ;
        //The line uses a boolean variable called playerWins, which will be true if the player wins and false otherwise.

        if (playerWins) {
            playerScore++;
            return "Player Wins!";
        } else {
            computrScore++;
            return "Try next time";
        }

    }
}
//How the RockPaperScissor Class Works
//Purpose: This class handles the game logic, including computer choice generation, score tracking, and determining the outcome of each round.
//Key Elements:
//CHOICES Array: Holds the three possible choices: "Rock", "Paper", and "Scissors".
//Variables:
//computerChoice stores what the computer picked.
//computerScore and playerScore keep track of the scores.
//random is used to pick a random choice for the computer.
//Methods:
//getComputerChoice(), getComputerScore(), and getPlayerScore(): These methods return the current state of the game, like the computer's choice and the scores.
//play(String playerChoice):
//It generates a random choice for the computer.
//It compares the player’s choice with the computer’s choice to determine the winner.
//Updates the scores based on who wins and returns a message: "Draw", "Player Wins", or "Computer Wins".