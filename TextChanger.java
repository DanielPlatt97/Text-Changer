//Main class 
public class TextChanger
{
    //Creates instancess of the model and GUI class
    public static void main(String[] args)
    {
		Model myModel = new Model();    //Model
        new GUI(myModel);               //View + Controller
    }
}