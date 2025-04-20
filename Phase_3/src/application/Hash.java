package application;

import java.util.Arrays;

public class Hash {
    private int tableSize;
    public HashEntry[] table;
    private int currentSize;
    public int countD = 0;

    public Hash(int size) {
        tableSize = size;
        table = new HashEntry[tableSize];
        currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getTableSize() {
        return tableSize;
    }

    public boolean contains(String key) {
        int i = 1; 
        int location = getHash(key);
        while ((table[location] != null) && (table[location].getStatus() != 'E')) {
            if (table[location].getKey().equals(key))
                return true;
            location = (location + i * i) % tableSize;
            i++;
        }
        return false;
    }

    public AVLTree get(String key) {
        int i = 1;
        int location = getHash(key);
        while ((table[location] != null) && (table[location].getStatus() != 'E')) {
            if (table[location].getKey().equals(key))
                return table[location].getTree();
            location = (location + i * i) % tableSize;
            i++;
        }
        return null;
    }

    public int getHash(String key) {
        return Math.abs(key.hashCode() % tableSize);
    }
    
    public void remove(String key) {
        int i = 1;
        if (!contains(key))
            return;
        int hash = getHash(key);
        while ((table[hash] != null) && (table[hash].getStatus() != 'E') && (!table[hash].getKey().equals(key))) {
            hash = (hash + i * i) % tableSize;
            i++;
        }

        if (table[hash] != null && table[hash].getKey().equals(key)) {
            currentSize--;
            table[hash].setDeleteStatus(); 
        }
    }

    public void insert(String key, Martyr martyr) {
        if (currentSize >= tableSize / 2) {
            rehash();
        }

        int hash = getHash(key);
        int i = 1;
        int initialHash = hash;

        while (table[hash] != null && table[hash].getStatus() != 'E') {
            if (table[hash].getKey().equals(key) && table[hash].getStatus() == 'F') {
                AVLTree tree = table[hash].getTree();
                tree.insert(martyr);
                return;
            }
            hash = (initialHash + i * i) % tableSize;
            i++;

            if (hash == initialHash) {
                rehash();
                insert(key, martyr);
                return;
            }
        }

        AVLTree newTree = new AVLTree();
        newTree.setRoot(newTree.insert(newTree.getRoot(), martyr));
        table[hash] = new HashEntry(key, newTree, 'F');
        currentSize++;
        countD++;
    }

    private void rehash() {
        int newSize = nextPrime(2 * tableSize);
        Hash newTable = new Hash(newSize);

        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null && table[i].getStatus() == 'F') {
                rehashAVLTree(newTable, table[i].getKey(), table[i].getTree().getRoot());
            }
        }

        table = newTable.table;
        tableSize = newTable.tableSize;
    }

    private void rehashAVLTree(Hash newTable, String key, Node node) {
        if (node != null) {
            newTable.insert(key, node.getMartyr());
            rehashAVLTree(newTable, key, node.getLeft());
            rehashAVLTree(newTable, key, node.getRight());
        }
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public void printHashTable() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].getStatus() == 'F') {
                System.out.println(i + " -> " + table[i].getKey() + " -> " + table[i].getTree());
            }
        }
        System.out.println("Total martyrs: " + getTotalMartyrs());
        System.out.println("Total inserts: " + countD);
    }

    public int getTotalMartyrs() {
        int total = 0;
        for (int i = 0; i < tableSize; i++) {
            if (table[i] != null && table[i].getStatus() == 'F') {
                total += table[i].getTree().getCountM();
            }
        }
        return total;
    }

    public String[] getAllDates() {
        String[] dates = new String[currentSize];
        int index = 0;
        for (HashEntry entry : table) {
            if (entry != null && entry.getStatus() == 'F') {
                dates[index++] = entry.getKey();
            }
        }
        return dates;
    }
    
    public String[] getAllDistricts() {
        String[] districts = new String[tableSize];
        int index = 0;
        for (HashEntry entry : table) {
            if (entry != null && entry.getStatus() == 'F') {
                String district = entry.getTree().getRoot().getMartyr().getDistrict();
                boolean found = false;
                for (int i = 0; i < index; i++) {
                    if (districts[i] != null && districts[i].equals(district)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    districts[index++] = district;
                }
            }
        }
        return Arrays.copyOf(districts, index);
    }

    public String[] getLocationsInDistrict(String district) {
        String[] locations = new String[tableSize];
        int index = 0;
        for (HashEntry entry : table) {
            if (entry != null && entry.getStatus() == 'F' && entry.getTree().getRoot().getMartyr().getDistrict().equals(district)) {
                String location = entry.getTree().getRoot().getMartyr().getLocation();
                boolean found = false;
                for (int i = 0; i < index; i++) {
                    if (locations[i] != null && locations[i].equals(location)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    locations[index++] = location;
                }
            }
        }
        return Arrays.copyOf(locations, index);
    }
}
