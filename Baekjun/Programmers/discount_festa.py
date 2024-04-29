def solution(want, number, discount):
    answer = 0
    dic = {}
    l = len(want)
    for i in range(l):
        dic[want[i]] = number[i]
    
    for d in discount[:10]:
        if d in dic:
            dic[d] -= 1
    flag = True
    for v in dic:
        if not dic[v] == 0:
            flag = False
            break
    if flag:
        answer += 1
    for i in range(10,len(discount)):
        d = discount[i]
        prev = discount[i-10]
        flag = True
        if prev in dic:
            dic[prev] += 1
        if d in dic:
            dic[d] -= 1
        for v in dic:
            if not dic[v] == 0:
                flag = False
                break
        if flag:
            answer += 1
            
    return answer