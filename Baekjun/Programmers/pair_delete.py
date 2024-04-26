def solution(s):
    answer = 0
    l = len(s)
    prev = []
    for i in range(0,l):
        if len(prev) == 0 or not prev[-1] == s[i]:
            prev.append(s[i])
        else:
            prev.pop()
                
    if len(prev) == 0:
        answer = 1
    return answer