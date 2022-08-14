package elvis.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
//        config.addResource("hbase-site.xml");
//        config.set("hbase.cluster.distributed", "false");
//        config.set("hbase.tmp.dir", "./tmp");
//        config.set("hbase.unsafe.stream.capability.enforce", "false");
//        config.set("hbase.rootdir", "hdfs://node2.hadoop.com:9866/hbase");
//        config.set("hbase.cluster.distributed", "true");
        config.set("hbase.zookeeper.quorum", "node1.hadoop.com:2181,node2.hadoop.com:2181,node3.hadoop.com:2181");
        Connection conn = ConnectionFactory.createConnection(config);

        // 创建 DDL 对象
        DDL ddl = new DDL();
        // 创建 DML 对象
        DML dml = new DML();


        // 查看所有的表
        ddl.list(conn);

        // 创建表
        ddl.createTable(conn, "test_tab", "info");

        // 添加列簇
        ddl.addColumnFamily(conn, "test_tab", "other");
        ddl.addColumnFamily(conn, "test_tab", "test_tab_family");
        // 删除列簇
        ddl.deleteColumnFamily(conn, "test_tab", "test_tab_family");

        // 查看表描述
        ddl.desc(conn, "test_tab", null);

        // 添加数据
        Put put1 = new Put(Bytes.toBytes("1001"));
        put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("zhangsan"));
        put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("20"));
        put1.addColumn(Bytes.toBytes("info"), Bytes.toBytes("addr"), Bytes.toBytes("beijing"));
        put1.addColumn(Bytes.toBytes("other"), Bytes.toBytes("id"), Bytes.toBytes("1"));
        put1.addColumn(Bytes.toBytes("other"), Bytes.toBytes("email"), Bytes.toBytes("1@1.com"));

        Put put2 = new Put(Bytes.toBytes("1002"));
        put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("lisi"));
        put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("19"));
        put2.addColumn(Bytes.toBytes("info"), Bytes.toBytes("addr"), Bytes.toBytes("shanghai"));
        put2.addColumn(Bytes.toBytes("other"), Bytes.toBytes("id"), Bytes.toBytes("2"));
        put2.addColumn(Bytes.toBytes("other"), Bytes.toBytes("email"), Bytes.toBytes("21@1.com"));

        Put put3 = new Put(Bytes.toBytes("1003"));
        put3.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("wangwu"));
        put3.addColumn(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("18"));
        put3.addColumn(Bytes.toBytes("info"), Bytes.toBytes("addr"), Bytes.toBytes("guangzhou"));
        put3.addColumn(Bytes.toBytes("other"), Bytes.toBytes("id"), Bytes.toBytes("3"));
        put3.addColumn(Bytes.toBytes("other"), Bytes.toBytes("email"), Bytes.toBytes("31@1.com"));

        List<Put> data = new ArrayList<Put>();

        data.add(put1);
        data.add(put2);
        data.add(put3);

        dml.put(conn, "test_tab", data);

        // 查询数据
        dml.get(conn, "test_tab", "1001");
        dml.get(conn, "test_tab", "1002");
        dml.get(conn, "test_tab", "1003");

        // 查看表
        dml.scan(conn, "test_tab");

        // 删除数据
        dml.delete(conn, "test_tab", "1003");

        // 查看表
        dml.scan(conn, "test_tab");

        ddl.list(conn);

        // 删除表
        ddl.deleteTable(conn, "test_tab");

    }
}
