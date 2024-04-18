def solution(cookie):
    answer = 0
    lc = len(cookie)
    s = sum(cookie)
    ms = 0
    for m in range(lc-1):
        ms += cookie[m]
        tls = ms
        trs = s - ms
        l = 0
        r = lc-1
        while l <= m < r:
            if tls == trs:
                answer = max(answer,tls)
                break
            elif tls > trs:
                tls -= cookie[l]
                l += 1
            else: 
                trs -= cookie[r]
                r -= 1
        
    return answer