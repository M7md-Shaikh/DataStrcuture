package application;

public class AVLTree {
	public Node root;
	public int countM; 
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}


	public Node rotateRight(Node node) {
		Node leftChild = node.getLeft();
		if (leftChild == null) {
			return node; 
		}
		node.setLeft(leftChild.getRight());
		leftChild.setRight(node);
		updateHeight(node);
		updateHeight(leftChild);
		return leftChild;
	}

	public Node rotateLeft(Node node) {
		Node rightChild = node.getRight();
		if (rightChild == null) {
			return node; 
		}
		node.setRight(rightChild.getLeft());
		rightChild.setLeft(node);
		updateHeight(node);
		updateHeight(rightChild);
		return rightChild;
	}

	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
	}

	public void updateHeight(Node node) {
		if (node != null) {
			int leftHeight = getHeight(node.getLeft());
			int rightHeight = getHeight(node.getRight());
			node.setHeight(Math.max(leftHeight, rightHeight) + 1);
		}
	}

	public int getBalance(Node node) {
		return node == null ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
	}

	public void insert(Martyr martyr) {
		root = insert(root, martyr);
	}

	public Node insert(Node node, Martyr martyr) {
		try {
			if (node == null) {
				countM++;
				return new Node(martyr);
			}

			int disComp = martyr.getDistrict().compareTo(node.getMartyr().getDistrict());
			int marName = martyr.getName().compareTo(node.getMartyr().getName());

			if (disComp < 0 || (disComp == 0 && marName < 0)) {
				node.setLeft(insert(node.getLeft(), martyr));
			} else if (disComp > 0 || (disComp == 0 && marName > 0)) {
				node.setRight(insert(node.getRight(), martyr));
			} else {
				return node; 
			}

			updateHeight(node);
			return balance(node, martyr);
		}
			catch(NullPointerException e) {
				System.out.println(e.getMessage());
			}
		return node;
		}

		private Node balance(Node node, Martyr martyr) {
			int balance = getBalance(node);

			if (balance > 1) {
				if (martyr.getDistrict().compareTo(node.getLeft().getMartyr().getDistrict()) < 0) {
					return rotateRight(node);
				} else {
					node.setLeft(rotateLeft(node.getLeft()));
					return rotateRight(node);
				}
			}

			if (balance < -1) {
				if (martyr.getDistrict().compareTo(node.getRight().getMartyr().getDistrict()) > 0) {
					return rotateLeft(node);
				} else {
					node.setRight(rotateRight(node.getRight()));
					return rotateLeft(node);
				}
			}

			return node;
		}

		public Node delete(Node node, Martyr martyr) {
			if (node == null) {
				return null;
			}

			int disComp = martyr.getDistrict().compareTo(node.getMartyr().getDistrict());
			int marName = martyr.getName().compareTo(node.getMartyr().getName());

			if (disComp < 0 || (disComp == 0 && marName < 0)) {
				node.setLeft(delete(node.getLeft(), martyr));
			} else if (disComp > 0 || (disComp == 0 && marName > 0)) {
				node.setRight(delete(node.getRight(), martyr));
			} else {
				if (node.getLeft() == null) {
					return node.getRight();
				} else if (node.getRight() == null) {
					return node.getLeft();
				} else {
					Node successor = findMin(node.getRight());
					node.setMartyr(successor.getMartyr());
					node.setRight(delete(node.getRight(), successor.getMartyr()));
				}
			}

			updateHeight(node);

			return balanceDeletion(node);
		}

		private Node balanceDeletion(Node node) {
			int balance = getBalance(node);

			if (balance > 1) {
				if (getBalance(node.getLeft()) >= 0) {
					return rotateRight(node);
				} else {
					node.setLeft(rotateLeft(node.getLeft()));
					return rotateRight(node);
				}
			}

			if (balance < -1) {
				if (getBalance(node.getRight()) <= 0) {
					return rotateLeft(node);
				} else {
					node.setRight(rotateRight(node.getRight()));
					return rotateLeft(node);
				}
			}

			return node;
		}

		private Node findMin(Node node) {
			while (node.getLeft() != null) {
				node = node.getLeft();
			}
			return node;
		}

		public int getCountM() {
			return countM;
		}

		public String getDisWithMaxMar(Node root) {
			String[] maxDistrict = new String[1];
			int[] maxCount = new int[1];
			countMarByDis(root, maxDistrict, maxCount);
			return maxDistrict[0];
		}

		public  String getLocWithMaxMar(Node root) {
			String[] maxLocation = new String[1];
			int[] maxCount = new int[1];
			countMarByLoc(root, maxLocation, maxCount);
			return maxLocation[0];
		}

		private void countMarByDis(Node node, String[] maxDistrict, int[] maxCount) {
			if (node == null) {
				return;
			}

			String currentDistrict = node.getMartyr().getDistrict();
			int count = countMarInDis(node, currentDistrict);
			if (count > maxCount[0]) {
				maxDistrict[0] = currentDistrict;
				maxCount[0] = count;
			}

			countMarByDis(node.getLeft(), maxDistrict, maxCount);
			countMarByDis(node.getRight(), maxDistrict, maxCount);
		}

		private void countMarByLoc(Node node, String[] maxLocation, int[] maxCount) {
			if (node == null) {
				return;
			}

			String currentLocation = node.getMartyr().getLocation();
			int count = countMarInLoc(node, currentLocation);
			if (count > maxCount[0]) {
				maxLocation[0] = currentLocation;
				maxCount[0] = count;
			}

			countMarByLoc(node.getLeft(), maxLocation, maxCount);
			countMarByLoc(node.getRight(), maxLocation, maxCount);
		}

		private int countMarInDis(Node node, String district) {
			if (node == null) {
				return 0;
			}

			int count = 0;
			if (node.getMartyr().getDistrict().equals(district)) {
				count = 1;
			}

			return count + countMarInDis(node.getLeft(), district) + countMarInDis(node.getRight(), district);
		}

		private int countMarInLoc(Node node, String location) {
			if (node == null) {
				return 0;
			}

			int count = 0;
			if (node.getMartyr().getLocation().equals(location)) {
				count = 1;
			}

			return count + countMarInLoc(node.getLeft(), location) + countMarInLoc(node.getRight(), location);
		}

		public void delete(String martyrName) {
			root = deleteRec(root, martyrName);
		}

		private Node deleteRec(Node root, String martyrName) {
			if (root == null) {
				return root;
			}

			if (martyrName.compareTo(root.getMartyr().getName()) < 0) {
				root.left = deleteRec(root.left, martyrName);
			} else if (martyrName.compareTo(root.getMartyr().getName()) > 0) {
				root.right = deleteRec(root.right, martyrName);
			} else {
				if (root.left == null) {
					return root.right;
				} else if (root.right == null) {
					return root.left;
				}

				root.martyr = minValue(root.right);

				root.right = deleteRec(root.right, root.getMartyr().getName());
			}

			return root;
		}

		private Martyr minValue(Node root) {
			Martyr minv = root.martyr;
			while (root.left != null) {
				minv = root.left.martyr;
				root = root.left;
			}
			return minv;
		}

		public int getSizeOfTree() {
			return getSize(root);
		}

		public int getSize(Node node) {
			if (node == null) {
				return 0;
			}
			return 1 + getSize(node.getLeft()) + getSize(node.getRight());
		}

		public int getHeightOfTree() {
			return getHeight(root);
		}


		@Override
		public String toString() {
			return "AVLTree [root=" + root.getMartyr() + ", countM=" + countM + "]";
		}


	}
