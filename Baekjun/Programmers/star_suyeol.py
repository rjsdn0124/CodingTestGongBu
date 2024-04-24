def solution(a):
    l = len(a)
    arr = [[0,0] for _ in range(l)]
    
    for i in range(l):
        n = a[i]
        count = arr[n][0]
        next_min = arr[n][1]
        if next_min < i:
            next_min = i+1
            count += 1
        elif next_min == i:
            next_min = i+2
            if i < l-1:
                count += 1
        else:
            if i == l-1:
                count -= 1
            next_min = i+2
        arr[n][0] = count
        arr[n][1] = next_min
        
    answer = max(arr[i][0] for i in range(l))
    return answer*2