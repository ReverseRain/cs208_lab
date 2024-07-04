javac genData2.java
javac countSub.java
javac bruteForce.java
cd ..
java lab11/genData2 data2.in
java lab11/countSub < data2.in > test2.out
java lab11/bruteForce < data2.in > output_standard.out

if diff test2.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi

