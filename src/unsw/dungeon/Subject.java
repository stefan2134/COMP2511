package unsw.dungeon;

/**
 * Interface for a subject.
 * 
 * @author z5208485
 *
 */
public interface Subject {
	public void addObserver(Observer o);

	public void remObserver(Observer o);

	public void notifyObservers();
}
