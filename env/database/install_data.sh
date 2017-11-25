# Import file sql
DIR=`pwd`

mysql --user='root' --password='root' needy < 'schema.sql'
mysql --user='root' --password='root' needy < 'import.sql'
mysql --user='root' --password='root' needy < 'functions.sql'

cd $DIR/demo
mysql --user='root' --password='root' needy < 'import_categories.sql'
mysql --user='root' --password='root' needy < 'import_attributes.sql'
mysql --user='root' --password='root' needy < 'import_companies.sql'
mysql --user='root' --password='root' needy < 'import_users.sql'
mysql --user='root' --password='root' needy < 'import_stores.sql'
mysql --user='root' --password='root' needy < 'import_companystaffs.sql'
mysql --user='root' --password='root' needy < 'import_products.sql'

cd $DIR