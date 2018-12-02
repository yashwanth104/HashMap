
package apiformap;
import java.util.ArrayList;
/**
 *
 * @author Yashwanth
 */
// class for every node 
class HashNode<K, V> 
{ 
    K key; 
    V value; 
    
    HashNode<K, V> next; 
 
    public HashNode(K key, V value) 
    { 
        this.key = key; 
        this.value = value; 
    } 
} 
public class APIforMap {

    public static void main(String[] args) {
        
        Map<String, Double>map = new Map<>(); 
        map.put("yash",2.0 ); 
        System.out.println(map.get("yash"));
    }
    
}
	  
// hash table class 
class Map<K, V> 
{ 
    // array of chains for hash table, size to keep track of size and 
    //numbuckets to fix the size of hashmap bucket
    private ArrayList<HashNode<K, V>> bucketArray; 
    private int numBuckets;
    private int size;
  
    // To initiate arraylist for hashmap with empty values(null)
    public Map() 
    { 
        bucketArray = new ArrayList<>(); 
        numBuckets = 888; 
        size = 0;
        for (int i = 0; i < numBuckets; i++) 
            bucketArray.add(null); 
    } 
  
  
    // function returns the bucket index by calclulating hashvalue of key
    private int getBucketIndex(K key) 
    { 
        int hashCode = key.hashCode(); 
        int index = hashCode % numBuckets; 
        return index; 
    } 
  
  
  
    // function to get value of the paired key
    public V get(K key) 
    { 
        // get index for particular key
        int bucketIndex = getBucketIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
  
        // Searching value for the given key in hashmap
        while (head != null) 
        { 
            if (head.key.equals(key)) 
                return head.value; 
            head = head.next; 
        } 
        return null; 
    } 
  
    // function to add key,value to map
    public void put(K key, V value) 
    { 
        // get the index for particular key
        int bucketIndex = getBucketIndex(key); 
        HashNode<K, V> head = bucketArray.get(bucketIndex); 
  
        // Check for existing key and handle collisions
        while (head != null) 
        { 
            if (head.key.equals(key)) 
            { 
                head.value = value; 
                return; 
            } 
            head = head.next; 
        } 
        
        //Add the key value to list
        size++; 
        
        head = bucketArray.get(bucketIndex); 
        HashNode<K, V> newNode = new HashNode<K, V>(key, value); 
        newNode.next = head; 
        bucketArray.set(bucketIndex, newNode); 
  
        // If size(current) goes more than 70% of bucket size increases bucket size by 2 itmes
        if ((1.0*size)/numBuckets >= 0.7) 
        { 
            ArrayList<HashNode<K, V>> temp = bucketArray; 
            bucketArray = new ArrayList<>(); 
            numBuckets = 2 * numBuckets; 
            size = 0; 
            for (int i = 0; i < numBuckets; i++) 
                bucketArray.add(null); 
  
            for (HashNode<K, V> headNode : temp) 
            { 
                while (headNode != null) 
                { 
                    put(headNode.key, headNode.value); 
                    headNode = headNode.next; 
                } 
            } 
        } 
    } 
  
}