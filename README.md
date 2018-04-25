# CassandraCRUD
CRUD operation in Apache Cassandra Using Java Client 

Using Java client perform following Operation

http://www.devinline.com/2018/04/apache-cassandra-crud-operation-with-java-client.html

===================================

1. Create Keyspace
2. Create Table (Column Family)
3. Insert rows form Column Family
4. Delete row from Column Family
5. Select speific record 
6. Select all records

==================================

Run CASClient.java 

****** Enter Operation **********
 1. CREATE_KEYSPACE, 
 2. CREATE_ORDER_TABLE, 
 3. CREATE_NEW_ORDER, 
 4. DELETE_ORDER, 
 5. SELECT_ORDER_BY_ORDER_NUMBER, 
 6. SELECT_ALL_ORDER
*******************************

Enter your choice: 

1

CREATE_KEYSPACE

Connected to cluster: Test Cluster

Datacenter: datacenter1; Host: localhost/127.0.0.1; Rack: rack1

Keyspace "SPOrders" created successfully !

****** Enter Operation **********
 1. CREATE_KEYSPACE, 
 2. CREATE_ORDER_TABLE, 
 3. CREATE_NEW_ORDER, 
 4. DELETE_ORDER, 
 5. SELECT_ORDER_BY_ORDER_NUMBER, 
 6. SELECT_ALL_ORDER
*******************************

Enter your choice: 

3

CREATE_NEW_ORDER

Connected to cluster: Test Cluster

Datacenter: datacenter1; Host: localhost/127.0.0.1; Rack: rack1

New ORDER created successfully with order number ORDER44

-------------------------------------------------
Verify using cqlsh clinet: 

cqlsh:SPOrders> select * from "ORDERS";

 order_number | delivery_date | item_id | item_price | order_date

--------------+---------------+---------+------------+------------

      ORDER44 |    2018-03-23 |  ITEM34 |       12.6 | 2018-03-18

      ORDER30 |    2018-03-23 |  ITEM20 |       14.4 | 2018-03-18
-----------------------------------

******  Enter Operation  **********
 1. CREATE_KEYSPACE, 
 2. CREATE_ORDER_TABLE, 
 3. CREATE_NEW_ORDER, 
 4. DELETE_ORDER, 
 5. SELECT_ORDER_BY_ORDER_NUMBER, 
 6. SELECT_ALL_ORDER
*******************************

Enter your choice: 

5

Enter Order Number: 

ORDER30

Connected to cluster: Test Cluster

Datacenter: datacenter1; Host: localhost/127.0.0.1; Rack: rack1

[Row[ORDER30, 2018-03-23, ITEM20, 14.4, 2018-03-18]]
