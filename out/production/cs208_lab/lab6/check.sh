javac genData.java
javac pA.java
javac pro1.java
cd ..
java lab6.genData data.in
java lab6.pro1 < data.in > output_custom.out
java lab6.pA < data.in > output_standard.out

if diff output_custom.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi