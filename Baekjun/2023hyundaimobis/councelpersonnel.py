
def cal_wait_time(arr, cnum):
    wait_time = 0
    counc = [0 for _ in range(cnum)]
    for i in range(len(arr)):
        minidx = 0
        for j in range(cnum):
            minidx = j if counc[j] < counc[minidx] else minidx
        if counc[minidx] > arr[i][0]:
            wait_time += counc[minidx] - arr[i][0]
            counc[minidx] = counc[minidx]  + arr[i][1]
        else:
            counc[minidx] = arr[i][0]  + arr[i][1]
    return wait_time

def solution(k, n, reqs):
    answer = 0
    arr = [[] for _ in range(k)]
    carr = [1 for _ in range(k)]
    n-=k
    
    for req in reqs:
        arr[req[2]-1].append(req)
    
    wait_time_arr=[0 for _ in range(k)]
    
    for i in range(k):
        wait_time_arr[i] = cal_wait_time(arr[i],carr[i])
        
    while n > 0:
        maxidx = 0
        maxdiff = 0
        for i in range(k):
            v = cal_wait_time(arr[i],carr[i]+1)
            if maxdiff < wait_time_arr[i] - v:
                maxidx = i
                maxdiff = wait_time_arr[i] -v
        carr[maxidx] += 1
        wait_time_arr[maxidx] -= maxdiff
        n-=1
        
    answer = sum(wait_time_arr)
    return answer