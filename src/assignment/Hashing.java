package assignment;

/**
 * Created by Koo Lee on 11/13/2014.
 */
public class Hashing {

    private final HashValue[] hashTable;
    private final int defaultNumber = Character.getNumericValue('A') - 1;

    public Hashing(int n) {
        hashTable = new HashValue[n];
    }

    public void put(String value) {
        int index = numOfString(value) % hashTable.length;
        HashValue hashValue = hashTable[index];

        HashValue linkedHashValue;

        // Check element of hashTable is null.
        if (hashValue == null) {
            linkedHashValue = new HashValue();
            hashTable[index] = linkedHashValue;
        } else {
            linkedHashValue = hashValue;
        }

        while (hashValue != null) {
            linkedHashValue = hashValue;
            hashValue = hashValue.link;
        }
        linkedHashValue.link = new HashValue();
        linkedHashValue.key = value;
    }

    public String get(String value) {
        HashValue hashValue = hashTable[numOfString(value) % hashTable.length];
        while (hashValue != null) {
            if (value.equals(hashValue.key)) {
                return value;
            }
        }
        return null;
    }

    public String status() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (HashValue hashValue : hashTable) {
            result.append(i++ + " :");
            while (hashValue != null) {
                if (hashValue.key != null) {
                    result.append(" -> ");
                    result.append(hashValue.key);
                }
                hashValue = hashValue.link;
            }
            result.append("\n");
        }
        return result.toString();
    }

    private int numOfString(String value) {
        int sum = 0;
        for (char ch : value.toCharArray()) {
            sum += (Character.getNumericValue(ch) - defaultNumber);
        }
        return sum;
    }

    // **********
    // Value Class

    class HashValue {
        String key = null;
        HashValue link = null;
    }

    // **********
    // Test Program

    public static final String[] testInput = {"A", "FOOL", "AND", "HIS", "MONEY", "ARE", "SOON", "PARTED"};

    public static void main(String[] args) {
        Hashing hashing = new Hashing(13);
        for (String input : testInput) hashing.put(input);

        System.out.println(hashing.status());
    }
}
