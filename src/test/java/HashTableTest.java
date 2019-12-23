import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class HashTableTest
{
    @Test
    public void testAddGet()
    {
        HashTable<K, V> ht = new HashTable<>();

        K k = new K(1, 2);
        V v = new V("sas");

        ht.put(k, v);
        V v2 = ht.get(new K(1, 2));
        Assertions.assertEquals(v, v2);
    }

    @Test
    public void testContains()
    {
        HashTable<K, V> ht = new HashTable<>();

        K k = new K(1, 2);
        V v = new V("sas");

        ht.put(k, v);

        Assertions.assertTrue(ht.containsKey(k));
        Assertions.assertTrue(ht.containsValue(v));
        Assertions.assertTrue(ht.containsKey(new K(1, 2)));
        Assertions.assertTrue(ht.containsValue(new V("sas")));
        Assertions.assertFalse(ht.containsKey(new K (12, 2)));
    }

    @Test
    public void testAddManyItems()
    {
        HashTable<K, V> ht = new HashTable<>(1, 0.75f);

        K k0 = new K(1, 2);
        V v0 = new V("sas0");

        K k1 = new K(1, 3);
        V v1 = new V("sas1");

        K k2 = new K(1, 4);
        V v2 = new V("sas2");

        K k3 = new K(1, 5);
        V v3 = new V("sas3");

        ht.put(k0, v0);
        ht.put(k1, v1);
        ht.put(k2, v2);
        ht.put(k3, v3);
        V v02 = ht.get(new K(1, 2));
        V v12 = ht.get(new K(1, 3));
        V v22 = ht.get(new K(1, 4));
        V v32 = ht.get(new K(1, 5));
        Assertions.assertEquals(v0, v02);
        Assertions.assertEquals(v1, v12);
        Assertions.assertEquals(v2, v22);
        Assertions.assertEquals(v3, v32);
    }

    @Test
    public void testRemove()
    {
        HashTable<K, V> ht = new HashTable<>();

        K k0 = new K(1, 2);
        V v0 = new V("sas0");

        K k1 = new K(1, 3);
        V v1 = new V("sas1");

        K k2 = new K(1, 4);
        V v2 = new V("sas2");

        K k3 = new K(1, 5);
        V v3 = new V("sas2");

        ht.put(k0, v0);
        ht.put(k1, v1);
        ht.put(k2, v2);
        ht.put(k3, v3);

        ht.remove(k1);

        V v02 = ht.get(new K(1, 2));
        V v12 = ht.get(new K(1, 3));
        V v22 = ht.get(new K(1, 4));
        V v32 = ht.get(new K(1, 5));

        Assertions.assertEquals(v0, v02);
        Assertions.assertNull(v12);
        Assertions.assertEquals(v2, v22);
        Assertions.assertEquals(v3, v32);
    }

    @Test
    public void testPutAll()
    {
        HashTable<K, V> ht = new HashTable<>(1, 0.75f);

        Map<K, V> map = new HashMap<>();

        K k0 = new K(1, 2);
        V v0 = new V("sas0");

        K k1 = new K(1, 3);
        V v1 = new V("sas1");

        K k2 = new K(1, 4);
        V v2 = new V("sas2");

        K k3 = new K(1, 5);
        V v3 = new V("sas3");

        map.put(k0, v0);
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);

        ht.putAll(map);

        V v02 = ht.get(new K(1, 2));
        V v12 = ht.get(new K(1, 3));
        V v22 = ht.get(new K(1, 4));
        V v32 = ht.get(new K(1, 5));

        Assertions.assertEquals(v0, v02);
        Assertions.assertEquals(v1, v12);
        Assertions.assertEquals(v2, v22);
        Assertions.assertEquals(v3, v32);
    }

    @Test
    public void testEntrySet()
    {
        HashTable<K, V> ht = new HashTable<>(1, 0.75f);

        Set<HashTable.Entry<K, V>> set = new HashSet<>();

        K k0 = new K(1, 2);
        V v0 = new V("sas0");

        K k1 = new K(1, 3);
        V v1 = new V("sas1");

        K k2 = new K(1, 4);
        V v2 = new V("sas2");

        K k3 = new K(1, 5);
        V v3 = new V("sas3");

        ht.put(k0, v0);
        ht.put(k1, v1);
        ht.put(k2, v2);
        ht.put(k3, v3);

        set.add(new HashTable.Entry<>(k0, v0));
        set.add(new HashTable.Entry<>(k1, v1));
        set.add(new HashTable.Entry<>(k2, v2));
        set.add(new HashTable.Entry<>(k3, v3));

        Assertions.assertEquals(set, ht.entrySet());
    }

    @Test
    public void testClear()
    {
        HashTable<K, V> ht = new HashTable<>(1, 0.75f);

        K k0 = new K(1, 2);
        V v0 = new V("sas0");

        K k1 = new K(1, 3);
        V v1 = new V("sas1");

        K k2 = new K(1, 4);
        V v2 = new V("sas2");

        K k3 = new K(1, 5);
        V v3 = new V("sas3");

        ht.put(k0, v0);
        ht.put(k1, v1);
        ht.put(k2, v2);
        ht.put(k3, v3);

        ht.clear();

        V v02 = ht.get(new K(1, 2));
        V v12 = ht.get(new K(1, 3));
        V v22 = ht.get(new K(1, 4));
        V v32 = ht.get(new K(1, 5));
        Assertions.assertNull(v02);
        Assertions.assertNull(v12);
        Assertions.assertNull(v22);
        Assertions.assertNull(v32);
        Assertions.assertEquals(ht.size(), 0);
    }



    class K implements Comparable
    {
        int a;
        int b;

        K(int a, int b)
        {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            K k = (K) o;
            return a == k.a &&
                    b == k.b;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(a, b);
        }

        @Override
        public int compareTo(Object o)
        {
            return ((K)o).a < a && ((K)o).b < b ? 1 : 0;
        }

        @Override
        public String toString()
        {
            return "K{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    class V implements Comparable
    {
        String s;

        V(String s)
        {
            this.s = s;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            V v = (V) o;
            return s.equals(v.s);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(s);
        }

        @Override
        public int compareTo(Object o)
        {
            return s.compareTo(((V)o).s);
        }

        @Override
        public String toString()
        {
            return "V{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }
}