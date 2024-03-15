def solution(priorities, location):
    answer = 0
    temp = []
    l = len(priorities)
    for i in range(l):
        if priorities[i] >= priorities[location]:
            temp.append([priorities[i],i])
    pri = priorities[location]
    temp.sort(key=lambda x:(-x[0],x[1]))
    n = len(temp)
    while answer < n:
        move = temp[answer][1]
        for ind in range(answer,n):
            temp[ind][1] = (temp[ind][1] - move + l) % l
        temp.sort(key=lambda x:(-x[0],x[1]))
        location = (location - move + l) % l
        [p,i] = temp[answer]
        if p == pri and location == i:
            answer += 1
            break
        answer += 1
        

    return answer