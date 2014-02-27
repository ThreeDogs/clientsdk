chmod 777 .

chmod 777 ./dex2jar-0.0.9.15/.

rm -r ./temp
mkdir ./temp

chmod 777 ./temp

echo target file is \'$1\'

sudo ./dex2jar-0.0.9.15/d2j-dex2jar.sh -f -o ./temp/jarfile.jar $1
mkdir ./temp/newclz

chmod 777 ./temp/newclz/.

tar xvf ./temp/jarfile.jar -C ./temp/newclz

#rm ./temp/jarfile.jar

echo $PWD

java -classpath ./asm-all-4.2.jar:./asm.jar test.org.cerberus.jarasm.JarAsmTest $PWD/temp/newclz

jar cvf ./temp/newjar.jar ./temp/newclz/.

./dex2jar-0.0.9.15/d2j-asm-verify.sh ./temp/newjar.jar

./dex2jar-0.0.9.15/d2j-jar2dex.sh -f -o ./classes.dex ./temp/newjar.jar

cp $1 ./temp/$1_.apk 

zip -u ./temp/$1_.apk ./classes.dex

./dex2jar-0.0.9.15/d2j-apk-sign.sh -f -o $2 ./temp/$1_.apk