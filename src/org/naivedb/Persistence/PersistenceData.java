package org.naivedb.Persistence;

import org.naivedb.utils.*;
import org.naivedb.Type.Type;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.logging.*;

public class PersistenceData {

    private HashMap<Long, LinkedList> cachedData;
    private long maxRowIndex;
    private ArrayList<Long> rowIndexPool;
    private RandomAccessFile dataFile;
    private int rowSize;
    private ArrayList<Type> types;

    private static Logger logger = MyLogger.getLogger("database");

    /*
        create a new persistence data:
            requires upper layer to handle the file existence
    */
    public PersistenceData(String filename, ArrayList<Type> types, ArrayList<Long> rowIndexPool)
            throws IOException, NDException {
        if (types.size() == 0) throw new NDException("input type length is zero");
        this.dataFile = new RandomAccessFile(filename+".data", "rw");
        this.cachedData = new HashMap<Long, LinkedList>();
        this.types = new ArrayList<>(types);
        this.rowSize = 0;

        for (Type type: types)
            this.rowSize += type.typeSize();
        if (rowIndexPool == null) {
            this.rowIndexPool = new ArrayList<>();
        } else {
            this.rowIndexPool = rowIndexPool;
        }
        this.maxRowIndex = this.dataFile.length() / this.rowSize - 1;
    }

    public void output(BufferedOutputStream output) throws IOException {
        StreamUtils.writeInt(output, this.rowIndexPool.size());
        for (long row: this.rowIndexPool)
            StreamUtils.writeLong(output, row);
    }

    public void close() {
        try {
            write();
            this.dataFile.close();
        } catch (Exception e) {
            logger.warning("Error happened while trying to write back to data file!");
        }
    }

    public ArrayList<Long> getAllRowNum() {
        ArrayList<Long> res = new ArrayList<>();
        res.ensureCapacity((int)this.maxRowIndex+1-this.rowIndexPool.size()+1);
        for (long i = 0; i <= this.maxRowIndex; ++i)
            if (this.rowIndexPool.indexOf(i) < 0)
                res.add(i);
        return res;
    }

    public long add(LinkedList value)
            throws IOException, NDException {
        long newRowIndex = getNewRowIndex();
        updateCachedData(newRowIndex, value);
        return newRowIndex;
    }

    public void update(long rowNum, LinkedList value)
            throws IOException, NDException {
        LinkedList oldValue = get(rowNum);
        if (oldValue.getFirst() != value.getFirst()) {
            throw new NDException("The values in " + rowNum + " must have the same key with new values!");
        }
        updateCachedData(rowNum, value);
    }

    public LinkedList get(long rowNum)
            throws IOException, NDException {
        if (cachedData.containsKey(rowNum)) {
            return cachedData.get(rowNum);
        }
        return getFromFile(rowNum);
    }

    private LinkedList getFromFile(long rowNum)
            throws IOException, NDException {
        if (rowNum < 0 || rowNum > this.maxRowIndex) {
            throw new NDException("Invalid row number!");
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
            pos += NumberUtils.fromBytes(ret, s, pos, this.types.get(i).typeName(), true);
        }
        updateCachedData(rowNum, ret);

        return ret;
    }

    public LinkedList remove(long rowNum)
            throws IOException, NDException {
        LinkedList obj = this.get(rowNum);

        byte[] bytes = new byte[rowSize];
        Arrays.fill(bytes, (byte)0);
        this.dataFile.seek(rowNum * this.rowSize);
        this.dataFile.write(bytes, 0, this.rowSize);
        // add to rowPool for recycling
        this.rowIndexPool.add(rowNum);
        // remove from cachedData
        this.cachedData.remove(rowNum);

        return obj;
    }

    public void write()
            throws IOException, NDException {
        for (Map.Entry<Long, LinkedList> entry: this.cachedData.entrySet()) {
            this.write(entry.getValue(), entry.getKey());
        }
    }

    private void write(LinkedList values, long rowNum)
            throws IOException, NDException {
        byte[] rowData = new byte[this.rowSize];
        Arrays.fill(rowData, (byte)0);
        int pos = 0;
        int n = this.types.size();
        for (int i = 0; i < n; ++i) {
            pos += NumberUtils.toBytes(rowData, pos, values.get(i), this.types.get(i).typeName());
        }

        this.dataFile.seek(rowNum*this.rowSize);
        this.dataFile.write(rowData, 0, this.rowSize);
    }

    private void updateCachedData(long rowNum, LinkedList value)
            throws IOException, NDException {
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
