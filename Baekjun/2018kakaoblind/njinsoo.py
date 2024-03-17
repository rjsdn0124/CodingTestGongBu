def solution(n, t, m, p):
    answer = ''
    i = 0
    num = 0
    
    while t > 0:
        now = ''
        tn = num
        while tn > 0:
            temp = tn % n
            tn //= n
            now = hex(temp)[2:] + now
        if not now:
            now = '0'
        for c in now:
            i = i % m + 1
            if p == i:
                t -= 1
                answer += c
                if t == 0:
                    break
        num += 1  
    return answer.upper()