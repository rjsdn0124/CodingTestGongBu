tester_num, testee_num = map(int, input().split())
times = [int(input()) for _ in range(tester_num)]

max_time = times[0] * testee_num
min_time = 0
result = max_time

while min_time < max_time:
    max_num = 0
    result = (max_time + min_time) // 2
    for t in times:
        max_num+=result//t
    if max_num >= testee_num: 
        max_time = result
    elif max_num < testee_num : 
        min_time = result+1

print(max_time)