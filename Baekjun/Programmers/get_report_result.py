def solution(id_list, report, k):
    l = len(id_list)
    answer = [0 for _ in range(l)]
    report_carr = [[0 for _ in range(l)] for _ in range(l)]
    id_dic = {}
    
    for i in range(l):
        id_dic[id_list[i]] = i
        
    for r in report:
        x,y = r.split(' ')
        xind = id_dic[x]
        yind = id_dic[y]
        report_carr[yind][xind] = 1 if report_carr[yind][xind] == 0 else 1
        
    for i in range(l):
        if sum(report_carr[i]) >= k:
            for j in range(l):
                if report_carr[i][j] == 1:
                    answer[j] += 1
    return answer