package application;

public class HashEntry {
    private String key;
    private AVLTree tree;
    private char status;

    public HashEntry(String key, AVLTree tree, char status) {
        this.key = key;
        this.tree = tree;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public AVLTree getTree() {
        return tree;
    }

    public char getStatus() {
        return status;
    }

    public void setDeleteStatus() {
        this.status = 'D';
    }
}
