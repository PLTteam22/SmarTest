PTC="positive_test_cases"
NTC="negative_test_cases"
exitStatus=0

for f in $( ls $PTC/Testcases);
do

# Run the test case with runstl.sh and store it in a temporary file
        ./runstl.sh $PTC/Testcases/$f > $PTC/RunResults/error
        exitStatus=$?

	if [ $exitStatus = 0 ] 
then
#		echo "$exitStatus is what is passed"
# calculate the differences with previous test output for the same file
		diff $PTC/RunResults/$f $PTC/RunResults/error > $PTC/Differences/$f
		mv $PTC/RunResults/error $PTC/RunResults/$f
	else
		echo "$f - a positive test case failed"
		break
	fi
done 


#for f in $( ls $NTC/Testcases);
#do

# Run the test case with runstl.sh and store it in a temporary file
#        ./runstl.sh $NTC/Testcases/$f > $NTC/RunResults/error
#        exitStatus=$?

#        if [ $exitStatus != 0 ] 
#then
#               echo "$exitStatus is what is passed"
# calculate the differences with previous test output for the same file
#               diff $NTC/RunResults/$f $NTC/RunResults/error > $NTC/Differences/$f
#                mv $NTC/RunResults/error $NTC/RunResults/$f
#        else
#                echo "$f - a negative test case succeeded"
#                break
#        fi
#done
