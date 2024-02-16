def solution(s):
    answer = [0,0]
    while not s == '1':
        ts = len(s.replace('0',''))
        answer[1] += len(s) - ts
        s = ''
        while ts > 0:
            nam = ts % 2
            ts = ts // 2
            s += str(nam)
        answer[0] += 1
    return answer