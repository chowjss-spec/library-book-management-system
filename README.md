# library-book-management-system
- Install project dependencies.
- Setup db.
- Install docker and mysqlsh.
- Pull mysql image. 
docker pull mysql:8
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 -d mysql:8
docker exec -it mysql-container mysql -u root -p


- Drag and drop images (requires your Dropbox account be linked)