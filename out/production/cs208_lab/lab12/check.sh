javac genData.java
javac labB.java
javac xorSum.java
cd ..
java lab12/genData data.in
java lab12/xorSum < data.in > test2.out
java lab12/labB < data.in > output_standard.out

if diff test2.out output_standard.out -b; then
    echo "Accepted"
else
    echo "Wrong Answer"
fi

