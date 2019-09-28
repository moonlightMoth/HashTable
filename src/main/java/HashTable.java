import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashTable <K, V> implements Map<K, V>
{
    private Entry<?, ?>[] table;
    private float loadFactor;
    private int threshold;
    private int count;
    private int space;

    public HashTable(int space, float loadFactor)
    {
        this.loadFactor = loadFactor;
        this.space = space;
        this.table = new HashTable.Entry[space];
        threshold = (int)(loadFactor * space);
    }

    public HashTable()
    {
        new HashTable(10, 0.75f);
    }

    public V get(Object o) //TODO
    {
        return null;
    }

    public V put(K k, V v) //TODO
    {
        //int hashCode = K.hashCode();

        return v;
    }

    public boolean remove(Object o, Object o1) //TODO
    {
        return false;
    }

    public V remove(Object o) //TODO
    {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) //TODO
    {

    }

    public void clear() //TODO
    {

    }

    public Set<K> keySet() //TODO
    {
        return null;
    }

    public Collection<V> values() //TODO
    {
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() //TODO
    {
        return null;
    }

    public int size() //TODO
    {
        return 0;
    }

    public boolean isEmpty() //TODO
    {
        return false;
    }

    public boolean containsKey(Object o) //TODO
    {
        return false;
    }

    public boolean containsValue(Object o) //TODO
    {
        return false;
    }


    private int rehash(Object o) //TODO
    {
        return 0;
    }


    private static class Entry<K, V> implements Map.Entry<K, V>
    {
        private int hash;
        private K key;
        private V value;

        public K getKey()
        {
            return key;
        }

        public V getValue()
        {
            return value;
        }

        public V setValue(V v)
        {
            value = v;
            return value;
        }
    }
}
