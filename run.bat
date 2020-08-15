@echo off
rem This batch script selects a driver program and executes it.

if %1%==markovchains java -cp ".;build\classes" edu.thinkbox.math.matrix.applications.MarkovChains
