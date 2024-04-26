def solution(citations):
    answer = 0
    citations.sort()
    l = len(citations)
    for i in range(1,l+1):
        c = citations[-i]
        if i >= c:
            return max(i-1,c)
    return l