# KeyQuest Game

![Gameplay](Gameplay.gif)

Compile

```
javac -d out -sourcepath src src/main/Main.java
```

Run on Windows

```
java -cp "out;res" main.Main
```

Run on Mac

```
java -cp "out:res" main.Main
```

Save as jar

```
jar cfm KeyQuest.jar manifest.txt -C out . -C res .
```