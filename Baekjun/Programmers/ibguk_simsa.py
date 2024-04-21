def solution(n, times):
    answer = 0
    mt = min(times)
    l = 0
    r = mt * n
    while l < r:
        mid = (l + r) // 2
        s = 0
        for t in times:
            s += mid//t
        if s >= n:
            r = mid
        elif s < n:
            l = mid + 1
    answer = l
    return answer