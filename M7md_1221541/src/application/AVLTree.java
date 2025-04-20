
package application;

public class AVLTree {


	public TNode rotateRight(TNode node){
		TNode leftChild = node.left;
		node.left = leftChild.right;
		leftChild.right = node;
		updateHeight(node);
		updateHeight(leftChild);

		return leftChild;
	}


	public TNode rotateLeft(TNode node){
		TNode rightChild = node.right;
		rightChild.left = node;
		updateHeight(node);
		updateHeight(rightChild);

		return rightChild;
	}

	public int getHeight(TNode node){
		return node == null ? -1 : node.height;
	}

	public void updateHeight(TNode node){
		if (node == null )
			return ;
		int left = getHeight(node.left);
		int right = getHeight(node.right);
		node.height = Math.max(left, right)+1;
	}

	public int Balance(TNode node){
		if (node == null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}

	public TNode insert ( TNode node , Object data){
		if (node == null)
			return new TNode(data);

		Comparable<Object> comData = (Comparable<Object>) data;
		if (comData.compareTo(node.data) < 0)
			node.left = insert(node.left , data);
		else if (comData.compareTo(node.data) > 0)
			node.right = insert(node.right , data);
		else
			return node;

		updateHeight(node);
		int bf = Balance(node);
		if (bf  > 1){
			if(Balance(node.left) >= 0)
				return rotateRight(node);
			else {
				node.left = rotateLeft(node.left);
				return rotateRight(node);
			}
		}
		else if (bf < -1){
			if (Balance(node.right) <= 0)
				return rotateLeft(node);
			else{
				node.right = rotateRight(node.right);
				return rotateLeft(node);
			}
		}
		else
			return node;
	}


	public TNode delete(TNode node, Object data) {
        if (node == null)
            return null;

        Comparable<Object> comData = (Comparable<Object>) data;
        if (comData.compareTo(node.data) < 0)
            node.left = delete(node.left, data);
        else if (comData.compareTo(node.data) > 0)
            node.right = delete(node.right, data);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                TNode successor = findMin(node.right);
                node.data = successor.data;
                node.right = delete(node.right, successor.data);
            }
        }

        updateHeight(node);
        int bf = Balance(node);

        if (bf > 1) {
            if (Balance(node.left) >= 0)
                return rotateRight(node);
            else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (bf < -1) {
            if (Balance(node.right) <= 0)
                return rotateLeft(node);
            else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private TNode findMin(TNode node) {
        if (node.left == null)
            return node;
        return findMin(node.left);
    }
}