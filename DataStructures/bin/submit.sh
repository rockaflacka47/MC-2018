#!/bin/bash

if [ -d src ]
then
    rm -f submit.tar.gz
    rm -f submit.tar
    find src -type f -name "*.java" | xargs tar rvf submit.tar && \
    gzip submit.tar
else
    echo "Error: no src directory found!" 1>&2
fi
