import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//RockPaperScissorGUI: The frontend (GUI) that interacts with the user.

//Frontend
public class RockPaperScissorGUI extends JFrame implements ActionListener  {
    private JButton rockButton, paperButton, scissorButton;
    private JLabel computerChoiceLable, computerScoreLable, playerScoreLable;
    private final RockPaperScissor rockPaperScissor = new RockPaperScissor();

    public RockPaperScissorGUI() {
        // tile add GUI
        super("Rock Paper Scissor Game!");//Sets the title of the window.
        // set size
        setSize(450, 575);
        setLayout(null);// Sets no specific layout, allowing us to manually position components.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addGuiComponents();//Calls a method to add all the buttons and labels to the window.
    }

    private void addGuiComponents() {
        //Intiallize computerScoreLable
        computerScoreLable = createLabel("Computer score: 0", 0, 43, 450, 30, 26);
        playerScoreLable = createLabel("Player score: 0", 0, 317, 450, 30, 26);

        //Intiallize computureChoice label
        computerChoiceLable = createLabel("?", 175, 118, 98, 81, 18);
        computerScoreLable.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Intiallize player choice button
        rockButton = createButton("Rock", 40, 387);
        paperButton = createButton("Paper", 165, 387);
        scissorButton = createButton("Scissor", 290, 387);
    }

