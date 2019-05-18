package utils;

public class Consts {
    public static int intSize = 16;          // bits of int stored in file
    public static int longSize = 24;         // bits of long stored in file
    public static int stringSize = 128;      // bits of string stored in file
    public static int floatSize = 32;        // bits of float stored in file
    public static int doubleSize = 64;       // bits of double stored in file
    public static int pointSize = 32;        // bits of ptr stored in file
    public static int nodeTypeSize = 1;
    public static int parentSize = pointSize;
    public static int defaultBlockSize = 4096;
    public static int columnNameSize = 64;
    public static int columnTypeSize = 10;
    public static int memoryNodeLimitation = 1024*1024; // bytes of node stored in memory
    public static int memoryDataLimitation = 1024*1024; // bytes of data stored in memory
    public static int databaseNameSize = 32;
    public static int tableNameSize = 32;

    public static int Type2Size(String type)
            throws utilsException {
        int ret;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                break;
            case "Long":
                ret = Consts.longSize;
                break;
            case "String":
                ret = Consts.stringSize;
                break;
            case "Float":
                ret = Consts.floatSize;
                break;
            case "Double":
                ret = Consts.doubleSize;
                break;
            default:
                if (type.startsWith("String")) {
                    if (NumberUtils.isPureInteger(type.substring(6))) {
                        ret = NumberUtils.parseInt(type.substring(6), 0, type.substring(6).length())+1;
                    } else
                    {
                        throw new utilsException("Unknown type '" + type + "'");
                    }
                } else
                {
                    throw new utilsException("Unknown type '" + type + "'");
                }
        }
        return ret;
    }
}
