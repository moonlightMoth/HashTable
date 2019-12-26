import java.util.*;

public class HashTable <K, V> implements Map<K, V>
{
    private Entry<K, V>[] table;
    private float loadFactor;
    private int count;
    private int space;
    private int firstPrime;

    public HashTable(int space, float loadFactor)
    {
        this.loadFactor = loadFactor;
        this.space = space;
        this.table = new HashTable.Entry[space];
        firstPrime = findFirstPrime();
    }

    public HashTable()
    {
        this(10, 0.75f);
    }

    public V get(Object o)
    {
        K k = (K) o;

        int h1 = hash1(k);
        int h2 = hash2(k);

        int h = h1;

        for (int i = 0; i < space; i++)
        {
            if(table[h] == null)
                return null;
            if(table[h].key.equals(k))
                return table[h].value;
            h = (h + h2) % space;
        }

        return null;
    }

    public V put(K k, V v)
    {
        HashTable.Entry<K, V> e = new HashTable.Entry<K, V>(k, v);


        int h1 = hash1(k);
        int h2 = hash2(k);

        int h = h1;

        for (int i = 0; i < space; i++)
        {
            if (table[h] == null)
            {
                table[h] = e;
                count++;
                return v;
            }
            else if (table[h].key.equals(k))
                return null;
            else
                h = (h + h2) % space;
        }

        if (((float)count / (float)space) > loadFactor)
        {
            expand();
            put(k, v);
        }


        return null;
    }

    public boolean remove(Object o, Object o1)
    {
        if (o1 == null)
            return false;

        K k = (K) o;
        V v = (V) o1;

        int h1 = hash1(k);
        int h2 = hash2(k);

        int h = h1;

        for (int i = 0; i < space; i++)
        {
            if(table[h] == null)
                return false;
            if(table[h].key.equals(k) && table[h].value.equals(v))
            {
                table[h] = null;
                count--;
                return true;
            }
            h = (h + h2) % space;
        }

        return false;
    }

    public V remove(Object o)
    {
        V v = get(o);
        remove(o, get(o));

        return v;
    }

    public void putAll(Map<? extends K, ? extends V> map)
    {
        for(Map.Entry<? extends K, ? extends V> e : map.entrySet())
        {
            put(e.getKey(), e.getValue());
        }
    }

    public void clear()
    {
        table = new HashTable.Entry[space];
        count = 0;
    }

    public Set<K> keySet()
    {
        Set<K> set = new HashSet();

        for (int i = 0; i < count; i++)
        {
            set.add(table[i].key);
        }

        return set;
    }

    public Collection<V> values()
    {
        Collection<V> list = new ArrayList<>();
        for (int i = 0; i < space; i++)
        {
            if (table[i] != null)
                list.add(table[i].value);
        }
        return list;
    }

    public Set<Map.Entry<K, V>> entrySet()
    {
        return new HashSet<>(Arrays.asList(table));
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public boolean containsKey(Object o)
    {
        return get(o) != null;
    }

    public boolean containsValue(Object o)
    {
        for (int i = 0; i < space; i++)
        {
            if(table[i] != null && table[i].value.equals((V)o))
                return true;
        }

        return false;
    }

    private void expand()
    {
        space = space << 1;
        Entry<K, V>[] oldTable = table.clone();
        table = new HashTable.Entry[space];

        for (int i = 0; i < oldTable.length; i++)
        {
            if (oldTable[i] != null)
                put(oldTable[i].key, oldTable[i].value);
        }

    }

    private int hash1(K k)
    {
        return Math.abs(k.hashCode() + 15) % space;
    }

    private int hash2(K k)
    {
        return Math.abs(firstPrime - (2 * k.hashCode() % firstPrime));
    }

    private int findFirstPrime()
    {
        if (space < 3)
            return 3;
        if (space == 3)
            return 5;

        for (int i = 3; i < space; i++)
        {
            if (i % 2 == 0) continue;
            if (gcd(space, i) == 1)
                return i;
        }

        space--;
        return findFirstPrime();
    }

    private int gcd(int a, int b)
    {
        int c;
        while (b > 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a);
    }


    static class Entry<K, V> implements Map.Entry<K, V>
    {
        private K key;
        private V value;

        Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

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

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) &&
                    Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(key, value);
        }
    }
}
