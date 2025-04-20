package application;

public class BST extends BaseBinaryTree implements BSTIF{
	
	@Override
	public TNode find(Object key) {
		return find( key , getRoot());
	}

	public TNode find(Object data , TNode node) {
		
		if(node ==null)
			return null;
		if (node.getData() == data)
			return node;
		else if (((Comparable<Object>) node.getData()).compareTo(data) > 0)
			return find(data , node.right);
		else
			return find(data,node.left);
	}
	
	@Override
    public void insert(Object data) {
        root = insert(data, getRoot());
    }
	
	public TNode insert(Object data , TNode node) {
		if (node == null)
			node = new TNode(data);
		if (((Comparable<Object>) node.getData()).compareTo(data) <= 0)
			node.right = insert(data , node.right);
		else 
			node.left = insert(data , node.left);
		return node;
	}

	@Override
    public TNode delete(Object data) {
        return root = delete(data,  getRoot());
    }
	
	public TNode delete(Object data, TNode node) {
        if (node == null)
            return null;
        if (((Comparable<Object>) node.getData()).compareTo(data) < 0)
            node.setRight(delete(data, node.getRight()));
        else if (((Comparable<Object>) node.getData()).compareTo(data) > 0)
            node.setLeft(delete(data, node.getLeft()));
        else if (node.getRight() == null)
            node = node.getLeft();
        else if (node.getLeft() == null)
            node = node.getRight();
        else {
            node = deleteNodeWithTwoChildren(node);
        }
        return node;
    }
	
	private TNode deleteNodeWithTwoChildren(TNode node) {
		TNode suc = new TNode(node.right);
		node.data = suc.data;
		return delete(node.data , node.right);
	}
	
	private TNode findMin(TNode node) {
		if (node.left == null)
			return node;
		return findMin(node.left);
	}
}
