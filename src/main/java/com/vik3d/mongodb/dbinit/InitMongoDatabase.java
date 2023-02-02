package com.vik3d.mongodb.dbinit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vik3d.mongodb.dbinit.exceptions.ConvertBookToMapException;
import com.vik3d.mongodb.dbinit.exceptions.ConvertJsonToBookException;
import com.vik3d.mongodb.dbinit.model.Book;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialize the MongoDB Atlas AWS Cloud database with data (from JSON file).
 *
 * @author viki3d
 */
public class InitMongoDatabase {

  private static Logger logger = LoggerFactory.getLogger(InitMongoDatabase.class);

  private static final String CONNECTION_STRING_TEMPLATE = "mongodb+srv://username:password@cluster" 
      + "/?retryWrites=true&w=majority";
  private static final String DATABASE_NAME = "vibookstore";
  private static final String COLLECTION_NAME = "books";
  private static final String RES_PATH = "./src/main/resources/";
  private static final String BOOKS_JSON_FILE = RES_PATH + "books.json";
  private static final String CREDENTIALS_PROPERTIES = RES_PATH + "credentials.properties";
  private static final String PROPERTIES_KEY_CLUSTER = "cluster";
  private static final String PROPERTIES_KEY_USERNAME = "username";
  private static final String PROPERTIES_KEY_PASSWORD = "password";

  /**
   * Main entrance point.
   *
   * @param args Command line args
   */
  public static void main(String[] args) {
    // Set the actual 'cluster', 'username' and 'password' in the connection string template:
    String connectionString = CONNECTION_STRING_TEMPLATE;
    try (InputStream input = Files.newInputStream(Paths.get(CREDENTIALS_PROPERTIES))) {
      // Read actual values from the .properties file
      Properties properties = new Properties();
      properties.load(input);
      String cluster = properties.getProperty(PROPERTIES_KEY_CLUSTER);
      String username = properties.getProperty(PROPERTIES_KEY_USERNAME);
      String password = properties.getProperty(PROPERTIES_KEY_PASSWORD);
      connectionString = connectionString.replace(PROPERTIES_KEY_CLUSTER, cluster);
      connectionString = connectionString.replace(PROPERTIES_KEY_USERNAME, username);
      connectionString = connectionString.replace(PROPERTIES_KEY_PASSWORD, password);
      logger.debug("connectionString = {}", connectionString);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    // Prepare the MongoDB ConnectionString, used for the connection:
    ConnectionString mongoConnectionString = new ConnectionString(connectionString);
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(mongoConnectionString)
        .serverApi(ServerApi.builder()
          .version(ServerApiVersion.V1)
          .build())
        .build();

    // Connect to MongoDB Atlas Cloud and insert the data into database:
    try (MongoClient client = MongoClients.create(settings)) {
      // Set database and collection:
      MongoDatabase database = client.getDatabase(DATABASE_NAME);
      MongoCollection<org.bson.Document> collection = database.getCollection(COLLECTION_NAME);

      //  Drop previously existing collection(table)
      collection.drop();

      // JSON to books
      List<Book> books = readBooksFromJsonFile();

      // books to (mongo)documents
      List<Document> bookDocuments = convertBooksToDocuments(books);

      // Insert into database
      collection.insertMany(bookDocuments);
    }

  }
  
  private static List<Book> readBooksFromJsonFile() {
    try {
      // Create object mapper instance
      ObjectMapper mapper = new ObjectMapper();

      // Convert JSON array to list of books
      List<Book> books = Arrays
          .asList(mapper.readValue(Paths.get(BOOKS_JSON_FILE).toFile(), Book[].class));

      // Print books
      books.forEach(book -> logger.debug(book.toJsonString()));
      
      return books;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new ConvertJsonToBookException(ex.getMessage());
    }
  }

  private static List<Document> convertBooksToDocuments(List<Book> books) {
    ObjectMapper mapper = new ObjectMapper();
    return 
      // List<Book>  
      books.stream()
        // Book --> Map<String, Object>
        .map(book -> convertBookToMap(mapper, book))
        // Map<String, Object> --> Document
        .map(Document::new)
        // List<Document>
        .collect(Collectors.toList());
  }
  
  private static Map<String, Object> convertBookToMap(ObjectMapper mapper, Book b) {
    try {
      return mapper.readValue(b.toJsonString(), new TypeReference<Map<String, Object>>(){});
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new ConvertBookToMapException(e.getMessage());
    }
  }
  
}
