del /s *.class
del /s *.jar
javac Driver.java
jar -cvmf0 sim.mf DirectCacheSim.jar *.class
PAUSE