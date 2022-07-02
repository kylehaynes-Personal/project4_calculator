//**************************************************************************************************************
// CLASS: View
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// Web: http://www.devlang.com
//**************************************************************************************************************

import java.awt.AWTEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The View class implements the GUI.
 */
public class View extends JFrame implements ActionListener {

    public static final int FRAME_WIDTH  = 500;
    public static final int FRAME_HEIGHT = 180;

    // Declare instance variables
    private JButton         mClearButton;
    private JButton         mEvalButton;
    private JTextField      mInputText;
    private JButton         mExitButton;
    private Main            mMain;
    private JLabel          mResultLabel;

    /**
     * View()
     *
     * The View constructor creates the GUI interface and makes the frame visible at the end.
     */
    public View(Main pMain) {
        // Save a reference to the Main object pMain in mMain.
    	Main mMain = pMain;
    	
        // PSEUDOCODE:
        // Declare and create a JPanel named panelLabel using the default FlowLayout layout manager.
        // Create mResultLabel as a JLabel initialized to the empty string ""
        // Add mResultLabel to panelLabel
        JPanel panelLabel = new JPanel(new FlowLayout());
        this.mResultLabel = new JLabel("Result: ");
        this.mResultLabel.setAlignmentY(LEFT_ALIGNMENT);
        panelLabel.add(this.mResultLabel);
        
        
    	
        // PSEUDOCODE:
        // Declare and create a JPanel named panelInput using the default FlowLayout layout manager.
        // Create mInputText as a JTextField initialized to 40 columns wide
        // Add mInputText to panelInput
        JPanel paneInput = new JPanel(new FlowLayout());
        this.mInputText = new JTextField(40);
        paneInput.add(this.mInputText);

        // PSEUDOCODE:
        // Create a JPanel named panelButtons using FlowLayout.
        // Create the Clear button mClearButton.
        // Make this View the action listener for mClearButton.
        // Add the  Clear button to the panel.
        // Repeat the three above statements for the Evalute button.
        // Repeat the three above statements for the Exit button.
        JPanel panelButtons = new JPanel(new FlowLayout());
        
        this.mClearButton = new JButton("Clear");
        mClearButton.addActionListener(this);
        	
        this.mEvalButton = new JButton("Evaluate");
        mEvalButton.addActionListener(this);
        
        this.mExitButton = new JButton("Exit");
        mExitButton.addActionListener(this);
        
        panelButtons.add(this.mClearButton);
        panelButtons.add(this.mEvalButton);
        panelButtons.add(this.mExitButton);

        // PSEUDOCODE
        // Create a JPanel named panelMain using a vertical BoxLayout.
        // Add some vertical glue to panelMain
        // Add panelLabel to panelMain.
        // Add panelInput to panelMain.
        // Add panelButtons to panelMain.
        
        JPanel panelMain = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
        panelMain.setLayout(boxlayout);
        
        panelMain.add(panelLabel);
        panelMain.add(Box.createVerticalGlue());
        panelMain.add(paneInput);
        panelMain.add(Box.createVerticalGlue());
        panelMain.add(panelButtons);
        panelMain.add(Box.createVerticalGlue());

        setTitle("Kalkutron-9001");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panelMain);
        setVisible(true);
        
        /* _________{ DEBUGER } _____________*/
        System.out.println("\n--- Waiting on user input ---\n");
        /* ______{ END DUBUGGER }____________*/
    }

   

    /**
     * clear() is called when the Clear button is clicked. Set the text in mInputText and mResultLabel to the
     * empty strings "".
     */
    public void clear() {
		this.mInputText.setText("");
		this.mResultLabel.setText("Result: ");
    }

    /**
     * evaluate() is called when the Evaluate button is clicked.
     *
     * PSEUDOCODE:
     * 1. Retrieve the text string from mInputText
     * 2. Declare and create an Expression object named expr passing the text string to the ctor
     * 3. Call expr.evaluate() and assign the return value a Double object named result
     * 4. Display result in mResultLabel (call toString on result)
     */
    public void evaluate() {
    	// 1
    	String input = this.mInputText.getText();
    	
    	if (input.length() < 2) {
    		
    		/* _________{ DEBUGER } _____________*/
    		System.out.println("\t\t- *** INCORRECT INPUT: Evaluation skipped");
    		/* ______{ END DUBUGGER }____________*/
    	}
    	else {
    		// 2
    		Expression expr = new Expression(input);
    		// 3
    		double result = expr.evaluate();
    		// 4 
    		this.mResultLabel.setText("Result: " + result);
    	}
    }

    /**
     * messageBox()
     *
     * Note that passing 'this' as the first arg causes the View to be the parent of the message
     * dialog window, so the dialog will be centered in the middle of the View. If we pass 'null'
     * as the argument, then the dialog does not have a parent frame and will be centered in the
     * middle of the display.
     */
    public void messageBox(String pMessage) {
        JOptionPane.showMessageDialog(this, pMessage, "Message", JOptionPane.PLAIN_MESSAGE);
    }

    
    /**
     * actionPerformed()
     *
     * Called when one of the JButtons is clicked. Detects which button was clicked and handles it.
     *
     * PSEUDOCODE:
     * If the source of the event was mClearbutton Then
     *     Call clear()
     * ElseIf the source of the event was mEvalButton Then
     *     Call evaluate()
     * ElseIf the source of the event was mExitButton Then
     *     Call exit() on mMain.
     * End If
     * @param WindowEvent 
     */
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == mClearButton) {
			
			/* _________{ DEBUGER } _____________*/
			System.out.println("\t[ CLEAR ] Button Pressed...\n");
			/* ______{ END DUBUGGER }____________*/
			
			this.clear();
		}
		else if (e.getSource() == mEvalButton ) {
			
			/* _________{ DEBUGER } _____________*/
			System.out.println("\t[ EVALUATE ] Button Pressed...");
			/* ______{ END DUBUGGER }____________*/
			
			this.evaluate();
		}
		else if (e.getSource() == mExitButton) {
			
			/* _________{ DEBUGER } _____________*/
			System.out.println("\t[ EXIT ] Button Pressed...");
			System.out.println("\n--- Closing application / terminating process ---");
			/* ______{ END DUBUGGER }____________*/
			
			
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else {
			
			/* _________{ DEBUGER } _____________*/
			System.out.println("\t *** ERROR: user input not recognized - View.actionPerformed()");	
			/* ______{ END DUBUGGER }____________*/
		}
	}
}
