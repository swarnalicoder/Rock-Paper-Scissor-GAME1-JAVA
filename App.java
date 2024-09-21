import javax.swing.*;
//App: The entry point of the application that starts the GUI.
    public class App {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                // Instantiate and display the RockPaperScissorGUI
                RockPaperScissorGUI rockPaperScissorGUI = new RockPaperScissorGUI();
                // rockpaperScissorGUI is variable created type of RockPaperScisssorGUI
                rockPaperScissorGUI.setVisible(true);
            });
        }
}
//Code Execution:
//When you run the program, the main method is called.
//Inside main, SwingUtilities.invokeLater is used to safely create and show the GUI on the Event Dispatch Thread (EDT), which is necessary for Swing applications to work correctly.
//It creates an instance of RockPaperScissorGUI and makes it visible (setVisible(true)).