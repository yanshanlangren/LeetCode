package elvis.hbase;

import org.apache.hadoop.hbase.HRegionLocation;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ClusterConnection;
import org.apache.hadoop.hbase.client.RegionInfo;
import org.apache.hadoop.hbase.ipc.RpcControllerFactory; //导入方法依赖的package包/类

public class MultiServerCallable extends HRegionLocation {
    public MultiServerCallable(RegionInfo regionInfo, ServerName serverName) {
        super(regionInfo, serverName);
    }

//    private final MultiAction<R> multiAction;
//    private final HRegionLocation location;
//    private final Object cellBlock;
//
//    MultiServerCallable(final ClusterConnection connection, final TableName tableName, final ServerName location, RpcControllerFactory rpcFactory, final MultiAction<R> multi) {
//        super(connection, tableName, null);
//        this.multiAction = multi;
//        // RegionServerCallable has HRegionLocation field, but this is a multi-region request.
//        // Using region info from parent HRegionLocation would be a mistake for this class; so
//        // we will store the server here, and throw if someone tries to obtain location/regioninfo.
//        this.location = new HRegionLocation(null, location);
//        this.cellBlock = isCellBlock();
//        controller = rpcFactory.newController();
//    }
}
