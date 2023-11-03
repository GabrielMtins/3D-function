PKG_NAME:=app
SRC_DIR:=./src

all:
	javac -d ./ $(SRC_DIR)/*.java
	jar cvfm $(PKG_NAME).jar manifest.txt $(PKG_NAME)

run:
	java -jar $(PKG_NAME).jar

clean:
	rm -rf ./$(PKG_NAME)
	rm ./$(PKG_NAME).jar
