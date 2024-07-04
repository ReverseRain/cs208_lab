javac genData.java
javac labB.java
javac projectSelect.java
cd ..
java lab14/genData data.in
java lab14/projectSelect < data.in > test2.out
java lab14/labB < data.in > output_standard.out

if diff test2.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi

