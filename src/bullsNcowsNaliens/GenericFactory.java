package bullsNcowsNaliens;

public abstract class GenericFactory<T> implements Createable<T>{	
	public abstract String guessTemplate(); 
	
	public abstract Target<T> generateTarget(int length);
}
