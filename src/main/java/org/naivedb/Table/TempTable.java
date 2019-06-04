package org.naivedb.Table;

import java.util.*;
import java.util.logging.*;
import java.io.*;

import org.naivedb.Statement.Conditions;
import org.naivedb.Type.Type;
import org.naivedb.Persistence.PersistenceData;
import org.naivedb.utils.NDException;
import org.naivedb.utils.StreamUtils;
import org.naivedb.utils.MyLogger;
import java.text.SimpleDateFormat;
import javafx.util.Pair;

/**
 * This class provides the temp table
 * usage: create a table -> new Table()
 *        
 */
public class TempTable{
    private String fileName;
    private ArrayList<Type> colTypes;
    private ArrayList<String> colNames;

    private static Logger logger = MyLogger.getLogger("temp_table");
    private PersistenceData persistence;

// ------------------------------------ Interfaces ------------------------------------
    /**
     * create a new temp table object
     * parameter: cols definition
     */
    public TempTable(ArrayList<Pair<String, Type>> cols) throws IOException, NDException {
        // generate a none taken name
        this.fileName = generateFileName();
        while (new File(this.fileName + ".data").exists())
            this.fileName = generateFileName();
        
        this.colTypes = new ArrayList<Type>();
        this.colNames = new ArrayList<String>();

        HashSet<String> names = new HashSet<String>();
        for (Pair<String, Type> pair : cols) {
            this.colNames.add(pair.getKey());
            names.add(pair.getKey());
            this.colTypes.add(pair.getValue());
        }
        if (cols.size() != names.size()) throw new NDException("input names have duplicate.");

        // create file
        File dataFile = new File(this.fileName + ".data");
        dataFile.createNewFile();

        this.persistence = new PersistenceData(this.fileName, this.colTypes, null);
    }

    /**
     * close a temp table, delete file
     */
    public void close() throws IOException, NDException {
        this.persistence.close();
        File f = new File(this.fileName + ".data");
        if (!f.delete()) throw new NDException("temp table delete failed");
    }

    /**
     * insert a row
     * param: a linked list of given values
     * return: the new row index
     */
    public long insert(LinkedList values) throws IOException, NDException {
        if (values.size() != this.colTypes.size()) throw new NDException("row value number wrong");

        int i = 0;
        for (Object val: values) {
            // otherwise check
            if (val == null) i++;
            else if (this.colTypes.get(i).check(val)) i++;
            else throw new NDException("row values type check error!");
        }

        // type check pass
        long rowNum = this.persistence.add(values);
        return rowNum;
    }

    /**
     * search
     * param: conditions
     * return: result of rows
     */
    public ArrayList<Long> search(Conditions cond) throws IOException, NDException {
        if (cond == null) return getAllRows();
        ArrayList<Long> res = new ArrayList<>();
        ArrayList<Long> allRow = this.persistence.getAllRowNum();
        for (long row: allRow) {
            if (cond.satisfied(new LinkedList<String>(this.colNames),
                                new LinkedList<Type>(this.colTypes),
                                this.persistence.get(row)))
                res.add(row);
        }
        return res;
    }

    /**
     * delete
     * param: row number
     * return: no return value
     */
    public void delete(long row) throws IOException, NDException {
        this.persistence.remove(row);
    }

    /**
     * get all row numbers
     * param: none
     * return: an array list of row numbers
     */
    public ArrayList<Long> getAllRows() {
        return this.persistence.getAllRowNum();
    }

    /**
     * get single row number data
     * param: row number
     * return: an linked list of row data in line "row"
     */
    public LinkedList getSingleRowData(long row) throws NDException, IOException {
        return this.persistence.get(row);
    }

    public ArrayList<String> getColNames() {
        return colNames;
    }

    public ArrayList<Type> getColTypes() {
        return colTypes;
    }

    // ----------------------------- Private methods ---------------------------------------
    private static String generateFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        Random rand = new Random();
        return "tmp_" + format.format(now) + Integer.toString(rand.nextInt(9999) + 1);
    }
}