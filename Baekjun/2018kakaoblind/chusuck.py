def solution(lines):
    answer = 0
    arr = []
    for l in lines:
        trash, time, stime = l.split(' ')
        h,m,s = time.split(':')
        s,ms = s.split('.')
        t = (int(h) * 3600 + int(m) * 60 + int(s))*1000 + int(ms)
        stime = stime[:-1] +('.000' if len(stime) == 2 else '0' * (6-len(stime)))
        st,stms = list(map(int,stime.split('.')))
        arr.append([t-st*1000-stms+1,t])
    arr.sort(key=lambda x : x[1])
    
    l = len(arr)
    for i in range(l):
        p = arr[i][1]
        count = 0
        for j in range(i,l):
            left = arr[j][0]
            right = arr[j][1]
            if left < p+1000 <= right or left <= p <= right or p <= left < p+ 1000 or p <=right<=p+1000:
                count += 1

        answer = max(answer,count)
        if i + count == l:
            break
        count -= 1
    return answer