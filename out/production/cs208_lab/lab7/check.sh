javac genData.java
javac elevator.java
javac elevator2.java
cd ..
java lab7.genData data.in
java lab7.elevator < data.in > output_custom.out
java lab7.elevator2 < data.in > output_standard.out

if diff output_custom.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi
