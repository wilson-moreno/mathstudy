@echo off
rem This batch script selects a driver program and executes it.
set cpath

if %1%==markovchains java -cp ".;build\classes" edu.thinkbox.math.matrix.applications.MarkovChains
if %1%==lineartransformations java -classpath ".";"build\classes";"C:/Program Files/Java/openjfx-14.0.2.1_windows-x64_bin-sdk/javafx-sdk-14.0.2.1/lib/*.jar" edu.thinkbox.math.matrix.applications.LinearTransformations
