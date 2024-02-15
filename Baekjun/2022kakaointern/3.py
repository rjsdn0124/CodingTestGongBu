def solution(alp, cop, problems):
    answer = 0
    ma=0
    mc=0
    for p in problems:
        ma = max(ma,p[0])
        mc = max(mc,p[1])
    arr = [[300 for _ in range(mc+1)] for _ in range(ma+1)]
    problems.append([0,0,1,0,1])
    problems.append([0,0,0,1,1])
    alp = alp if alp < ma else ma
    cop = cop if cop < mc else mc
    arr[alp][cop] = 0

    for i in range(alp,ma+1):
        for j in range(cop, mc+1):
            for p in problems:
                if i >= p[0] and j >= p[1]:
                    temp = arr[i][j]
                    temp += p[4]
                    ti = i+p[2]
                    tj = j+p[3]
                    if ti > ma:
                        ti = ma
                    if tj > mc:
                        tj = mc
                    arr[ti][tj]=min(temp,arr[ti][tj])
    answer = arr[ma][mc]
    return answer