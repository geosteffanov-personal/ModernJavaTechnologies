package bullsNcowsNaliens;

import java.util.List;

public abstract class Target<T> {
	private List<T> target;
		
	public abstract GuessResult<T> getResult(Guess<T> guess); 
}
