#!/bin/bash
cc  -g -Wall -Werror -Wextra -Wno-unused-parameter -I. -I/home/ahn573/Research/libpfm-4.8.0/perf_examples/../include \
 -I /usr/lib/jvm/java-6-openjdk-i386/include/ \
 -DCONFIG_PFMLIB_DEBUG -DCONFIG_PFMLIB_OS_LINUX -I. -D_GNU_SOURCE -pthread -c Invoked.c
cc  -g -Wall -Werror -Wextra -Wno-unused-parameter -I. -I/home/ahn573/Research/libpfm-4.8.0/perf_examples/../include \
-I /usr/lib/jvm/java-6-openjdk-i386/include/ -shared -o libhello.so\
 -DCONFIG_PFMLIB_DEBUG -DCONFIG_PFMLIB_OS_LINUX -I. -D_GNU_SOURCE -pthread Invoked.o perf_util.o \
 /home/ahn573/Research/libpfm-4.8.0/perf_examples/../lib/libpfm.a
