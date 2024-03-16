def solution(brown, yellow):
    s = brown + yellow
    for i in range(3,s//2):
        if s % i == 0:
            garo = s // i
            if brown == (garo*2) + (i-2)*2 and yellow == (garo - 2) * (i - 2):
                return [garo,i]