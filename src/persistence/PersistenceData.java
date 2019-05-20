package persistence;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.awt.image.ImageWatched;
import utils.Consts;
import utils.utilsException;
import utils.NumberUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;


public class PersistenceData {

    private HashMap<Long, LinkedList> cachedData;
    private long maxRowIndex;
    private ArrayList<Long> rowIndexPool;
    private RandomAccessFile dataFile;
    private int rowSize;
    private LinkedList<String> types;

    public PersistenceData(String filename, LinkedList<String> types)
            throws IOException, utilsException {
        this.dataFile = new RandomAccessFile(filename+".data", "rw");
        this.cachedData = new HashMap<Long, LinkedList>();

        this.types = new LinkedList<>(types);
        this.rowSize = 0;
        for (String type: types)
            this.rowSize += utils.Consts.Type2Size(type);
        this.rowIndexPool = new ArrayList<Long>();
        this.maxRowIndex = this.dataFile.length()/this.rowSize;
    }

    public void close() {
        try {
            write();
            dataFile.close();
        } catch (Exception e) {
            System.err.println("Error happened while trying to write back to data file!");
        }
    }

    public long add(LinkedList value)
            throws IOException, utilsException {
        long newRowIndex = getNewRowIndex();
        updateCachedData(newRowIndex, value);
        return newRowIndex;
    }

    public void update(long rowNum, LinkedList value)
            throws IOException, utilsException, PersistenceException {
        LinkedList oldValue = get(rowNum);
        if (oldValue.getFirst() != value.getFirst()) {
            throw new PersistenceException("The values in " + rowNum + " must have the same key with new values!");
        }
        updateCachedData(rowNum, value);
    }

    public LinkedList get(long rowNum)
            throws IOException, utilsException, PersistenceException {
        if (cachedData.containsKey(rowNum)) {
            return cachedData.get(rowNum);
        }
        return getFromFile(rowNum);
    }

    private LinkedList getFromFile(long rowNum)
            throws IOException, utilsException, PersistenceException {
        if (rowNum < 0 || rowNum > this.maxRowIndex) {
            throw new PersistenceException("Invalid row number!");
        }

        if (this.cachedData.containsKey(rowNum)) {
            return this.cachedData.get(rowNum);
        }
        LinkedList ret = new LinkedList();
        long offset = this.rowSize * rowNum;
        byte[] row = new byte[this.rowSize];

        this.dataFile.seek(offset);
        this.dataFile.read(row, 0, this.rowSize);

        String s = new String(row);

        int n = this.types.size();
        int pos = 0;
        for (int i = 0; i < n; ++i) {
            pos += NumberUtils.fromBytes(ret, s, pos, types.get(i), true);
        }
        updateCachedData(rowNum, ret);

        return ret;
    }

    public void remove(long rowNum)
            throws IOException {
        byte[] bytes = new byte[rowSize];
        Arrays.fill(bytes, (byte)0);
        this.dataFile.seek(rowNum * this.rowSize);
        this.dataFile.write(bytes, 0, this.rowSize);
        // add to rowPool for recycling
        this.rowIndexPool.add(rowNum);
        // remove from cachedData
        this.cachedData.remove(rowNum);
    }

    public void write()
            throws IOException, utilsException {
        for (Map.Entry<Long, LinkedList> entry: this.cachedData.entrySet()) {
            this.write(entry.getValue(), entry.getKey());
        }
    }

    private void write(LinkedList values, long rowNum)
            throws IOException, utilsException {
        byte[] rowData = new byte[this.rowSize];
        Arrays.fill(rowData, (byte)0);
        int pos = 0;
        int n = this.types.size();
        for (int i = 0; i < n; ++i) {
            pos += NumberUtils.toBytes(rowData, pos, values.get(i), types.get(i));
        }

        this.dataFile.seek(rowNum*this.rowSize);
        this.dataFile.write(rowData, 0, this.rowSize);
    }

    private void updateCachedData(long rowNum, LinkedList value)
            throws IOException, utilsException {
        if (!this.cachedData.containsKey(rowNum)) {
            // remove some data cache
            while (this.rowSize * this.cachedData.size() > Consts.memoryDataLimitation) {
                for (Map.Entry<Long, LinkedList> entry : this.cachedData.entrySet()) {
                    write(entry.getValue(), entry.getKey());
                    this.cachedData.remove(entry.getKey());
                    break;
                }
            }
        }
        this.cachedData.put(rowNum, value);
    }

    private long getNewRowIndex() {
        if (this.rowIndexPool.isEmpty()) {
            this.maxRowIndex += 1;
            return this.maxRowIndex;
        } else
        {
            return this.rowIndexPool.remove(0);
        }
    }
}
