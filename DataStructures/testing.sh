#!/usr/bin/env bash

./bin/build.sh

./bin/test_data_structures fgt 1 50000 0 >> results1.txt
./bin/test_data_structures fgt 2 50000 0 >> results2.txt
./bin/test_data_structures fgt 3 50001 0 >> results3.txt
./bin/test_data_structures fgt 4 50000 0 >> results4.txt
./bin/test_data_structures fgt 5 50000 0 >> results5.txt
./bin/test_data_structures fgt 6 49998 0 >> results6.txt
./bin/test_data_structures fgt 7 50001 0 >> results7.txt
./bin/test_data_structures fgt 8 50000 0 >> results8.txt

./bin/test_data_structures fgt 1 50000 2 >> results9.txt
./bin/test_data_structures fgt 2 50000 2 >> results10.txt
./bin/test_data_structures fgt 3 50001 2 >> results11.txt
./bin/test_data_structures fgt 4 50000 2 >> results12.txt
./bin/test_data_structures fgt 5 50000 2 >> results13.txt
./bin/test_data_structures fgt 6 49998 2 >> results14.txt
./bin/test_data_structures fgt 7 50001 2 >> results15.txt
./bin/test_data_structures fgt 8 50000 2 >> results16.txt

./bin/test_data_structures fgt 1 50000 4 >> results17.txt
./bin/test_data_structures fgt 2 50000 4 >> results18.txt
./bin/test_data_structures fgt 3 50001 4 >> results19.txt
./bin/test_data_structures fgt 4 50000 4 >> results20.txt
./bin/test_data_structures fgt 5 50000 4 >> results21.txt
./bin/test_data_structures fgt 6 49998 4 >> results22.txt
./bin/test_data_structures fgt 7 50001 4 >> results23.txt
./bin/test_data_structures fgt 8 50000 4 >> results24.txt

./bin/test_data_structures fgt 1 50000 8 >> results25.txt
./bin/test_data_structures fgt 2 50000 8 >> results26.txt
./bin/test_data_structures fgt 3 50001 8 >> results27.txt
./bin/test_data_structures fgt 4 50000 8 >> results28.txt
./bin/test_data_structures fgt 5 50000 8 >> results29.txt
./bin/test_data_structures fgt 6 49998 8 >> results30.txt
./bin/test_data_structures fgt 7 50001 8 >> results31.txt
./bin/test_data_structures fgt 8 50000 8 >> results32.txt
