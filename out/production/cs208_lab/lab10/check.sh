javac genData.java
javac bruteForce.java
javac pra1.java
cd ..
java lab10/genData data.in
java lab10/bruteForce < data2.in > output_custom.out
java lab10/pra1 < data2.in > output_standard.out

if diff output_custom.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi