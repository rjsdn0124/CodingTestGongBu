def solution(s):
    answer = True
    n = 0
    for c in s:
        if c == '(':
            n+=1
        else:
            n-=1
        if n < 0:
            answer = False
            break
    if n > 0:
        answer = False
    return answer