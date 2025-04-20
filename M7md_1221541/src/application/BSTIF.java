package application;

public interface BSTIF  extends BTIF{

	TNode find(Object key);
	void insert(Object key);
	TNode delete(Object key);
}
