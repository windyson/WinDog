import os
from datetime import datetime, timedelta
import glob


def getDateTimeList():
    # 上午开盘时间
    start_time = datetime(2023, 1, 1, 9, 30)  # 设置开始时间
    end_time = datetime(2023, 1, 1, 11, 30)  # 设置结束时间
    minute_list = []
    while start_time <= end_time:
        minute_list.append(start_time.strftime("%H%M"))
        start_time += timedelta(minutes=1)
    # 下午开盘时间
    start_time = datetime(2023, 1, 1, 13, 0)  # 设置开始时间
    end_time = datetime(2023, 1, 1, 15, 0)  # 设置结束时间
    while start_time <= end_time:
        minute_list.append(start_time.strftime("%H%M"))
        start_time += timedelta(minutes=1)

    return minute_list


def dataFileCheck():
    log_dir = os.getcwd() + '/../data/'
    log_files = glob.glob(os.path.join(log_dir, '*.dat'))

    for log_file in log_files:
        print('正在校验文件[' + log_file + ']...')
        f = open(log_file, "r", encoding="UTF-8")
        minuteLst = getDateTimeList();
        while True:
            s = f.readline()
            if (0 == len(s)):
                break
            else:
                minuteLst.remove(s[0:4])
        if (len(minuteLst) > 0):
            print('校验数据文件[' + log_file + ']发现缺失时段：' + minuteLst.__str__())
        else:
            print('校验数据文件[' + log_file + ']完整有效.')
        f.close()


if __name__ == '__main__':
    dataFileCheck()
