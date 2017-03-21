#!/bin/bash
/usr/lib/jvm/java-6-openjdk-i386/bin/javac -classpath ../dist/production_ia32-linux/jksvm.jar Multithread.java 
/usr/lib/jvm/java-6-openjdk-i386/bin/javah Multithread
/usr/lib/jvm/java-6-openjdk-i386/bin/javac -classpath ../dist/production_ia32-linux/jksvm.jar Benchmark.java
