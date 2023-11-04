PKG_NAME:=app
SRC_DIR:=./src

all:
	javac -source 1.8 -target 1.8 -d ./ $(SRC_DIR)/*.java
	jar cvfm $(PKG_NAME).jar manifest.txt $(PKG_NAME)

run:
	java -jar $(PKG_NAME).jar

clean:
	rm -rf ./$(PKG_NAME)
	rm ./$(PKG_NAME).jar
