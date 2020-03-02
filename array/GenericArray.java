package array;

public class GenericArray<T> {
    /**
     * 
     * The default size of the array is 10
     * 
     */
    static final int DEFAULT_LENGTH = 10;

    private T[] data;
    private int size;

    /**
     * The default constructor to initialize the generiz type array.
     */
    public GenericArray(int capacity) {
        this.data = (T[]) new Object[capacity];
        size = 0;
    }

    public GenericArray() {
        this(DEFAULT_LENGTH);
    }

    /**
     * Get the capacity of current array.
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Get the size of current array.
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the current array is empty array.
     * 
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param data the data to set at the position index
     */
    public void set(int index, T value) {
        this.data[index] = value;
    }

    /**
     * @return the data at the position of index
     */
    public T get(int index) {
        return data[index];
    }

    /**
     * @param e input element to check if it exists in the array.
     * 
     * @return true if the array includes the target.
     */
    public boolean contains(T e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                continue;
            }
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param e input element to find where e locates in the array.
     * 
     * @return the position of the index in the array. otherwise, -1
     */
    public int find(T e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 
     * @param index position where to insert the new element
     * @param e     element to insert
     * 
     *              Time Complexity: O(n)
     */

    public void add(int index, T e) {
        checkIndexForAdd(index);
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    public void add(T e) {
        addLast(e);
    }

    /**
     * methods to add the @param e in the beginning.
     * 
     */
    public void addFirst(T e) {
        add(0, e);
    }

    /**
     * methods to add the @param e at the end.
     * 
     */
    public void addLast(T e) {
        add(size, e);
    }

    /**
     * 
     * @param index position where to remove the new element
     * 
     *              Time Complexity: O(n)
     */
    public T remove(int index) {
        checkIndexForRemove(index);

        T removed = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;

        // resize to a smaller array
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return removed;
    }

    /**
     * methods to remove the @param e in the beginning.
     * 
     */
    public T removeFirst() {
        return remove(0);
    }

    /**
     * methods to remove the @param e at the end.
     * 
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     * methods to remove the @param e in the array.
     * 
     */
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * Resize Method
     * 
     * Time Complexity: O(n)
     */
    private void resize(int size) {
        T[] newData = (T[]) new Object[size];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index >= getCapacity()) {
            throw new IllegalArgumentException("Add Failed! Require index >= 0 and index <= size.");
        }
    }

    private void checkIndexForRemove(int index) {
        if (index < 0 || index >= getCapacity()) {
            throw new IllegalArgumentException("Remove Failed! Require index >= 0 and index <= size.");
        }
    }
}