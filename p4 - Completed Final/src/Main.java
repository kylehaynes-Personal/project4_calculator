//***************************************************************************************************************************
// CLASS: Main
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// Web: http://www.devlang.com
//***************************************************************************************************************************
import javax.swing.JFrame;

/**
 * The Main class containing the main() and run() methods.
 */
public class Main {

    // A reference to the View object.
    private View mView;

    /**
     * This is where execution starts. Instantiate a Main object and then call run().
     */
    public static void main(String[] args) {
    	
    	/* _________{ DEBUGER } _____________*/
    	System.out.println("\n\n* Running Main");
    	/* ______{ END DUBUGGER }____________*/
    	
        new Main().run();
    }

    /**
     * exit() is called when the Exit button in the View is clicked. Call System.exit(0).
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Accessor method for mView.
     */
    private View getView() {
        return mView;
    }

    /**
     * run() is the main routine.
     */
    private void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        /* _________{ DEBUGER } _____________*/
        System.out.println("* Opening View()");
        /* ______{ END DUBUGGER }____________*/
        
        setView(new View(this));
    }

    /**
     * Mutator method for mView.
     */
    private void setView(View pView) {
        mView = pView;
    }

}
