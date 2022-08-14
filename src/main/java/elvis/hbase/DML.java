package elvis.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class DML {
    /**
     * 添加数据
     *
     * @param conn
     * @param tabName
     * @param data
     * @throws IOException
     */
    public void put(Connection conn, String tabName, List data) throws IOException {
        // 获取表名对象
        TableName name = TableName.valueOf(tabName);
        // 获取表对象
        Table table = conn.getTable(name);

        table.put(data);
        System.out.println("添加数据成功!");
    }

    /**
     * 获取数据
     *
     * @param conn
     * @param tabName
     * @param rk
     * @throws IOException
     */
    public void get(Connection conn, String tabName, String rk) throws IOException {
        // 获取表名对象
        TableName name = TableName.valueOf(tabName);
        // 获取表对象
        Table table = conn.getTable(name);
        Get get = new Get(Bytes.toBytes(rk));
        // 过滤到行的列族的具体列
        get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));
        // 查询get
        Result result = table.get(get);
        System.out.println("查询的数据");
        System.out.println("rowkey" + "\t" + "columnFamliy" + "\t" + "column" + "\t" + "value");

        for (Cell cell : result.listCells()) {
            System.out.println(Bytes.toString(CellUtil.cloneRow(cell)) + "\t" + Bytes.toString(CellUtil.cloneFamily(cell)) + "\t\t" + Bytes.toString(CellUtil.cloneQualifier(cell)) + "\t" + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    /**
     * 查看表
     *
     * @param conn
     * @param tabName
     * @throws IOException
     */
    public void scan(Connection conn, String tabName) throws IOException {
        // 获取表名对象
        TableName name = TableName.valueOf(tabName);
        // 获取表对象
        Table table = conn.getTable(name);

        Scan scan = new Scan();
        //按列族扫描
//		scan.addFamily(Bytes.toBytes("info"));
        //按具体列扫描
        scan.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name")).withStartRow(Bytes.toBytes("1001")).withStopRow(Bytes.toBytes("1002"), true);

        // 查询scan
        ResultScanner scanner = table.getScanner(scan);
        System.out.printf("查看%s\n", table);
        for (Result result : scanner) {

            System.out.println("rowkey" + "\t" + "columnFamliy" + "\t" + "column" + "\t" + "value");
            for (Cell cell : result.listCells()) {
                System.out.println(Bytes.toString(CellUtil.cloneRow(cell)) + "\t" + Bytes.toString(CellUtil.cloneFamily(cell)) + "\t\t" + Bytes.toString(CellUtil.cloneQualifier(cell)) + "\t" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }

    }

    /**
     * 删除
     *
     * @param conn
     * @param tabName
     * @param rk
     * @throws IOException
     */
    public void delete(Connection conn, String tabName, String rk) throws IOException {
        // 获取表名对象
        TableName name = TableName.valueOf(tabName);
        // 获取表对象
        Table table = conn.getTable(name);
        Delete delete = new Delete(Bytes.toBytes(rk));
        // 删除指定行的列的数据，但只能删除最新版本的数据 Delete
        // delete.addColumn(Bytes.toBytes("other"), Bytes.toBytes("id"));
        // 删除指定行的列的数据的所有版本 DeleteColumn
        // delete.addColumns(Bytes.toBytes("other"), Bytes.toBytes("id"));
        // 删除整个列族 DeleteFamily
        // delete.addFamily(Bytes.toBytes("other"));
        //删除数据 逐个列族DeleteFamily

        table.delete(delete);
        System.out.println("删除数据成功");
    }
}
