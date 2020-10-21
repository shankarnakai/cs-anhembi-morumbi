NOW := $(shell date +"%Y%m%d_%H%M%S")
TABLE_DATABASE := chatsac
USER_DATABASE := root
	
#DATABASE

dumpDB : 
	if [ ! -d "./database" ]; then mkdir ./database; fi;
	mysqldump -u ${USER_DATABASE} ${TABLE_DATABASE} > "./database/database-${NOW}.sql"

dropTRIGGER :
	mysql -u ${USER_DATABASE} --skip-column-names ${TABLE_DATABASE} -e 'SHOW TRIGGERS;' | cut -f1 | sed -r 's/(.*)/DROP TRIGGER IF EXISTS \1;/' | mysql -u ${USER_DATABASE} ${TABLE_DATABASE} 

createTRIGGER : 
	ls -m | sed -e 's/, /,/g'

truncateDB : 
	mysql -u ${USER_DATABASE} --skip-column-names ${TABLE_DATABASE} -e 'SET foreign_key_checks = 0;  TRUNCATE TABLE `Departament`; TRUNCATE TABLE `Message`; TRUNCATE TABLE `Organization`; TRUNCATE TABLE `User`;'
