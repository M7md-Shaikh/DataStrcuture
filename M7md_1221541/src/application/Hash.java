package application;

public class Hash {
    int tableSize = 128;
    HashEntry [] table;
    int currentSize = 0;
    public Hash(int size){
        table = new HashEntry[size];
        for(int i = 0; i < table.length; i++){
            table [i] = null;
        }
        tableSize = size;
        currentSize = 0;
    }
    public void makeEmpty(){
        for(int i = 0; i < table.length; i++){
            table [i] = null;
        }
        currentSize = 0;
    }
    public int getCurrentSize(){
        return currentSize;
    }
    public int getTableSize(){
        return tableSize;
    }
    public boolean contains(String key){
        return get(key) != null;
    }
    public String get(String key){
        int i = 1;
        int location = getHash(key);
        while((table[location] != null) && (table[location].getStatus() != 0)){
            if (table[location].getKey().equals(key))
                return table[location].getValue();
            location = (location + i*i) % tableSize;
        }
        return null;
    }

    public int getHash(String key){
        int hashVal = 0;
        for(int i =0; i < key.length(); i++)
            hashVal = (2<<5) * hashVal + key.charAt(i);

        hashVal %= tableSize;
        if(hashVal < 0)
            hashVal += tableSize;
        return hashVal;
    }

    public void remove(String key){
        int i = 1;
        if(!contains(key))
            return;
        int hash = getHash(key);
        while ((table[hash] != null) && (table[hash].getStatus() != 0) && (!table[hash].getKey().equals(key)))
            hash = (hash + i*i) % tableSize;

        currentSize--;
        table[hash].setDeleteStatus();
    }
    public String find(String key)
    {
        int i =1 ;
        int hash = getHash(key);
        while (((table[hash] != null) && (table[hash].getStatus() != 0)
                && (!table[hash].getKey().equals(key))) ||
                ((table[hash].getKey().equals(key)) &&
                        (table[hash].getStatus() ==2)))
            hash = (hash + i*i) % tableSize;

        if ((table[hash] == null) || (table[hash].getStatus() == 0))
            return null;
        else
            return table[hash].getValue();
    }
    public void insert(String key, String value)
    {
        if ( currentSize >= tableSize / 2)
            rehash();
        int hash = getHash(key);
        int i = 1;
        while ((table[hash] != null) && (table[hash].getStatus() != 0) &&
                (table[hash].getStatus() != 2))
            hash = (hash + i*i) % tableSize;

        currentSize++;
        table[hash] = new HashEntry(key, value,(byte)1);
    }
    private void rehash( )
    {
        Hash newList;
        newList = new Hash(nextPrime( 2 * table.length ));
        for( int i = 0; i < table.length; i++ )
            if ((table[i] != null) && (table[i].getStatus() == 1))
                newList.insert( table[ i ].getKey(), table[ i ].getValue() );

        table = newList.table;
        tableSize = newList.tableSize;
    }
    
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;
        for( ; !isPrime( n ); n += 2 )
            ;
        return n;
    }
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;
        if( n == 1 || n % 2 == 0 )
            return false;
        for( int i = 3; i * i <= n/2; i += 2 )
            if( n % i == 0 )
                return false;
        return true;
    }

    public void printHashTable()
    {
        for( int i = 0; i < table.length; i++ )
            if ((table[ i ] != null)&& (table[i].getStatus() == 1))
                System.out.println(i +" , " + table[ i ].getKey()+ " , "+ table[ i ].getValue());
    }



}
