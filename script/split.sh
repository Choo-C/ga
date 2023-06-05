#!/bin/bash
#nohup.out日志分割
this_path=$(cd `dirname $0`;pwd)
current_date=`date -d "-1 day" "+%Y%m%d"`
cd $this_path
echo $this_path
echo $current_date
do_split(){
	[ ! -d logs ] && mkdir -p logs
	split -b 10m -d -a 4 ./nohup.out ./logs/nohup-${current_date}
	if [ $? -eq 0 ];
	then
		echo "split is finished!"
	else
		echo "split is failed!"
		exit 1 #退出shell,失败
	fi
}
do_del_log(){

	cat /dev/null > nohup.out
}
if do_split;
then
	do_del_log
	echo "nohup is split success!"
else
	echo "nohup is split failure!"
	exit 2
fi

# 设置定时：
# crontab -e
# 0 0 * * * /test/split.sh &>/dev/null
