package learningtest.com.mongodb;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoClientTest {

	@Test
	public void test() throws UnknownHostException {
		// MongoClient mongoClient = new MongoClient();
		// MongoClient mongoClient = new MongoClient("localhost");
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		// MongoClient mongoClient = new MongoClient(Arrays.asList(
		// new ServerAddress("localhost", 27016), new ServerAddress(
		// "localhost", 27017), new ServerAddress("localhost",
		// 27018)));
		DB db = mongoClient.getDB("mydb");

		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println(s);
		}

		DBCollection coll = db.getCollection("testCollection");
		 BasicDBObject doc = new BasicDBObject("name", "MongoDB")
		 .append("type", "database").append("count", 1)
		 .append("info", new BasicDBObject("x", 203).append("y", 102));
		 coll.insert(doc);

		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);

		for (int i = 0; i < 100; i++) {
			coll.insert(new BasicDBObject("i", i));
		}

		System.out.println(coll.getCount());

		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		BasicDBObject query = new BasicDBObject("i", 71);
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		query = new BasicDBObject("i", new BasicDBObject("$gt", 50));
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		query = new BasicDBObject("i", new BasicDBObject("$gt", 20).append(
				"$lte", 30));
		cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		coll.createIndex(new BasicDBObject("i", 1));

		List<DBObject> list = coll.getIndexInfo();
		for (DBObject o : list) {
			System.out.println(o);
		}

		for (String s : mongoClient.getDatabaseNames()) {
			System.out.println(s);
		}

		mongoClient.dropDatabase("mydb");
	}

}
