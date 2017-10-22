import java.util.ArrayList;

//Model is the class which handles reading and altering the user's input to the GUI
public class Model
{
	private String text;				//The text inputted by user to be changed
	private String[] rulesReplace;		//Strings to be replaced in the text
	private String[] rulesReplaceWith;	//String to replace the original strings
	
	//Puts inputted text into the text variable
	public void setText(String inputText)
	{
		text = inputText;
	}
	
	//Reads inputted rule text and puts strings into two respective replace variables
	public void setRules(String ruleText)
	{
		ArrayList<String> rules = new ArrayList<String>();	//Array list created to store unknown amount of rules
		//Loop separates the rule string into separate rules separated by the semicolons
		while(ruleText.contains(";") == true)
		{
			System.out.println("Reading next rule ending with ;..."); //(for error checking)
			rules.add(ruleText.substring(0, ruleText.indexOf(";"))); //Adds rule to arraylist
			ruleText = ruleText.substring(ruleText.indexOf(";")+1, ruleText.length()); //removes rule alreadys added
		}
		System.out.println(rules); //(for error checking)

		//Setting the size of the rule arrays to equal the amount of rules
		int amountOfRules = rules.size();
		rulesReplace = new String[amountOfRules];
		rulesReplaceWith = new String[amountOfRules];

		//Loop separates each rule in the arraylist into the two corresponding words
		for(int n = 0; n<amountOfRules; n++)
		{
			if(rules.get(n).contains("="))
			{
				System.out.println("Splitting next rule string via =..."); //(for error checking)
				rulesReplace[n] = rules.get(n).substring(0, rules.get(n).indexOf("=")); //For string n in the arraylist: places the text before the = in the rulesReplace array
				rulesReplaceWith[n] = rules.get(n).substring(rules.get(n).indexOf("=")+1, rules.get(n).length()); //For string n in the arraylist: places the text after the = in the rulesReplaceWith array
			}
		}
	}
	
	//For each rule, replaces any occurences of rulesReplace[n] with rulesReplaceWith[n] in the text
	public void convertText()
	{
		for(int n = 0; n<rulesReplace.length; n++)
		{
			text = text.replace(rulesReplace[n], rulesReplaceWith[n]);
		}
		System.out.println(text); 
	}

	public String getText(){return text;}
}