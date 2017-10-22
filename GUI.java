import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame
{
	private Model myModel;
	
	//Declared as class variables so they can be access by inner ChangeListener class
	private JTextArea inputText;
	private JTextArea outputText;
	private TextField enterRules;
	
    //Constructor 
	public GUI(Model myModel)
	{
		super("Text Changer");						//Calls the JFrame class constructor which sets the title of the window
		this.myModel = myModel;
		buildGUI(); 								//Calls method which implements the GUI and controllers
		setDefaultCloseOperation(EXIT_ON_CLOSE); 	//Ends program when frame is closed
		pack(); 									//Fits each component in the frame together
		setSize(800, 500); 							//Sets deafult size of the frame
		setVisible(true); 							//Makes frame visible
    }
    
    public void buildGUI()
	{
		//Creating area to input text
        inputText = new JTextArea();
        inputText.setBorder(BorderFactory.createTitledBorder("Input:"));
		inputText.setLineWrap(true);	//Text goes onto new line if crosses the boundry
		inputText.setText("Insert any text you want to alter in this box.\n"+
						  "Enter rules to alter this text in the box bellow.\n"+
						  "Separate rules using the ; character,\n"+
						  "A rule looks like [characters]=[characters];\n"+
						  "Spaces are also read as characters.\n"+
						  "Example: hello=hi;cat=dog;a=b; =_;");
		JScrollPane inputScroll = new JScrollPane(inputText); //Adding the JTextArea to a JScrollPane so you can scroll through text
		inputScroll.setBorder(null);
		
		//Creating area for outputting text
        outputText = new JTextArea();
        outputText.setBorder(BorderFactory.createTitledBorder("Output:"));
		outputText.setLineWrap(true);
		JScrollPane outputScroll = new JScrollPane(outputText);
		outputScroll.setBorder(null);
		
		//Creating text field for entering rules and adding actionlistener to call methods from model
		ChangeListener myChangeListener = new ChangeListener();
        enterRules = new TextField(85);
		enterRules.addActionListener(myChangeListener);

		//Creating button and adding actionlistener for alternative way to convert text
        JButton changeButton = new JButton("Change");
		changeButton.addActionListener(myChangeListener);

		//Creating layout using panels
        JPanel top = new JPanel();
        top.add(inputScroll, BorderLayout.SOUTH);
        top.add(outputScroll, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        top.setLayout(new GridLayout(1,2));
        bottom.add(enterRules);
        bottom.add(changeButton);

		//Adding panels to the GUI
        add(top);
        add(bottom, BorderLayout.SOUTH);
	}
	
	//Inner class extends ActionListener and actionPerformed will be called after enter 
	//is pressed in text field or button is pressed.
	private class ChangeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			myModel.setText(inputText.getText());	//Passes input text to model
			myModel.setRules(enterRules.getText()); //Passes rules string to model which processes them
			myModel.convertText();					//Model converts text
			outputText.setText(myModel.getText());	//Gets converted text back from model
		}
	}
}