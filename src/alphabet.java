import java.util.ArrayList;

public class alphabet {
	private ArrayList<String> alpha = new ArrayList<String>();// tableau de lettre de X
	
	public alphabet(ArrayList<String> alpha)
	{
		this.alpha=alpha;
	}
public void setalpha(ArrayList<String> alpha)
{
	this.alpha=alpha;
}
public ArrayList<String> getalpha()
{
	return alpha;
}
}
