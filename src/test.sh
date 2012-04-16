#!/bin/bash

PTC="positive_test_cases"
for f in $( ls $PTC/Testcases); 
do
#	status = $?

	if [ $? -eq 0 ] ; then


# Run the test case with runstl.sh and store it in a temporary file
		./runstl.sh $PTC/Testcases/$f > $PTC/RunResults/stdout

# calculate the differences with previous test output for the same file
		diff $PTC/RunResults/$f $PTC/RunResults/stdout > $PTC/Differences/$f

		mv $PTC/RunResults/stdout $PTC/RunResults/$f
	else
		echo "$f - a positive test case failed"
		break
	fi
done 
