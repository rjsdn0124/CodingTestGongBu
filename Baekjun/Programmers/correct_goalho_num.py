from collections import deque
def solution(n):
    answer = 0
    q = deque()
    q.append([1,0])
    while q:
        can, left = q.popleft()
        if left == n and can == 0:
            answer += 1
            continue
        if can > 0:
            q.append([can-1,left+1])
        if n > can+left:
            q.append([can+1,left])
        
    return answer