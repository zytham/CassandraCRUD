import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

import com.datastax.driver.core.ResultSet;
/**
 * 
 * @author nikhil(www.devinline.com)
 * 
 */
public class CASClient {
	private static String KEY_SPACE_NAME = "\"SPOrders\"";
	private static String REPLICAION_STRATEGY = "SimpleStrategy";
	private static Integer REPLICATION_FACTOR = 1;

	public static void main(String[] args) throws UnknownHostException {
		System.out.print("******Enter Operation**********");
		System.out.print("\n 1. CREATE_KEYSPACE, " + "\n 2. CREATE_ORDER_TABLE, " + "\n 3. CREATE_NEW_ORDER, "
				+ "\n 4. DELETE_ORDER, " + "\n 5. SELECT_ORDER_BY_ORDER_NUMBER, " + "\n 6. SELECT_ALL_ORDER");
		System.out.print("\n*******************************");
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter your choice: ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			doDBOperation("CREATE_KEYSPACE");
			break;
		case 2:
			doDBOperation("CREATE_ORDER_TABLE");
			break;
		case 3:
			doDBOperation("CREATE_NEW_ORDER");
			break;
		case 4:
			doDBOperation("DELETE_ORDER");
			break;

		case 5:
			doDBOperation("SELECT_ORDER_BY_ORDER_NUMBER");
			break;

		case 6:
			doDBOperation("SELECT_ALL_ORDER");
			break;

		default:
			break;
		}
		sc.close();

	}

	public static void doDBOperation(String value) {
		CassandraDBOperation dbOperation = new CassandraDBOperation();
		DBOperationsEnum operation = DBOperationsEnum.valueOf(value);
		switch (operation) {
		case CREATE_KEYSPACE:
			System.out.println("CREATE_KEYSPACE");
			/* Create Keyspace */
			if (dbOperation.createKeyspace(KEY_SPACE_NAME, REPLICAION_STRATEGY, REPLICATION_FACTOR)) {
				System.out.println("Keyspace " + KEY_SPACE_NAME + " created successfully !");
			}
			break;
		case CREATE_ORDER_TABLE:
			System.out.println("CREATE_ORDER_TABLE");
			/*
			 * Create ORDERS table : KEY_SPACE_NAME is passed in input
			 */
			if (dbOperation.createTable(CQLQueryConstants.CREATE_ORDER_TABLE, KEY_SPACE_NAME)) {
				System.out.println("TABLE CREATE_ORDER created successfully !");
			}
			break;
		case CREATE_NEW_ORDER:
			System.out.println("CREATE_NEW_ORDER");
			String orderNumber = getOrderNum();
			if (dbOperation.createNewOrder(KEY_SPACE_NAME, getOrderNum(), "2018-03-18", getItemNum(), getPrice(),
					"2018-03-23")) {
				System.out.println("New ORDER created successfully with order number " + orderNumber);
			}
			break;

		case DELETE_ORDER:
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEnter Order Number: ");
			String orderNumberDel = sc.next();
			if (dbOperation.deleteOrder(KEY_SPACE_NAME, orderNumberDel)) {
				System.out.println("ORDER " + orderNumberDel + "deleted successfully ");
			}
			sc.close();
			break;

		case SELECT_ORDER_BY_ORDER_NUMBER:
			System.out.println("\nEnter Order Number: ");
			Scanner sc1 = new Scanner(System.in);
			String orderNumSel = sc1.next();
			ResultSet resultSet = dbOperation.getOrderDetailByOrdrNumber(KEY_SPACE_NAME, orderNumSel);
			if (null != resultSet) {
				System.out.println(resultSet.all());
			}
			sc1.close();
			break;

		case SELECT_ALL_ORDER:
			ResultSet resultSet1 = dbOperation.getAllOrders(KEY_SPACE_NAME);
			if (null != resultSet1) {
				System.out.println(resultSet1.all());
			}
			break;

		default:
			System.out.println("No operations selectd !!");
			break;
		}
	}

	private static String getOrderNum() {
		Random rand = new Random();
		int value = rand.nextInt(50);
		return "ORDER" + value;
	}

	private static Double getPrice() {
		Random rand = new Random();
		int value = rand.nextInt(50);
		return value * 0.9;
	}

	private static String getItemNum() {
		Random rand = new Random();
		int value = rand.nextInt(50);
		return "ITEM" + value;
	}
}

enum DBOperationsEnum {
	CREATE_KEYSPACE, CREATE_ORDER_TABLE, CREATE_NEW_ORDER, DELETE_ORDER, SELECT_ORDER_BY_ORDER_NUMBER, SELECT_ALL_ORDER

}
