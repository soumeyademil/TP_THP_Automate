import java.util.HashSet;

public class Alphabet {
	private HashSet<String> alpha = new HashSet<String>();// tableau de lettre de X
	
	public Alphabet(HashSet<String> alpha)
	{
		this.alpha=alpha;
	}
	public void setalpha(HashSet<String> alpha)
	{
	this.alpha=alpha;
	}
	public HashSet<String> getalpha()
	{
		return alpha;
	}
	@Override
	public String toString() {
		return "Alphabet [" + alpha + "]";
	}

	
}
