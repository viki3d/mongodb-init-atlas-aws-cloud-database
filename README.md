# mongodb-init-altas-aws-cloud-database
MongoDB, Atlas-on-AWS-Cloud, Java, Maven, Jackson, Log4j2

![10](/10-mongodb-logo.png?v=1 "mongodb logo")  

### Overview  
c

### Setting up MongoDB Atlas Cloud  
- create new project  
- create new Cluster: AWS (ATLAS/DEPLOYMENT/Database)  
![01](/01-select-cloud-provider.png?v=1 "select cloud provider")
- create custom roles: (ATLAS/SECURITY/DatabaseAccess)  
![02](/02-adding-custom-db-role.png?v=1 "adding custom db role")
- create database user: for ViBookstore (ATLAS/SECURITY/DatabaseAccess)  
![03](/03-apply-cutom-role-to-user.png?v=1 "apply cutom role to user")

### Run the Java project to initialize the MongoDB database  
- transfer credentials into Java project .properties file  
- run the app  
```
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":11,"title":"Final Diagnosis","year":1959,"tags":["medicine"],"author":{"firstname":"Arthur","lastname":"Hailey","country":"GBP"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":13,"title":"Airport","year":1968,"tags":["airplanes"],"author":{"firstname":"Arthur","lastname":"Hailey","country":"GBP"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":14,"title":"Wheels","year":1971,"tags":["cars","business"],"author":{"firstname":"Arthur","lastname":"Hailey","country":"GBP"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":21,"title":"A Farewell to Arms","year":1929,"tags":["war","love"],"author":{"firstname":"Ernest","lastname":"Hemingway","country":"USA"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":22,"title":"The Old Man and the Sea","year":1952,"tags":["ocean"],"author":{"firstname":"Ernest","lastname":"Hemingway","country":"USA"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":31,"title":"Dune","year":1965,"tags":["space","planet","future","love","war"],"author":{"firstname":"Frank","lastname":"Herbert","country":"USA"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":33,"title":"Children of Dune","year":1976,"tags":["space","planet","future"],"author":{"firstname":"Frank","lastname":"Herbert","country":"USA"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":41,"title":"Dubliners","year":1914,"tags":["war"],"author":{"firstname":"James","lastname":"Joyce","country":"IRL"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":42,"title":"Ulysses","year":1922,"tags":null,"author":{"firstname":"James","lastname":"Joyce","country":"IRL"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":51,"title":"The Andromeda Strain","year":1969,"tags":["space","infection","future","aliens"],"author":{"firstname":"Michael","lastname":"Crichton","country":"USA"}}
[main] DEBUG com.vik3d.mongodb.dbinit.InitMongoDatabase - {"id":54,"title":"Jurassicpark","year":1990,"tags":["dinosaurs"],"author":{"firstname":"Michael","lastname":"Crichton","country":"USA"}}
```
