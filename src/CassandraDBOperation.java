import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

/**
 * 
 * @author nikhil(www.devinline.com)
 * 
 */
public class CassandraDBOperation {
	public Boolean createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(keyspaceName)
				.append(" WITH replication = {").append("'class':'").append(replicationStrategy)
				.append("','replication_factor':").append(replicationFactor).append("};");
		String query = sb.toString();
		CASConnectionFactory.getInstance().getSession().execute(query);
		CASConnectionFactory.getInstance().getSession().close();
		return Boolean.TRUE;
	}

	public Boolean createTable(String query, String keyspace) {
		Session session = CASConnectionFactory.getInstance().getSession(keyspace);
		session.execute(query);
		session.close();
		return Boolean.TRUE;
	}

	public Boolean createNewOrder(String keyspace, String orderNumber, String order_date, String item_id,
			Double item_price, String delivery_date) {
		Session session = CASConnectionFactory.getInstance().getSession(keyspace);
		PreparedStatement prepared = session.prepare(CQLQueryConstants.CREATE_NEW_ORDER);
		BoundStatement boundStmt = prepared.bind(orderNumber, order_date, item_id, item_price, delivery_date);
		session.execute(boundStmt);
		session.close();
		return Boolean.TRUE;
	}

	public Boolean deleteOrder(String keyspace, String orderNumber) {
		Session session = CASConnectionFactory.getInstance().getSession(keyspace);
		PreparedStatement prepared = session.prepare(CQLQueryConstants.DELETE_ORDER);
		BoundStatement boundStmt = prepared.bind(orderNumber);
		session.execute(boundStmt);
		CASConnectionFactory.getInstance().close();
		return Boolean.TRUE;
	}

	public ResultSet getOrderDetailByOrdrNumber(String keyspace, String orderNumber) {
		Session session = CASConnectionFactory.getInstance().getSession(keyspace);
		PreparedStatement prepared = session.prepare(CQLQueryConstants.SELECT_ORDER);
		BoundStatement boundStmt = prepared.bind(orderNumber);
		ResultSet result = session.execute(boundStmt);
		CASConnectionFactory.getInstance().close();
		return result;
	}

	public ResultSet getAllOrders(String keyspace) {
		Session session = CASConnectionFactory.getInstance().getSession(keyspace);
		ResultSet resultSet = session.execute(CQLQueryConstants.SELECT_ORDER_ALL);
		CASConnectionFactory.getInstance().close();
		return resultSet;
	}

}