    //Helper method to create labels
    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text, SwingUtilities.CENTER);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Dialog", Font.BOLD,fontSize));
        add(label);
        revalidate();//Force update to ensure that the label is added and visible
        repaint();
        return label;
    }

    //Helper method to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 105, 82);
        button.setFont(new Font("Dialog", Font.PLAIN, 18));
        button.addActionListener(this);
        add(button);
        return button;
    }

    //Display result dialog
    private void showDialog(String message) {
        JDialog resultDialog = new JDialog(this,"Result", true);
        //What it Does: Creates a new pop-up window (JDialog) named resultDialog.
        //Parameters:
        //this refers to the current window (RockPaperScissorGUI)
        // to make this dialog appear on top of the main game window.
        //"Result" is the title of the dialog window.
        //true means the dialog is modal, which means the main game window will be blocked until the dialog is closed
        // (the player must interact with this dialog before returning to the game).
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        //Result message
        JLabel resultLable = new JLabel(message, SwingConstants.CENTER);
        resultLable.setFont(new Font("Dialog", Font.BOLD, 18));
        resultDialog.add(resultLable, BorderLayout.CENTER);

        //tRY AGAIN BUTTON
        JButton tryAgainButton = new JButton("Try again?");
        //Action Listener: This line attaches an ActionListener to the button.
        // An ActionListener is a piece of code that defines what happens when the button is clicked.
        //Lambda Expression (e -> { ... }): This shorthand method is used to specify the action that occurs when the button is pressed.
        tryAgainButton.addActionListener(e -> {
            //computerScoreLable.setText("?");
            //
            //What It Does: This line sets the text of the computerScoreLable to "?".
            //Purpose: This is used to reset the computer’s choice display back to "?",
            // indicating that a new game round has not yet started or the computer's choice is unknown.
            // It's a visual reset for the next round.
            computerScoreLable.setText("?");
            resultDialog.dispose();
            //resultDialog.dispose();
            //
            //What It Does: This line closes the dialog window.
            //Purpose: It hides the result dialog and frees up any resources associated with it.
            // This allows the player to continue with the next round of the game.


        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH);
        //Add to Dialog: This line adds the "Try again?" button to the resultDialog window.
        //BorderLayout.SOUTH: This specifies that the button should be placed at the bottom (south) section of the dialog.
        //Purpose: Placing the button at the bottom keeps the layout organized, making it easy for the player to see and interact with.
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
        //Together, these two lines ensure that when the result dialog is shown,
        // it is properly centered over the main application window and is immediately visible to the user.
        // This enhances the user experience by providing clear feedback on the game’s outcome
        // and prompting the player to continue easily.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get player choice play the game
        String playerChoice = e.getActionCommand();
        //Purpose: Retrieves the text of the button that was clicked (Rock, Paper, or Scissors).
       // Functionality: This string is stored in playerChoice and will be used to determine the outcome of the game.
        String result = rockPaperScissor.play(playerChoice);
        //Purpose: Calls the play method from the RockPaperScissor class, passing in the player's choice.
        //Functionality:
        // This method calculates the outcome of the round (who wins) and
        // returns a result string (e.g., "Player Wins", "Computer Wins", or "Draw").

        // Update GUI with the game state
        //Purpose: These lines update the labels in the GUI to reflect the current state of the game.
        computerChoiceLable.setText(rockPaperScissor.getComputerChoice());
        computerScoreLable.setText("Computer: " + rockPaperScissor.getComputrScore());
        playerScoreLable.setText("Player: " + rockPaperScissor.getPlayerScore());

        // Display result
        showDialog(result);
    }
}
// How the RockPaperScissorGUI Class Works
//Purpose: This class creates and manages the graphical interface that the user interacts with.
//
//Components:
//
//Labels (JLabel):
//computerScoreLabel: Displays the computer’s score.
//playerScoreLabel: Displays the player’s score.
//computerChoiceLabel: Displays the computer’s choice.
//Buttons (JButton):
//rockButton, paperButton, scissorButton: Buttons that the player can click to make their choice.
//Constructor (RockPaperScissorGUI()):
//
//Sets up the main window (size, title, layout, etc.).
//Calls addGuiComponents() to set up the buttons and labels on the screen.
//addGuiComponents() Method:
//
//Adds the labels and buttons to the window using helper methods (createLabel() and createButton()).
//Helper Methods:
//
//createLabel(): Creates a label with the specified text, size, and font.
//createButton(): Creates a button, sets its action listener to this class, and adds it to the window.
//Action Handling (actionPerformed()):
//
//This method is triggered when any of the buttons (Rock, Paper, or Scissors) are clicked.
//It gets the player’s choice (based on the button clicked).
//Calls the play() method from the RockPaperScissor class to play a round and determine the result.
//Updates the labels on the GUI to reflect the current state of the game.
//Shows a dialog with the result of the round (showDialog()).
//showDialog(String message):
//
//Creates a pop-up dialog to show the result of the round.
//It has a "Try Again?" button to reset the game state for the next round.
//The line:
//
//```java
//private final RockPaperScissor rockPaperScissor = new RockPaperScissor();
//```
//
//is creating an instance of the `RockPaperScissor` class inside the `RockPaperScissorGUI` class. Here's a detailed explanation of what each part of this line does:
//
//### **1. `private final` Keywords**
//- **`private`**: This means that the `rockPaperScissor` variable can only be accessed within the `RockPaperScissorGUI` class. It is not visible or accessible from outside this class.
//- **`final`**: This keyword means that the variable `rockPaperScissor` is constant and cannot be reassigned to another instance once it's initialized. In other words, once `rockPaperScissor` is set to a `RockPaperScissor` object, it will always refer to that same object throughout the life of the `RockPaperScissorGUI`.
//
//### **2. `RockPaperScissor rockPaperScissor`**
//- This declares a variable named `rockPaperScissor` of type `RockPaperScissor`. The `RockPaperScissor` class contains the backend logic for the game, such as determining the winner, keeping scores, and handling the game state.
//
//### **3. `= new RockPaperScissor();`**
//- **`new RockPaperScissor()`**: This creates a new instance (object) of the `RockPaperScissor` class using its constructor. It sets up the game logic needed for each round, including the initial state of the scores and the computer’s choice.
//- **Assigning the Object**: The created object is assigned to the `rockPaperScissor` variable, allowing the GUI to use this backend logic.
//
//### **Purpose of This Line in the GUI Class**
//This instance of `RockPaperScissor` is used to:
//- **Play the game**: When the player clicks one of the buttons (`Rock`, `Paper`, or `Scissors`), the GUI class will call methods from this `rockPaperScissor` object to determine the result of the game.
//- **Update scores and choices**: The GUI updates the display by fetching the computer’s choice, player score, and computer score from the `rockPaperScissor` object.
//- **Maintain Game State**: The game state, including scores and the computer’s choice, is managed by this backend object, keeping the frontend (GUI) and backend (game logic) separate.
//
//### **Example of How It’s Used in the GUI**
//When a button is clicked, the `actionPerformed()` method in `RockPaperScissorGUI` does the following:
//- Calls `rockPaperScissor.play(playerChoice)` to play a round based on the player’s input.
//- Updates the GUI by retrieving values like `rockPaperScissor.getComputerChoice()`, `rockPaperScissor.getComputerScore()`, and `rockPaperScissor.getPlayerScore()`.
//
//This approach keeps the game logic separate from the user interface, following a good design practice called **separation of concerns**.