def solution(friends, gifts):
    answer = 0
    l=len(friends)
    arr=[[0 for _ in range(l)] for _ in range(l)]
    fr={}

    for i in range(l):
        fr[friends[i]] = i

    for g in gifts:
        gs = g.split()
        give_g = gs[0]
        get_g = gs[1]
        arr[fr[give_g]][fr[get_g]] += 1
        arr[fr[get_g]][fr[give_g]] -= 1
        
    for i in range(l):
        s = sum(arr[i])
        temp = 0
        for j in range(l):
            if arr[i][j] > 0:
                temp += 1
            elif arr[i][j] == 0:
                sj = sum(arr[j])
                if s >sj:
                    temp+=1
        answer = max(answer,temp)
    return answer