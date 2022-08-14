package elvis.hbase;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class DDL {
    /**
     * 获取表列表
     *
     * @param conn
     * @throws IOException
     */
    public void list(Connection conn) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 获取表的列表
        TableName[] listTableNames = admin.listTableNames();
        for (TableName tableName : listTableNames) {
            System.out.println(tableName);
        }
        System.out.println("已列出所有表");
    }

    /**
     * 创建表
     *
     * @param conn
     * @param tabName
     * @param cf
     * @throws IOException
     */
    public void createTable(Connection conn, String tabName, String cf) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);
        // 构建表描述器
        TableDescriptorBuilder tabDesBuilder = TableDescriptorBuilder.newBuilder(name);
        // 构建列族描述器
        ColumnFamilyDescriptorBuilder cfDesBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));

        // 构建列族描述
        ColumnFamilyDescriptor family = cfDesBuilder.build();
        // 将列族描述添加到表描述器
        tabDesBuilder.setColumnFamily(family);
        // 构建表描述
        TableDescriptor tabDes = tabDesBuilder.build();
        // 创建表
        admin.createTable(tabDes);
        System.out.printf("已成功创建表%s\n", tabName);
    }

    /**
     * 添加列簇
     *
     * @param conn
     * @param tabName
     * @param cf
     * @throws IOException
     */
    public void addColumnFamily(Connection conn, String tabName, String cf) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        // 构建列族描述器
        ColumnFamilyDescriptorBuilder cfDesBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
        // 最大版本为3
        cfDesBuilder.setMaxVersions(3);
        // 构建列族描述
        ColumnFamilyDescriptor family = cfDesBuilder.build();

        // 增加列族
        admin.addColumnFamily(name, family);
        System.out.printf("已成功在%s添加列族%s\n", tabName, cf);
    }

    /**
     * 修改列簇
     *
     * @param conn
     * @param tabName
     * @param cf
     * @throws IOException
     */
    public void modifyColumnFamily(Connection conn, String tabName, String cf) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        // 构建列族描述器
        ColumnFamilyDescriptorBuilder cfDesBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
        // 设置列族属性
        cfDesBuilder.setMaxVersions(3);

        // 构建列族描述
        ColumnFamilyDescriptor family = cfDesBuilder.build();

        // 修改列族
        admin.modifyColumnFamily(name, family);
        System.out.printf("已成功修改%s的列簇\n", tabName);
    }

    public void deleteColumnFamily(Connection conn, String tabName, String cf) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        // 删除列族
        admin.deleteColumnFamily(name, Bytes.toBytes(cf));
        System.out.printf("已删除表%s列簇%s\n", tabName, cf);
    }

    public void desc(Connection conn, String tabName, String cf) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        // 获取表描述
        TableDescriptor tabDes = admin.getDescriptor(name);
        System.out.printf("表%s的描述\n", tabName);
        System.out.println(tabDes);
        // 获取具体列族描述
        if (cf != null) {
            ColumnFamilyDescriptor columnFamily = tabDes.getColumnFamily(Bytes.toBytes(cf));
            System.out.println(columnFamily);
        }
    }

    public void truncateTable(Connection conn, String tabName) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        //清空表之前要先禁用
        admin.disableTable(name);
        // 清空表
        admin.truncateTable(name, true);
        System.out.println("表已清空");
        admin.enableTable(name);

    }

    public void deleteTable(Connection conn, String tabName) throws IOException {
        // 获取Admin管理对象
        Admin admin = conn.getAdmin();
        // 构建表名对象
        TableName name = TableName.valueOf(tabName);

        if (!admin.tableExists(name)) {
            System.out.println("要删除的表不存在");
            System.exit(0);
        }

        //删除表之前也要先禁用
        admin.disableTable(name);
        // 删除表
        admin.deleteTable(name);
        System.out.println("删除表成功");

    }

}
