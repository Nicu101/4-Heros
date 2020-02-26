package main;

public final class Main {

    private Main() {
        /* Prevent instantiation */
    }

    public static void main(final String[] args) {
        /* Starting the program */

        /* Reading the information from the file*/
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        Game game = gameInputLoader.load();

        /* Creating the Great Wizard */
        TheGreatWizard greatWizard = TheGreatWizard.getTheGreatWizardInstance(args[1]);

        /* Adding the observers for the Great Wizard */
        game.addObservers(greatWizard);

        /* Executing the game*/
        game.playGame();

        /* Ending the game*/
        /* The Great Wizard is closing his notebook */
        greatWizard.closeFile();
        /* Saving Results */
        game.gameResults(args[1]);
    }

}
