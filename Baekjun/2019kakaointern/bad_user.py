from collections import deque
def solution(user_id, banned_id):
    answer = 0
    blpa = [[] for _ in range(len(banned_id))]
    for uii,ui in enumerate(user_id):
        for i,bi in enumerate(banned_id):
            if len(bi) == len(ui):
                flag = True
                for j in range(len(bi)):
                    if not bi[j] == '*':
                        if not bi[j] == ui[j]:
                            flag = False
                            break
                if flag:
                    blpa[i].append(uii)
                    
    q = deque()
    for blp in blpa[0]:
        q.append([1,[blp]])
    resarr=[]
    while q:
        i,arr = q.popleft()
        if i == len(blpa):
            flag = True
            for res in resarr:
                tflag = False
                for c in arr:
                    if not c in res:
                        tflag = True
                        break
                if not tflag:
                    flag = False
                    break
                
            if flag:
                answer += 1
                resarr.append(arr)
        elif i < len(blpa):
            for blp in blpa[i]:
                if not blp in arr:
                    q.append([i+1, arr+[blp]])
    return answer