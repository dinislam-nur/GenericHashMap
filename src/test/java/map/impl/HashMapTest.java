package map.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private static final Object OBJECT_KEY = new Object();
    private static final Object OBJECT_VALUE = new Object();

    @Test
    @DisplayName("Check the size functionality")
    void SizeTest() {


        //Prepare new map
        final Map<String, String> hashMap = new HashMap<>();
        hashMap.put("first", "1");
        hashMap.put("second", "2");

        //Assertion
        assertEquals(2, hashMap.size());
        hashMap.remove("first");
        assertEquals(1, hashMap.size());
    }

    @Nested
    @DisplayName("Checking the update of value")
    class UpdateTest {

        @Test
        @DisplayName("for String Key")
        void updateTestForStringKey() {
            //Prepare map
            final Map<String, String> map = new HashMap<>();
            map.put("forUpdate", "beforeUpdateValue");

            //Assertion
            assertEquals("beforeUpdateValue", map.get("forUpdate"));
            map.put("forUpdate", "UpdatedValue");
            assertEquals("UpdatedValue", map.get("forUpdate"));
        }

        @Test
        @DisplayName("for Object Key")
        void updateTestForObjectKey() {

            //Prepare
            final Object newObjectValue = new Object();
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);


            //Assertion
            assertEquals(OBJECT_VALUE, map.get(OBJECT_KEY));
            map.put(OBJECT_KEY, newObjectValue);
            assertEquals(newObjectValue, map.get(OBJECT_KEY));
        }
    }

    @Nested
    @DisplayName("Checking the get functionality")
    class GetTest {

        @Test
        @DisplayName("for String Keys")
        void getTestForStringKey() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Assertion
            assertAll(
                    () -> assertEquals(1, map.get("first")),
                    () -> assertEquals(2, map.get("second"))
            );
        }

        @Test
        @DisplayName("for Object Key and Value")
        void getTestForObjectKeyAndValue() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertEquals(OBJECT_VALUE, map.get(OBJECT_KEY));
        }

        @Test
        @DisplayName("for Non-HashMap element")
        void getTestForNonHashMapElement() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertNull(map.get("Non-HashMap element"));
        }
    }


    @Nested
    @DisplayName("Checking content of Map")
    class ContainsTest {

        @Test
        @DisplayName("for String Key")
        void containsTestForStringKey() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);

            //Assertion
            assertTrue(map.containsKey("first"));
        }

        @Test
        @DisplayName("for Object Key")
        void containsTestForObjectKey() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertTrue(map.containsKey(OBJECT_KEY));
        }

        @Test
        @DisplayName("for Non-HashMap element")
        void containsTestForNonHashMapElement() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertFalse(map.containsKey("Non-HashMap element"));
        }
    }

    @Nested
    @DisplayName("Checking the delete function")
    class RemoveTest {

        @Test
        @DisplayName("for entry with String Key")
        void removeTestForEntryWithStringKey() {
            //Prepare
            final Map<String, String> map = new HashMap<>();
            map.put("removeKey", "removeValue");

            //Assertion
            assertTrue(map.containsKey("removeKey"));
            assertEquals("removeValue", map.remove("removeKey"));
            assertFalse(map.containsKey("removeKey"));
        }

        @Test
        @DisplayName("for entry with Object Key")
        void removeTestForEntryWithObjectKey() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertTrue(map.containsKey(OBJECT_KEY));
            map.remove(OBJECT_KEY);
            assertFalse(map.containsKey(OBJECT_KEY));
        }

        @Test
        @DisplayName("for Non-HashMap element")
        void removeNonHashMapElementTest() {
            //Prepare
            final Map<Object, Object> map = new HashMap<>();
            map.put(OBJECT_KEY, OBJECT_VALUE);

            //Assertion
            assertNull(map.remove("Non-HashMap element"));
        }
    }

    @Test
    @DisplayName("Checking functionality of an empty Map")
    void anEmptyHashMapTest() {
        //Prepare empty HashMap
        final Map<Object, Object> emptyMap = new HashMap<>();

        //Assertion
        assertAll(
                () -> assertNull(emptyMap.get(OBJECT_KEY)),
                () -> assertFalse(emptyMap.containsKey(OBJECT_KEY))
        );
    }

    @Test
    @DisplayName("Checking the compare functionality")
    void equalsTest() {
        //Preparing a pair Maps that have the same entries
        final Object firstKey = new Object();
        final Object secondKey = new Object();
        final Object firstValue = new Object();
        final Object secondValue = new Object();

        final Map<Object, Object> leftMap = new HashMap<>();
        final Map<Object, Object> rightMap = new HashMap<>();
        final HashMap<Object, Object> map = new HashMap<>();

        leftMap.put(firstKey, firstValue);
        leftMap.put(secondKey, secondValue);
        rightMap.put(secondKey, secondValue);
        rightMap.put(firstKey, firstValue);

        //Assertion
        assertAll(
                () -> assertEquals(rightMap, leftMap),
                () -> assertNotEquals(map, leftMap)
        );
    }

    @Test
    @DisplayName("Check the ability to add null key and null value")
    void nullKeyAndNullValueTest() {
        //Prepare data
        final Map<Object, Object> map = new HashMap<>();
        final Object key = null;
        final Object value = null;
        map.put(key, value);

        //Assertion
        assertNull(map.get(key));
    }

    @Nested
    @DisplayName("Check the ability to throw exceptions")
    class ExceptionTests {

        @Test
        @DisplayName("when we pass invalid capacity into constructor")
        void invalidCapacityTest() {
            assertThrows(IllegalArgumentException.class, () -> new HashMap<>(-1));
        }

        @Test
        @DisplayName("when we pass invalid load factor into constructor")
        void invalidLoadFactoryTest() {
            assertAll(
                    () -> assertThrows(IllegalArgumentException.class, () -> new HashMap<>(16, 1.1)),
                    () -> assertThrows(IllegalArgumentException.class, () -> new HashMap<>(16, -0.1))
            );

        }
    }

    @Nested
    @DisplayName("Checking entrySet functionality")
    class EntrySetTestClass {

        @Test
        @DisplayName("Getting entrySet from Map")
        void getEntrySetTest() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);
            final Set<Map.Entry<String, Integer>> entries = map.entrySet();

            //Assertion
            for (Map.Entry<String, Integer> entry : entries) {
                assertTrue(map.containsKey(entry.getKey()));
            }
        }

        @Test
        @DisplayName("Removing entries from EntrySet")
        void removeEntryFromEntrySet() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Execution
            final Set<Map.Entry<String, Integer>> entries = map.entrySet();
            entries.removeIf(entry -> "first".equals(entry.getKey()));

            //Assertion
            assertFalse(map.containsKey("first"));
            assertTrue(map.containsKey("second"));
            assertEquals(map.size(), entries.size());
        }

        @Test
        @DisplayName("Getting entrySet from empty Map")
        void getEntryFromEmptyMap() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();

            //Execution
            final Set<Map.Entry<String, Integer>> entries = map.entrySet();

            //Assertion
            assertEquals(map.size(), entries.size());
        }

        @Test
        @DisplayName("Checking entrySet size when we delete the entry from the map")
        void getEntrySetSizeWhenDeleteEntryFromMap() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Execution and assertion
            final Set<Map.Entry<String, Integer>> entries = map.entrySet();
            assertEquals(2, entries.size());
            assertEquals(map.size(), entries.size());

            //remove
            map.remove("first");

            //assertion
            assertEquals(1, entries.size());
            assertEquals(map.size(), entries.size());
        }
    }

    @Nested
    @DisplayName("Checking keySet functionality")
    class KeySetTestClass {

        @Test
        @DisplayName("Getting keySet from Map")
        void getKeySetTest() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Execution
            final Set<String> keys = map.keySet();

            //Assertion
            assertTrue(keys.contains("first"));
            assertTrue(keys.contains("second"));
            assertFalse(keys.contains("third"));
            map.put("third", 3);
            assertTrue(keys.contains("third"));
        }

        @Test
        @DisplayName("Removing keys from EntrySet")
        void removeKeysFromKeySet() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Execution
            final Set<String> keySet = map.keySet();
            keySet.removeIf("first"::equals);

            //Assertion
            assertFalse(keySet.contains("first"));
            assertTrue(keySet.contains("second"));
            assertFalse(map.containsKey("first"));
            assertTrue(map.containsKey("second"));
            assertEquals(map.size(), keySet.size());
        }

        @Test
        @DisplayName("Getting keySet from empty Map")
        void getKeySetFromEmptyMap() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();

            //Execution
            final Set<String> keys = map.keySet();

            //Assertion
            assertEquals(map.size(), keys.size());
        }

        @Test
        @DisplayName("Checking keySet size when we delete the entry from the map")
        void getKeySetSizeWhenDeleteEntryFromMap() {
            //Prepare
            final Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);

            //Execution and assertion
            final Set<String> keys = map.keySet();
            assertEquals(2, keys.size());
            assertEquals(map.size(), keys.size());

            //remove
            map.remove("first");

            //assertion
            assertEquals(1, keys.size());
            assertEquals(map.size(), keys.size());
        }
    }

    @Nested
    @DisplayName("Checking Collection of values functionality")
    class CollectionValuesTestClass {

        @Test
        @DisplayName("Getting Collection of values")
        void getValuesTest() {
            Map<String, Integer> map = new HashMap<>();
            map.put("first", 1);
            map.put("second", 2);
            map.put("third", 3);
            map.put("fourth", 4);

            final Collection<Integer> values = map.values();

            assertTrue(values.contains(3));
            assertFalse(values.contains(5));
        }
    }

    @Test
    @DisplayName("Checking clearing map")
    void clearMapTest() {
        //Prepare
        Map<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("fourth", 4);

        //Assertion
        assertEquals(4, map.size());
        assertTrue(map.containsKey("first"));
        map.clear();
        assertFalse(map.containsKey("first"));
        assertTrue(map.isEmpty());
    }

    @Test
    @DisplayName("Checking containsValue functionality")
    void containsValueTest() {
        //Prepare
        Map<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("fourth", 4);

        //Assertion
        assertTrue(map.containsValue(4));
        assertTrue(map.containsValue(3));
        assertTrue(map.containsValue(2));
        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(5));
    }

    @Test
    @DisplayName("Checking putAll functionality")
    void putAllTest() {
        Map<String, Integer> map = new java.util.HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("fourth", 4);

        final HashMap<String, Integer> newMap = new HashMap<>();
        assertTrue(newMap.isEmpty());
        newMap.putAll(map);
        assertEquals(4, map.size());
        assertTrue(map.containsKey("first"));
        assertEquals(map, newMap);
    }

}