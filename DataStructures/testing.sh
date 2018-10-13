#!/usr/bin/env bash

./bin/build.sh

./bin/test_data_structures cgl 1 50000 0 >> results.txt
./bin/test_data_structures cgl 2 50000 0 >> results.txt
./bin/test_data_structures cgl 3 50001 0 >> results.txt
./bin/test_data_structures cgl 4 50000 0 >> results.txt
./bin/test_data_structures cgl 5 50000 0 >> results.txt
./bin/test_data_structures cgl 6 49998 0 >> results.txt
./bin/test_data_structures cgl 7 50001 0 >> results.txt
./bin/test_data_structures cgl 8 50000 0 >> results.txt

./bin/test_data_structures cgl 1 50000 2 >> results.txt
./bin/test_data_structures cgl 2 50000 2 >> results.txt
./bin/test_data_structures cgl 3 50001 2 >> results.txt
./bin/test_data_structures cgl 4 50000 2 >> results.txt
./bin/test_data_structures cgl 5 50000 2 >> results.txt
./bin/test_data_structures cgl 6 49998 2 >> results.txt
./bin/test_data_structures cgl 7 50001 2 >> results.txt
./bin/test_data_structures cgl 8 50000 2 >> results.txt

./bin/test_data_structures cgl 1 50000 4 >> results.txt
./bin/test_data_structures cgl 2 50000 4 >> results.txt
./bin/test_data_structures cgl 3 50001 4 >> results.txt
./bin/test_data_structures cgl 4 50000 4 >> results.txt
./bin/test_data_structures cgl 5 50000 4 >> results.txt
./bin/test_data_structures cgl 6 49998 4 >> results.txt
./bin/test_data_structures cgl 7 50001 4 >> results.txt
./bin/test_data_structures cgl 8 50000 4 >> results.txt

./bin/test_data_structures cgl 1 50000 8 >> results.txt
./bin/test_data_structures cgl 2 50000 8 >> results.txt
./bin/test_data_structures cgl 3 50001 8 >> results.txt
./bin/test_data_structures cgl 4 50000 8 >> results.txt
./bin/test_data_structures cgl 5 50000 8 >> results.txt
./bin/test_data_structures cgl 6 49998 8 >> results.txt
./bin/test_data_structures cgl 7 50001 8 >> results.txt
./bin/test_data_structures cgl 8 50000 8 >> results.txt